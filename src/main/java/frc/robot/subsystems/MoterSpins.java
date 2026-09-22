// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkLowLevel.MotorType;
public class MoterSpins extends SubsystemBase {
  /** Creates a new MoterSpins. */
  private final SparkMax m_motor;
  private final SparkMax s_motor;
  public MoterSpins() {
    m_motor = new SparkMax (0, MotorType.kBrushless);
    s_motor = new SparkMax (1, MotorType.kBrushless);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void start(){m_motor.set(.5);}
  public void stop(){m_motor.set(0);}
  public void runIntake() {
    m_motor.set(0.8);
  
  }
  public void runOuttake() {
    m_motor.set(-0.8);
  }
  public void stopIntake() {
    m_motor.set(0.0);
  }

  public void startshoot(){s_motor.set(.5);}
  public void stopshoot(){s_motor.set(0);}
  public void runshoot() {
    s_motor.set(0.8);
  }

}
