/**
 * @author 222001975-Abdullah Mayet
 */
package acsse.csc2a.fmb.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A Class which holds information of individual Firework Displays
 */
public class FireworkDisplay implements IDisplayable
{
	//attributes
	private String displayID; 				// a unique alphanumeric ID of the Firework Display
	private String displayName; 			// The name of the Firework Display
	private String displayTheme;			// The theme of the Firework Display
	private PyroTechnician leadTechnician;	// The lead PyroTechnician
	private Firework[] fireworks;			// The fireworks that make up the display
	private Matcher dataMatcher;
	private Pattern dataPattern = Pattern.compile("^((FD[0-9]{4})\\s(\\[[^\\s].*?[^\\s]\\])\\s(\\\"[^\\s].*?[^\\s]\\\"))$");
	
	/**
	 * A default constructor to populate the object with default values
	 */
	public FireworkDisplay()
	{
		this("NoID", "NoName", "NoTheme", null);
	}
	
	/**
	 * A Constructor to build a Firework Display
	 * @param displayID  a unique alphanumeric ID of the Firework Display
	 * @param displayName The name of the Firework Display
	 * @param displayTheme The theme of the Firework Display
	 * @param leadTechnician The lead PyroTechnician
	 */
	public FireworkDisplay(String displayID, String displayName, String displayTheme, PyroTechnician leadTechnician)
	{
		this.displayID = displayID;
		this.displayName = displayName;
		this.displayTheme = displayTheme;
		this.leadTechnician = leadTechnician;
		this.fireworks = new Firework[0];
	}
	
	/**
	 * A method to add a Firework to the Firework Display
	 * @param firework The Firework to add to the display
	 */
	public void addFireWork(Firework firework)
	{
		//resize the array
		growArray();
		fireworks[fireworks.length -1] = firework;
	}
	
	/**
	 * A method to remove a Firework from the Firework Display
	 */
	public void removeFireWork()
	{
		shrinkArray();
	}
	
	/**
	 * A helper method to grow our Firework Array
	 */
	private void growArray()
	{
		//Resize the teamMembers array to be one larger
		Firework[] tempArr = new Firework[fireworks.length + 1]; 
		//copy the elements over
		System.arraycopy(fireworks, 0, tempArr, 0, fireworks.length);
		//reassign the instance reference to point to the newly created temp array
		fireworks = tempArr;
	}
	
	/**
	 * A helper method to shrink the Firework Array
	 */
	private void shrinkArray()
	{
		//ensure the array has elements
		if(fireworks.length > 0)
		{
			//create the temporary array 1 less than the current array's size
			Firework[] tempArr = new Firework[fireworks.length - 1];
			//copy the elements over
			System.arraycopy(fireworks, 0, tempArr, 0, fireworks.length - 1);
			//reassign the instance reference to point to the newly created temp array
			fireworks = tempArr;
		}
	}
	
	/**
	 * A method to print the Contents of the Firework Display
	 * @return String value to display
	 */
	@Override
	public String toString()
	{
		String pyroTechString = leadTechnician.toString();
		String outputString = "---------------------------------------- /\n"
				+ " ~~~~~~~~~~~ " + displayName + " ~~~~~~~~~~~~~~\n"
						+ "\t\tID: " + displayID + "\n"
						+ "\tTheme: \" " + displayTheme + " \"!\n"
				+ "- - - - - - Orchestrated By  - - - - - -\n(PT) "
				+ pyroTechString
				+ "\n- - - - - - Fireworks - - - - - -";
		//Print each Firework's info
		int counter = 0;
		
		  for(Firework f : fireworks)
		  {
			  outputString += "(%d) " + counter;
			  outputString += f.toString();
			  outputString += "\n"; counter++;
		  }
		 
		outputString += "---------------------------------------";
		return outputString;
	}
	
	@Override
	public void display()
	{
		System.out.println(this.toString());
	}
	
	public boolean validate(String line)
	{
		//Process Line
		//(Firework Display)RegEx: ^((FD[0-9]{4})\s(\[[^\s].*?[^\s]\])\s(\"[^\s].*?[^\s]\"))
		//FD0076	[Ode to Joy]	"New Years Eve"
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
	
	public FireworkDisplay processLine(String line, PyroTechnician leadTechnician)
	{
		//Create Matcher for specific Data Matcher
		dataMatcher = dataPattern.matcher(line);
		//this.leadTechnician = leadTechnician;
		
		//Make sure the data matches
		if(dataMatcher.matches())
		{
			//Variables to create FireworksDisplay Instance
			// .group groups up the string according to the RegEx
			String File_FD_ID = dataMatcher.group(2);
			String File_FD_Name = dataMatcher.group(3);
			String File_FD_Theme = dataMatcher.group(4);
			
			return new FireworkDisplay(File_FD_ID, File_FD_Name, File_FD_Theme, leadTechnician);
		}
		return null;
	}
	
	/**
	 * A method to get the Display's ID
	 * @return The Firework Display's ID
	 */
	public String getDisplayID() {
		return displayID;
	}

	/**
	 * A method to get the Display's Name
	 * @return The Display's Name
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * A method to get the Display's Theme
	 * @return The Display's Theme
	 */
	public String getDisplayTheme() {
		return displayTheme;
	}

	/**
	 * A method to get the lead technician
	 * @return The Display's lead Technician
	 */
	public PyroTechnician getLeadTechnician() {
		return leadTechnician;
	}
	
	/**
	 * A method to set the Display's ID
	 * @param displayID A unique alpa-numeric Identifier
	 */
	public void setDisplayID(String displayID) {
		this.displayID = displayID;
	}

	/**
	 * A method to set the Display's Name
	 * @param displayName The name of the Display
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * A method to set the Display's Theme
	 * @param displayTheme The theme of the Display
	 */
	public void setDisplayTheme(String displayTheme) {
		this.displayTheme = displayTheme;
	}

	/**
	 * a method to set the leadTechnician
	 * @param leadTechnician The PyroTechnician in charge of the display
	 */
	public void setLeadTechnician(PyroTechnician leadTechnician) {
		this.leadTechnician = leadTechnician;
	}
	
}
