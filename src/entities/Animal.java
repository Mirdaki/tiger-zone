package entities;

public class Animal {
	// Attributes

	protected char theType;

	// Constructors

	/**
	 * Animal that is placed on a terrain
	 * @param  aType String with type of animal
	 * @return       Animal
	 */
	public Animal(char aType) {
		theType = aType;
	}

	// Getter

	/**
	 * Get the type of the animal
	 * @return String
	 */
	char getType() {
		return theType;
	}
	
	public String toString() {
		return "Animal type: " + theType;
	}
}
