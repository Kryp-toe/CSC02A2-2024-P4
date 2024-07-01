/**
 * @author 222001975-Abdullah Mayet
 */
package acsse.csc2a.fmb.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fountain extends Firework
{
	private double Duration;
	private String Colours;
	private Matcher dataMatcher;
	private Pattern dataPattern = Pattern.compile("(FF[0-9]{6})\\s(.*)\\s(\\d*\\.\\d{1,4})\\s(RED|YELLOW|ORANGE|GREEN|BLUE|MAGENTA|WHITE|CYAN)"
			+ "\\s(([1-9]\\d*)|[1-9]\\d*\\.\\d{1,4})\\s(\\[(RED|YELLOW|ORANGE|GREEN|BLUE|MAGENTA|WHITE|CYAN)"
			+ "\\|(RED|YELLOW|ORANGE|GREEN|BLUE|MAGENTA|WHITE|CYAN)\\])$");
	
	public Fountain()
	{
		// TODO Auto-generated constructor stub
	}
	
	public Fountain(String f_id, String f_name, double f_fuse, E_COLOUR f_colour, double duration, String colours)
	{
		super(f_id,f_name,f_fuse,f_colour);
		this.Duration = duration;
		this.Colours = colours;
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
    public String toString()
	{
        return super.toString() + "FountainFirework{" +
                "fountainDuration=" + this.Duration +
                ", fountainTransitionColours=" + this.Colours +
                "} ";
    }
	
	@Override
	public boolean validate(String line)
	{
		//(Fireworks-Fountain)RegEx: (FF[0-9]{6})\s(.*)\s(\d*\.\d{1,4})\s(RED|YELLOW|ORANGE|GREEN|BLUE|MAGENTA|WHITE|CYAN)
		//                             \s(([1-9]\d*)|[1-9]\d*\.\d{1,4})\s(\[(RED|YELLOW|ORANGE|GREEN|BLUE|MAGENTA|WHITE|CYAN)
		//                                \|(RED|YELLOW|ORANGE|GREEN|BLUE|MAGENTA|WHITE|CYAN)\])$
		//FF007600	Bouncing Betty	1.2	YELLOW	20	[YELLOW|CYAN]
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
    public Fountain processLine(String line)
	{
		//Declare variables to read fireworks
		String File_F_ID = null;
		String File_F_Name = null;
		double File_F_Fuse = 0;
		E_COLOUR File_F_Color = null;
		
		double File_FT_Duration = 0;
		String File_FT_Transition_Colours = null;
		
		this.dataMatcher = this.dataPattern.matcher(line);
		
		//Data is Rocket
		if(validate(line))
		{
			//Variables to create Firework Instance
			// .group groups up the string according to the RegEx
			File_F_ID = dataMatcher.group(1);
			
			if (File_F_ID.startsWith("FF"))
			{
				File_F_Name = dataMatcher.group(2);
				File_F_Fuse = Double.valueOf(dataMatcher.group(3));
				File_F_Color = E_COLOUR.valueOf(dataMatcher.group(4));
				File_FT_Duration = Double.valueOf(dataMatcher.group(5));
				File_FT_Transition_Colours = dataMatcher.group(6);
				
				//Create firework instance
				return new Fountain(File_F_ID, File_F_Name, File_F_Fuse, File_F_Color, File_FT_Duration, File_FT_Transition_Colours);
			}
		}
		System.err.println("Regular Expression was not a match! \n"
							+ "Could not create Firework (Fountain) Instance!!");
		return null;
    }
	
	public double getDuration()
	{
		return this.Duration;
	}
	
	public void setDuration(int duration)
	{
		this.Duration = duration;
	}
	
	public String getColours()
	{
		return this.Colours;
	}
	
	public void setColour(String colours)
	{
		this.Colours = colours;
	}
}
