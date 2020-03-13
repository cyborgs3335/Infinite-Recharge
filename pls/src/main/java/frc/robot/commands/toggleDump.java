// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.commands;

// import edu.wpi.first.wpilibj.command.Command;
// import frc.robot.Robot;

// public class toggleDump extends Command {
//   boolean isFinished;
//   public toggleDump() {
//     // Use requires() here to declare subsystem dependencies
//     // eg. requires(chassis);
//     requires(Robot.dumper);
//   }

//   // Called just before this Command runs the first time
//   @Override
//   protected void initialize() {
//     // Robot.dumper.dump(true);
//     // Robot.dumper.flap(true);
//     Robot.dumper.togDump = !Robot.dumper.togDump;
//     isFinished = true;
//   }

//   // Called repeatedly when this Command is scheduled to run
//   @Override
//   protected void execute() {
    
//     //System.out.println(Robot.dumper.checkState());
//   }

//   // Make this return true when this Command no longer needs to run execute()
//   @Override
//   protected boolean isFinished() {
//     return false;
//   }

//   // Called once after isFinished returns true
//   @Override
//   protected void end() {
//     // Robot.dumper.dump(false);
//     // Robot.dumper.flap(false);
//   }

//   // Called when another command which requires one or more of the same
//   // subsystems is scheduled to run
//   @Override
//   protected void interrupted() {
//     end();
//   }
  
// }
