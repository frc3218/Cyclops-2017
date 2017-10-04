package org.usfirst.frc.team3218.robot.subsystems;

import javax.jws.Oneway;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.climbing.ClimbStop;
import org.usfirst.frc.team3218.robot.commands.climbing.ForwardClimbing;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Climbing extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public Victor climbMotorRight = new Victor(RobotMap.climbMotorPortRight);
	public Encoder climbEncoder = new Encoder(RobotMap.climbEncoderPortA, RobotMap.climbEncoderPortB);
	public short climbDistance = 2000; // Total encoder count once rope is climbed.

	public void climb() {

		climbMotorRight.set(-1);
		SmartDashboard.putNumber("Climb Encoder", Math.abs(climbEncoder.get()));

	}

	public void climbReverse() {

		climbMotorRight.set(1);
		SmartDashboard.putNumber("Climb Encoder", Math.abs(climbEncoder.get()));

	}

	public void climbStop() {

		climbMotorRight.set(0);
		SmartDashboard.putNumber("Climb Encoder", Math.abs(climbEncoder.get()));

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new ClimbStop());

	}
}
