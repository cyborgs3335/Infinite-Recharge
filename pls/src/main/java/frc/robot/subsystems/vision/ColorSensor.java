/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.vision;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class ColorSensor extends Subsystem {
  ColorSensorV3 cs;
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  ColorMatch matcher;
  Color cyan, green, red, yellow;
  SendableChooser<Integer> colorChooser = new SendableChooser<Integer>();

  public ColorSensor() {
    cs = new ColorSensorV3(i2cPort);
    // cs.configureColorSensor(res, rate, gain); Might be needed
    matcher = new ColorMatch();

    colorChooser.setDefaultOption("green", 1);
    colorChooser.addOption("cyan", 2);
    colorChooser.addOption("red", 3);
    colorChooser.addOption("yellow", 4);
    SmartDashboard.putData("ColorChooser", colorChooser);
    SmartDashboard.putNumber("Number of Revs", 4);

    cyan = ColorMatch.makeColor(.143, .427 , .429);
    green = ColorMatch.makeColor(.197, .561, .240);
    red = ColorMatch.makeColor(.561, .232, .114);
    yellow = ColorMatch.makeColor(.316, .524, .113);

    matcher.addColorMatch(cyan);
    matcher.addColorMatch(green);
    matcher.addColorMatch(red);
    matcher.addColorMatch(yellow);
  }

  public boolean onTarget() {
    switch (colorChooser.getSelected()) {
    case 4:
      return (getDetected().equals(green));// if we want yellow we look for green
    case 3:
      return (getDetected().equals(cyan));// if want red, look for cyan
    case 2:
      return (getDetected().equals(red));// if want cyan, look for red
    default:
      return (getDetected().equals(yellow));// if want green, look for yellow
    }
  }

  public void rotatePanel(double speed) {
    Robot.armControl.shimmy(speed);
  }

  public Color getDetected() {
    return matcher.matchClosestColor(cs.getColor()).color;
  }

  public int getDistance() {
    return cs.getProximity();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public void periodic() {
    if(getDetected().equals(green))
    SmartDashboard.putString("ColorDetected", "green");
    if(getDetected().equals(cyan))
    SmartDashboard.putString("ColorDetected", "cyan");
    if(getDetected().equals(red))
    SmartDashboard.putString("ColorDetected", "red");
    if(getDetected().equals(yellow))
    SmartDashboard.putString("ColorDetected", "yellow");
  }
}
