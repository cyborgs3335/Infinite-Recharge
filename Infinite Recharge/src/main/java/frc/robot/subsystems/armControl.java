/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.RobotMap;
import frc.util.LatchedBoolean;

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
  
  public SensorCollection sensors;  
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
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  protected void initDefaultCommand() {
    // TODO Auto-generated method stub

  }
}
