/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Drive;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.GrabHatch;
import frc.robot.commands.SpinToBlue;
import frc.robot.commands.SpinToGreen;
import frc.robot.commands.SpinToRed;
import frc.robot.commands.SpinToYellow;
import frc.robot.commands.SpinWheel;
import frc.robot.subsystems.ColorWheel;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Hatch;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  SendableChooser<Command> chooser = new SendableChooser<>();

  // subsystems
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Hatch m_hatch = new Hatch();
  private final ColorWheel m_colorWheel = new ColorWheel();
  private final DriveTrain m_driveTrain = new DriveTrain();

  // commands
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final Command m_grabHatch = new GrabHatch(m_hatch);
  private final Command m_spinToRed = new SpinToRed(m_colorWheel);
  private final Command m_spinToBlue = new SpinToBlue(m_colorWheel);
  private final Command m_spinToGreen = new SpinToGreen(m_colorWheel);
  private final Command m_spinToYellow = new SpinToYellow(m_colorWheel);
  private final Command m_spinWheel = new SpinWheel(m_colorWheel);
  public final Command m_drive = new Drive(m_driveTrain);
  
  // joysticks
  public static Joystick m_joystick = new Joystick(0);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    chooser.addOption("Spin to Red", m_spinToRed);
    chooser.addOption("Spin to Blue", m_spinToBlue);
    chooser.addOption("Spin to Green", m_spinToGreen);
    chooser.addOption("Spin to Yellow", m_spinToYellow);
  
    SmartDashboard.putData("Chooser", chooser);
  
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

  private void configureButtonBindings() {

    new JoystickButton(m_joystick, 3)
        .whenPressed(m_grabHatch);

  }

  public void chooserCommand() {
    new JoystickButton(m_joystick, 4)
        .whenPressed(chooser.getSelected());
  }
 
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
