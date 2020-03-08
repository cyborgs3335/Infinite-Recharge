/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.util.mapAxis;

public class toggleIntake extends Command {
  boolean isFinished;
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
    // Robot.intake.driveBeltMotor();
    // Robot.intake.extendArm(Value.kForward);
    Robot.intake.beltRun = !Robot.intake.beltRun;
    isFinished = true;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end() {
    // Robot.intake.stopBeltMotor();
    // Robot.intake.extendArm(Value.kReverse);
  }

  @Override
  protected void interrupted() {
    end();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
