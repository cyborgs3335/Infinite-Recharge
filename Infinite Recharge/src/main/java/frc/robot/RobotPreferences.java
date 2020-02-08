/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class RobotPreferences {
    //all constants TODO: correct

    //where constats for the robot are placed
    public static final double kDistancePerRevolution = 1.2*Math.PI;//in inches
    public static final double kRatioToOutput = 60/18;
    public static final double kChassisHeightOffset = 4.5; //in inches
    public static final double kDrivetrainWheelCircumference = 4.0 * Math.PI;
    public static final double kElevatorScalar = 1.1342376464;
    public static final double kMaxArmAngle = 50;//50 is a filler number to be changed
    public static final double kSRXEncoderCPR = 4096;
    public static final double kRearEncoderToOutputRatio = (1.00/7)*(1.99/5);
    public static final double kRearMaxAngle = 90;
    public static final double kRearMinAngle = 0;
    public static final double kEGearRatio = -.3;
    public static final int kWinchDegreesPerInch = 1;

    //speeds
    public static final double armSpeed = .1;

    //pid
    public static final double kP = .6;
    public static final double kI = .00001;
    public static final double kD = 50;
    public static final double kF = 0;
    public static final double kIzone = 0;
    public static final double kPeakOutput = 1;
    public static final double kTimeoutMS = 30;

    //intake pid
    public static final double kIntakeB_P = 0;
    public static final double kIntakeB_I = 0;
    public static final double kIntakeB_D = 0;
    public static final double kIntakeA_P = 0;
    public static final double kIntakeA_I = 0;
    public static final double kIntakeA_D = 0;
    

    //arm pid
    public static final double kArmW_P = .1;
    public static final double kArmW_I = 0;
    public static final double kArmW_D = 0;
    public static final double kArmRR_P = .1;
    public static final double kArmRR_I = 0;
    public static final double kArmRR_D = 0;
    public static final double kArmRL_P = .1;
    public static final double kArmRL_I = 0;
    public static final double kArmRL_D = 0;

    //vision
    public static final double kCameraDistanceFromFront = 17.75; 
    public static final double kCameraHeight = 36+(5d/8); 
    public static final double kCameraAngle = -18; 
    public static final double kPortTargetBottomToPortCenter = 6.5;
    public static final double kFloorToLowPortCenter = 19;
    public static final double kDrivetrainWheelDiameterInches = 4.0;

    public static final double LowestPort = 19;//bottom
    public static final double HighPort = 47;
    public static final double Backport = 1;
	

}
