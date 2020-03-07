/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;

public class driveForward extends Command {
  double s;
  boolean isFinished = false;
  /**
   * Creates a new driveForward.
   */
  public driveForward() {
    // Use addRequirements() here to declare subsystem dependencies.
    requires(Robot.driveTrain);
    s = 0;
  }

  public driveForward(double sec) {
    // Use addRequirements() here to declare subsystem dependencies.
    requires(Robot.driveTrain);
    s = sec;
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.driveTrain.driveMotorsL(.5, .5);
    Robot.driveTrain.driveMotorsR(.5,.5);
    if(s != 0.0)
    {
      WaitCommand w = new WaitCommand(s);
      w.initialize();
      isFinished = true;
    }
    else
    {
      double prev = Robot.driveTrain.getVoltage();
      //if(Robot.driveTrain.getVoltage())
    }
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
    return isFinished;
  }
}
