/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.RobotPreferences;

public class shooter extends Subsystem {
  Solenoid deploy,deploy2;
  TalonSRX launch,launch2;
  /**
   * Creates a new shooter.
   */
  public shooter() {
      deploy = new Solenoid(RobotMap.SHOOT_PCM, RobotMap.SHOOT_SOLENOID);
      deploy2 = new Solenoid(RobotMap.SHOOT_PCM2,RobotMap.SHOOT_SOLENOID2);
      launch = new TalonSRX(RobotMap.SHOOT_MOTOR_FIRE);
      launch2 = new TalonSRX(RobotMap.SHOOT_MOTOR_FIRE2);
      launch.configFactoryDefault(100);
      launch2.configFactoryDefault(100);

      //pid
      launch.config_kP(0,RobotPreferences.kShoot_P);
      launch.config_kI(0,RobotPreferences.kShoot_I);
      launch.config_kD(0,RobotPreferences.kShoot_D);
      launch2.config_kP(0,RobotPreferences.kShoot_P);
      launch2.config_kI(0,RobotPreferences.kShoot_I);
      launch2.config_kD(0,RobotPreferences.kShoot_D);
  }

  public void setPID(double p)
  {
    launch.config_kP(0,p);
    launch2.config_kP(0,p);
  }
  
  public void setI(double i)
  {
    launch.config_kI(0,i);
    launch2.config_kI(0,i);
  }

  public void setD(double d)
  {
    launch.config_kD(0,d);
    launch2.config_kD(0,d);
  }

  public void revMotor(double speed)
  {
    launch.set(ControlMode.PercentOutput, speed);
    launch2.set(ControlMode.PercentOutput,speed);
  }

  public void setShooter(boolean isOn)
  {
    deploy.set(isOn);
    deploy2.set(isOn);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  protected void initDefaultCommand() {

  }
}
