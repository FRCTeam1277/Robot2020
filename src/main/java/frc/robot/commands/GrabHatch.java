/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hatch;

public class GrabHatch extends CommandBase {

  private final Hatch m_hatch;

  /**
   * Creates a new GrabHatch.
   */
  public GrabHatch(Hatch hatch) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_hatch = hatch;
    addRequirements(m_hatch);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_hatch.reset();
    m_hatch.setSetpoint(1000);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_hatch.run();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_hatch.hatchDisable();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_hatch.isComplete();
  }
}
