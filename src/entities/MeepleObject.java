/*
 * This is the MeepleObject to handle the meeples that will be placeable on a tile.
 * A typical meeple should have:
 * 		1.) an ID
 * 		2.) its type (thief, farmer, knight, monk)
 * 		3.) its location/placement on the tile
 * 		4.) its location/placement on the board?
 */
public class MeepleObject 
{
	//Attributes
	protected int id;
	protected char type;
	protected Player owner;
	protected int terrainID;
		
	//Constructors
	public MeepleObject(int id, char type, Player owner, int terrainID) {
		this.id = id;
		this.type = type;
		this.owner = owner;
		this.terrainID = terrainID;
	}
	
	//Getters
	/**
	* Get Meeple ID
	* @return int
	*/
	public int getID()
	{
		return id;
	}
	
	/**
	* Get Meeple Type
	* @return string
	*/
	public char getType() {
		return type;
	}
	
	/**
	* Get Meeple Owner
	* @return char
	*/
	public Player getMeepleOwner()
	{
		return owner;
	}
	
	/**
	* Get Meeple's Terrain ID
	* @return int
	*/
	public int getTerrainID()
	{
		return terrainID;
	}
	
	//Mutators
	//Changes the Meeple Type
	public void setType(char type)
	{
		this.type = type;
	}
	
	//Changes the Terrain ID of the Meeple
	public void setTerrainID(int terrainID)
	{
		this.terrainID = terrainID;
	}
}
