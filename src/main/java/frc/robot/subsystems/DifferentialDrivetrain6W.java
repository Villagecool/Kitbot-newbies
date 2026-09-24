// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.Pigeon2;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DifferentialDrivetrain6W extends SubsystemBase {
  private final SparkMax r_motorLeader;  //right motor
    private final SparkMax r_motorFollower1;
    // private final TalonFX r_motorFollower2;
  private final SparkMax l_motorLeader;  //left motor
     private final SparkMax l_motorFollower1;
    // private final TalonFX l_motorFollower2;
     public final DifferentialDrive m_robotDrive;
  private final Pigeon2 PigeonGyro = new Pigeon2 (1);
  private final Pose2d pose;
  private final RelativeEncoder leftEncoder;
  private final RelativeEncoder rightEncoder;
  
    private final DifferentialDriveOdometry odometry;
  
  


  @SuppressWarnings("removal")
  public DifferentialDrivetrain6W() {// Instantiate Motor Controllers
    r_motorLeader = new SparkMax(1, MotorType.kBrushless);
    r_motorFollower1 = new SparkMax(2, MotorType.kBrushless);
    rightEncoder = r_motorLeader.getEncoder();
    pose = new Pose2d(0, 0, new Rotation2d());
    l_motorLeader = new SparkMax(4, MotorType.kBrushless);
    l_motorFollower1 = new SparkMax(5, MotorType.kBrushless);
    leftEncoder = l_motorLeader.getEncoder();
      
    odometry = new DifferentialDriveOdometry(
      PigeonGyro.getRotation2d(),
      leftEncoder.getPosition(),
      rightEncoder.getPosition(),
      new Pose2d(0, 0, new Rotation2d()));
  
    // --- Right Side Configuration ---
    SparkMaxConfig rightLeaderConfig = new SparkMaxConfig();
    // If your right side runs backward compared to left, invert it here:
    // rightLeaderConfig.inverted(true);

    SparkMaxConfig rightFollowerConfig = new SparkMaxConfig();
    rightFollowerConfig.follow(r_motorLeader);

    // Apply configurations to Right motors
    r_motorLeader.configure(rightLeaderConfig, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters);
    r_motorFollower1.configure(rightFollowerConfig, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters);

    // --- Left Side Configuration ---
    SparkMaxConfig leftLeaderConfig = new SparkMaxConfig();

    SparkMaxConfig leftFollowerConfig = new SparkMaxConfig();
    leftFollowerConfig.follow(l_motorLeader);

    // Apply configurations to Left motors
    l_motorLeader.configure(leftLeaderConfig, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters);
    l_motorFollower1.configure(leftFollowerConfig, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters);

    // Initialize DifferentialDrive using the leader motors
    m_robotDrive = new DifferentialDrive(l_motorLeader::set, r_motorLeader::set);
      
    
  }

 

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
   pose = odometry.update(
    PigeonGyro.getRotation2d(),
      leftEncoder.getPosition(),
      rightEncoder.getPosition());
  }
  
  public Pose2d getPose2d() {
    return pose;}
  } 



/*    r_motorLeader = new TalonFX(1);       // middle right
      r_motorFollower1 = new TalonFX(2);  // top right
        r_motorFollower1.setControl(new Follower(r_motorLeader.getDeviceID(), MotorAlignmentValue.Aligned));
      // r_motorFollower2 = new TalonFX(3);  // bottom right
      //   r_motorFollower2.setControl(new Follower(r_motorLeader.getDeviceID(), MotorAlignmentValue.Aligned));
        
    l_motorLeader = new TalonFX(4);       // middle left
        // l_motorLeader.setControl(new Follower(r_motorLeader.getDeviceID(), MotorAlignmentValue.Opposed));
      l_motorFollower1 = new TalonFX(5);  // top left
         l_motorFollower1.setControl(new Follower(l_motorLeader.getDeviceID(), MotorAlignmentValue.Aligned));
    //  l_motorFollower2 = new TalonFX(6);  // bottom 
    //     l_motorFollower2.setControl(new Follower(l_motorLeader.getDeviceID(), MotorAlignmentValue.Aligned)); */ 
    
    // public void arcadedrive(double x, double y){
  //    r_motorLeader.set(-(x-y));
  //   l_motorLeader.set(-(x+y));