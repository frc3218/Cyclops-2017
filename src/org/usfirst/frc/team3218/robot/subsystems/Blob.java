package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.commands.vision.Pixy;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Blob {
	public float averageX = 0;
	public float averageY = 0;
	public float averageWidth = 0;
	public float averageHeight = 0;
	public boolean wasUpdated = false;//this allows you to tell if this blob was updated in the most recent frame
	public int lastIndex = 0;
}
