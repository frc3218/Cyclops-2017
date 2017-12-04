package org.usfirst.frc.team3218.robot.subsystems;

import org.usfirst.frc.team3218.robot.RobotMap;
import org.usfirst.frc.team3218.robot.commands.vision.I2CPixy;
import org.usfirst.frc.team3218.robot.commands.vision.LightsOut;
import org.usfirst.frc.team3218.robot.commands.vision.USBCamera;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public float[] AverageX = new float[I2CPixy.MAX_SIGNATURES];
	public float[] AverageY = new float[I2CPixy.MAX_SIGNATURES];
	public float[] AverageHeight = new float[I2CPixy.MAX_SIGNATURES];
	public float[] AverageWidth = new float[I2CPixy.MAX_SIGNATURES];
	public boolean[] wasUpdated = new boolean[I2CPixy.MAX_SIGNATURES];
	private byte[] BrightnessArray = new byte[] {(byte) 0xFE, 0x00, 0x64};
	public I2C pixyi2c = new I2C(I2C.Port.kOnboard, 0x54);
	public SPI pixySPI = new SPI(SPI.Port.kOnboardCS0);
	public int tapeMinX = 130;
	public int tapeMaxX = 190;
	public int tapeRangeX = tapeMaxX - tapeMinX;
	//Relay lightSpike = new Relay(RobotMap.lightsPort);
	
	public void lightsOn(){
		
	//	lightSpike.set(Value.kOn);
		
	}

	public void lightsOut(){
		
	//	lightSpike.set(Value.kOff);
		
	}
	
	public void setBrightness(int brightness){
		
		BrightnessArray[2] = (byte) brightness;
		pixyi2c.writeBulk(BrightnessArray);
		
	}

    public void initDefaultCommand() {
    	
    	setDefaultCommand(new LightsOut());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

