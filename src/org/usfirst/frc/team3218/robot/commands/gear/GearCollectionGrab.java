package org.usfirst.frc.team3218.robot.commands.gear;

import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearCollectionGrab extends Command {
int counter=0;
int intialPosition;
    public GearCollectionGrab() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearColletion);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	intialPosition = Robot.gearColletion.gearLiftMotor.getEncPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putBoolean("dont Have Gear", Robot.gearColletion.dontHasGear.get());
    	
    	if(Robot.gearColletion.dontHasGear.get() || intialPosition == (int) (Robot.oi.getJoystickThrottle()*Robot.gearColletion.upperLimit) ){
    		Robot.gearColletion.gearCollectionGrab();
    		

    	}
    	else{
    		Robot.gearColletion.gearCollectionStop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.gearColletion.dontHasGear.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gearColletion.gearCollectionMotorRight.set(0);
    	Robot.gearColletion.gearCollectionMotorLeft.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
