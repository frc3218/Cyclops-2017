package org.usfirst.frc.team3218.robot.commands.gearshift;

import java.awt.RenderingHints;
import java.awt.datatransfer.FlavorTable;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.filters.LinearDigitalFilter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearShifting extends Command {

	
	
    public GearShifting() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*
    	/*
    
    
    	if( Robot.driveTrain.leftShiftEncoder.getRate() > Robot.driveTrain.shiftUpRate){
    		Robot.driveTrain.shiftUp();
    	}
    	else{
    		Robot.driveTrain.shiftDown();
    	}
    	//
    	averager((float)-Robot.driveTrain.rightEncoder.getRate(), (float)Robot.driveTrain.leftEncoder.getRate());
    	
    	SmartDashboard.putNumber("Left Rate", Robot.driveTrain.leftEncoder.getRate());
    	SmartDashboard.putNumber("Right Rate", -Robot.driveTrain.rightEncoder.getRate());
    	SmartDashboard.putNumber("drive delta", Math.abs(Robot.driveTrain.leftAverage[30]) - Math.abs(Robot.driveTrain.rightAverage[30]));
    */
    }
    
    public void averager(float leftRate, float rightRate){
        /*
    	Robot.driveTrain.rightAverage[Robot.driveTrain.rightAverage.length - 1] = 0;
    	
    	for(int i = Robot.driveTrain.rightAverage.length - 2; i > 0; i--){ 
    		
    		
    		Robot.driveTrain.rightAverage[i] = Robot.driveTrain.rightAverage[i - 1];
    	
    		Robot.driveTrain.rightAverage[0] = rightRate;
        	
    		Robot.driveTrain.rightAverage[Robot.driveTrain.rightAverage.length - 1] += (Robot.driveTrain.rightAverage[i] ) / (Robot.driveTrain.rightAverage.length - 1);
    		}
    	
    	Robot.driveTrain.leftAverage[Robot.driveTrain.leftAverage.length - 1] = 0;
    	
    	for(int i = Robot.driveTrain.leftAverage.length - 2; i > 0; i--){ 
    		
    		
    		Robot.driveTrain.leftAverage[i] = Robot.driveTrain.leftAverage[i - 1];
    	
    		Robot.driveTrain.leftAverage[0] = leftRate;
        	
    		Robot.driveTrain.leftAverage[Robot.driveTrain.leftAverage.length - 1] += (Robot.driveTrain.leftAverage[i] ) / (Robot.driveTrain.leftAverage.length - 1);
    		}
    	*/
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
