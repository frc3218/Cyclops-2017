package org.usfirst.frc.team3218.robot.commands.vision;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class USBCamera extends Command {

	 CameraServer server0;
	 CameraServer server1;
    public USBCamera() {
    	requires(Robot.USB);
    	try{
   		 server0 = CameraServer.getInstance(); 
   		 server0.startAutomaticCapture("cam0", 0);
   	}catch(Exception e){
   		
   	}	try{
  		 server1 = CameraServer.getInstance(); 
  		 server1.startAutomaticCapture("cam1", 1);
  	}catch(Exception e){
  		
  	}
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
