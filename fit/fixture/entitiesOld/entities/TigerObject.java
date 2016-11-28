package entities;

/*
 * This is the TigerObject to handle the Tigers that will be placeable on a tile.
 * A typical Tiger should have:
 * 		1.) an ID
 * 		2.) its type (thief, farmer, knight, monk)
 * 		3.) its location/placement on the tile
 * 		4.) its location/placement on the board?
 */
public class TigerObject
{
	//Attributes
	protected int id;
	protected char type;
	protected Player owner;
	protected int terrainID;

	//Constructors
	public TigerObject() { 
		
	}
	public TigerObject(int id, char type, Player owner, int terrainID) {
		this.id = id;
		this.type = type;
		this.owner = owner;
		this.terrainID = terrainID;
	}

	//Getters
	/**
	* Get Tiger ID
	* @return int
	*/
	public int getID()
	{
		return id;
	}

	/**
	* Get Tiger Type
	* @return string
	*/
	public char getType() {
		return type;
	}

	/**
	* Get Tiger Owner
	* @return char
	*/
	public Player getTigerOwner()
	{
		return owner;
	}

	/**
	* Get Tiger's Terrain ID
	* @return int
	*/
	public int getTerrainID()
	{
		return terrainID;
	}

	//Mutators
	//Changes the Tiger Type
	public void setType(char type)
	{
		this.type = type;
	}

	//Changes the Terrain ID of the Tiger
	public void setTerrainID(int terrainID)
	{
		this.terrainID = terrainID;
	}
}
