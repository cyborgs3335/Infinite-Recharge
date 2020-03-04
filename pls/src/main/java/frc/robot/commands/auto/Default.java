/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.driveForward;
import frc.robot.commands.vision.DriveToTarget;
import frc.robot.commands.vision.FindTarget;
import frc.robot.commands.vision.FireAccurateShot;
import frc.robot.subsystems.vision.Limelight.Target;

public class Default extends SequentialCommandGroup {

  public Default() {
    //requires(Robot.driveTrain);
    //requires(Robot.shooter);
    //requires(Robot.dumper);
    //requires(Robot.limelight);
    super(new Command[] {
      new driveForward(),
      // TODO: add back in when robot vision and motion magic turning is finished
      // new WaitCommand(1),
      // new FindTarget(Target.PORT),
      // new DriveToTarget(Target.PORT),
      // new FireAccurateShot()
    });

  }
}
