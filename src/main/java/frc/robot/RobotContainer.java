// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.MoterSpins;

public class RobotContainer {
  public final CommandXboxController joystick = new CommandXboxController(0);
  public final MoterSpins MoterSpins = new MoterSpins();

  public RobotContainer() {
    configureBindings();
  }

  private final Command runOuttake = MoterSpins.startEnd(() -> MoterSpins.runIntake(), () -> MoterSpins.stop())
      .until(() -> joystick.x().getAsBoolean() == false);
  private final Command runIntake = MoterSpins.startEnd(() -> MoterSpins.runOuttake(), () -> MoterSpins.stop())
      .until(() -> joystick.y().getAsBoolean() == false);
  private final Command runshoot = MoterSpins.startEnd(() -> MoterSpins.runshoot(), () -> MoterSpins.stopshoot())
      .until(() -> joystick.rightTrigger().getAsBoolean() == false);

  public void configureBindings() {
      joystick.x().whileTrue(runOuttake);
      joystick.y().whileTrue(runIntake);
      joystick.rightTrigger().whileTrue(runshoot);
  }
  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}