/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

  private Spark m_motor;
  /**
   * Creates a new Shooter.
   */
  public Shooter(int motor_channel) {

    m_motor = new Spark(motor_channel);
    addChild("S Motor", m_motor);
    SmartDashboard.putData("Shooter Motor", m_motor);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
