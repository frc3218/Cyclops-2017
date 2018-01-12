package org.usfirst.frc.team3218.robot.commands.vision;

import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3218.robot.commands.vision.Pixy;

/**
 *
 */
public class TapeTracking extends Command {
	
	private int MAX_X = 280;
	private final int PIXY_MINN_X = 20;
	private final int PIXY_RANGE_X = MAX_X - PIXY_MINN_X;
	//private final int PIXY_CENTER_X = PIXY_RANGE_X/2;
	
	private int CLOSE_MAX_X = 200;
	private int CLOSE_MIN_X = 90;
	private int CLOSE_RANGE_X =  CLOSE_MAX_X - CLOSE_MIN_X;	
	
	private int PIXY_MAX_HEIGHT = 170;
	private int PIXY_MAX_Y = 50;
	private static int PIXY_MIN_Y = 20;
	private int PIXY_RANGE_Y = PIXY_MAX_Y - PIXY_MIN_Y;
	
	double xPower;
	double yPower;
	public TapeTracking() {
		requires(Robot.driveTrain);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	
    		SmartDashboard.putBoolean("Object Found", Robot.vision.wasUpdated[1]);
    		SmartDashboard.putNumber("gearX", Robot.vision.AverageX[1]);
    		System.out.println(Robot.vision.AverageX[1]);
    		
    		xPower = ((Robot.vision.AverageX[1]-PIXY_MINN_X)/(PIXY_RANGE_X) - .5) * 1.5;
    	
    		yPower = PIXY_RANGE_Y /( 2.3 * Robot.vision.AverageY[1]);
    
    		if(Robot.vision.wasUpdated[1] && Robot.vision.AverageY[1]>=PIXY_MIN_Y &&
    				Robot.vision.AverageY[1] < PIXY_MAX_Y-15 )
    		{
    			
    		Robot.driveTrain.autoDrive(-yPower, xPower);
    			SmartDashboard.putNumber("driveX", xPower);
    		SmartDashboard.putNumber("driveY", yPower);
    		SmartDashboard.putNumber("tester", 1);
    		
    		}
    		else if(Robot.vision.wasUpdated[1] && Robot.vision.AverageY[1] >= PIXY_MAX_Y-15){
    		
    			xPower = ((Robot.vision.AverageX[1]-CLOSE_MIN_X)/(CLOSE_RANGE_X) - .5) * 1.5;
    			
    			xPower = PIXY_MAX_HEIGHT / (Robot.vision.AverageHeight[1] * 6);
    			
    			Robot.driveTrain.autoDrive(-yPower, xPower );
    			SmartDashboard.putNumber("driveX", xPower);
    			SmartDashboard.putNumber("drivey", yPower);
    			SmartDashboard.putNumber("tester", 2);
    			
    		}
    		else{ 
    			Robot.driveTrain.autoDrive(0, 0);
    			SmartDashboard.putNumber("tester", 3);
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
