package org.usfirst.frc.team3218.robot.commands.vision;

import org.usfirst.frc.team3218.robot.subsystems.Blob;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionExample {
	//find if an object is in the center of the screen
	public VisionExample()
	{
		Pixy pixy = new Pixy();
		Blob blob = pixy.blobArray[0];
		SmartDashboard.putNumber("Pixy X:", blob.averageX);
		SmartDashboard.putNumber("Pixy Y:", blob.averageY);
		SmartDashboard.putNumber("Pixy Width:", blob.averageWidth);
		SmartDashboard.putNumber("Pixy Height:", blob.averageHeight);
	}
}
