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

public class toggleIntake extends Command {
  /**
   * Creates a new toggleIntake.
   */
  public toggleIntake() {
    // Use addRequirements() here to declare subsystem dependencies.\
    requires(Robot.intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.intake.driveBeltMotor(mapAxis.map(Robot.oi.getJoystick().getRawAxis(2)), false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end() {
    Robot.intake.driveBeltMotor(0, false);
  }

  @Override
  protected void interrupted() {
    end();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
