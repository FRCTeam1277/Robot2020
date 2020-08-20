/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

/**
 * Add your docs here.
 */
public class Climber extends SubsystemBase {
  // Motors and encoder 
  private final WPI_VictorSPX m_liftMotor1, m_liftMotor2, m_balanceMotor;
  private final Encoder m_liftEncoder;
  private final Encoder m_balanceEncoder;
  // Add the 2 limit switches
  private final DigitalInput m_bottom, m_top;
  // Add 2 solenoids, one provided the power and one is timed.
  private final Solenoid m_powerSol, m_2secSol;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
 
  // Constructor 
  // Create motors, limit switches
  public Climber() {
    m_liftMotor1 = new  WPI_VictorSPX(20);
    m_liftMotor2 = new WPI_VictorSPX(21);
    m_balanceMotor = new WPI_VictorSPX(22);
      // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    addChild("Lift Motor", m_liftMotor1);
    SmartDashboard.putData(m_liftMotor1);
    m_liftMotor2.follow(m_liftMotor1);   // They are a pair so make 2 follow 1.

    addChild("Balance Motor", m_balanceMotor);
    SmartDashboard.putData(m_balanceMotor);

    m_liftEncoder = new Encoder(3, 4, false, EncodingType.k4X);
    addChild("Lift Encoder", m_liftEncoder);
    SmartDashboard.putData("Hatch Encoder", m_liftEncoder);
    m_liftEncoder.setDistancePerPulse(1.0);
    m_liftEncoder.reset();

    m_balanceEncoder = new Encoder(5, 6, false, EncodingType.k4X);
    addChild("BalanceEncoder", m_balanceEncoder);
    SmartDashboard.putData("Hatch Encoder", m_balanceEncoder);
    m_balanceEncoder.setDistancePerPulse(1.0);
    m_balanceEncoder.reset();

    // Limit Switches
    m_bottom = new DigitalInput(7);
    addChild("Bottom", m_bottom);
    SmartDashboard.putData("Bottom", m_bottom);
    m_top    = new DigitalInput(8);
    addChild("Top", m_top);
    SmartDashboard.putData("Top", m_top);

    // Solenoids
    m_powerSol = new Solenoid(0);
    m_2secSol = new Solenoid(1);
    SmartDashboard.putData("PowerSol", m_powerSol);
    SmartDashboard.putData("2SecSol", m_2secSol);
  }

}
