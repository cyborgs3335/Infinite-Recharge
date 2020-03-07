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
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.RobotMap;
import frc.robot.RobotPreferences;
import frc.robot.commands.DefaultHeight;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;


public class armControl extends Subsystem {
  
  public enum armPosition
  {
    fullRetract,
    fullExtend,
    defaultStart,
    lowerExtend,
    controlboard;

    private static armPosition[] vals = values();
    public armPosition next()
    {
      return vals[(this.ordinal()+1) % vals.length];
    }
  }

  
  public double currentHeight;

  public TalonFX armMotorWinchL/*,armMotorWinchR*/;
  public VictorSPX armMotorRollerL,armMotorRollerR;
  public DoubleSolenoid /*armSolenoidR,*/armSolenoidL;
  public Solenoid brake;
  
  private armPosition armHeight;
  private boolean isSafe;

  public WaitCommand w = new WaitCommand(.5);
  
  /**
   * Creates a new armControl.
   */
  public armControl() {
    armMotorWinchL = new TalonFX(RobotMap.ARM_MOTOR_WINCHL);
    armMotorWinchL.configFactoryDefault(100);
    // armMotorWinchR = new TalonSRX(RobotMap.ARM_MOTOR_WINCHR);
    // armMotorWinchR.configFactoryDefault(100);
    armMotorRollerR = new VictorSPX(RobotMap.ARM_MOTOR_RR);
    armMotorRollerR.configFactoryDefault(100);
    armMotorRollerL = new VictorSPX(RobotMap.ARM_MOTOR_RL);
    armMotorRollerL.configFactoryDefault(100);
    //armSolenoidR = new DoubleSolenoid(RobotMap.ARM_PCM_SOLENOIDR, RobotMap.ARM_SOLENOID_ARMRF, RobotMap.ARM_SOLENOID_ARMRR);
    armSolenoidL = new DoubleSolenoid(RobotMap.ARM_PCM_SOLENOIDL, RobotMap.ARM_SOLENOID_ARMLF, RobotMap.ARM_SOLENOID_ARMLR);
    brake = new Solenoid(RobotMap.ARM_PCM_SOLENOIDB, RobotMap.ARM_SOLENOID_BRAKE);

    brake.set(true);
    armSolenoidL.set(Value.kForward);

    ErrorCode encoderPresentW = armMotorWinchL.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
    ErrorCode encoderPresentRL = armMotorRollerL.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
    ErrorCode encoderPresentRR = armMotorRollerR.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);

    if (encoderPresentW != ErrorCode.OK) {
      DriverStation.reportError("EncoderWinch is dead", false);
    } else if (encoderPresentRL != ErrorCode.OK) {
      DriverStation.reportError("EncoderRollerLeft is dead", false);
    } else if (encoderPresentRR != ErrorCode.OK) {
      DriverStation.reportError("EncoderRollerRight is dead", false);
    }
    currentHeight = RobotPreferences.kDefaultStart;

    //set all motors in the arm to the same neutral mode, and disable all limits *might need to modifiy this later
    armMotorWinchL.setNeutralMode(NeutralMode.Brake);
    armMotorWinchL.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
    armMotorWinchL.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
    armMotorWinchL.configForwardSoftLimitEnable(false);
    armMotorWinchL.configReverseSoftLimitEnable(false);
    // armMotorWinchR.setNeutralMode(NeutralMode.Brake);
    // armMotorWinchR.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
    // armMotorWinchR.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
    // armMotorWinchR.configForwardSoftLimitEnable(false);
    // armMotorWinchR.configReverseSoftLimitEnable(false);
    // arMotorWinchR.set(ControlMode.Position, 0);
    armMotorRollerR.setNeutralMode(NeutralMode.Brake);
    //armMotorRollerR.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
    armMotorRollerR.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
    armMotorRollerR.configForwardSoftLimitEnable(false);
    armMotorRollerR.configReverseSoftLimitEnable(false);
    armMotorRollerL.setNeutralMode(NeutralMode.Brake);
    //armMotorRollerL.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
    armMotorRollerL.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
    armMotorRollerL.configForwardSoftLimitEnable(false);
    armMotorRollerL.configReverseSoftLimitEnable(false);

    isSafe = true;
    zeroEncoder();
    armHeight = armPosition.defaultStart;

    //pid for all motors (rollers L&R should be the same)
    armMotorWinchL.config_kP(0,RobotPreferences.kArmW_P);
    armMotorWinchL.config_kI(0,RobotPreferences.kArmW_I);
    armMotorWinchL.config_kD(0,RobotPreferences.kArmW_D);
    // armMotorWinchR.config_kP(0,RobotPreferences.kArmW_P);
    // armMotorWinchR.config_kI(0,RobotPreferences.kArmW_I);
    // armMotorWinchR.config_kD(0,RobotPreferences.kArmW_D);
    // armMotorRollerR.config_kP(0,RobotPreferences.kArmRR_P);
    // armMotorRollerR.config_kI(0,RobotPreferences.kArmRR_I);
    // armMotorRollerR.config_kD(0,RobotPreferences.kArmRR_D);
    // armMotorRollerL.config_kP(0,RobotPreferences.kArmRL_P);
    // armMotorRollerL.config_kI(0,RobotPreferences.kArmRL_I);
    // armMotorRollerL.config_kD(0,RobotPreferences.kArmRL_D);
  }

  public void brake(boolean t)
  {
    if(t)
    {
      if(getArmHeight().equals(armPosition.fullExtend))
        moveArm(.1);
      else if(getArmHeight().equals(armPosition.fullRetract))
        moveArm(.1);
      else
        moveArm(.1);
    }
    brake.set(!t);
  }

  public void setSolenoid(Value v)
  {
    armSolenoidL.set(v);
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
      //should take the height(the inches we want) and multiply it by how many ticks it takes to get an inch(kWinchScalar)
      //TODO:make sure it works
      int targetSensorPosition = (int) Math.round(RobotPreferences.kWinchScalar * height);
      armMotorWinchL.set(ControlMode.Position, targetSensorPosition);
      // armMotorWinchR.set(ControlMode.Position, targetSensorPosition);
      currentHeight = height;
   }
  }

  public double getTranslateHight()
  {
    //TODO: make sure it works
    return  getEncoder()/RobotPreferences.kWinchScalar;
  }

  //TODO: Find out what this means and how to use it
  public void zeroEncoder()
  {
    armMotorWinchL.setSelectedSensorPosition(0,0,10);
    // armMotorWinchR.setSelectedSensorPosition(0,0,10);
  }

  public int getEncoder()
  {
    return armMotorWinchL.getSelectedSensorPosition(1);
  }

  public void moveArm(double speed)
  {
    armMotorWinchL.set(ControlMode.PercentOutput, speed);
    // armMotorWinchR.set(ControlMode.PercentOutput, speed);
  }

  public void shimmy(double speed)
  {
    armMotorRollerR.set(ControlMode.PercentOutput, speed);
    armMotorRollerL.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


  @Override
  protected void initDefaultCommand() {
    // TODO: might not work
    //setDefaultCommand(new DefaultHeight());
  }
}
