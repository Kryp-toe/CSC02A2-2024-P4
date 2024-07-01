/**
 * @author 222001975-Abdullah Mayet
 */
package acsse.csc2a.fmb.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A Class which holds information of an Individual PyroTechnician
 */
public class PyroTechnician implements IDisplayable
{
	//attributes
	private String fullName;
	private String phoneNumber;
	private Matcher dataMatcher;
	private Pattern dataPattern = Pattern.compile("((([A-Z][a-z]*)-([A-Z][a-z]*))\\s([1-9][0-9]{2}-[0-9]{3}-[0-9]{3}[1-9]))$");
	
	//Constructors
	/**
	 * A default constructor to build a PyroTechnician with default values
	 */
	public PyroTechnician()
	{
		this("NoFirstName", "NoLastName", "NoPhoneNumber");
	}
	
	/**
	 * A constructor to build a PyroTechnician
	 * @param firstName The firstname of the Technician
	 * @param lastName The last name of the Technician
	 * @param phoneNumber The contact number of the Technician
	 */ 
	public PyroTechnician(String firstName, String lastName, String phoneNumber)
	{
		this.fullName = formatFullName(firstName, lastName);
		this.phoneNumber = phoneNumber;
	}
	
	//Helper Methods
	/**
	 * A helper method to format the PyroTechnician's Full Name to be <FirstName>-<LastName>
	 * @param firstName The First Name of the Technician
	 * @param lastName The Last Name of the Technician
	 * @return The formatted Full Name as <FirstName>-<LastName>
	 */
	private static String formatFullName(String firstName, String lastName) {
		return String.format("%s-%s", firstName, lastName);
	}
	
	/**
	 * A method to print the Technician's details
	 * @return String value to display
	 */	
	@Override
	public String toString()
	{
		String outputString = String.format("%s (%s)", fullName, phoneNumber);
		return outputString;
	}
	
	/**
	 * Prints the details of the Firework
	 * @return String value to display
	 */
	@Override
	public void display()
	{
		String outputString = this.toString();
		System.out.println(outputString);
	}
	
	public boolean validate(String line)
	{
		//Process Line
		//(Pyro Technician)RegEx: ((([A-Z][a-z]*)-([A-Z][a-z]*))\s([1-9][0-9]{2}-[0-9]{3}-[0-9]{3}[1-9]))
		//Jane-Doe	555-010-9111
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
	
	public PyroTechnician processLine(String line)
	{
		//Create Matcher for specific Data Matcher
		dataMatcher = dataPattern.matcher(line);
		
		//Make sure the data matches
		if(this.validate(line))
		{
			//Variables to create PyroTechnician Instance
			// .group groups up the string according to the RegEx
			String File_PT_FirstName = dataMatcher.group(3);
			String File_PT_LastName = dataMatcher.group(4);
			String File_PT_PhoneNumb = dataMatcher.group(5);
			
			//Create PyroTechnician instance
			return new PyroTechnician(File_PT_FirstName, File_PT_LastName, File_PT_PhoneNumb);
			
		}
		return null;
		
	}
	
	//Accessors
	/**
	 * A method to get the Technician's phone number
	 * @return The Technician's phone number
	 */
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	
	/**
	 * A method to get the Technician's Full Name
	 * @return The Technician's Full Name
	 */
	public String getFullName()
	{
		return fullName;
	}
	
	//Mutators
	/**
	 * A method to set the Technician's phone number
	 * @param phoneNumber The Technician's phone number
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}
}
