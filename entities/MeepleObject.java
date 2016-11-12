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
	protected double theMeepleID;
	protected string theMeepleType;
	protected string theMeepleOwner;
	protected double theMeepleTerrainID;
		
	//Constructors
	public Meeple(double aMeepleID, string aMeepleType, string aMeepleOwner, double theMeepleTerrainID)
	{
		theMeepleID = aMeepleID;
		theMeepleType = aMeepleType;
		theMeepleOwner = aMeepleOwner;
		theMeepleTerrainID = aMeepleTerrainID;
	}
	
	//Getters
	/**
	* Get Meeple ID
	* @return double
	*/
	public double getMeepleID()
	{
		return theMeepleID;
	}
	
	/**
	* Get Meeple Type
	* @return string
	*/
	public string getMeepleType()
	{
		if (theMeepleType == null)
		{
			return "None";
		}
		return theMeepleType;
	}
	
	/**
	* Get Meeple Owner
	* @return string
	*/
	public string getMeepleOwner()
	{
		return theMeepleOwner;
	}
	
	/**
	* Get Meeple's Terrain ID
	* @return double
	*/
	public double getMeepleTerrainID()
	{
		return theMeepleTerrainID;
	}
	
	//Mutators
	//Changes the Meeple Type
	public void setMeepleType(string aMeepleType)
	{
		this.theMeepleType = aMeepleType;
	}
	
	//Changes the Terrain ID of the Meeple
	public void setMeepleTerrainID(double aMeepleTerrainID)
	{
		this.theMeepleTerrainID = aMeepleTerrainID;
	}
}
