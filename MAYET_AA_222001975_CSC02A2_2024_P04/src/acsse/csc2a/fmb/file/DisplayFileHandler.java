/**
 * @author 222001975-Abdullah Mayet
 */
package acsse.csc2a.fmb.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import acsse.csc2a.fmb.model.*;

public class DisplayFileHandler
{
	public DisplayFileHandler()
	{
		
	}
	
	/**
	* Reading a file using ARM
	* @return FireworkDisplay
	* @param fileName File handle to read from
	*/
	public FireworkDisplay readDisplay(String fileName)
	{
		File handle = new File(fileName);
		
		//Use Scanner to read file (can throw exception)
		try(Scanner textin = new Scanner(handle))
		{
			//Declare Variables (RegEx Match, Scanner Line, Data Pattern)
			String line = null;
			FireworkDisplay fileFireworkDisplay = new FireworkDisplay();
			PyroTechnician tempPyroTechnician = new PyroTechnician();
			Rocket tempRocket = new Rocket();
			Fountain tempFountain = new Fountain();
			
			//Read first line from file
			line = textin.nextLine();
			
			//Read second line from file
			String line1 = textin.nextLine();
			tempPyroTechnician = tempPyroTechnician.processLine(line1);
			fileFireworkDisplay = fileFireworkDisplay.processLine(line, tempPyroTechnician);
			
			//Read rest of file
			while(textin.hasNext())
			{
				//Read from file, line by line
				line = textin.nextLine();
				char sRocket = line.charAt(1);
				System.out.println(sRocket);
				if(sRocket == 'R')
				{
					tempRocket = tempRocket.processLine(line);
					//fileFireworkDisplay.addFireWork(tempRocket);
				}else if (sRocket == 'F')
				{
					tempFountain = tempFountain.processLine(line);
					//fileFireworkDisplay.addFireWork(tempFountain);
				}else 
				{
					System.err.println("Line is not a Rocket or Fountain Firework!!");
				}
			}
			
		}catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}










