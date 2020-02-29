/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.util.mapAxis;

public class shimmy extends Command {
  /**
   * Creates a new shimmyLeft.
   */
  public shimmy() {
    // Use addRequirements() here to declare subsystem dependencies.
    requires(Robot.armControl);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.armControl.shimmy(mapAxis.map(Robot.oi.getJoystick().getRawAxis(2)));
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end() {
    Robot.armControl.shimmy(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
