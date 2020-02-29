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
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    if(!Robot.limelight.hasTarget())
    {
      Robot.driveTrain.driveMotorsL(.2, .2);
      Robot.driveTrain.driveMotorsR(-.2, -.2);
    }
    else
    {
      isFinished  = true;
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
    Robot.driveTrain.driveMotorsR(0, 0);
    Robot.driveTrain.driveMotorsL(0, 0);
  }

}
