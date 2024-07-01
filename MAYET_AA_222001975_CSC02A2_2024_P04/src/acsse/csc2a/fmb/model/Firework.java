/**
 * @author 222001975-Abdullah Mayet
 */
package acsse.csc2a.fmb.model;

public abstract class Firework implements IDisplayable
{
	// Attributes
	private String fireworkID;		// a unique alphanumeric ID of the firework
	private String fireworkName; 	// The name of the firework
	private double fuseLength; 		// Constrols when the explosion will occur in seconds
	private E_COLOUR colour;		//Controls the Firework's Colour
	
	/**
	 * A default constructor, populating the object with default values
	 */
	public Firework()
	{
		this("No_ID", "No_Name", 0, E_COLOUR.WHITE);
	}
	
	/**
	 * A Constructor to build a Firework
	 * @param fireworkID A unique alphanumeric ID of the firework
	 * @param fireworkName The name of the firework
	 * @param fuseLength Constrols when the explosion will occur in seconds
	 * @param colour Controls the Firework's Colour
	 */
	public Firework(String fireworkID, String fireworkName, double fuseLength, E_COLOUR colour)
	{
		this.fireworkID = fireworkID;
		this.fireworkName = fireworkName;
		this.fuseLength = fuseLength;
		this.colour = colour;
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
	
	@Override
    public String toString()
	{
        return String.format("> ID: %s - %s [%s] %s seconds delay", this.fireworkID, this.fireworkName, this.colour, this.fuseLength);
    }
	
	public abstract boolean validate(String data);

    public abstract Firework processLine(String data);
    
    /**
	 * Gets the Firework's ID
	 * @return The Firework's ID
	 */
	public String getFireworkID()
	{
		return this.fireworkID;
	}
	
	/**
	 * Gets the Firework's name
	 * @return The Firework's Name
	 */
	public String getFireworkName()
	{
		return this.fireworkName;
	}
	
	/**
	 * Gets the Firework's Fuse Length
	 * @return The Firework's Fuse Length (in seconds)
	 */
	public double getFuseLength()
	{
		return this.fuseLength;
	}
	
	/**
	 * Gets the Colour Assigned to the Firework
	 * @return The Colour Assigned to the Firework
	 */
	public E_COLOUR getColour()
	{
		return this.colour;
	}
	
	/**
	 * Sets the Firework's ID
	 * @param fireworkID  A unique alphanumeric ID of the firework
	 */
	public void setFireworkID(String fireworkID)
	{
		this.fireworkID = fireworkID;
	}
	
	/** Sets the Name of the Firework
	 * @param fireworkName The name of the Firework
	 */
	public void setFireworkName(String fireworkName)
	{
		this.fireworkName = fireworkName;
	}
	
	/**
	 * Sets the Length of the Firework's Fuse
	 * @param fuseLength The explosion delay (in seconds)
	 */
	public void setFuseLength(double fuseLength)
	{
		this.fuseLength = fuseLength;
	}
	
	/**
	 * Sets the colour of the Firework
	 * @param colour The E_COLOUR assigned to the Firework
	 */
	public void setColour(E_COLOUR colour)
	{
		this.colour = colour;
	}
    
}

