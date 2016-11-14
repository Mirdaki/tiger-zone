import java.util.ArrayList;

/**
 * Terrain is the parent class of all the subregions: cities, fields,
 * roads, and monasteries. These are a part of the tiles.
 */
public abstract class Terrain
{
	// The attributes
	protected int theTerrainID;
	protected ArrayList<Integer> theTileConnections;
	protected TigerObject theTiger;
	protected String theType;
	public static int terrainNum;


	// Constructors
	public Terrain()
	{
		theTerrainID = terrainNum++;
		// Empty for inheretance
	}

	/**
	 * Terrain is an object of tiles that describes cities, fields,
	 * roads, and monasteries.
	 * @param aTerrainID A unique ID derived from the tile and type.
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @return Terrain
	 */
	public Terrain(int aTerrainID, ArrayList<Integer> aTileConnections)
	{
		theTerrainID       = aTerrainID;
		theTileConnections = aTileConnections;
	}

	// Getters
	/**
	 * Get terrain ID
	 * @return double
	 */
	public int getTerrainID()
	{
		return theTerrainID;
	}

	/**
	 * Get tile connections for this terrain
	 * @return ArrayList<Integer>
	 */
	public ArrayList<Integer> getTileConnections()
	{
		return theTileConnections;
	}

	/**
	 * Get Tiger on terrain
	 * @return TigerObject
	 */
	public TigerObject getTiger()
	{
		return theTiger;
	}

	/**
	 * Get type of terrain
	 * @return String
	 */
	public String getType()
	{
		String result = theType;
		if (theType == null)
		{
			result = "None";
		}
		return result;
	}

	// Tiger Methods
	/**
	 * Checks if there is a Tiger on this terrain
	 * @return boolean True if there, false if not
	 */
	public boolean hasTiger()
	{
		boolean result = true;
		if (theTiger == null)
		{
			result = false;
		}
		return result;
	}

	/**
	 * Adds a Tiger to the terrain. Can only be one per.
	 * @param aTiger TigerObject
	 */
	public void addTiger(TigerObject aTiger)
	{
		// Check that there isn't an exisiting Tiger
		if (this.hasTiger() == true)
		{
			throw new IllegalArgumentException("Tile already has a Tiger!");
		}
		else
		{
			theTiger = aTiger;
		}
	}

	/**
	 * Removes the Tiger on this terrain.
	 * @return TigerObject Null if no Tiger on terrain.
	 */
	public TigerObject removeTiger()
	{
		TigerObject result = theTiger;
		theTiger = null;
		return result;
	}

	/**
	 * String of the terrain ID, type, if there is a Tiger, and number of tile
	 * connections
	 * @return String description
	 */
	public String toString()
	{
		String terrainID = String.valueOf(theTerrainID);
		String terrainType = theType;
		String hasTiger = "no";
		if (hasTiger())
		{
			hasTiger = "a";
		}
		String numberOfConnections = String.valueOf(theTileConnections.size());
		return "The terrain " + terrainID + " of type " + terrainType + " has " +
				hasTiger + " Meepel and " + numberOfConnections + " tile connection(s)";
	}

}
