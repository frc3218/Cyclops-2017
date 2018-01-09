package org.usfirst.frc.team3218.robot.commands.vision;

import org.usfirst.frc.team3218.robot.Robot;
import org.usfirst.frc.team3218.robot.subsystems.Blob;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class I2CPixy extends Command {

	public static final int MAX_SIGNATURES = 3; //TODO: make constants final
	public static final int MAX_OBJECTS = 4;
	public static final int SAMPLE_COUNT = 10;
	int maxBytes = 14 * MAX_OBJECTS + 2;
	
	char currentChecksum;
	char currentSig;
	char currentX;
	char currentY;
	char currentWidth;
	char currentHeight;
	
	//start of object sync: 0xaa55
	
	//boolean[] wasUpdated = new boolean[MAX_SIGNATURES];
	
	
	//[signature] [sampleCount]
	Blob blob = new Blob();
	public Blob[] blobArray = new Blob[MAX_SIGNATURES];
	
    public I2CPixy() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.vision);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	byte[] pixyValues = new byte[maxBytes];
    	Robot.vision.pixyi2c.readOnly(pixyValues, maxBytes);
	
	// set was updated array to false.
    	for(int i = 0; i<Robot.vision.wasUpdated.length; i++)  
    	{
    		blobArray[i].wasUpdated=false;
    	}
    	int i = 0;
    
	//checks if data has been put into storage array
    	if(pixyValues!=null)
	{
    		//search through data array to find object
    		while(littleEndianToBigEndian(pixyValues[i],pixyValues[i+1])!=0xaa55)
    		{
    			i++;
    			
			//if no object is found within the data exit the loop
    			if (i>maxBytes-2)
    			{
    				break;
    			}
    	}
	
    	i+=2;
	
	//if there is room for an object's data in the array and an object is found
    	if(i<maxBytes-14 && littleEndianToBigEndian(pixyValues[i],pixyValues[i+1])==0xaa55)
    	{
    		//segments chunks of object Data
    		for(;i < pixyValues.length-14; i+=14)	
    		{
			//checks for beginning of object
    			if(littleEndianToBigEndian(pixyValues[i],pixyValues[i+1]) == 0xaa55)  
    			{
    				//sets all variables for current object in for loop
    				currentChecksum = littleEndianToBigEndian(pixyValues[i + 2],pixyValues[i + 3]);
    				currentSig = littleEndianToBigEndian(pixyValues[i + 4],pixyValues[i + 5]);
    				currentX = littleEndianToBigEndian(pixyValues[i + 6],pixyValues[i + 7]);
    				currentY = littleEndianToBigEndian(pixyValues[i + 8],pixyValues[i + 9]);
    				currentWidth = littleEndianToBigEndian(pixyValues[i + 10],pixyValues[i + 11]); 
    				currentHeight = littleEndianToBigEndian(pixyValues[i + 12],pixyValues[i + 13]);
    				
				//checksum for one object
    				if( currentChecksum == (currentSig + currentX + currentY + currentWidth + currentHeight) && (currentChecksum > 0 )){//make sure data is good		
    				
    					int tempInt = currentSig;
    					SmartDashboard.putNumber("sig" , currentSig);   			
    					SmartDashboard.putNumber("X" +tempInt, blobArray[currentSig].averageX);
    			    	SmartDashboard.putNumber("Y" +tempInt, blobArray[currentSig].averageY);
    			    	SmartDashboard.putNumber("Width"+tempInt, blobArray[currentSig].averageWidth);
    			    	SmartDashboard.putNumber("Height"+tempInt, blobArray[currentSig].averageHeight);
    					
    					calculateAverage(currentX, currentY, currentWidth, currentHeight, blobArray[currentSig]);
    					
    				}//checksum if close				
    			}//check for object and succesful parse if close
    		}//for loop that segments object data close
    	}//if that checks for object in data close
    }//if the array has any data check close
  }//execute close

	/**
	 * this will calculate the average location & size over time of previous blob samples
	 * @param X: the horizontal position of the blob
	 * @param Y: the vertical position of the blob
	 * @param Width: the width of the blob
	 * @param Height: the height of the blob
	 */
	public void calculateAverage(char X, char Y, char Width, char Height, Blob blob) 
	{
		blob.lastIndex = ((blob.lastIndex+1) >= I2CPixy.SAMPLE_COUNT) ? 0 : blob.lastIndex + 1;
		SmartDashboard.putNumber("lastIndex",blob.lastIndex);
		//x position average
		blob.averageX += (float)X/I2CPixy.SAMPLE_COUNT;
		blob.averageX -= (float)blob.xSamples[blob.lastIndex]/I2CPixy.SAMPLE_COUNT;
		blob.xSamples[blob.lastIndex] = X;
	
		//y position average
		blob.averageY += (float)Y/I2CPixy.SAMPLE_COUNT;
		blob.averageY -= (float)blob.ySamples[blob.lastIndex]/I2CPixy.SAMPLE_COUNT;
		blob.ySamples[blob.lastIndex] = Y;
		
		//height average
		blob.averageHeight += (float)Height/I2CPixy.SAMPLE_COUNT;
		blob.averageHeight -= (float)blob.heightSamples[blob.lastIndex]/I2CPixy.SAMPLE_COUNT;
		blob.heightSamples[blob.lastIndex] = Height;
		
		//width average
		blob.averageWidth += (float)Width/I2CPixy.SAMPLE_COUNT;
		blob.averageWidth -= (float)blob.widthSamples[blob.lastIndex]/I2CPixy.SAMPLE_COUNT;
		blob.widthSamples[blob.lastIndex] = Width;
		blob.wasUpdated = true;		
	}
    private char littleEndianToBigEndian(byte one, byte two)
    {
    	return (char) (((two & 0xff) << 8) | (one & 0xff)); 
	    
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
