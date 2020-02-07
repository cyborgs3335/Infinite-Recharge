/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.triggerIntake;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  TalonSRX beltDrive = new TalonSRX(RobotMap.MOTOR_DRIVE_INTAKE_BELT);
  TalonSRX armDrive = new TalonSRX(RobotMap.MOTOR_DRIVE_INTAKE_ARM);
  //rotate motors so balls are taken into the robot
  public void takeIn()
  {
      
  }

  //rotate motors so balls are pushed out of the intake, meant for jams.
  public void reject()
  {

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new triggerIntake());
  }
}
