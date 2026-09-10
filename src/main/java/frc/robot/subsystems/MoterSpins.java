// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.TalonFX;

public class MoterSpins extends SubsystemBase {
  /** Creates a new MoterSpins. */
  private final TalonFX m_motor;

  public MoterSpins() {
    m_motor = new TalonFX(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void start(){m_motor.set(.5);}
  public void stop(){m_motor.set(0);}
  
}
