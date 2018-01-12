package org.usfirst.frc.team3218.robot.commands.vision;

import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3218.robot.commands.vision.Pixy;

/**
 *
 */
public class NewTapeTracking extends Command {
	
	private int MAX_X = 280;
	private final int PIXY_MIN_X = 20;
	private final int PIXY_RANGE_X = MAX_X - PIXY_MIN_X;
	//private final int PIXY_CENTER_X = PIXY_RANGE_X/2;
	
	private static final byte[] DARK_ARRAY = new byte[] {(byte) 0xFE, 0x00, 0x1e};
	private int CLOSE_MIN_X = 110;
	private int CLOSE_MAX_X = 230;
	private int CLOSE_RANGE_X =  CLOSE_MAX_X - CLOSE_MIN_X;	
	
	private int PIXY_MIN_HEIGHT = 11;
	private int PIXY_MAX_HEIGHT = 150;
	private int PIXY_RANGE_HEIGHT = PIXY_MAX_HEIGHT - PIXY_MIN_HEIGHT;
	private int THRESHOLD = 50;
	public boolean closeEnough = false;
	
	double xPower;
	double yPower;
	public NewTapeTracking() {
		requires(Robot.driveTrain);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.autoDrive(0, 0);
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    		Robot.vision.pixyi2c.writeBulk(DARK_ARRAY); //make camera dark
    		
    		if(Robot.vision.wasUpdated[1] || Robot.vision.AverageHeight[1] > 0)
    		{
    			xPower = ((Robot.vision.AverageX[1]-PIXY_MIN_X)/(PIXY_RANGE_X) - .5) * 1.5; //
    			yPower = PIXY_MAX_HEIGHT /( 8 * Robot.vision.AverageHeight[1]);
    			System.out.println(PIXY_MAX_HEIGHT + "/(" + Robot.vision.AverageHeight[1]+")" + "= "+yPower);
    		
    			if(Robot.vision.AverageHeight[1] <= PIXY_MAX_HEIGHT - THRESHOLD) //if not out of range, it does the do
    			{
    			
    				Robot.driveTrain.autoDrive(-yPower, xPower);
    				SmartDashboard.putNumber("driveX", xPower);
    				SmartDashboard.putNumber("driveY", yPower); 
    				SmartDashboard.putNumber("tester", 1);
    		
    			}
    			
    			else if( Robot.vision.AverageHeight[1] >= PIXY_MAX_HEIGHT - THRESHOLD)
    			{
    				xPower = ((Robot.vision.AverageX[1] - CLOSE_MIN_X) / (CLOSE_RANGE_X) - .5) * 2.2;
    				yPower = PIXY_MAX_HEIGHT / (Robot.vision.AverageHeight[1] * 14);
    			if(Math.abs(xPower) < .6 && yPower < .1){
    				Robot.driveTrain.autoDrive(-yPower, xPower );
    				
    			}
    				SmartDashboard.putNumber("driveX", xPower);
    				SmartDashboard.putNumber("driveY", yPower);
    				SmartDashboard.putNumber("tester", 2);
    				
    			
    			}
    		
    		}
    	else{  //if none of the above statements are true, then set drive to zero
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
