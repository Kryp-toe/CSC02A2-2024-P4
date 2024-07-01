/**
 * @author 222001975-Abdullah Mayet
 */
package acsse.csc2a.fmb.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rocket extends Firework
{
	private double Count;
	private double Radius;
	private E_COLOUR Colour;
	private Matcher dataMatcher;
	private Pattern dataPattern = Pattern.compile("(FR[0-9]{6})\\s(.*)\\s(\\d*\\.\\d{1,4})\\s(RED|YELLOW|ORANGE|GREEN|BLUE|MAGENTA|WHITE|CYAN)"
			+ "\\s([1-9]\\d*)\\s(\\d*\\.\\d{1,4})\\s(RED|YELLOW|ORANGE|GREEN|BLUE|MAGENTA|WHITE|CYAN)$");
	
	public Rocket() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public Rocket(String f_id, String f_name, double f_fuse, E_COLOUR f_colour, double count, double radius, E_COLOUR colour)
	{
		super(f_id,f_name,f_fuse,f_colour);
		this.Count = count;
		this.Radius = radius;
		this.Colour = colour;		
	}
	
	@Override
    public String toString()
	{
        return super.toString() + "RocketFirework{" +
                "rocketStarCount=" + this.Count +
                ", rocketStarRadius=" + this.Radius +
                ", rocketBlackPowderColour=" + this.Colour +
                "} ";
    }
	
	/**
	 * Prints the details of the Firework
	 * @return String value to display
	 */
	@Override
	public void display()
	{
		String outputString = super.toString() + this.toString();
		System.out.println(outputString);
	}
    
	@Override
	public boolean validate(String line)
	{
		//Process Line
		//(Fireworks-Rocket)RegEx: (FR[0-9]{6})\s(.*)\s(\d*\.\d{1,4})\s(RED|YELLOW|ORANGE|GREEN|BLUE|MAGENTA|WHITE|CYAN)\s([1-9]\d*)
		//                         \s(\d*\.\d{1,4})\s(RED|YELLOW|ORANGE|GREEN|BLUE|MAGENTA|WHITE|CYAN)
		//FR007609	Bouncing Betty	0.177	YELLOW	20	5.0	RED
		//Pattern
		//Create Matcher for specific Data Matcher
		this.dataMatcher = this.dataPattern.matcher(line);
				
		//Make sure the data matches
		if(this.dataMatcher.matches())
		{
			return true;
		}else 
		{
			System.err.println("Regular Expression was not a match!!");
			return false;
		}
		
	}
	
	@Override
    public Rocket processLine(String line)
	{
		//Declare variables to read fireworks
		String File_F_ID = null;
		String File_F_Name = null;
		double File_F_Fuse = 0;
		E_COLOUR File_F_Color = null;
		
		double File_FR_StarCount = 0;
		double File_FR_Radius = 0;
		E_COLOUR File_FR_BP_Colour = null;
		
		this.dataMatcher = this.dataPattern.matcher(line);
		
		//Data is Rocket
		if(validate(line))
		{
			//Variables to create Firework Instance
			// .group groups up the string according to the RegEx
			File_F_ID = dataMatcher.group(1);
			
			if (File_F_ID.startsWith("FR"))
			{
				File_F_Name = dataMatcher.group(2);
				File_F_Fuse = Double.valueOf(dataMatcher.group(3));
				File_F_Color = E_COLOUR.valueOf(dataMatcher.group(4));
				File_FR_StarCount = Double.valueOf(dataMatcher.group(5));
				File_FR_Radius = Double.valueOf(dataMatcher.group(6));
				File_FR_BP_Colour = E_COLOUR.valueOf(dataMatcher.group(7));
				
				//Create firework instance
				return new Rocket(File_F_ID, File_F_Name, File_F_Fuse, File_F_Color, File_FR_StarCount, File_FR_Radius, File_FR_BP_Colour);
			}
		}		
		System.err.println("Regular Expression was not a match! \n"
					+ "Could not create Firework (Rocket) Instance!!");
		return null;
    }
	
	public E_COLOUR getColour()
	{
		return this.Colour;
	}
	
	public double getCount()
	{
		return this.Count;
	}
	
	public double getRadius()
	{
		return this.Radius;
	}
	
	public void setColour(E_COLOUR colour)
	{
		this.Colour = colour;
	}
	
	public void setCount(int count)
	{
		this.Count = count;
	}
	
	public void setRadius(int radius)
	{
		this.Radius = radius;
	}
}






















