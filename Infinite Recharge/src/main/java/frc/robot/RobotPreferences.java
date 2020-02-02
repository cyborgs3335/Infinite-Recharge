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
    //all constants TODO

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

    //arm pid
    public static final double armW_kP = .1;
    public static final double armW_kI = 0;
    public static final double armW_kD = 0;
    public static final double armRR_kP = .1;
    public static final double armRR_kI = 0;
    public static final double armRR_kD = 0;
    public static final double armRL_kP = .1;
    public static final double armRL_kI = 0;
    public static final double armRL_kD = 0;

    //vision
    public static final double kCameraDistanceFromFront = 17.75; 
    public static final double kCameraHeight = 36+(5d/8); //TODO Correct
    public static final double kCameraAngle = -18; 
    public static final double kPortTargetBottomToPortCenter = 6.5;
    public static final double kFloorToLowPortCenter = 19;
    public static final double kDrivetrainWheelDiameterInches = 4.0;

    public static final double LowestPort = 19;//bottom
    public static final double HighPort = 47;
    public static final double Backport = 1;

}
