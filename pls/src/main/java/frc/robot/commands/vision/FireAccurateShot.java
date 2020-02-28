/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotPreferences;

public class FireAccurateShot extends Command {

  boolean isFinished = false;
  double distFromBase;
  
  public FireAccurateShot() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.limelight);
    requires(Robot.driveTrain);
    requires(Robot.shooter);
    SmartDashboard.putBoolean("Ready to Fire", isFinished);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    distFromBase = Robot.limelight.getDistance();//might have to fix getDistance()
    if(distFromBase < RobotPreferences.kDistFromBaseMax && distFromBase > RobotPreferences.kDistFromBaseMin)
    {
      if(Robot.limelight.getTx() < -.5)
      {
        Robot.driveTrain.driveMotors(.2, -.2);
      }
      else if(Robot.limelight.getTx() > .5)
      {
        Robot.driveTrain.driveMotors(-.2, .2);
      }
      else
      {
        Robot.driveTrain.driveMotors(0, 0);
        Robot.shooter.revMotor(distFromBase * 3568);//rev motor of dist * by some constant to properly place the ball TODO
        isFinished = true;
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
