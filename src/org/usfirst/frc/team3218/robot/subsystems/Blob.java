package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.commands.vision.I2CPixy;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Blob {
	public int averageX = 0;
	public float averageY = 0;
	public float averageWidth = 0;
	public float averageHeight = 0;
	public char[] xSamples = new char[I2CPixy.SAMPLE_COUNT];
	public char[] ySamples = new char[I2CPixy.SAMPLE_COUNT];
	public char[] widthSamples = new char[I2CPixy.SAMPLE_COUNT];
	public char[] heightSamples = new char[I2CPixy.SAMPLE_COUNT];
	public boolean wasUpdated = false;//this allows you to tell if this blob was updated in the most recent frame
	public int lastIndex = 0;
}
