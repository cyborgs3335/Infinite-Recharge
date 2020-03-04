/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.sensors.PigeonIMU;

//import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;
//import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
//import frc.robot.commands.whatOI;

public class driveTrain extends SubsystemBase {
  //private final TalonSRX leftBack,leftFront,rightBack,rightFront;
  private final TalonFX leftBack,leftFront,rightBack,rightFront;
  private final PigeonIMU pidgeon;
  //private final Solenoid solenoid1, solenoid2;
  private int direction = RobotMap.DRIVE_TRAIN_FORWARD_DIRECTION;
  private double voltageRampRateDefault;
  public Compressor comp;
  private double[] YPR,heading;//yaw,pitch,roll | x,y,z | z is the one that tracks robot rotation


  /**
   * Creates a new driveTrain.
   */
  public driveTrain()
  {
    System.out.println("Drive ");
    leftBack = new TalonFX(RobotMap.DRIVE_MOTOR_LEFTB);
    leftBack.configFactoryDefault(100);
    leftFront = new TalonFX(RobotMap.DRIVE_MOTOR_LEFTF);
    leftFront.configFactoryDefault(100);
    rightBack = new TalonFX(RobotMap.DRIVE_MOTOR_RIGHTB);
    rightBack.configFactoryDefault(100);
    rightFront = new TalonFX(RobotMap.DRIVE_MOTOR_RIGHTF);
    rightFront.configFactoryDefault(100);
    
    pidgeon = new PigeonIMU(Robot.intake.beltDrive);
    YPR = new double[3];
    heading = new double[3];

    comp = new Compressor(0);
    
		double voltageRampRate = 6;//20;
		setRampRate(voltageRampRate);

    FeedbackDevice feedbackDevice = FeedbackDevice.CTRE_MagEncoder_Relative;
    //FeedbackDevice feedbackDevice = FeedbackDevice.IntegratedSensor;
    leftFront.configSelectedFeedbackSensor(feedbackDevice, 0, 10);//10ms
    leftBack.configSelectedFeedbackSensor(feedbackDevice, 0, 10);
    rightFront.configSelectedFeedbackSensor(feedbackDevice, 0, 10);
    rightBack.configSelectedFeedbackSensor(feedbackDevice, 0, 10);
    rightFront.setInverted(true);
    rightBack.setInverted(true);

    //PID

		// leftFront.config_kD(0, RobotPreferences.kD);
		// leftBack.config_kD(0, RobotPreferences.kD);
		// rightFront.config_kD(0, RobotPreferences.kD);
		// rightBack.config_kD(0, RobotPreferences.kD);
		// leftFront.config_kP(0, RobotPreferences.kP);
		// leftBack.config_kP(0, RobotPreferences.kP);
		// rightFront.config_kP(0, RobotPreferences.kP);
		// rightBack.config_kP(0, RobotPreferences.kP);
		// leftFront.config_kI(0, RobotPreferences.kI);
		// leftBack.config_kI(0, RobotPreferences.kI);
		// rightFront.config_kI(0, RobotPreferences.kI);
		// rightBack.config_kI(0, RobotPreferences.kI);

		//double output = .75;//.75

		// leftFront.configPeakOutputForward(output); //30 is timeout
		// leftBack.configPeakOutputForward(output);
		// rightFront.configPeakOutputForward(output);
		// rightBack.configPeakOutputForward(output);
		// leftFront.configPeakOutputReverse(-output);
		// leftBack.configPeakOutputReverse(-output);
		// rightFront.configPeakOutputReverse(-output);
    // rightBack.configPeakOutputReverse(-output);
    
    

  }

  private void setRampRate(double voltageRampRate) 
  {
    //                              volts/theArgument
    double secondsFromNeutralToFull = .5;//12/voltageRampRate;
    leftFront.configOpenloopRamp(secondsFromNeutralToFull, 0);
		leftBack.configOpenloopRamp(secondsFromNeutralToFull, 0);
        
        
		rightFront.configOpenloopRamp(secondsFromNeutralToFull, 0);
    rightBack.configOpenloopRamp(secondsFromNeutralToFull, 0);
  }

  public void setDefaltRampRate()
  {
		double voltageRampRate = voltageRampRateDefault;
		setRampRate(voltageRampRate);
  }
  
  public void setBrake(boolean brake) 
  {
		NeutralMode mode = brake ? NeutralMode.Brake : NeutralMode.Coast;

        
		leftFront.setNeutralMode(mode);
		leftBack.setNeutralMode(mode);
		
		rightFront.setNeutralMode(mode);
    rightBack.setNeutralMode(mode);
        
  }
  
  public double getleftFrontPostition()
  {
		return leftFront.getSelectedSensorPosition();
	}

  public double getMotorLeft2Position()
  {
		return leftBack.getSelectedSensorPosition();
	}

  public double getrightFrontPosition()
  {
		return rightFront.getSelectedSensorPosition();
	}

  public double getMotorRight2Position()
  {	 
    
		return rightBack.getSelectedSensorPosition();
  }

  public double getYaw()
  {
    return YPR[0];
  }

  public double getPitch()
  {
    return YPR[1];
  }

  public double getRoll()
  {
    return YPR[3];
  }

  public double[] getHeading()
  {
    return heading;
  }
  
  public void driveMotorsL(double lf, double lb) 
  {     
		leftFront.set(ControlMode.PercentOutput, lf);
		leftBack.set(ControlMode.PercentOutput,lb);
  }

  public void driveMotorsR(double rf, double rb)
  {
    rightFront.set(ControlMode.PercentOutput,rf);
		rightBack.set(ControlMode.PercentOutput,rb);
  }

  public void driveMotors(double l, double r)
  {
    driveMotorsL(l, l);
    driveMotorsR(r,r);
  }
  
  public void driveMotorsVision(double rf, double rb, double lf, double lb) {
		int irf = (int) rf*4096;
		int irb = (int) rb*4096;
		int ilf = (int) lf*4096;
		int ilb = (int) lb*4096;
          
		leftFront.set(ControlMode.Position, leftFront.getSelectedSensorPosition() + ilf);
		leftBack.set(ControlMode.Position, leftBack.getSelectedSensorPosition() + ilb);
		rightFront.set(ControlMode.Position, rightFront.getSelectedSensorPosition() + irf);
		rightBack.set(ControlMode.Position, rightBack.getSelectedSensorPosition() + irb);
  }

  /**
   * Turn the robot by the specified angle (in degrees).
   * @param angleDeg positive angle is clockwise motion, negative angle is counter-clockwise
   */
  public void turn(double angleDeg) {
    // Use approximation of distance = radius * theta (in radians)
    double drivetrainRadius = 12.0; // inches TODO FIXME
    double fudgeFactor = 1.0; // TODO FIXME -- take actual measurements
    double dist = fudgeFactor * drivetrainRadius * Math.toRadians(angleDeg);
    int idist = (int) dist * 4096;
    leftFront.set(TalonFXControlMode.MotionMagic, leftFront.getSelectedSensorPosition() + idist);
    leftBack.set(TalonFXControlMode.MotionMagic, leftBack.getSelectedSensorPosition() + idist);
    rightFront.set(TalonFXControlMode.MotionMagic, rightFront.getSelectedSensorPosition() - idist);
    rightBack.set(TalonFXControlMode.MotionMagic, rightBack.getSelectedSensorPosition() - idist);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    pidgeon.getAccumGyro(heading);
    pidgeon.getYawPitchRoll(YPR);
    SmartDashboard.putNumber("roll", YPR[2]);
    SmartDashboard.putNumber("yaw", YPR[0]);
    SmartDashboard.putNumber("pitch", YPR[1]);
    SmartDashboard.putNumber("x angle", heading[0]);
    SmartDashboard.putNumber("y angle", heading[1]);
    SmartDashboard.putNumber("z angle", heading[2]);
  }
}
