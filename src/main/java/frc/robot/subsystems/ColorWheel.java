/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.I2C;


import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.ColorMatch;

public class ColorWheel extends SubsystemBase {
  
  // Test Board
  // private Spark m_wheelMotor;
  // Real Robot
  private final WPI_VictorSPX m_wheelMotor;
  private final I2C.Port i2cPort;
  private final ColorSensorV3 m_colorSensor;
  private final ColorMatch m_colorMatcher;
  private final Color kBlueTarget;
  private final Color kGreenTarget;
  private final Color kRedTarget;
  private final Color kYellowTarget;

  /**
   * Creates a new ColorWheel.
   */
  public ColorWheel(int motor_channel) {
     m_wheelMotor = new  WPI_VictorSPX(23);
   // m_wheelMotor = new Spark(motor_channel);   
    addChild("Wheel Motor", m_wheelMotor);
    SmartDashboard.putData(m_wheelMotor);
    m_wheelMotor.setInverted(false);
    m_wheelMotor.set(ControlMode.PercentOutput, 0);
    // m_wheelMotor.set(0);

    i2cPort = I2C.Port.kOnboard;
    m_colorSensor = new ColorSensorV3(i2cPort);
    m_colorMatcher = new ColorMatch();
    
    kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);    

  }

  public void detectColor(){
    Color detectedColor = m_colorSensor.getColor();

    /**
     * Run the color match algorithm on our detected color
     */
    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    /**
     * Open Smart Dashboard or Shuffleboard to see the color detected by the 
     * sensor.
     */
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
  }

  public boolean isRed(){

    Color detectedColor = m_colorSensor.getColor();
    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    boolean colorMatch = (match.color == kRedTarget);
    return colorMatch;

  }

  public boolean isBlue(){

    Color detectedColor = m_colorSensor.getColor();
    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    boolean colorMatch = (match.color == kBlueTarget);
    return colorMatch;

  }

  public boolean isGreen(){

    Color detectedColor = m_colorSensor.getColor();
    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    boolean colorMatch = (match.color == kGreenTarget);
    return colorMatch;

  }

  public boolean isYellow(){

    Color detectedColor = m_colorSensor.getColor();
    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    boolean colorMatch = (match.color == kYellowTarget);
    return colorMatch;

  }

  public void run(){
     m_wheelMotor.set(ControlMode.PercentOutput, -0.5);
  }

  public void wheelDisable(){
     m_wheelMotor.set(ControlMode.PercentOutput, 0);
  }

}
