package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.ballcollection.BallCollectionOff;
import org.usfirst.frc.team3218.robot.commands.gear.GearArmControl;
import org.usfirst.frc.team3218.robot.commands.gear.GearCollectionStop;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearCollection extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public CANTalon gearLiftMotor = new CANTalon(RobotMap.gearLiftMotorPort);
	public int lowerLimit = 0;
	public int upperLimit = 1100; // Find what lower limit is and set this equal to it.
	public int range = upperLimit - lowerLimit;
	public int countsPerRotation = 4096; // The # of encoder counts for 1 rotation.
	public boolean armOverride = false;
	public Spark gearCollectionMotorLeft = new Spark(RobotMap.gearCollectionMotorPortLeft);
	public Spark gearCollectionMotorRight = new Spark(RobotMap.gearCollectionMotorPortRight); 
	private double armPower = .3;
	public static DigitalInput dontHasGear = new DigitalInput(RobotMap.hasGear);
	public static double armMotorSpeed;

	public void gearArmUp() {

		gearLiftMotor.set(armPower);

	}

	public void gearArmDown() {

		gearLiftMotor.set(-armPower);

	}

	public void gearCollectionGrab() {

		gearCollectionMotorLeft.set(-1);
		gearCollectionMotorRight.set(1);
		gearLiftMotor.set(.05);
	}

	public void gearCollectionStop() {

		gearCollectionMotorLeft.set(0);
		gearCollectionMotorRight.set(0);

		gearLiftMotor.set(.05);

	}

	public void gearCollectionRelease() {
		gearLiftMotor.set(.01);
		gearCollectionMotorLeft.set(.6);
		gearCollectionMotorRight.set(-.6);

	}

	// Using the throttle to control the arm
	public void controlGearArm(double x) {

		gearLiftMotor.set(x);
		gearCollectionMotorLeft.set(0);
		gearCollectionMotorRight.set(0);

	}

	public void initDefaultCommand() {

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());

		setDefaultCommand(new GearArmControl());

	}
}
