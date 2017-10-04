package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.commands.vision.USBCamera;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class USB extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new USBCamera());
    }
}

