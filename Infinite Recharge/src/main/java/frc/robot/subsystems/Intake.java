/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.RobotPreferences;
import frc.robot.commands.triggerIntake;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  TalonSRX beltDrive;
  TalonSRX armDrive;
  Solenoid armExtend;
  public Intake()
  {
    armExtend = new Solenoid(RobotMap.SOLENOID_ARM_DRIVE_INTAKE);
    armDrive = new TalonSRX(RobotMap.MOTOR_DRIVE_INTAKE_ARM);
    beltDrive = new TalonSRX(RobotMap.MOTOR_DRIVE_INTAKE_BELT);

    //armDrive pid
    armDrive.config_kP(0, RobotPreferences.armID_kP);
    armDrive.config_kI(0, RobotPreferences.armID_kI);
    armDrive.config_kD(0, RobotPreferences.armID_kD);

    //beltDrive pid
    beltDrive.config_kP(0, RobotPreferences.armID_kP);
    beltDrive.config_kI(0, RobotPreferences.armID_kI);
    beltDrive.config_kD(0, RobotPreferences.armID_kD);
  }

  public void driveBeltMotor(double speed, boolean isInverted)
  {
    beltDrive.set(ControlMode.PercentOutput, speed);
    beltDrive.setInverted(isInverted);
  }

  public void driveArmMotor(double speed, boolean isInverted)
  {
    armDrive.set(ControlMode.PercentOutput, speed);
    armDrive.setInverted(isInverted);
  }

  public void extendArm(boolean s)
  {
    armExtend.set(s);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new triggerIntake());
  }
}
