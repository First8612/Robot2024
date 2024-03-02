// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.net.PortForwarder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;

public class XCaliper extends TimedRobot {
  private RobotContainer m_robotContainer;
  private Command m_autonomousCommand;

  public void robotInit() {
    m_robotContainer = new RobotContainer(this);

    for (int port = 5800; port <= 5807; port++) {
      PortForwarder.add(port, "limelight.local", port);
    }

    var camera = CameraServer.startAutomaticCapture();
    var server = CameraServer.getServer();
    camera.setResolution(160, 120);
    camera.setFPS(40);
    server.setSource(camera);
  }

  public void robotPeriodic() {
    m_robotContainer.robotPeriodic();
  }

  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
    m_autonomousCommand.schedule();
   }
  }

  @Override
  public void teleopInit() {
     if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    m_robotContainer.teleopInit();
  }

  @Override
  public void teleopPeriodic() {
    m_robotContainer.teleopPeriodic();
  }
  
}
