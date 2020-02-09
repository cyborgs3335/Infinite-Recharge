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
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.RobotPreferences;

public class driveTrain extends SubsystemBase {
  private final TalonSRX leftBack,leftFront,rightBack,rightFront;
  private final Solenoid solenoid1, solenoid2;
  private int direction = RobotMap.DRIVE_TRAIN_FORWARD_DIRECTION;
  private double voltageRampRateDefault;

  /**
   * Creates a new driveTrain.
   */
  public driveTrain()
  {
    leftBack = new TalonSRX(RobotMap.MOTOR_DRIVE_LEFTB);
    leftFront = new TalonSRX(RobotMap.MOTOR_DRIVE_LEFTF);
    rightBack = new TalonSRX(RobotMap.MOTOR_DRIVE_RIGHTB);
    rightFront = new TalonSRX(RobotMap.MOTOR_DRIVE_RIGHTF);
    solenoid1 = new Solenoid(RobotMap.BUTTERFLY_PCM_MODULE1, RobotMap.BUTTERFLY_FORWARD_CHANNEL1);
		solenoid2 = new Solenoid(RobotMap.BUTTERFLY_PCM_MODULE1, RobotMap.BUTTERFLY_FORWARD_CHANNEL2);
		double voltageRampRate = voltageRampRateDefault;//20;
		setRampRate(voltageRampRate);
		solenoid1.set(false);
    solenoid2.set(false);

    leftFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		leftBack.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		rightFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		rightBack.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		rightFront.setInverted(true);
		rightBack.setInverted(true);
        
		//PID

		leftFront.config_kD(0, RobotPreferences.kD);
		leftBack.config_kD(0, RobotPreferences.kD);
		rightFront.config_kD(0, RobotPreferences.kD);
		rightBack.config_kD(0, RobotPreferences.kD);
		leftFront.config_kP(0, RobotPreferences.kP);
		leftBack.config_kP(0, RobotPreferences.kP);
		rightFront.config_kP(0, RobotPreferences.kP);
		rightBack.config_kP(0, RobotPreferences.kP);
		leftFront.config_kI(0, RobotPreferences.kI);
		leftBack.config_kI(0, RobotPreferences.kI);
		rightFront.config_kI(0, RobotPreferences.kI);
		rightBack.config_kI(0, RobotPreferences.kI);

		double output = .75;

		leftFront.configPeakOutputForward(output); //30 is timeout
		leftBack.configPeakOutputForward(output);
		rightFront.configPeakOutputForward(output);
		rightBack.configPeakOutputForward(output);
		leftFront.configPeakOutputReverse(-output);
		leftBack.configPeakOutputReverse(-output);
		rightFront.configPeakOutputReverse(-output);
		rightBack.configPeakOutputReverse(-output);

  }

  private void setRampRate(double voltageRampRate) 
  {
    //                              volts/theArgument
    double secondsFromNeutralToFull = 12/voltageRampRate;
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
		// Formerly: frontLeft.enableBrakeMode(brake);
		// See https://github.com/CrossTheRoadElec/Phoenix-Documentation#installing-phoenix-framework-onto-your-frc-robot
		NeutralMode mode = brake ? NeutralMode.Brake : NeutralMode.Coast;

        //uncoment this
        
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

  public void switchSolenoidState(boolean state){
		solenoid1.set(state);
		solenoid2.set(state);
		
	}

  public void setDirection(int direction) 
  {
		this.direction = direction * RobotMap.DRIVE_TRAIN_FORWARD_DIRECTION;
  }
  
  public void driveMotors(double rf, double rb, double lf, double lb) 
  {     
		leftFront.set(ControlMode.PercentOutput, lf);
		leftBack.set(ControlMode.PercentOutput,lb);
		rightFront.set(ControlMode.PercentOutput,rf);
		rightBack.set(ControlMode.PercentOutput,rb);
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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
