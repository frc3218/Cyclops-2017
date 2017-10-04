package org.usfirst.frc.team3218.robot.commands.auto;

import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoMethods 
{
	static double leftRate;
	static double rightRate;
	
	static int leftEncoderValue;
	static int rightEncoderValue;
	
	static final double RATE_DIVISOR = 100; //300; // normalizes encoder rate values
	
	static double straightPower;
	static double rateDifference;
	static double adjustmentPower = 0;
	
	static final double TICKS_PER_FOOT = 232;// 333.96 on school tile
	static final double TICKS_PER_INCH = TICKS_PER_FOOT / 12; //27.83 on school tile
	
	static final double CENTER_DISTANCE_TO_AIRSHIP = (85 * TICKS_PER_INCH);
	static final double SIDE_DISTANCE_TO_AIRSHIP = 86 * TICKS_PER_INCH;
	static final double SIDE_INITIAL_TURN = 14.37 * TICKS_PER_INCH;
	static final double SIDE_DISTANCE_TO_PEG = 25.69 * TICKS_PER_INCH;
	//boiler values require testing
	static final double BOILER_TURN = SIDE_INITIAL_TURN - 11.68 * TICKS_PER_INCH;
	static final double FINAL_BOILER_DRIVE = 104 * TICKS_PER_INCH;
	public static void calculateRate()
	{
		//leftRate = Math.abs(Robot.driveTrain.leftEncoder.getRate());
		//rightRate = Math.abs(Robot.driveTrain.rightEncoder.getRate());
		
		//adjustmentPower = (rightRate - leftRate) / RATE_DIVISOR; // divides the right encoder rate - left encoder rate by the RATE_DIVISOR
		leftEncoderValue = Math.abs(Robot.driveTrain.leftEncoder.get());
		rightEncoderValue = Math.abs(Robot.driveTrain.rightEncoder.get());
		
		adjustmentPower = (leftEncoderValue - rightEncoderValue) / RATE_DIVISOR;
		
	//	adjustmentPower *= Math.abs(straightPower) / straightPower;
		
	}
	
	public static boolean driveStraight(double distance, double power)
	{
		boolean x = false;
		straightPower = power;
		
		if(Math.abs(Robot.driveTrain.leftEncoder.get()) < distance 
				&& Math.abs(Robot.driveTrain.rightEncoder.get()) < distance)
		{
			if(Robot.auto)
			{
				calculateRate();
				
		    	SmartDashboard.putNumber("Left Value", Robot.driveTrain.leftEncoder.get());
		    	SmartDashboard.putNumber("Right Value", Robot.driveTrain.rightEncoder.get());
		    	
		    	SmartDashboard.putNumber("Left Rate", Robot.driveTrain.leftEncoder.getRate());
		    	SmartDashboard.putNumber("Right Rate", Robot.driveTrain.rightEncoder.getRate());
				
				System.out.println("LEFT VALUE: " + Robot.driveTrain.leftEncoder.get() + ", RIGHT VALUE: " + Robot.driveTrain.rightEncoder.get());
				System.out.println();
				System.out.println("LEFT RATE: " + Robot.driveTrain.leftEncoder.getRate() + ", RIGHT RATE: " + Robot.driveTrain.rightEncoder.getRate());
				System.out.println();
				System.out.println("ADJUSTMENT POWER: " + adjustmentPower);
				SmartDashboard.putNumber("adj", adjustmentPower);
				
				if(Math.abs(adjustmentPower) > Math.abs(straightPower))
				{
					Robot.driveTrain.autoDrive(power, 0);
				}
				else
				{
					Robot.driveTrain.autoDrive(power, adjustmentPower);
				}
			} // if robot.auto
			else
			{
				Robot.driveTrain.drive(0, 0);
				Robot.autonomousCommand.cancel();
				
			}
		}
		else
		{
			x = true;
		}
		
		return x;


	}
	
	public static void resetEncoders()
	{
		Robot.driveTrain.leftEncoder.reset();
		Robot.driveTrain.rightEncoder.reset();
	}
	
	public static void turnLeft(double distance, double power)
	{
		while(Math.abs(Robot.driveTrain.rightEncoder.get()) < distance)
		{
			if(Robot.auto)
			{
				Robot.driveTrain.autoDrive(0, -power);
			}
			else
			{
				Robot.driveTrain.drive(0, 0);
				Robot.autonomousCommand.cancel();
				break;
			}
		}
		resetEncoders();
	}
	
	public static void turnRight(double distance, double power)
	{
		while(Math.abs(Robot.driveTrain.leftEncoder.get()) < distance)
		{
			if(Robot.auto)
			{
				Robot.driveTrain.autoDrive(0, power);
			}
			else
			{
				Robot.driveTrain.drive(0, 0);
				Robot.autonomousCommand.cancel();
				break;
			}
		}
		resetEncoders();
	}
}
