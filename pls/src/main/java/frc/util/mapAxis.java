/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util;

/**
 * Add your docs here.
 */
public class mapAxis {
    static double deadzone = .1;
    public static double map(double input) 
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
}
