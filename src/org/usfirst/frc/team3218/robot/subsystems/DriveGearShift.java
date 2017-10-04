package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.gearshift.GearShifting;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveGearShift extends Subsystem {
	
	/*
	public Servo leftShiftServo = new Servo(RobotMap.leftShiftingServoMotorPort);
	public Servo rightShiftServo = new Servo(RobotMap.rightShiftingServoMotorPort);
	//public Encoder leftShiftEncoder = new Encoder(RobotMap.leftShiftEncoderPortA, RobotMap.leftShiftEncoderPortB);
	//public Encoder rightShiftEncoder = new Encoder(RobotMap.rightShiftEncoderPortA, RobotMap.rightShiftEncoderPortB);
	public String currentGear ="";
	public float countPerRotation = 4096; //The # of encoder accounts for 1 rotation.
	//Get some real Strings boiz
	public float shiftUpRate = 1000;
    public float shiftDownRate;

    public float rightRate;
    public float leftRate;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

*/
    public void shiftUp(){
/*
    		leftShiftServo.set(0);
    		rightShiftServo.set(0);
    		currentGear = "high";
    		SmartDashboard.putString("gear", currentGear);
    	*/
    }
    
    public void shiftDown(){
/*
    		leftShiftServo.set(1);
    		rightShiftServo.set(1);
    		currentGear = "low";
    		SmartDashboard.putString("gear", currentGear);
*/
    }
    
    public void initDefaultCommand() {
    	/*
    	setDefaultCommand(new GearShifting());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
         */
    }
}

