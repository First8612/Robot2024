package frc.robot.subsystems;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  private TalonFX m_shootMotor = new TalonFX(14);
  private TalonFX m_shootFollower = new TalonFX(15);
  private CANSparkMax m_feedMotor = new CANSparkMax(11, MotorType.kBrushless);
  private double m_shooterSpeed = 0;
  private double m_feedSpeed = 0;
  private SlewRateLimiter m_shooterRateLimiter = new SlewRateLimiter(10);

  public Shooter() {
    super();
    m_shootMotor.setNeutralMode(NeutralModeValue.Brake);
    m_shootFollower.setControl(new Follower(m_shootMotor.getDeviceID(), true));
    m_shootFollower.setNeutralMode(NeutralModeValue.Brake);
    m_feedMotor.setIdleMode(IdleMode.kBrake);
  }

  public void shootSpeaker() {
    m_feedSpeed = 0.5;
    setSpeakerShootSpeed();
  }

  public void shootAmp() {
    m_feedSpeed = 0.5;
    m_shooterSpeed = 0.3;
  }

  public void feedOnly() {
    m_feedSpeed = 0.5;
  }

  public void shootSpeakerOnly() {
    setSpeakerShootSpeed();
  }

  public void shootAmpOnly() {
    m_shooterSpeed = 0.3;
  }

  public void backfeed() {
    m_feedSpeed = -0.25;
  }

  public void stop() {
    m_shooterSpeed = 0;
    m_feedSpeed = 0;
  }

  // NEW STUFF

  public void setSpeakerShootSpeed() {
    m_shooterSpeed = 1;
  }

  public boolean isShooterAtAmpVelocity() {
    return Math.abs(m_shootMotor.getVelocity().getValueAsDouble()) > 30; 
  }

  public boolean isShooterAtSpeakerVelocity() {
    return Math.abs(m_shootMotor.getVelocity().getValueAsDouble()) > 95; 
  }

  public void shoot() {
    m_feedSpeed = 1;
  }

  public void stopFeed() {
    m_feedSpeed = 0;
  }

  public void stopShooter() {
    m_shooterSpeed = 0;
  }

  @Override
  public void periodic() {
    m_shootMotor.set(m_shooterRateLimiter.calculate(m_shooterSpeed));
    m_feedMotor.set(m_feedSpeed);
    SmartDashboard.putNumber("Shooter/Speed", m_shootMotor.getVelocity().getValueAsDouble());
    SmartDashboard.putNumber("Shooter/Feed", m_feedMotor.getEncoder().getVelocity());
  }

}
