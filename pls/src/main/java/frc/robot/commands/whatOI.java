/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.commands.drive.Podracing;
import frc.robot.commands.drive.TankDrive;

public class whatOI extends Command {
  Podracing podracer;
  TankDrive tank;
  /**
   * Creates a new whatOI.
   */
  public whatOI() {
    // Use addRequirements() here to declare subsystem dependencies.
    requires(Robot.driveTrain);
    podracer = new Podracing();
    tank = new TankDrive();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("OI is online");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Robot.podrace)
    {
      System.out.println("now this is podracing");
      podracer.execute();
    }
    else
    {
      
      tank.execute();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end() {
    System.out.println("OI was murdered or interrupted");
    Robot.oi = new OI(Robot.podrace);
    System.out.println("new OI generated");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
