/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  TalonSRX beltDrive/*, armDrive*/;
  public boolean beltRun,armTrigger;
  DoubleSolenoid armExtend; 
  //Solenoid armExtend;
  public Intake()
  {
    //armExtend = new DoubleSolenoid(RobotMap.INTAKE_SOLENOID_ARM,RobotMap.INTAKE_SOLENOID_ARM2);
    beltRun = armTrigger = false;
    armExtend = new DoubleSolenoid(5, 4);
    //armExtend2 = new Solenoid(0,RobotMap.INTAKE_SOLENOID_ARM2);
    //armDrive = new TalonSRX(RobotMap.INTAKE_MOTOR_ARM);
    //armDrive.configFactoryDefault(100);
    beltDrive = new TalonSRX(RobotMap.INTAKE_MOTOR_BELT);
    beltDrive.configFactoryDefault(100);

    //armDrive pid
    // armDrive.config_kP(0, RobotPreferences.kIntakeA_P);
    // armDrive.config_kI(0, RobotPreferences.kIntakeA_I);
    // armDrive.config_kD(0, RobotPreferences.kIntakeA_D);

    //beltDrive pid
    // beltDrive.config_kP(0, RobotPreferences.kIntakeB_P);
    // beltDrive.config_kI(0, RobotPreferences.kIntakeB_I);
    // beltDrive.config_kD(0, RobotPreferences.kIntakeB_D);
  }

  public void driveBeltMotor()
  {
    beltDrive.set(ControlMode.PercentOutput, -.8);
  }

  public void stopBeltMotor()
  {
    beltDrive.set(ControlMode.PercentOutput, 0);
  }

  public void driveArmMotor(double speed, boolean isInverted)
  {
    // armDrive.set(ControlMode.PercentOutput, speed);
    // armDrive.setInverted(isInverted);
  }

  public void extendArm(Value v)
  {
    armExtend.set(v);
    //armExtend2.set(s);
  }

  // public String get()
  // {
    // if(armExtend.get().equals(Value.kForward))
    //   return "forward";
    // if(armExtend.get().equals(Value.kReverse))
    //   return "reverse";
    // else
    //   return "off";
  // }

  @Override
  public void periodic() {
    // TODO Auto-generated method stub
    super.periodic();
    if(beltRun)
      driveBeltMotor();
    else
      stopBeltMotor();

    if(armTrigger)
      extendArm(Value.kForward);
    else
      extendArm(Value.kReverse);
  }

  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(null);
  }
}
