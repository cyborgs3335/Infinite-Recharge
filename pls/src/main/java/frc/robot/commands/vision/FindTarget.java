/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.vision.Limelight.Target;

public class FindTarget extends CommandBase {

  boolean isFinished = false;
  
  public FindTarget() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    addRequirements(Robot.limelight);
    addRequirements(Robot.driveTrain);
  }

  public FindTarget(Target t) {
    this();
    Robot.limelight.setPipeline(t);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    isFinished = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    Target t = Robot.limelight.getTargetSelected();
    double yaw = Robot.driveTrain.getYaw()%360; //the %360 removes excess if degrees are > 360, restricts yaw to -360 to 360
    boolean hasTarget = Robot.limelight.hasTarget();
    if (t.equals(Target.PORT)) {
      if (((-90 <= yaw && yaw <= 90 )|| yaw < -270 || yaw > 270) && hasTarget) {
        isFinished = true;
      } else if ((90 <= yaw && yaw <= 180)) {
        Robot.driveTrain.driveMotors(.2, -.2);
      } else { //(-90 to -180)
        Robot.driveTrain.driveMotors(-.2, .2);
      }
    } else if (t.equals(Target.BAY)) {
      if (((90 > yaw && yaw <= 270) || (-270 <= yaw && yaw < -90)) && hasTarget) {
        isFinished = true;
      } else if ((0 <= yaw && yaw <= 90) || (-270 <= yaw && yaw <= -360)) {
        Robot.driveTrain.driveMotors(-.2, .2);
      } else {
        Robot.driveTrain.driveMotors(.2, -.2);
      }
    } else {
      if (!hasTarget) {
        Robot.driveTrain.driveMotors(.2, -.2);
      } else {
        isFinished = true;
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
    Robot.driveTrain.driveMotors(0, 0);
  }

}
