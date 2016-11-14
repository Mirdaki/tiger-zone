import java.util.ArrayList;

/**
 * Region is the parent class of all the regions: cities, fields,
 * roads, and monasteries. These are a part of the board.
 */
public abstract class Region
{
	// The attributes
	protected int theRegionID;
	protected ArrayList<Terrain> theTerrains;
	protected ArrayList<TigerObject> theTigers;
	protected String theType;

	// Constructors
	public Region()
	{
		// Empty for inheretance
	}

	/**
	 * Region is an object of the board that describes cities, fields,
	 * roads, and monasteries. Use this if there is no starting terrain.
	 * @param aRegionID A unique ID derived from the tile and region
	 * @return Region
	 */
	public Region(int aRegionID)
	{
		theRegionID = aRegionID;
		theTerrains = new ArrayList<Terrain>();
		theTigers   = new ArrayList<TigerObject>();
	}

	/**
	* Region is an object of the board that describes cities, fields,
	* roads, and monasteries. Use this if there is a single terrain.
	* @param aTerrain Single terrain that is included in the region.
	* @return Region
	*/
	public Region(Terrain aTerrain)
	{
		// Region ID becomes the terrain's ID
		theRegionID = aTerrain.getTerrainID();
		theTerrains = new ArrayList<Terrain>();
		theTigers   = new ArrayList<TigerObject>();
		// Add all and update meepels
		addTerain(aTerrain);
	}

	/**
	 * Region is an object of the board that describes cities, fields,
	 * roads, and monasteries. Use this if there is a set of terrain.
	 * @param aTerrains Set of terrain that is included in the region.
	 * @return Region
	 */
	public Region(ArrayList<Terrain> aTerrains)
	{
		// Region ID becomes the first terrain's ID
		theRegionID = aTerrains.get(0).getTerrainID();
		theTerrains = new ArrayList<Terrain>();
		theTigers   = new ArrayList<TigerObject>();
		// Add all and update meepels
		addTerain(aTerrains);
	}

	// Getters
	/**
	 * Get region ID
	 * @return double
	 */
	public double getRegionID()
	{
		return theRegionID;
	}

	/**
	 * Returns array list of terrains in this region.
	 * @return ArrayList<Terrain>
	 */
	public ArrayList<Terrain> getTerrains()
	{
		return theTerrains;
	}

	/**
	 * Returns array list of Tigers in this region.
	 * @return ArrayList<TigerObject>
	 */
	public ArrayList<TigerObject> getTigers()
	{
		return theTigers;
	}

	/**
	 * Get type of region
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

	/**
	 * Gets number of terrain in a region
	 * @return int
	 */
	public int getNumberOfTerrains()
	{
		return theTerrains.size();
	}

	// Checks

	/**
	 * Check if there are any Tigers in this region.
	 * @return boolean
	 */
	public boolean hasTigers()
	{
		boolean result = false;
		// Are any Tigers in the array
		if (theTigers.size() != 0)
		{
			result = true;
		}
		return result;
	}

	// Mutator

	// /**
	//  * Adds a Tiger to the region. Should only be added
	//  * @param aMeepel
	//  */
	// public void addTiger(TigerObject aMeepel)
	// {
	// 	theTigers.add(aMeepel);
	// }

	/**
	 * Goes through the current train and updates the held Tigers.
	 */
	public void updateTigers()
	{
		// Go through all the Terrain adding Tigers
		for (int i = 0; i < theTerrains.size(); i++)
		{
			if (theTerrains.get(i).hasTiger() == true)
			{
				theTigers.add(theTerrains.get(i).getTiger());
			}
		}
	}

	// Remove all Tigers
	/**
	 * Removes all Tigers from this region. Not the individual tiles.
	 */
	public void removeAllTigers()
	{
		theTigers.clear();
	}

	/**
	 * Check if a single terrain is valid, and adds Tigers and terrain to region.
	 * @param aTerrain A single terrain
	 */
	public void addTerain(Terrain aTerrain)
	{
		// Check if the type is right
		if (theType != aTerrain.getType())
		{
			throw new IllegalArgumentException("Mismatch terrain");
		}

		// Add terrain
		theTerrains.add(aTerrain);

		// Add Tiger
		if (aTerrain.hasTiger() == true)
		{
			theTigers.add(aTerrain.getTiger());
		}
	}

	/**
	 * Check if an array list of terrain is valid, and adds Tigers and terrain
	 * to region.
	 * @param aTerrains An arrayList of terrain
	 */
	public void addTerain(ArrayList<Terrain> aTerrains)
	{
		for (int i = 0; i < theTerrains.size(); i++)
		{
			this.addTerain(aTerrains.get(i));
		}
	}

	/**
	 * Merge an existing region into the current one
	 * @param aRegion Of the same type
	 */
	public void addRegion(Region aRegion)
	{
		// Check if the type is right
		if (theType != aRegion.getType())
		{
			throw new IllegalArgumentException("Mismatch Region");
		}

		addTerain(aRegion.getTerrains());
	}

	/**
	 * Prints out the region ID, type, number of Tigers, and number of terrains
	 * @return String description
	 */
	public String toString()
	{
		String regionID = String.valueOf(theRegionID);
		String regionType = theType;
		String numberOfTigers = String.valueOf(theTigers.size());
		String numberOfTerrain = String.valueOf(theTerrains.size());
		return "The region " + regionID + " of type " + regionType + " has " +
				numberOfTigers + " Meepel(s) and " + numberOfTerrain + " Terrain(s)";
	}

}
