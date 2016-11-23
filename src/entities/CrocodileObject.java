package entities;

/*
 * This is the CrocodileObject to handle the Crocodiles that will be placeable on a tile.
 * A typical Crocodile should have:
 * 		1.) an ID
 * 		2.) its type (thief, farmer, knight, monk)
 * 		3.) its location/placement on the tile
 * 		4.) its location/placement on the board?
 */
public class CrocodileObject
{
	//Attributes
	protected int id;
	protected char type;
	protected Player owner;
	protected int terrainID;

	//Constructors
	public CrocodileObject(int id, char type, Player owner, int terrainID) {
		this.id = id;
		this.type = type;
		this.owner = owner;
		this.terrainID = terrainID;
	}

	//Getters
	/**
	* Get Crocodile ID
	* @return int
	*/
	public int getID()
	{
		return id;
	}

	/**
	* Get Crocodile Type
	* @return string
	*/
	public char getType() {
		return type;
	}

	/**
	* Get Crocodile Owner
	* @return char
	*/
	public Player getCrocodileOwner()
	{
		return owner;
	}

	/**
	* Get Crocodile's Terrain ID
	* @return int
	*/
	public int getTerrainID()
	{
		return terrainID;
	}

	//Mutators
	//Changes the Crocodile Type
	public void setType(char type)
	{
		this.type = type;
	}

	//Changes the Terrain ID of the Crocodile
	public void setTerrainID(int terrainID)
	{
		this.terrainID = terrainID;
	}
}
