package org.usfirst.frc.team3218.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
	//spooky
	
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	//DIO
	
	
	public static int climbEncoderPortA = 4;
	public static int climbEncoderPortB = 5;
	
	public static int hasGear = 3;
	

	public static int leftShiftEncoderPortA = 6;
	public static int leftShiftEncoderPortB = 7;
	public static int rightShiftEncoderPortA = 1;
	public static int rightShiftEncoderPortB = 2;
	
	
	//PWM
	
	public static int leftShiftingServoMotorPort = 0;
	public static int rightShiftingServoMotorPort = 5;


	public static int gearCollectionMotorPortRight = 1;
	public static int gearCollectionMotorPortLeft = 2;
	
	public static int climbMotorPortRight = 3;
	
	
	public static int ballCollectionMotorPort = 4; 
		
	//CANBUS
	public static int ballEjectionMotorPort = 5;
	public static int gearLiftMotorPort = 3;
	
	public static int frontLeftMotorPort = 7;
	public static int frontRightMotorPort = 8;
	public static int backLeftMotorPort = 9;
	public static int backRightMotorPort = 10;

	//Quarantine
	//public static int ballCollectionMotorPort = 4; 
	//public static int gearCollectionMotorPortLeft = 2;
	//public static int gearCollectionMotorPortRight = 1;
	//public static int gearLiftMotorPort = 3;
	//Quarantine
	
	
	
	
}
