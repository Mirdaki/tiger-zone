package entities;

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
	protected char theType;

	// Constructors

	public Region() {
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
		addTerrain(aTerrain, theRegionID);
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
		addTerrain(aTerrains, theRegionID);
	}

	// Getters

	/**
	 * Get region ID
	 * @return int
	 */
	public int getRegionID()
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
	public char getType()
	{
		// char result = theType;
		// if (theType == null)
		// {
		// 	result = "None";
		// }
		return theType;
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
	public void addTerrain(Terrain aTerrain, int regionID)
	{
		// Check if the type is right
//		if (theType != aTerrain.getType())
//		{
//			throw new IllegalArgumentException("Mismatch terrain");
//		}

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
	public void addTerrain(ArrayList<Terrain> aTerrains, int regionID) {
		
		int neededSize = aTerrains.size();

		for (int i = 0; i < neededSize; i++) {

			this.addTerrain(aTerrains.get(i), regionID);

		}
	}


	/**
	 * Prints out the region ID, type, number of Tigers, and number of terrains
	 * @return String description
	 */
	public String toString()
	{
		String regionID = String.valueOf(theRegionID);
		char regionType = theType;
		String numberOfTigers = String.valueOf(theTigers.size());
		String numberOfTerrain = String.valueOf(theTerrains.size());
		return "The region " + regionID + " of type " + regionType + " has " +
				numberOfTigers + " Meepel(s) and " + numberOfTerrain + " Terrain(s)";
	}

}
