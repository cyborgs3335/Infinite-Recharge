/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Robot;

public class rotationControl extends Command 
{

  int count;
  boolean isFinished = false;
  Color prev,current;

  public rotationControl() 
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.colorSensor);
    requires(Robot.armControl);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    count = 0;
    Robot.colorSensor.rotatePanel(.5);
    current = Robot.colorSensor.getDetected();
    if(!(count >= 8))
    {
      if(prev == null || !current.equals(prev))
      {
        prev = current;
        count++;
      }
    }
    else
    {
      Robot.colorSensor.rotatePanel(0);
      isFinished = true;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    end();
  }

}
