package org.usfirst.frc.team3218.robot.commands.gear;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.subsystems.GearCollection;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearArmControl extends Command {

	public static int desiredPosition;
	public static int currentPosition;
	int errorMargin = 10;
	int lowTolerance = 50;

	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	public GearArmControl() {

		requires(Robot.gearColletion);

	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is skl cheduled to run
	protected void execute() {
		if (!Robot.gearColletion.armOverride) {
			desiredPosition = (int) (Robot.oi.getJoystickThrottle() * Robot.gearColletion.upperLimit);
			currentPosition = Robot.gearColletion.gearLiftMotor.getEncPosition();
		
			if (desiredPosition == 0)
				desiredPosition = Robot.gearColletion.lowerLimit;

			//high
			if (desiredPosition == Robot.gearColletion.upperLimit && currentPosition < Robot.gearColletion.upperLimit-100) {

				Robot.gearColletion.gearArmUp();
				//low 
			} 
			else if (currentPosition < lowTolerance && desiredPosition == 0) {
			
				Robot.gearColletion.gearLiftMotor.set(0);
			}
			else if (desiredPosition == Robot.gearColletion.lowerLimit
					&& currentPosition > Robot.gearColletion.lowerLimit) {

				Robot.gearColletion.gearArmDown();

			} else if (currentPosition >= desiredPosition && desiredPosition == Robot.gearColletion.upperLimit) {

				Robot.gearColletion.gearCollectionStop();

			} 
			else {
				SmartDashboard.putNumber("Desired Position", desiredPosition);
				SmartDashboard.putNumber("Throttle Value", Robot.oi.getJoystickThrottle());
				SmartDashboard.putNumber("Gear Arm Encoder", currentPosition);
				/*
				 * if(!Robot.gearColletion.dontHasGear.get() || Robot.auto){
				 * 
				 * Robot.gearColletion.armMotorSpeed = (float)((desiredPosition
				 * - currentPosition)/((float)Robot.gearColletion.range / 2.0) *
				 * .3) + .17; Robot.gearColletion.armMotorSpeed *= .35;
				 * 
				 * } else {
				 * 
				 * Robot.gearColletion.armMotorSpeed = (float)((desiredPosition
				 * - currentPosition)/((float)Robot.gearColletion.range / 2.0));
				 * Robot.gearColletion.armMotorSpeed *= .35; }
				 */
				Robot.gearColletion.armMotorSpeed = (float) ((desiredPosition - currentPosition)
						/ ((float) Robot.gearColletion.range / 2.0) * .3) + .17;
				Robot.gearColletion.armMotorSpeed *= .45;
				Robot.gearColletion.controlGearArm(Robot.gearColletion.armMotorSpeed);
				SmartDashboard.putNumber("arm power", Robot.gearColletion.armMotorSpeed);

			}

		} else {

			Robot.gearColletion.gearCollectionStop();

		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		return false;

	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

}
