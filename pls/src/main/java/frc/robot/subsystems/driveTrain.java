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
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.whatOI;

public class driveTrain extends Subsystem 
{
  private final TalonFX leftBack,leftFront,rightBack,rightFront;
  //private final PigeonIMU pidgeon;
  private int direction = RobotMap.DRIVE_TRAIN_FORWARD_DIRECTION;
  private double voltageRampRateDefault;
  public Compressor comp;

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
    //TODO:correct the talon the pigeon is attached to
    //pidgeon = new PigeonIMU(rightFront);

    comp = new Compressor(0);
    
		double voltageRampRate = 6;//20;
		setRampRate(voltageRampRate);

    leftFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);//10ms
		leftBack.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		rightFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		rightBack.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
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

		double output = .75;//.75

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

  public void setDirection(int direction) 
  {
		this.direction = direction * RobotMap.DRIVE_TRAIN_FORWARD_DIRECTION;
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

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new whatOI());
  }
}
