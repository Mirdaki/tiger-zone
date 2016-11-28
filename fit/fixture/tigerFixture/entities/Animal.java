package entities;

public class Animal
{
	// Attributes

	protected String theType;

	// Constructors

	/**
	 * Animal that is placed on a terrain
	 * @param  aType String with tupe of animal
	 * @return       Animal
	 */
	public Animal(String aType)
	{
		theType = aType;
	}

	// Getter

	/**
	 * Get the type of the animal
	 * @return String
	 */
	String getType()
	{
		return theType;
	}
}
