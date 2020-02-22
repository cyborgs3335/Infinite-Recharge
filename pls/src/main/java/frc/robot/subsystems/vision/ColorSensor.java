/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.vision;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class ColorSensor extends Subsystem 
{
  ColorSensorV3 cs;
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  ColorMatch matcher;
  Color cyan,green,red,yellow;
  
  ColorSensor()
  {
    cs = new ColorSensorV3(i2cPort);
    matcher = new ColorMatch();
    //TODO: correct colortargets
    cyan = ColorMatch.makeColor(0,0,0);
    green = ColorMatch.makeColor(0,0,0);
    red = ColorMatch.makeColor(0,0,0);
    yellow = ColorMatch.makeColor(0,0,0);
    matcher.addColorMatch(cyan);
    matcher.addColorMatch(green);
    matcher.addColorMatch(red);
    matcher.addColorMatch(yellow);
  }

  public boolean isTarget()
  {
    
    ColorMatchResult match = matcher.matchClosestColor(getDetected());
    return false;
  }

  public Color getDetected()
  {
    return cs.getColor();
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
