package org.usfirst.frc.team3218.robot.commands.drivetrain;

import java.util.concurrent.Callable;

import org.usfirst.frc.team3218.robot.OI;
import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithJoystick extends Command {

	double y;
	double z;
	double deltaDrive;
	
	public DriveWithJoystick() {
        requires(Robot.driveTrain);   
        
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	y = Robot.oi.getJoystickY();
    	z = Robot.oi.getJoystickZ();
    	deltaDrive = (Math.abs(Robot.driveTrain.rightAverage[30]) - Math.abs(Robot.driveTrain.leftAverage[30]));
    	deltaDrive = Math.abs(deltaDrive);
    	if (Math.abs(y) > .4 && Math.abs(z)< .15 && Math.abs(deltaDrive) < 1000) {
			z += (Math.abs(y) * deltaDrive / 500);
		SmartDashboard.putNumber("z", z);
		SmartDashboard.putNumber("drive delta", deltaDrive);
		if(z>.7){
			z = .7;
		}
		
			Robot.driveTrain.driveStraight(y, z);
		
    	
    	} 
    	else{
    	Robot.driveTrain.drive(y, Robot.oi.getJoystickZ());
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
