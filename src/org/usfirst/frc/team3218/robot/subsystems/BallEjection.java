package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.ballcollection.BallCollectionOff;
import org.usfirst.frc.team3218.robot.commands.ballejection.StopBallEjection;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallEjection extends Subsystem {
	
	CANTalon ballEjectionMotor = new CANTalon(RobotMap.ballEjectionMotorPort);
    
	public void startEjectionMotor(){
		
		ballEjectionMotor.set(-.75);
		
	}
	
	public void stopEjectionMotor(){
		
		ballEjectionMotor.set(0);
		
	}
	
public void reverseEjectionMotor(){
		
		ballEjectionMotor.set(.7);
		
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new StopBallEjection());
    }
}

