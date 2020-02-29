/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Robot;

public class rotationControl extends CommandBase {

  int count;
  boolean isFinished = false;
  Color prev,current;

  public rotationControl() 
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    addRequirements(Robot.colorSensor);
    addRequirements(Robot.armControl);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() 
  {
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() 
  {
    count = 0;
    Robot.colorSensor.rotatePanel(.5);
    current = Robot.colorSensor.getDetected();
    if(!(count >= 8*4))
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
  public boolean isFinished() 
  {
    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) 
  {
  }
}
