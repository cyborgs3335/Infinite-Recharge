/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotPreferences;
import frc.robot.subsystems.Climber.armPosition;

public class armControlBoard extends Command {
  /**
   * Creates a new armControlBoard.
   */
  public armControlBoard() {
    // Use addRequirements() here to declare subsystem dependencies.
    requires(Robot.climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.climber.setArmHeight(RobotPreferences.kControlBoardHeight);
    Robot.climber.setArmPosition(armPosition.controlboard);
    Robot.climber.brake(false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end() {
    Robot.climber.brake(true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
