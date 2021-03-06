package org.usfirst.frc.team3218.robot.commands.auto;

import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class BoilerRight extends Command {

	public String toString()
	{
		return("BoilerRight");
	}
	
    public BoilerRight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	AutoMethods.driveStraight(AutoMethods.SIDE_DISTANCE_TO_AIRSHIP, 0.5);
    	AutoMethods.turnLeft(AutoMethods.SIDE_INITIAL_TURN, 0.5);
        AutoMethods.driveStraight(AutoMethods.SIDE_DISTANCE_TO_PEG, 0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.drive(0, 0);
    	new WaitForGearLift().start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
