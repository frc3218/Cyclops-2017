package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.ballcollection.BallCollectionOff;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.hal.PDPJNI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BallCollection extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public PowerDistributionPanel PDP = new PowerDistributionPanel();
	Victor collectionMotor = new Victor(RobotMap.ballCollectionMotorPort);

	public void collectionOff() {

		collectionMotor.set(0);

	}

	public void collectionOn() {

		collectionMotor.set(-1);

		SmartDashboard.putNumber("Ball Collection Current", PDP.getCurrent(10));
	}

	public void initDefaultCommand() {

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());

		setDefaultCommand(new BallCollectionOff());

	}
}
