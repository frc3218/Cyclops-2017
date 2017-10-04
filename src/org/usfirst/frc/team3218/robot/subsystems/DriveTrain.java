package org.usfirst.frc.team3218.robot.subsystems;

import java.nio.DoubleBuffer;
import java.time.Year;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.drivetrain.DriveWithJoystick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	//Driving
	public CANTalon frontLeftMotor = new CANTalon(RobotMap.frontLeftMotorPort);
	public CANTalon frontRightMotor = new CANTalon(RobotMap.frontRightMotorPort);
	public CANTalon backLeftMotor = new CANTalon(RobotMap.backLeftMotorPort);
	public CANTalon backRightMotor = new CANTalon(RobotMap.backRightMotorPort);
	RobotDrive drive = new RobotDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);
	public Encoder leftEncoder = new Encoder(RobotMap.leftShiftEncoderPortA, RobotMap.leftShiftEncoderPortB);
	public Encoder rightEncoder = new Encoder(RobotMap.rightShiftEncoderPortA, RobotMap.rightShiftEncoderPortB);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	int arrlength =31;
	public float[] leftAverage = new float[arrlength];
	public float[] rightAverage = new float[arrlength];
	
	
	public DriveTrain(){
	//all motors are backwards
		frontLeftMotor.setInverted(true);
		backLeftMotor.setInverted(true);
		frontRightMotor.setInverted(true);
		backRightMotor.setInverted(true);
	}
	
	public void drive(double y, double z ) {
		drive.arcadeDrive(y*.75, z*.75);
		
	}
	
	public void autoDrive (double y, double z){
		drive.arcadeDrive(y, z);
		
	}
	public void driveStraight(double y, double z){
		drive.arcadeDrive( y, z);
		
	}
	public void autoStraightDrive(double y, double z){
		if(Math.abs(leftAverage[arrlength-1]) > Math.abs(rightAverage[arrlength-1])){
			z = (Math.abs(leftAverage[arrlength-1]) - Math.abs(rightAverage[arrlength-1]))/150;
			drive.arcadeDrive(y, -z);	
			}
		else{
			drive.arcadeDrive(y,0);	
		}
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoystick());
    }
    
}

