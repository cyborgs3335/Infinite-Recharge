/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.util.mapAxis;

/**
 * An example command.  You can replace me with your own command.
 */
public class triggerIntake extends CommandBase {
  public triggerIntake() {
    // Use requires() here to declare subsystem dependencies
    addRequirements(Robot.intake);
  }

  // cool!
  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    Robot.intake.driveBeltMotor(mapAxis.map(Robot.oi.getJoystick().getRawAxis(2)), false);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    //return true;
    return false;
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    Robot.intake.driveBeltMotor(0, false);
  }
}
