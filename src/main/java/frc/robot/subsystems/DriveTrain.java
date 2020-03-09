/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class DriveTrain extends SubsystemBase {

  // Test Board
  // private static Spark m_leftDrive = new Spark(0);
  // private static Talon m_rightDrive = new Talon(1);
  // Real Robot
  private static VictorSP m_leftDrive  = new VictorSP(8);
  private static VictorSP m_rightDrive = new VictorSP(9);
  public static int front = 1;
	public static double right, left;

  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {

    addChild("Left Drive", m_leftDrive);
    SmartDashboard.putData(m_leftDrive);
    m_leftDrive.setInverted(false);
    m_leftDrive.set(0);

    addChild("Right Drive", m_rightDrive);
    SmartDashboard.putData(m_rightDrive);
    m_rightDrive.setInverted(false);
    m_rightDrive.set(0);

  }

	public static void drive(double move, double rotate) {
		double greatestControl, greatestSpeed;
		// Preliminary calculations
		greatestControl = Math.max(Math.abs(move), Math.abs(rotate));
		greatestSpeed = Math.max(Math.abs(-move - rotate), Math.abs(move - rotate));
		// if(greatestSpeed == 0) greatestSpeed = 0.1;

		// Drive the motors
		right = (move + rotate) * greatestControl / greatestSpeed;
		left = (-move + rotate) * greatestControl / greatestSpeed;
		m_rightDrive.set(right);
    m_leftDrive.set(left);
    
    SmartDashboard.putNumber("Raw Axis", RobotContainer.m_joystick.getRawAxis(3));

	}

}
