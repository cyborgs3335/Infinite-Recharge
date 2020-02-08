/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Dumper extends Subsystem{

  Solenoid dump;
  /**
   * Creates a new Dumper.
   */
  public Dumper() {
    dump = new Solenoid(RobotMap.DRIVE_PCM_DUMPER , RobotMap.SOLENOID_DUMP);
  }

  public void dump(boolean d)
  {
    dump.set(!d);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  protected void initDefaultCommand() {
    // TODO Auto-generated method stub

  }
}
