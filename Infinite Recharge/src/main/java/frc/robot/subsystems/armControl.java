/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.RobotMap;
import frc.robot.RobotPreferences;
import frc.util.LatchedBoolean;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;


public class armControl extends Subsystem {
  
  public enum armPosition
  {
    fullRetract,
    fullExtend,
    lowerExtend;

    private static armPosition[] vals = values();
    public armPosition next()
    {
      return vals[(this.ordinal()+1) % vals.length];
    }
  }

  
  public double currentHeight;

  public TalonSRX armMotorWinch;
  public TalonSRX armMotorRollerL;
  public TalonSRX armMotorRollerR;
  
  public SensorCollection[] sensors = new SensorCollection[3];  
  private armPosition armHeight;
  private double height;
  private String datHeight = "ground";
  private boolean isSafe;
  private boolean seeEncoder;

  private LatchedBoolean tachCrossed = new LatchedBoolean();

  /**
   * Creates a new armControl.
   */
  public armControl() 
  {
    armMotorWinch = new TalonSRX(RobotMap.ARM_MOTOR_Winch);
    armMotorWinch.configFactoryDefault(100);
    armMotorRollerR = new TalonSRX(RobotMap.ARM_MOTOR_RR);
    armMotorRollerR.configFactoryDefault(100);
    armMotorRollerL = new TalonSRX(RobotMap.ARM_MOTOR_RL);
    armMotorRollerL.configFactoryDefault(100);

    sensors[0] = armMotorWinch.getSensorCollection();
    sensors[1] = armMotorRollerR.getSensorCollection();
    sensors[2] = armMotorRollerL.getSensorCollection(); 

    seeEncoder = true;
    ErrorCode encoderPresentW = armMotorWinch.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute,0,10);
    ErrorCode encoderPresentRL =armMotorRollerL.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute,0,10);
    ErrorCode encoderPresentRR = armMotorRollerR.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute,0,10);
    //TODO: tell which encoder isn't working
    if(encoderPresentW != ErrorCode.OK || encoderPresentRL != ErrorCode.OK || encoderPresentRR != ErrorCode.OK) 
    {
      DriverStation.reportError("Jesus im dying where is my encoder", false);
      seeEncoder = false;
    }
    currentHeight = 0; //TODO

    //set all motors in the arm to the same neutral mode, and disable all limits *might need to modifiy this later
    armMotorWinch.setNeutralMode(NeutralMode.Brake);
    armMotorWinch.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
    armMotorWinch.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
    armMotorWinch.configForwardSoftLimitEnable(false);
    armMotorWinch.configReverseSoftLimitEnable(false);
    armMotorRollerR.setNeutralMode(NeutralMode.Brake);
    armMotorRollerR.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
    armMotorRollerR.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
    armMotorRollerR.configForwardSoftLimitEnable(false);
    armMotorRollerR.configReverseSoftLimitEnable(false);
    armMotorRollerL.setNeutralMode(NeutralMode.Brake);
    armMotorRollerL.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
    armMotorRollerL.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
    armMotorRollerL.configForwardSoftLimitEnable(false);
    armMotorRollerL.configReverseSoftLimitEnable(false);

    isSafe = true;
    zeroEncoder();
    armHeight = armPosition.fullRetract;

    //pid
    armMotorWinch.config_kP(0,RobotPreferences.armW_kP);
    armMotorWinch.config_kI(0,RobotPreferences.armW_kI);
    armMotorWinch.config_kD(0,RobotPreferences.armW_kD);
    armMotorRollerR.config_kP(0,RobotPreferences.armRR_kP);
    armMotorRollerR.config_kI(0,RobotPreferences.armRR_kI);
    armMotorRollerR.config_kD(0,RobotPreferences.armRR_kD);
    armMotorRollerL.config_kP(0,RobotPreferences.armRL_kP);
    armMotorRollerL.config_kI(0,RobotPreferences.armRL_kI);
    armMotorRollerL.config_kD(0,RobotPreferences.armRL_kD);
  }

  /**
   * @return the isSafe
   */
  public boolean isSafe() {
    return isSafe;
  }

  public armPosition getArmHeight()
  {
    return armHeight;
  }

  public void setArmPosition(armPosition aP)
  {
    armHeight = aP;
  }
  /**
   * @param armHeight the armHeight to set
   */
  public void setArmHeight(double height) {
    if(isSafe())
    {
      //TODO: make talon move the arm(reference from DeepSpace below)
        //int targetSensorPosition = (int) Math.round(RobotPreferences.kElevatorScalar*(height - 19) / RobotPreferences.kDistancePerRevolution * 4096 * RobotPreferences.kRatioToOutput /.2);
        //elevatorMotor1.set(ControlMode.Position, targetSensorPosition);
      currentHeight = height;
   }
  }

  public double getTranslateHight()
  {
    //TODO: Make this accurate
    return (getEncoder() / RobotPreferences.kWinchDegreesPerInch * .2) + 19;
  }

  //TODO: Find out what this means and how to use it
  public void zeroEncoder()
  {
    armMotorWinch.setSelectedSensorPosition(0,0,10);
  }

  public int getEncoder()
  {
    return armMotorWinch.getSelectedSensorPosition(1);
  }

  public void moveArm(double speed)
  {
    armMotorWinch.set(ControlMode.PercentOutput, speed);
  }

  public void shimmy(double speed)
  {
    armMotorRollerR.set(ControlMode.PercentOutput, speed);
    armMotorRollerL.set(ControlMode.PercentOutput, speed);
  }

  public void endArm()
  {
    armMotorWinch.set(ControlMode.PercentOutput, 0);
    armMotorRollerR.set(ControlMode.PercentOutput, 0);
    armMotorRollerL.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  protected void initDefaultCommand() {
    // TODO: might not work
    setDefaultCommand(null);
  }
}
