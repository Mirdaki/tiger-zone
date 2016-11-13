import java.util.ArrayList;

/**
 * Terrain is the parent class of all the subregions: cities, fields,
 * roads, and monasteries. These are a part of the tiles.
 */
public abstract class Terrain
{
	// The attributes
	protected double theTerrainID;
	protected ArrayList<Integer> theTileConnections;
	protected MeepleObject theMeeple;
	protected String theType;

	// Constructors
	public Terrain()
	{
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
	public Terrain(double aTerrainID, ArrayList<Integer> aTileConnections)
	{
		theTerrainID       = aTerrainID;
		theTileConnections = aTileConnections;
	}

	// Getters
	/**
	 * Get terrain ID
	 * @return double
	 */
	public double getTerrainID()
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
	 * Get meeple on terrain
	 * @return MeepleObject
	 */
	public MeepleObject getMeeple()
	{
		return theMeeple;
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

	// Meeple Methods
	/**
	 * Checks if there is a meeple on this terrain
	 * @return boolean True if there, false if not
	 */
	public boolean hasMeeple()
	{
		boolean result = true;
		if (theMeeple == null)
		{
			result = false;
		}
		return result;
	}

	/**
	 * Adds a meeple to the terrain. Can only be one per.
	 * @param aMeeple MeepleObject
	 */
	public void addMeeple(MeepleObject aMeeple)
	{
		// Check that there isn't an exisiting meeple
		if (this.hasMeeple() == true)
		{
			throw new IllegalArgumentException("Tile already has a meeple!");
		}
		else
		{
			theMeeple = aMeeple;
		}
	}

	/**
	 * Removes the meeple on this terrain.
	 * @return MeepleObject Null if no meeple on terrain.
	 */
	public MeepleObject removeMeeple()
	{
		MeepleObject result = theMeeple;
		theMeeple = null;
		return result;
	}

	/**
	 * String of the terrain ID, type, if there is a meeple, and number of tile
	 * connections
	 * @return String description
	 */
	public String toString()
	{
		String terrainID = String.valueOf(theTerrainID);
		String terrainType = theType;
		String hasMeeple = "no";
		if (hasMeeple())
		{
			hasMeeple = "a";
		}
		String numberOfConnections = String.valueOf(theTileConnections.size());
		return "The terrain " + terrainID + " of type " + terrainType + " has " +
				hasMeeple + " Meepel and " + numberOfConnections + " tile connection(s)";
	}

}
