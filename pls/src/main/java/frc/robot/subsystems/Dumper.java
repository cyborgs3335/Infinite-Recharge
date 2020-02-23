/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class Dumper extends Subsystem{

  public Solenoid dump/*,dump2*/;
  /**
   * Creates a new Dumper.
   */
  public Dumper() {
    dump = new Solenoid(RobotMap.DUMP_PCM , RobotMap.DUMP_SOLENOID);
    // dump2 = new Solenoid(RobotMap.DUMP_PCM2, RobotMap.DUMP_SOLENOID2);
    
  }

  public void dump(boolean d)
  {
    dump.set(d);
    //System.out.println(dump.get());
    // dump2.set(!d);
  }
  
  public boolean checkState()
  {
    return dump.get();
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  protected void initDefaultCommand() {

  }
}
