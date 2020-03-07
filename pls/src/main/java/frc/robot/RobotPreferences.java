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
    //TODO: set all constants in Inches

    //arm constants  
    public static final double armheight = 22.25;                     //4096 ticks per revolution, 1 rev = 7d/6 inch
    public static final double kWinchScalar = 4096/(7d/6);//done //ticks per inch of hight on the arm
        //height of arm constants
    public static final double kControlBoardHeight = 30.5 - armheight;//done
    public static final double kFullExtendHeight = 78 +(7/8d) - armheight;//done
    public static final double kFullRetractHeight = 0;//done
    public static final double kDefaultStart = kFullRetractHeight;
    public static final double kLowerExtendHeight = 22.24 - armheight ;//done
    


    //speeds
    public static final double armSpeed = .1;

    //Drivetrain pid
    public static final double kP = .6;
    public static final double kI = .1;
    public static final double kD = 50;
    public static final double kF = .1;
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
    public static final double kArmW_P = .05;
    public static final double kArmW_I = 0;
    public static final double kArmW_D = 0;
    public static final double kArmRR_P = .05;
    public static final double kArmRR_I = 0;
    public static final double kArmRR_D = 0;
    public static final double kArmRL_P = .05;
    public static final double kArmRL_I = 0;
    public static final double kArmRL_D = 0;

    //shooter pid
    public static final double kShoot_P = 0;
    public static final double kShoot_I = 0;
    public static final double kShoot_D = 0;
    public static final double kDistFromBaseMax = 0;
    public static final double kDistFromBaseMin = 0;

    //vision
    public static final double kCameraDistanceFromFront = 17.75; 
    public static final double kCameraHeight = 36+(5d/8); 
    public static final double kCameraAngle = -18; 
    public static final double kPortTargetBottomToPortCenter = 15;//done
    public static final double kDrivetrainWheelDiameterInches = 4.0;

    //target center heights
    public static final double kFloorToPortCenter = 98.25;//done
    public static final double LowestPort = 23;//done
}
