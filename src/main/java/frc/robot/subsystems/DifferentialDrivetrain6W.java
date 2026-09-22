// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

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


  @SuppressWarnings("removal")
  public DifferentialDrivetrain6W() {// Instantiate Motor Controllers
    r_motorLeader = new SparkMax(1, MotorType.kBrushless);
    r_motorFollower1 = new SparkMax(2, MotorType.kBrushless);

    l_motorLeader = new SparkMax(4, MotorType.kBrushless);
    l_motorFollower1 = new SparkMax(5, MotorType.kBrushless);

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
   
  }
  
 
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