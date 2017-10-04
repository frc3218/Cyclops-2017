package org.usfirst.frc.team3218.robot.commands.climbing;

import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ForwardClimbing extends Command {

	float average[] = new float[31];
	float rate;
	int CLIMB_RATE_THRESHOLD = 600;

	public ForwardClimbing() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

		requires(Robot.climbing);

	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		rate = (float) -Robot.climbing.climbEncoder.getRate();

		if (rate > CLIMB_RATE_THRESHOLD) {

			Robot.climbing.climbEncoder.reset();

		}

		averager(rate);
		SmartDashboard.putNumber("Climb Encoder", -Robot.climbing.climbEncoder.get());
		SmartDashboard.putNumber("climb rate", average[average.length - 1]);

		if (Math.abs(Robot.climbing.climbEncoder.get()) < Robot.climbing.climbDistance) {

			Robot.climbing.climb();

		} else {

			Robot.climbing.climbStop();

		}

	}

	public void averager(float rate) {

		average[average.length - 1] = 0;

		for (int i = average.length - 2; i > 0; i--) {

			average[i] = average[i - 1];

			average[0] = rate;

			average[average.length - 1] += (average[i]) / (average.length - 1);

		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {

		return (-Robot.climbing.climbEncoder.get() < Robot.climbing.climbDistance);

	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
