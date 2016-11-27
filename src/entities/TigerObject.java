package entities;

/*
 * This is the TigerObject to handle the Tigers that will be placeable on a tile.
 * A typical Tiger should have:
 * 	1.) an owner
 * 	2.) the associated region it is placed on
 */
public class TigerObject
{
	//Attributes
	protected Player owner;
	protected int regionID;

	//Constructors
	public TigerObject() { 
		
	}
	
	public TigerObject(Player owner) { 
		this.owner = owner;
		this.regionID = -1;
	}

	public TigerObject(Player owner, int regionID) { 
		this.owner = owner;
		this.regionID = regionID;
	}

	//ACCESSORSS 

	/**
	* Get Tiger Owner
	* @return char
	*/
	public Player getTigerOwner() {
		return owner;
	}

	/**
	* Get Tiger's Region ID
	* @return int
	*/
	public int getRegionID() {
		return regionID;
	}

	//MUTATORS
	
	//Changes the Region ID of the Tiger
	public void setRegionID(int regionID) {
		this.regionID = regionID;
	}
	
	//Change the owner of a tiger
	public void setOwner(Player owner) { 
		this.owner = owner;
	}
	
	//METHODS	
	public String toString() {
		return "This crocodile belongs to " + owner + " in region " + regionID;
	}

}
