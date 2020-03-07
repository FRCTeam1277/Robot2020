/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class Drive extends CommandBase {

  private final DriveTrain m_driveTrain;
  private static double MIN_SPEED = .1;
  private static double MOVE_SPEED = 1.0f, ROTATE_SPEED = 1.0f, CONTROL_EXPONENT_MOVE = 1.0f,
	CONTROL_EXPONENT_ROTATE = 1.5f;
  private static double MOVE_DEADZONE = 0.025f, ROTATE_DEADZONE = 0.1f;
  public static double speed;
  
  public static double speedCap() {
		speed = (-RobotContainer.m_joystick.getRawAxis(3) + 1) / 2 * (1 - MIN_SPEED) + MIN_SPEED;
		return speed;
	}

  /**
   * Creates a new Drive.
   */
  public Drive(DriveTrain driveTrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveTrain = driveTrain;
    addRequirements(m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double move, rotate;
		move = -RobotContainer.m_joystick.getRawAxis(1);
	  	rotate = RobotContainer.m_joystick.getRawAxis(2);

		// Adjust for Move Deadzone
		if (move >= MOVE_DEADZONE)
			move -= MOVE_DEADZONE;
		else if (move <= -MOVE_DEADZONE)
			move += MOVE_DEADZONE;
		else
			move = 0.0f;
		move /= 1.0f - MOVE_DEADZONE;

		// Adjust for Rotate Deadzone
		if (rotate >= ROTATE_DEADZONE)
			rotate -= ROTATE_DEADZONE;
		else if (rotate <= -ROTATE_DEADZONE)
			rotate += ROTATE_DEADZONE;
		else
			rotate = 0.0f;
		rotate /= 1.0f - ROTATE_DEADZONE;

		// Exponential Adjustment
		move *= Math.pow(Math.abs(move), CONTROL_EXPONENT_MOVE - 1.0f);
		rotate *= Math.pow(Math.abs(rotate), CONTROL_EXPONENT_ROTATE - 1.0f);

		// Linear Adjustment for Maximum Speed
		move *= MOVE_SPEED;
		rotate *= ROTATE_SPEED;

		// Throttle for limiting speed
		move *= speedCap();
		rotate *= speedCap();

		move *= DriveTrain.front;

		DriveTrain.drive(move, rotate);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
