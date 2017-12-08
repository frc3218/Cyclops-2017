package org.usfirst.frc.team3218.robot.commands.vision;

import org.usfirst.frc.team3218.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class I2CPixy extends Command {

	public static final int MAX_SIGNATURES = 3; //TODO: make constants final
	final int MAX_OBJECTS = 4;
	int sampleCount = 10;
	int maxBytes = 14 * MAX_OBJECTS + 2;
	int[] lastIndex = new int[MAX_SIGNATURES];
	
	char currentChecksum;
	char currentSig;
	char currentX;
	char currentY;
	char currentWidth;
	char currentHeight;
	
	//start of object sync: 0xaa55
	
	//boolean[] wasUpdated = new boolean[MAX_SIGNATURES];
	
	float[] averageX = new float[MAX_SIGNATURES];
	float[] averageY = new float[MAX_SIGNATURES];
	float[] averageWidth = new float[MAX_SIGNATURES];
	float[] averageHeight = new float[MAX_SIGNATURES];
	
	//[signature] [sampleCount]
	char[][] xSamplerArray = new char[MAX_SIGNATURES][sampleCount];
	char[][] ySamplerArray = new char[MAX_SIGNATURES][sampleCount];
	char[][] widthSamplerArray = new char[MAX_SIGNATURES][sampleCount];
	char[][] heightSamplerArray = new char[MAX_SIGNATURES][sampleCount];
	
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
    	
    	//System.out.println("I2CPixy in" + System.currentTimeMillis());
    	byte[] pixyValues = new byte[maxBytes];
    	Robot.vision.pixyi2c.readOnly(pixyValues, maxBytes);
    	for(int i = 0; i<Robot.vision.wasUpdated.length; i++) // set was updated array to false. 
    	{
    		Robot.vision.wasUpdated[i]=false;
    	}
    	int i = 0;
    
    	if(pixyValues!=null){
    		
    	while(littleEndianToBigEndian(pixyValues[i],pixyValues[i+1])!=0xaa55)
    	{
    		i++;
    	
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
    					SmartDashboard.putNumber("X" +tempInt, averageX[currentSig]);
    			    		SmartDashboard.putNumber("Y" +tempInt, averageY[currentSig]);
    			    		SmartDashboard.putNumber("Width"+tempInt, averageWidth[currentSig]);
    			    		SmartDashboard.putNumber("Height"+tempInt, averageHeight[currentSig]);
    					
    					calculateAverage(currentSig, currentX, currentY, currentWidth, currentHeight); 
    					
    				}//checksum if close				
    			}//check for object and succesful parse if close
    		}//for loop that segments object data close
    	}//
    		}
    }//execute close

    private char littleEndianToBigEndian(byte one, byte two)
    {
    	return (char) (((two & 0xff) << 8) | (one & 0xff)); 
	    
    }
    
	protected void calculateAverage(char sig, char X, char Y, char Width, char Height) //TODO: make this work with different length arrays
	{
		
		lastIndex[sig] = ((lastIndex[sig]+1) >= sampleCount) ? 0 : lastIndex[sig]+1;
		SmartDashboard.putNumber("lastIndex",lastIndex[sig]);
		//x position average
		averageX[sig] += (float)X/sampleCount;
		averageX[sig] -= (float)xSamplerArray[sig][lastIndex[sig]]/sampleCount;
		xSamplerArray[sig][lastIndex[sig]] = X;
		Robot.vision.AverageX[sig]=averageX[sig];
	
		//y position average
		averageY[sig] += (float)Y/sampleCount;
		averageY[sig] -= (float)ySamplerArray[sig][lastIndex[sig]]/sampleCount;
		ySamplerArray[sig][lastIndex[sig]] = Y;
		Robot.vision.AverageY[sig]=averageY[sig];
		
		//height average
		averageHeight[sig] += (float)Height/sampleCount;
		averageHeight[sig] -= (float)heightSamplerArray[sig][lastIndex[sig]]/sampleCount;
		heightSamplerArray[sig][lastIndex[sig]] = Height;
		Robot.vision.AverageHeight[sig]=averageHeight[sig];
		
		//width average
		averageWidth[sig] += (float)Width/sampleCount;
		averageWidth[sig] -= (float)widthSamplerArray[sig][lastIndex[sig]]/sampleCount;
		widthSamplerArray[sig][lastIndex[sig]] = Width;
		Robot.vision.AverageWidth[sig]=averageWidth[sig];
		Robot.vision.wasUpdated[sig] = true;
		
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
