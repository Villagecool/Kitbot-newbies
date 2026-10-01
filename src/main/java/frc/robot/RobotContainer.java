// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.DifferentialDrivetrain6W;
import frc.robot.subsystems.MoterSpins;

public class RobotContainer {
  public final CommandXboxController joystick = new CommandXboxController(0);
  public final MoterSpins MoterSpins = new MoterSpins();
  private final DifferentialDrivetrain6W drivetrain = new DifferentialDrivetrain6W();

  public RobotContainer() {
    drivetrain.setDefaultCommand(
      new RunCommand(()->drivetrain.m_robotDrive.arcadeDrive(
        MathUtil.applyDeadband(joystick.getLeftY(), 0.1), 
        MathUtil.applyDeadband(joystick.getLeftX(), 0.1)
        ), drivetrain)
    );
    configureBindings();
  }

  private final Command runOuttake = MoterSpins.startEnd(() -> MoterSpins.runIntake(), () -> MoterSpins.stop())
      .until(() -> joystick.x().getAsBoolean() == false);
  private final Command runIntake = MoterSpins.startEnd(() -> MoterSpins.runOuttake(), () -> MoterSpins.stop())
      .until(() -> joystick.y().getAsBoolean() == false);
  private final Command runshoot = MoterSpins.startEnd(() -> MoterSpins.runshoot(), () -> MoterSpins.stopshoot())
      .until(() -> joystick.a().getAsBoolean() == false);

  public void configureBindings() {
      joystick.x().whileTrue(runOuttake);
      joystick.y().whileTrue(runIntake);
      joystick.a().whileTrue(runshoot);
  }
  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}