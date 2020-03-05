/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hatch extends SubsystemBase {

  private Spark m_hatchMotor;
  private Encoder m_hatchEncoder;
  private PIDController m_controller;

  /**
   * Creates a new Hatch.
   */
  public Hatch() {
    m_controller = new PIDController(0.05, 0, 0);
    m_controller.setTolerance(2);
    addChild("PID Controller", m_controller);
    SmartDashboard.putData("Hatch PID", m_controller);

    m_hatchMotor = new Spark(2);
    addChild("Hatch Motor", m_hatchMotor);
    SmartDashboard.putData("Hatch Motor", m_hatchMotor);
    m_hatchMotor.setInverted(false);
    m_hatchMotor.set(0);
            
    m_hatchEncoder = new Encoder(3, 4, false, EncodingType.k4X);
    addChild("Hatch Encoder", m_hatchEncoder);
    SmartDashboard.putData("Hatch Encoder", m_hatchEncoder);
    m_hatchEncoder.setDistancePerPulse(1.0);
    m_hatchEncoder.reset();
  }

  public void run() {
    // Use the output here
    double encoderValue = m_hatchEncoder.get();
    double output = m_controller.calculate(encoderValue);
    SmartDashboard.putNumber("Encoder Value", encoderValue);
    SmartDashboard.putNumber("Output", output);
    m_hatchMotor.set(output);
  }

  public void setSetpoint(int setpoint){
    m_controller.setSetpoint(setpoint);
  }

  public boolean isComplete(){
    boolean isComplete = m_controller.atSetpoint();
    SmartDashboard.putBoolean("Is Complete", isComplete);
    return isComplete;
  }

  public void hatchDisable(){
    m_hatchMotor.set(0);
  }

  public void reset(){
    m_hatchEncoder.reset();
  }
}
