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
import frc.robot.commands.*;



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
      System.out.println("podrace");
      mJoystick = new Joystick(0);
      mJoystick2 = new Joystick(1);
      //BUtton Info:
      //CONFLICTING: 4
      //DOUBLED: 1,6,7,11
      //Joystick1 buttons
      //-----------------
      //UNUSED: 2,5,8
      
      //intake

      //arm
      //int bArmExtend = 5;
      int bShimmy = 3;
      int bDriveArm = 4;

      //dump
      int bToggleDump = 10;
      int bToggleFlap = 9;

      //shooter
      int bToggleShooter = 11;
      int bTriggerShooter = 1;
      
      //switch drive
      int bSwitchDrive = 6;

      //Joystick2 buttons
      //-----------------
      //UNUSED: 8,9
      //intake
      int bIntakeTrigger = 2;
      int bIntakeToggle = 7;
      //int bIntakeArmMotorToggle = 10;
      int bIntakeArmToggle = 10;

      //arm
      int bFullArmExtend = 4;
      int bFullArmRetract = 5;
      int bArmControlBoard = 3;
      int bClimb = 9;

      //dump

      //shooter


      JoystickButton intakePC = addButton(mJoystick2, bIntakeTrigger, "Trigger Intake PC");
      intakePC.whileHeld(new triggerIntake());

      JoystickButton toggleIntake = addButton(mJoystick2, bIntakeToggle, "Intake Toggle PC");
      toggleIntake.toggleWhenPressed(new toggleIntake());
      JoystickButton toggleIntake2 = addButton(mJoystick, bIntakeToggle, "Intake Toggle PC");
      toggleIntake2.toggleWhenPressed(new toggleIntake());

      // JoystickButton toggleIntakeArmMotor = addButton(mJoystick2, bIntakeArmMotorToggle, "Toggle Intake Arm Motor");
      // toggleIntakeArmMotor.toggleWhenPressed(new toggleIntakeArmMotor());

      JoystickButton toggleIntakeArm = addButton(mJoystick2, bIntakeArmToggle, "Toggle Intake Arm");
      toggleIntakeArm.toggleWhenPressed(new toggleIntakeArm());

      JoystickButton switchDrive = addButton(mJoystick, bSwitchDrive, "Toggle DriveState");
      switchDrive.whenPressed(new switchDrive());
      JoystickButton switchDrive2 = addButton(mJoystick2, bSwitchDrive, "Toggle DriveState");
      switchDrive2.whenPressed(new switchDrive());

      JoystickButton retractArm = addButton(mJoystick, bDriveArm, "Drive Arm");
      retractArm.whileHeld(new driveArm());

      // JoystickButton extendArm = addButton(mJoystick, bArmExtend, "Extend Arm");
      // extendArm.whileHeld(new driveArm());

      JoystickButton fullArmExtend = addButton(mJoystick2, bFullArmExtend, "Full Extend Arm");
      fullArmExtend.whenPressed(new armFullExtend());

      JoystickButton fullArmRetract = addButton(mJoystick2, bFullArmRetract, "Full Retract Arm");
      fullArmRetract.whenPressed(new armFullRetract());

      JoystickButton armControlBoard = addButton(mJoystick2, bArmControlBoard, "arm to control board");
      armControlBoard.whenPressed(new armControlBoard());
      
      JoystickButton armClimb = addButton(mJoystick2, bClimb, "Climb");
      armClimb.toggleWhenPressed(new armClimb());

      JoystickButton shimmyLeft = addButton(mJoystick, bShimmy, "Shimmy");
      shimmyLeft.whileHeld(new shimmy());

      JoystickButton toggleDump = addButton(mJoystick, bToggleDump, "toggle Dump");
      toggleDump.toggleWhenPressed(new toggleDump());

      JoystickButton toggleFlap = addButton(mJoystick, bToggleFlap, "toggle flap");
      toggleFlap.toggleWhenPressed(new toggleFlap());

      JoystickButton toggleShooter = addButton(mJoystick, bToggleShooter, "toggle shooter");
      toggleShooter.toggleWhenPressed(new toggleShooter());
      JoystickButton toggleShooter2 = addButton(mJoystick2, bToggleShooter, "toggle shooter");
      toggleShooter2.toggleWhenPressed(new toggleShooter());

      JoystickButton triggerShooter = addButton(mJoystick, bTriggerShooter, "trigger shooter");
      triggerShooter.whileHeld(new triggerShooter());
      JoystickButton triggerShooter2 = addButton(mJoystick2, bTriggerShooter, "trigger shooter");
      triggerShooter2.whileHeld(new triggerShooter());
    }
    else
    {
      mJoystick = new Joystick(0);
      System.out.println("tank");
      //Joystick1
      //-1 indicates command is not used
      //Buttons
      //-------
      //UNUSED: 7

      //intake
      //int bIntakeTrigger = -1;
      int bIntakeToggle = 10;
      //int bIntakeArmMotorToggle = -1;
      int bIntakeArmToggle = 5;

      //arm
      int bMoveArm = 8;
      int bFullArmExtend = 3;
      int bFullArmRetract = 2;
      int bShimmy = 4;
      int bClimb = 9;
      //int bArmControlBoard = 10;
      //int bRetractArm = 9;

      //dump
      int bToggleDump = 11;

      //shooter
      //int bToggleShooter = -1;
      int bTriggerShooter = 1;

      //switch drive
      int bSwitchDrive = 6;
      
      

      // JoystickButton intakePC = addButton(mJoystick, bIntakeTrigger, "Trigger Intake PC");
      // intakePC.whileHeld(new triggerIntake());

      JoystickButton toggleIntake = addButton(mJoystick, bIntakeToggle, "Intake Toggle PC");
      toggleIntake.toggleWhenPressed(new toggleIntake());

      // JoystickButton toggleIntakeArmMotor = addButton(mJoystick, bIntakeArmMotorToggle, "Toggle Intake Arm Motor");
      // toggleIntakeArmMotor.toggleWhenPressed(new toggleIntakeArmMotor());

      JoystickButton toggleIntakeArm = addButton(mJoystick, bIntakeArmToggle, "Toggle Intake Arm");
      toggleIntakeArm.toggleWhenPressed(new toggleIntakeArm());

      JoystickButton switchDrive = addButton(mJoystick, bSwitchDrive, "Toggle DriveState");
      switchDrive.whenPressed(new switchDrive());

      JoystickButton driveArm = addButton(mJoystick, bMoveArm, "Drive Arm");
      driveArm.toggleWhenPressed(new driveArm());

      // JoystickButton extendArm = addButton(mJoystick, bArmExtend, "Extend Arm");
      // extendArm.whileHeld(new armExtend());

      JoystickButton fullArmExtend = addButton(mJoystick, bFullArmExtend, "Full Extend Arm");
      fullArmExtend.whenPressed(new armFullExtend());

      JoystickButton fullArmRetract = addButton(mJoystick, bFullArmRetract, "Full Retract Arm");
      fullArmRetract.whenPressed(new armFullRetract());

      // JoystickButton armControlBoard = addButton(mJoystick, bArmControlBoard, "arm to control board");
      // armControlBoard.whenPressed(new armControlBoard());

      JoystickButton shimmyLeft = addButton(mJoystick, bShimmy, "Shimmy");
      shimmyLeft.whileHeld(new shimmy());

      JoystickButton armClimb = addButton(mJoystick, bClimb, "Climb");
      armClimb.toggleWhenPressed(new armClimb());

      JoystickButton toggleDump = addButton(mJoystick, bToggleDump, "toggle Dump");
      toggleDump.toggleWhenPressed(new toggleDump());

      // JoystickButton toggleShooter = addButton(mJoystick, bToggleShooter, "toggle shooter");
      // toggleShooter.toggleWhenPressed(new toggleShooter());

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
