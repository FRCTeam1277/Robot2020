/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.HttpCamera.HttpCameraKind;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Camera extends SubsystemBase {
  private UsbCamera camera;
  private CameraServer m_server;
  /**
   * Creates a new Camera.
   */
  public Camera() {

  }
  // Thread t = new Thread(() -> {
    
  // 	boolean allowCam1 = false;
    
  // 	UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);
    //     camera1.setResolution(320, 240);
    //     camera1.setFPS(30);
        
    //     HttpCamera piCam = new HttpCamera("piCam", "http://10.12.77.3:1180/?action=stream", HttpCameraKind.kMJPGStreamer);
        
        
    //     CvSink cvSink1 = CameraServer.getInstance().getVideo(camera1);
    //     CvSink cvSink2 = CameraServer.getInstance().getVideo(piCam);
    //     CvSource outputStream = CameraServer.getInstance().putVideo("Switcher", 640, 480);
        
    //     Mat image = new Mat();
        
    //     while(!Thread.interrupted()) {
          
    //         if(allowCam1){
    //           cvSink2.setEnabled(false);
    //           cvSink1.setEnabled(true);
    //           cvSink1.grabFrame(image);
    //         } else{
    //           cvSink1.setEnabled(false);
    //           cvSink2.setEnabled(true);
    //           cvSink2.grabFrame(image);     
    //         }
            
    //         outputStream.putFrame(image);
    //     }
        
    // });
    // t.start();

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // Start the thread for the camera
  public void start() {
    new Thread(() -> {
      m_server = CameraServer.getInstance();
      camera = m_server.startAutomaticCapture();
      camera.setResolution(640, 480);
      
      CvSink cvSink = m_server.getVideo();
      CvSource outputStream = m_server.putVideo("Blur", 640, 480);

      // Make picture frames
      // Mat source = new Mat();
      // Mat output = new Mat();

      //while(!Thread.interrupted()) {
      //    cvSink.grabFrame(source);
          // Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
          // outputStream.putFrame(output);
          // outputStream.putFrame(source);
      // }
    }).start();
  }
}
