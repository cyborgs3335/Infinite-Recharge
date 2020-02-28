/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class driveBackward extends Command {
  /**
   * Creates a new driveBackward.
   */
  public driveBackward() {
    // Use addRequirements() here to declare subsystem dependencies.
    requires(Robot.driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.driveTrain.driveMotorsL(-.2, -.2);
    Robot.driveTrain.driveMotorsR(-.2,-.2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end() {
    Robot.driveTrain.driveMotorsL(0, 0);
    Robot.driveTrain.driveMotorsR(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
