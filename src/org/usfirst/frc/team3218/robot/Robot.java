
package org.usfirst.frc.team3218.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.hal.PDPJNI;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.usfirst.frc.team3218.robot.commands.ExampleCommand;
import org.usfirst.frc.team3218.robot.commands.auto.BoilerLeft;
import org.usfirst.frc.team3218.robot.commands.auto.BoilerRight;
import org.usfirst.frc.team3218.robot.commands.auto.Center;
import org.usfirst.frc.team3218.robot.commands.auto.Left;
import org.usfirst.frc.team3218.robot.commands.auto.Right;
import org.usfirst.frc.team3218.robot.commands.ballejection.StartBallEjection;
import org.usfirst.frc.team3218.robot.commands.gear.GearArmControl;
import org.usfirst.frc.team3218.robot.commands.gear.GearCollectionGrab;
import org.usfirst.frc.team3218.robot.commands.vision.Pixy;
import org.usfirst.frc.team3218.robot.subsystems.BallCollection;
import org.usfirst.frc.team3218.robot.subsystems.BallEjection;
import org.usfirst.frc.team3218.robot.subsystems.Climbing;
import org.usfirst.frc.team3218.robot.subsystems.DriveGearShift;
import org.usfirst.frc.team3218.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3218.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team3218.robot.subsystems.GearCollection;
import org.usfirst.frc.team3218.robot.subsystems.Vision;
import org.usfirst.frc.team3218.robot.subsystems.USB;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {

	public static Vision vision;
	public static DriveGearShift driveGearShift;
	public static GearCollection gearColletion;
	public static BallEjection ballEjection;
	public static BallCollection ballCollection;
	public static Climbing climbing;
	public static DriveTrain driveTrain;
	public static USB USB;
	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	public static boolean auto;
	public Object Left;
	public static final String CENTER = "Center";
	public static final String LEFT = "Left";
	public static final String RIGHT = "Right";
	public static final String BOILER_LEFT = "BoilerLeft";
	public static final String BOILER_RIGHT = "BoilerRight";

	public static Command autonomousCommand;
	SendableChooser autochooser;
	public static String autoMode;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */

	public void robotInit() {

		vision = new Vision();
		driveGearShift = new DriveGearShift();
		gearColletion = new GearCollection();
		ballEjection = new BallEjection();
		ballCollection = new BallCollection();
		climbing = new Climbing();
		driveTrain = new DriveTrain();
		USB = new USB();
		oi = new OI();
		autochooser = new SendableChooser();
		autochooser.addDefault("Default Auto", new ExampleCommand());
		Robot.gearColletion.gearLiftMotor.setEncPosition(0);
		autochooser.addObject(CENTER, new Center());
		//autochooser.addObject("SimpleCenter", new SimpleCenter());
		autochooser.addObject(LEFT, new Left());
		autochooser.addObject(RIGHT, new Right());
		autochooser.addObject(BOILER_LEFT, new BoilerLeft());
		autochooser.addObject(BOILER_RIGHT, new BoilerRight());

		//autochooser.addObject("Straight", new Straight());
		//autochooser.addObject("Nothing", new Nothing());
		SmartDashboard.putData("Auto mode", autochooser);

	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */

	public void disabledInit() {

	}

	public void disabledPeriodic() {

		Scheduler.getInstance().run();

	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */

	public void autonomousInit() {

		Robot.driveTrain.leftEncoder.reset();
		Robot.driveTrain.rightEncoder.reset();
		SmartDashboard.putNumber("Left Encoder", Robot.driveTrain.leftEncoder.get());
		SmartDashboard.putNumber("Right Encoder", -Robot.driveTrain.rightEncoder.get());
		auto = true;
		autoMode = autochooser.getSelected().toString();
		autonomousCommand = (Command)autochooser.getSelected();
		/*
		if(autochooser.getSelected() != "Defualt Auto" || autochooser.getSelected() != "Nothing" ){
		autonomousCommand = new AutoDrive();
		}
		*/
		// schedule the autonomous command (example)

		if (autonomousCommand != null)
			autonomousCommand.start();
		new Pixy().start();
	}

	/**
	 * This function is called periodically during autonomous
	 */

	public void autonomousPeriodic() {

		Scheduler.getInstance().run();
		SmartDashboard.putData(vision);
		SmartDashboard.putData(driveTrain);
		SmartDashboard.putNumber("Left Encoder", Robot.driveTrain.leftEncoder.get());
		SmartDashboard.putNumber("Right Encoder", -Robot.driveTrain.rightEncoder.get());
		SmartDashboard.putData(gearColletion);
		SmartDashboard.putNumber("Desired Position", GearArmControl.desiredPosition);
		SmartDashboard.putNumber("Throttle Value", Robot.oi.getJoystickThrottle());
		SmartDashboard.putNumber("Gear Arm Encoder", GearArmControl.currentPosition);
		SmartDashboard.putBoolean("dontHaveGear", GearCollection.dontHasGear.get());
		SmartDashboard.putNumber("arm power", Robot.gearColletion.armMotorSpeed);
		
		//SmartDashboard.putBoolean("forward", forward);
	}

	public void teleopInit() {
		auto = false;
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		/*
		 * driveTrain.backRightMotor.enableBrakeMode(false);
		 * driveTrain.backLeftMotor.enableBrakeMode(false);
		 * driveTrain.frontRightMotor.enableBrakeMode(false);
		 * driveTrain.frontRightMotor.enableBrakeMode(false);
		 */
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		Robot.gearColletion.gearLiftMotor.setEncPosition(0);

	}

	/**
	 * This function is called periodically during operator control
	 */

	public void teleopPeriodic() {

		Scheduler.getInstance().run();
		SmartDashboard.putData(gearColletion);
		SmartDashboard.putNumber("Desired Position", GearArmControl.desiredPosition);
		SmartDashboard.putNumber("Throttle Value", Robot.oi.getJoystickThrottle());
		SmartDashboard.putNumber("Gear Arm Encoder", GearArmControl.currentPosition);
		SmartDashboard.putBoolean("dontHaveGear", GearCollection.dontHasGear.get());
		SmartDashboard.putNumber("arm power", Robot.gearColletion.armMotorSpeed);
		SmartDashboard.putData(vision);
		SmartDashboard.putData(driveTrain);
		SmartDashboard.putNumber("driveY", oi.getJoystickY());
		SmartDashboard.putNumber("power", ballCollection.PDP.getVoltage());
		SmartDashboard.putNumber("Left Encoder", Robot.driveTrain.leftEncoder.get());
		SmartDashboard.putNumber("Right Encoder", -Robot.driveTrain.rightEncoder.get());
		
		//System.out.print("LEFT ENCODER TELE: " + driveTrain.leftEncoder.get() + " - RIGHT ENCODER TELE:" + driveTrain.rightEncoder.get());
	} 

	/**
	 * This function is called periodically during test mode
	 */

	public void testPeriodic() {

		LiveWindow.run();

	}
}
