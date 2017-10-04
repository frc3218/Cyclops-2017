package org.usfirst.frc.team3218.robot.commands.gear;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.subsystems.GearCollection;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearArmUp extends Command {

    public GearArmUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearColletion);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.gearColletion.gearLiftMotor.getEncPosition() < 1290)
    	Robot.gearColletion.gearArmUp();
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	    	
        return (Robot.gearColletion.gearLiftMotor.getEncPosition() == Robot.gearColletion.upperLimit);
        
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
