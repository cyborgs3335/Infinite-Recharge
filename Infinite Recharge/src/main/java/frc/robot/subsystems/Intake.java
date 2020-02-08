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
    armExtend = new Solenoid(RobotMap.INTAKE_SOLENOID_ARM);
    armDrive = new TalonSRX(RobotMap.INTAKE_MOTOR_ARM);
    armDrive.configFactoryDefault(100);
    beltDrive = new TalonSRX(RobotMap.INTAKE_MOTOR_BELT);
    beltDrive.configFactoryDefault(100);

    //armDrive pid
    armDrive.config_kP(0, RobotPreferences.kIntakeA_P);
    armDrive.config_kI(0, RobotPreferences.kIntakeA_I);
    armDrive.config_kD(0, RobotPreferences.kIntakeA_D);

    //beltDrive pid
    beltDrive.config_kP(0, RobotPreferences.kIntakeB_P);
    beltDrive.config_kI(0, RobotPreferences.kIntakeB_I);
    beltDrive.config_kD(0, RobotPreferences.kIntakeB_D);
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
