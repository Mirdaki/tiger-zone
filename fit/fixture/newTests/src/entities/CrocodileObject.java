package entities;

/*
 * This is the CrocodileObject to handle the Crocodiles that will be placeable on a tile.
 * A typical Crocodile should have:
 * 		1.) an owner
 * 		2.) its region association
 */
public class CrocodileObject {

	//Attributes
	protected Player owner;
	protected int regionID;

	//Constructors

	/**
	 * CrocodileObject that is placed on a terrain/tile
	 * @param  aType String with tupe of animal
	 * @return       Animal
	 */
	public CrocodileObject(Player owner) {
		this.owner = owner;
		this.regionID = -1;
	}

	//Getters

	/**
	* Get Crocodile Owner
	* @return the Player owner of the crocodile
	*/
	public Player getCrocodileOwner() {
		return owner;
	}

	/**
	* Get Crocodile's Region ID
	* @return an int of the crocodile's region
	*/
	public int getRegionID() {
		return regionID;
	}

	//MUTATORS

	//Changes the Region ID of the Crocodile
	public void setRegionID(int regionID) {
		this.regionID = regionID;
	}
		
	public String toString() {
		return "This crocodile belongs to " + owner + " in region " + regionID;
	}
}
