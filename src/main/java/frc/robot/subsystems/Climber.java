package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  private CANSparkMax m_climbMotor = new CANSparkMax(12, MotorType.kBrushless);
  private CANSparkMax m_climbFollower = new CANSparkMax(13, MotorType.kBrushless);
  private double m_speed;

    public Climber() {
      super();
      m_climbMotor.setIdleMode(IdleMode.kBrake);
      m_climbFollower.setIdleMode(IdleMode.kBrake);
      m_climbFollower.follow(m_climbMotor, true);
      m_climbMotor.getEncoder().setPosition(0);
    }

    public void climbUp(double speed) {
      m_speed = speed;
    }

    public void climbDown(double speed) {
      m_speed = speed;
    }

    public void stop() {
      m_climbMotor.set(0);
    }

    @Override
    public void periodic() {
      m_climbMotor.set(m_speed);
      SmartDashboard.putNumber("Climber Position", m_climbMotor.getEncoder().getPosition());
    }


}
