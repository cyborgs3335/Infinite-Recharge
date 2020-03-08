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

  public Solenoid dump/*,dump2*/,flap;
  public boolean togDump, togFlap;
  /**
   * Creates a new Dumper.
   */
  public Dumper() {
    dump = new Solenoid(RobotMap.DUMP_PCM , RobotMap.DUMP_SOLENOID);
    flap = new Solenoid(RobotMap.DUMP_PCM, RobotMap.DUMP_SOLENOID2);
    // dump2 = new Solenoid(RobotMap.DUMP_PCM2, RobotMap.DUMP_SOLENOID2);
    togDump = togFlap = false;
  }

  public void dump(boolean d)
  {
    dump.set(d);
    //System.out.println(dump.get());
    // dump2.set(!d);
  }

  public void flap(boolean v)
  {
    flap.set(v);
  }
  
  public boolean checkState()
  {
    return dump.get();
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    super.periodic();
    if(togDump)
      dump(true);
    else
      dump(false);
    
    if(togFlap)
      flap(true);
    else
      flap(false);
  }

  @Override
  protected void initDefaultCommand() {

  }
}
