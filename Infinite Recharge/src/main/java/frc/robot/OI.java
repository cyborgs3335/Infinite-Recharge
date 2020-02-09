/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.*;
import frc.robot.subsystems.armControl;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI 
{

  private Joystick mJoystick;
  private Joystick mJoystick2;

  //Joystick 0 (primary joystick)
  public OI(boolean podrace)
  {
    if(podrace)
    {
      mJoystick = new Joystick(0);
      mJoystick2 = new Joystick(1);

      //TODO:assign to actuall buttons
      //Joystick1 buttons
      //intake
      int bIntakeTrigger = 0;
      int bIntakeToggle = 0;
      int bIntakeArmMotorToggle = 0;
      int bIntakeArmToggle = 0;

      //arm
      int bArmExtend = 0;
      int bFullArmExtend = 0;
      int bFullArmRetract = 0;
      int bShimmyRight = 0;
      int bShimmyLeft = 0;
      int bArmControlBoard = 0;
      int bRetractArm = 0;

      //dump
      int bToggleDump = 0;

      //shooter
      int bToggleShooter = 0;
      int bTriggerShooter = 0;
      
      //switch drive
      int bSwitchDrive = 0;

      //Joystick2 buttons
      


      JoystickButton intakePC = addButton(mJoystick, bIntakeTrigger, "Trigger Intake PC");
      intakePC.whileHeld(new triggerIntake());

      JoystickButton toggleIntake = addButton(mJoystick, bIntakeToggle, "Intake Toggle PC");
      toggleIntake.toggleWhenPressed(new toggleIntake());

      JoystickButton toggleIntakeArmMotor = addButton(mJoystick, bIntakeArmMotorToggle, "Toggle Intake Arm Motor");
      toggleIntakeArmMotor.toggleWhenPressed(new toggleIntakeArmMotor());

      JoystickButton toggleIntakeArm = addButton(mJoystick, bIntakeArmToggle, "Toggle Intake Arm");
      toggleIntakeArm.toggleWhenPressed(new toggleIntakeArm());

      JoystickButton switchDrive = addButton(mJoystick, bSwitchDrive, "Toggle DriveState");
      switchDrive.whenPressed(new switchDrive());

      JoystickButton retractArm = addButton(mJoystick, bRetractArm, "Retract Arm");
      retractArm.whileHeld(new armRetract());

      JoystickButton extendArm = addButton(mJoystick, bArmExtend, "Extend Arm");
      extendArm.whileHeld(new armExtend());

      JoystickButton fullArmExtend = addButton(mJoystick, bFullArmExtend, "Full Extend Arm");
      fullArmExtend.whenPressed(new armFullExtend());

      JoystickButton fullArmRetract = addButton(mJoystick, bFullArmRetract, "Full Retract Arm");
      fullArmRetract.whenPressed(new armFullRetract());

      JoystickButton armControlBoard = addButton(mJoystick, bArmControlBoard, "arm to control board");
      armControlBoard.whenPressed(new armControlBoard());

      JoystickButton shimmyLeft = addButton(mJoystick, bShimmyLeft, "Shimmy Left");
      shimmyLeft.whileHeld(new shimmyLeft());

      JoystickButton shimmyRight = addButton(mJoystick, bShimmyRight, "Shimmy Right");
      shimmyRight.whileHeld(new shimmyLeft());

      JoystickButton toggleDump = addButton(mJoystick, bToggleDump, "toggle Dump");
      toggleDump.toggleWhenPressed(new toggleDump());

      JoystickButton toggleShooter = addButton(mJoystick, bToggleShooter, "toggle shooter");
      toggleShooter.toggleWhenPressed(new toggleShooter());

      JoystickButton triggerShooter = addButton(mJoystick, bTriggerShooter, "trigger shooter");
      triggerShooter.whileHeld(new triggerShooter());
    }
    else
    {
      mJoystick = new Joystick(0);

      //TODO:assign to actuall buttons
      //Joystick1

      //intake
      int bIntakeTrigger = 0;
      int bIntakeToggle = 0;
      int bIntakeArmMotorToggle = 0;
      int bIntakeArmToggle = 0;

      //arm
      int bArmExtend = 0;
      int bFullArmExtend = 0;
      int bFullArmRetract = 0;
      int bShimmyRight = 0;
      int bShimmyLeft = 0;
      int bArmControlBoard = 0;
      int bRetractArm = 0;

      //dump
      int bToggleDump = 0;

      //shooter
      int bToggleShooter = 0;
      int bTriggerShooter = 0;

      //switch drive
      int bSwitchDrive = 0;
      
      

      JoystickButton intakePC = addButton(mJoystick, bIntakeTrigger, "Trigger Intake PC");
      intakePC.whileHeld(new triggerIntake());

      JoystickButton toggleIntake = addButton(mJoystick, bIntakeToggle, "Intake Toggle PC");
      toggleIntake.toggleWhenPressed(new toggleIntake());

      JoystickButton toggleIntakeArmMotor = addButton(mJoystick, bIntakeArmMotorToggle, "Toggle Intake Arm Motor");
      toggleIntakeArmMotor.toggleWhenPressed(new toggleIntakeArmMotor());

      JoystickButton toggleIntakeArm = addButton(mJoystick, bIntakeArmToggle, "Toggle Intake Arm");
      toggleIntakeArm.toggleWhenPressed(new toggleIntakeArm());

      JoystickButton switchDrive = addButton(mJoystick, bSwitchDrive, "Toggle DriveState");
      switchDrive.whenPressed(new switchDrive());

      JoystickButton retractArm = addButton(mJoystick, bRetractArm, "Retract Arm");
      retractArm.whileHeld(new armRetract());

      JoystickButton extendArm = addButton(mJoystick, bArmExtend, "Extend Arm");
      extendArm.whileHeld(new armExtend());

      JoystickButton fullArmExtend = addButton(mJoystick, bFullArmExtend, "Full Extend Arm");
      fullArmExtend.whenPressed(new armFullExtend());

      JoystickButton fullArmRetract = addButton(mJoystick, bFullArmRetract, "Full Retract Arm");
      fullArmRetract.whenPressed(new armFullRetract());

      JoystickButton armControlBoard = addButton(mJoystick, bArmControlBoard, "arm to control board");
      armControlBoard.whenPressed(new armControlBoard());

      JoystickButton shimmyLeft = addButton(mJoystick, bShimmyLeft, "Shimmy Left");
      shimmyLeft.whileHeld(new shimmyLeft());

      JoystickButton shimmyRight = addButton(mJoystick, bShimmyRight, "Shimmy Right");
      shimmyRight.whileHeld(new shimmyLeft());

      JoystickButton toggleDump = addButton(mJoystick, bToggleDump, "toggle Dump");
      toggleDump.toggleWhenPressed(new toggleDump());

      JoystickButton toggleShooter = addButton(mJoystick, bToggleShooter, "toggle shooter");
      toggleShooter.toggleWhenPressed(new toggleShooter());

      JoystickButton triggerShooter = addButton(mJoystick, bTriggerShooter, "trigger shooter");
      triggerShooter.whileHeld(new triggerShooter());
    }
    
  }

  public JoystickButton addButton(Joystick j, int k, String key)
  {
    JoystickButton test = new JoystickButton(j, k);
    SmartDashboard.putData(key, test);
    return test;
  }

  public Joystick getJoystick() 
  {
	  return mJoystick;
  }

  public Joystick getJoystick2() 
  {
	  return mJoystick2;
  }
}

/// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
