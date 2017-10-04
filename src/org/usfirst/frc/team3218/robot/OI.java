package org.usfirst.frc.team3218.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import java.security.PublicKey;

import org.usfirst.frc.team3218.robot.commands.ExampleCommand;
import org.usfirst.frc.team3218.robot.commands.ballcollection.BallCollectionOn;
import org.usfirst.frc.team3218.robot.commands.ballejection.EjectBalls;
import org.usfirst.frc.team3218.robot.commands.ballejection.StartBallEjection;
import org.usfirst.frc.team3218.robot.commands.climbing.BongoClimbStart;
import org.usfirst.frc.team3218.robot.commands.climbing.BongoClimbStop;
import org.usfirst.frc.team3218.robot.commands.climbing.ClimbingOverride;
import org.usfirst.frc.team3218.robot.commands.climbing.ForwardClimbing;
import org.usfirst.frc.team3218.robot.commands.climbing.ReverseClimbing;
import org.usfirst.frc.team3218.robot.commands.gear.ArmOverride;
import org.usfirst.frc.team3218.robot.commands.gear.GearArmDown;
import org.usfirst.frc.team3218.robot.commands.gear.GearArmUp;
import org.usfirst.frc.team3218.robot.commands.gear.GearCollectionGrab;
import org.usfirst.frc.team3218.robot.commands.gear.GearCollectionRelease;
import org.usfirst.frc.team3218.robot.commands.gear.GearCollectionStop;
import org.usfirst.frc.team3218.robot.commands.gearshift.testDown;
import org.usfirst.frc.team3218.robot.commands.gearshift.testUp;
import org.usfirst.frc.team3218.robot.commands.vision.GearTracking;
import org.usfirst.frc.team3218.robot.commands.vision.I2CPixy;
import org.usfirst.frc.team3218.robot.commands.vision.LightsOn;
import org.usfirst.frc.team3218.robot.commands.vision.NewTapeTracking;
import org.usfirst.frc.team3218.robot.commands.vision.TapeTracking;
import org.usfirst.frc.team3218.robot.subsystems.BallEjection;
import org.usfirst.frc.team3218.robot.subsystems.GearCollection;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static Joystick stick = new Joystick(0);
	public static Joystick bongo = new Joystick(2);
	
	public static Button button1 = new JoystickButton(stick, 1);
	public static Button button2 = new JoystickButton(stick, 2);
	public static Button button3 = new JoystickButton(stick, 3);
	public static Button button4 = new JoystickButton(stick, 4);
	public static Button button5 = new JoystickButton(stick, 5);
	public static Button button6 = new JoystickButton(stick, 6);
	public static Button button7 = new JoystickButton(stick, 7);
	public static Button button8 = new JoystickButton(stick, 8);
	public static Button button9 = new JoystickButton(stick, 9);
	public static Button button10 = new JoystickButton(stick, 10);
	public static Button button11 = new JoystickButton(stick, 11);
	public static Button button12 = new JoystickButton(stick, 12);
	
	// Start button should be facing the driver
	// The small buttons are closest to to driver
	// The big buttons are farthest away from the driver
	public static Button bongoLeftBig = new JoystickButton(bongo, 4);
	public static Button bongoLeftSmall = new JoystickButton(bongo, 3);
	public static Button bongoRightBig = new JoystickButton(bongo, 1);
	public static Button bongoRightSmall = new JoystickButton(bongo, 2);


	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	public OI() {

		// button1.whileHeld(new LightsOn());
		bongoRightBig.whenPressed(new BongoClimbStart());
		bongoRightSmall.whenPressed(new BongoClimbStop());

		button1.whileHeld(new I2CPixy());
		button1.whileHeld(new GearTracking());

	//	button2.whileHeld(new I2CPixy());
	//	button2.whileHeld(new NewTapeTracking());
		// button2.whileHeld(new TapeTracking());
		button2.whenPressed(new GearCollectionStop());

		button6.whileHeld(new GearArmUp());
		button4.whileHeld(new GearArmDown());
		button7.whenPressed(new ArmOverride());

		button5.toggleWhenPressed(new GearCollectionRelease());
		button3.toggleWhenPressed(new GearCollectionGrab());

		//button6.whenPressed(new EjectBalls());
		//button4.toggleWhenPressed(new BallCollectionOn());

		button11.whileHeld(new ForwardClimbing());
		button12.whileHeld(new ClimbingOverride());
		//button9.whileHeld(new ForwardClimbing());
		
		// button10.whileHeld(new ReverseClimbing());
		// button10.whenPressed(new testUp());

	}

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	public double getJoystickY() {
		// returns negative because forwards is backwards
		return -stick.getY();

	}

	public double getJoystickZ() {
		// returns negative because forwards is backwards
		return -stick.getZ();

	}

	public double getJoystickThrottle() {

		return (stick.getThrottle() * -1 + 1) / 2;

	}

}
