/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class TankDrive {

  //not a command

  double deadzone = .1;

  public TankDrive(){
      Robot.driveTrain.setBrake(false);
      Robot.driveTrain.setDefaltRampRate();
      System.out.println("tank tank tank!");
  }

  public double map(double input) 
  {
    //return value==0 ? 0 : value*value*(Math.abs(value)/value);
    if (Math.abs(input) < deadzone) 
    {
      return 0;
    }
    if (input>0) 
    {
      return (input - deadzone)/(1 - deadzone);
    } 
    else if(input<0) 
    {
      return (input + deadzone)/(1 - deadzone);
    } 
    else 
    {
      return 0;
    }
  }
    // Called repeatedly when this Command is scheduled to run
    
  public void execute() 
  {
    double x = Robot.oi.getJoystick().getX();
    double y = Robot.oi.getJoystick().getY();
    double fr= y + x;
    double fl = y - x;
    // //fr
    // if(x >.8 && y < -.8)
    // {
    //   fr = .5;
    //   fl = 1;
    // }
    // //fl
    // else if(x < -.8 && y< -.8)
    // {
    //   fr = 1;
    //   fl = .5;
    // }
    // //bl
    // else if(x < -.8 && y > .8)
    // {
    //   fr = -1;
    //   fl = -.5;
    // }
    // //br
    // else if(x > .8 && y > .8)
    // {
    //   fr = -.5;
    //   fl = -1;
    // }
    
    double forwardRight = map(fr);
    double forwardLeft = map(fl);
    Robot.driveTrain.driveMotorsR(forwardRight, forwardRight);
    Robot.driveTrain.driveMotorsL(forwardLeft, forwardLeft);
    DriverStation.reportWarning("you are in the single stick drive now", true);
    }
}
