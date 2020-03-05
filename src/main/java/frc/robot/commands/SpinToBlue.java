/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheel;

public class SpinToBlue extends CommandBase {

  private static ColorWheel m_colorwheel;

  /**
   * Creates a new SpinToBlue.
   */
  public SpinToBlue(ColorWheel colorwheel) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_colorwheel = colorwheel;
    addRequirements(m_colorwheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_colorwheel.run();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_colorwheel.wheelDisable();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_colorwheel.isBlue();
  }
}
