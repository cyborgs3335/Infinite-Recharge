/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.commands.*;
import frc.robot.commands.vision.*;
import frc.robot.subsystems.vision.Limelight.Target;

public class Default extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Default() 
  {
    requires(Robot.driveTrain);
    requires(Robot.shooter);
    requires(Robot.dumper);
    requires(Robot.limelight);

    addSequential(new driveForward());
    addSequential(new WaitCommand(1));
    addSequential(new FindTarget(Target.PORT));
    addSequential(new DriveToTarget(Target.PORT));
    addSequential(new FireAccurateShot());
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
