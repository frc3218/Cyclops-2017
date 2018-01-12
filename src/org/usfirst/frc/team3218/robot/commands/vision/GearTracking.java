package org.usfirst.frc.team3218.robot.commands.vision;

import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3218.robot.commands.vision.Pixy;

/**
 *
 */
public class GearTracking extends Command {
	
	public int MAX_X = 260;
	public final int PIXY_MINN_X = 20;
	private final int PIXY_RANGE_X = MAX_X - PIXY_MINN_X;
	//private final int PIXY_CENTER_X = PIXY_RANGE_X/2;
	private int CLOSE_MAX_X = 200;
	private int CLOSE_MIN_X = 90;
	private int CLOSE_RANGE_X =  CLOSE_MAX_X - CLOSE_MIN_X;	
	public int PIXY_MAX_Y = 190;
	public static int PIXY_MIN_Y = 20;
	private int PIXY_RANGE_Y = PIXY_MAX_Y - PIXY_MIN_Y;
	final byte[] LIGHT_ARRAY = new byte[] {(byte) 0xFE, 0x00, 0x64};
	double xPower;
	double yPower;
	public GearTracking() {
		requires(Robot.driveTrain);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.vision.pixyi2c.writeBulk(LIGHT_ARRAY);
    	Robot.driveTrain.autoDrive(0, 0);
    		SmartDashboard.putBoolean("Object Found", Robot.vision.wasUpdated[2]);
    		SmartDashboard.putNumber("gearX", Robot.vision.AverageX[2]);
    		System.out.println( Robot.vision.AverageX[2]);
    		
    		xPower = ((Robot.vision.AverageX[2]-PIXY_MINN_X)/(PIXY_RANGE_X) - .5) * 1.5;
    	
    		yPower = PIXY_RANGE_Y /( 2.3 * Robot.vision.AverageY[2]);
    
    		if(Robot.vision.wasUpdated[2] && Robot.vision.AverageY[2]>=PIXY_MIN_Y &&
    				Robot.vision.AverageY[2] < PIXY_MAX_Y-15 )
    		{
    			
    		Robot.driveTrain.autoDrive(-yPower, -xPower);
    			SmartDashboard.putNumber("driveX", xPower);
    		SmartDashboard.putNumber("driveY", yPower);
    		SmartDashboard.putNumber("tester", 1);
    		}
    		else if(Robot.vision.wasUpdated[2] && Robot.vision.AverageY[2] >= PIXY_MAX_Y-15){
    			xPower = ((Robot.vision.AverageX[2]-CLOSE_MIN_X)/(CLOSE_RANGE_X) - .5) * 1.5;
    			Robot.driveTrain.autoDrive(0, -xPower );
    			SmartDashboard.putNumber("driveX", xPower);
    			SmartDashboard.putNumber("tester", 2);
    		}
    		else{ 
    			Robot.driveTrain.autoDrive(0, 0);
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
