/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;
  //TODO: correct all constants
  public static final int ARM_MOTOR_Winch = 0; 
  public static final int ARM_MOTOR_RR = 0;
  public static final int ARM_MOTOR_RL = 0;
  public static final int DRIVE_TRAIN_FORWARD_DIRECTION = 1;
	public static final int MOTOR_DRIVE_LEFTF = 14;// front left
	public static final int MOTOR_DRIVE_LEFTB = 13;//back left
	public static final int MOTOR_DRIVE_RIGHTF = 11;//front right
  public static final int MOTOR_DRIVE_RIGHTB = 12;//back right
  public static final int DRIVE_PCM_DUMPER = 0;
	public static final int DRIVE_PCM_MODULE1 = 0;
	public static final int DRIVE_FORWARD_CHANNEL1 = 0;
	public static final int DRIVE_FORWARD_CHANNEL2 = 0;
	public static final int MOTOR_DRIVE_INTAKE_BELT = 0;
	public static final int MOTOR_DRIVE_INTAKE_ARM = 0;
	public static final int SOLENOID_DRIVE_INTAKE = 0;
	public static final int SOLENOID_ARM_DRIVE_INTAKE = 0;
	public static final int SOLENOID_DUMP = 0;
	public static final int ARM_PCM_MODULE1 = 0;
	public static final int SOLENOID_ARM = 0;
	public static final int SOLENOID_ARM_WINCH = 0;
	public static final int ARM_WINCH_PCM_MODULE1 = 0;
	public static final int SOLENOID_ARM2 = 0;
	public static final int ARM_PCM_MODULE2 = 0;
	public static final int SHOOT_PCM_MODULE = 0;
	public static final int SOLENOID_SHOOT = 0;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
