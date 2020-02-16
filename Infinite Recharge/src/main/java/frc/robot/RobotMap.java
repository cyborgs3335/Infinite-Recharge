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
	//armControl constants
	public static final int ARM_MOTOR_WINCHL = 2; 
	public static final int ARM_MOTOR_WINCHR = 13;
 	public static final int ARM_MOTOR_RR = 4;
	public static final int ARM_MOTOR_RL = 11;
	public static final int ARM_SOLENOID_ARMRF = 0;
	public static final int ARM_SOLENOID_ARMRR = 1;
	public static final int ARM_SOLENOID_ARMLF = 2;
	public static final int ARM_SOLENOID_ARMLR = 3;
	public static final int ARM_PCM_SOLENOIDR = 1;
	public static final int ARM_PCM_SOLENOIDL = 1;
	//driveTrain constants
  	public static final int DRIVE_TRAIN_FORWARD_DIRECTION = 1;
	public static final int DRIVE_MOTOR_LEFTF = 0;// front left
	public static final int DRIVE_MOTOR_LEFTB = 1;//back left
	public static final int DRIVE_MOTOR_RIGHTF = 15;//front right
	public static final int DRIVE_MOTOR_RIGHTB = 14;//back right
  	//intake constants
	public static final int INTAKE_MOTOR_BELT = 5;
	public static final int INTAKE_MOTOR_ARM = 10;
	public static final int INTAKE_SOLENOID_ARM = 0;
	public static final int INTAKE_SOLENOID_ARM2 = 1;
	//dumper constants
	public static final int DUMP_SOLENOID = 2;
	public static final int DUMP_SOLENOID2 = 3;
	public static final int DUMP_PCM = 0;
	public static final int DUMP_PCM2 = 0;
	//shooter constants
	public static final int SHOOT_PCM = 0;
	public static final int SHOOT_PCM2= 0;
	public static final int SHOOT_SOLENOID = 4;
	public static final int SHOOT_SOLENOID2 = 5;
	public static final int SHOOT_MOTOR_FIRE = 3;
	public static final int SHOOT_MOTOR_FIRE2 = 12;
	public static final int SHOOT_MOTOR_PITCH = 6;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
