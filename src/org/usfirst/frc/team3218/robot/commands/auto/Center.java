package org.usfirst.frc.team3218.robot.commands.auto;

import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Center extends Command {

	public String toString()
	{
		return("Center");
	}
	
    public Center() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	//first argument = distance, second argument = power
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return AutoMethods.driveStraight(AutoMethods.CENTER_DISTANCE_TO_AIRSHIP, 0.5);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
