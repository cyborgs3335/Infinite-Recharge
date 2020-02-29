/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotPreferences;
import frc.robot.subsystems.armControl.armPosition;

public class armFullRetract extends CommandBase {
  /**
   * Creates a new armFullRetract.
   */
  public armFullRetract() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.armControl);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    Robot.armControl.setArmHeight(RobotPreferences.kFullRetractHeight);
    Robot.armControl.setArmPosition(armPosition.fullRetract);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
