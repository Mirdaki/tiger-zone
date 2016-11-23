package entities;

import java.util.ArrayList;

/**
 * A den Region. Collection of terrain that exist on a board.
 */
public class DenRegion extends Region
{

	// Den specifc properties

	protected boolean theCompleted;
	protected ArrayList<Terrain> theNeighboringTiles;

	// Constructor

	/**
	 * DenRegion is an object of the board that describes dens regions.
	 * Use this if there is no starting terrain.
	 * @param aTerrain Single terrain that is included in the region.
	 * @return DenRegion
	 */
	public DenRegion(Terrain aTerrain)
	{
		// Region ID becomes the terrain's ID
		theRegionID         = aTerrain.getTerrainID();
		theTerrains         = new ArrayList<Terrain>();
		theTigers           = new ArrayList<TigerObject>();
		theType             = 'D';
		theCompleted        = false;
		theNeighboringTiles = new ArrayList<Terrain>();
		// Add and update meepels
		addTerrain(aTerrain, theRegionID);
	}

	// Getters

	/**
	 * Get number of neighboring tiles
	 * @return int
	 */
	public int getNumberOfNeighboringTiles()
	{
		updateNeighboringTiles();
		return theNeighboringTiles.size();
	}

	/**
	 * Checks if the den is complete
	 * @return boolean
	 */
	public boolean isCompleted()
	{
		return theCompleted;
	}

	// Mutators

	/**
	 * Update the list of neighboring tiles. NOT WORKING YET.
	 */
	public void updateNeighboringTiles()
	{
		// TODO: Some method to add neighboriung tiles
		/*if (numberOfTiles == 8)
		{
			theCompleted = true;
		}*/
	}

	/**
	 * Check if a single terrain is valid, and adds Tigers, tiles and terrain
	 * to region.
	 * @param aTerrain A single terrain
	 */
//	public void addTerrain(Terrain aTerrain, int regionID)
//	{
//		// Check if the type is right
//		if (theType != aTerrain.getType())
//		{
//			throw new IllegalArgumentException("Mismatch terrain");
//		}
//
//		// Add terrain
//		aTerrain.setRegionID(regionID);
//		theTerrains.add(aTerrain);
//
//		// Add Tiger
//		if (aTerrain.hasTiger() == true)
//		{
//			theTigers.add(aTerrain.getTiger());
//		}
//
//		// Update neighboring tiles
//		updateNeighboringTiles();
//	}

	// Deprecated

	/**
	 * DO NOT USE, testing only.  DenRegion is an object of the board that describes dens regions.
	 * Use this if there is no starting terrain.
	 * @param aRegionID A unique ID derived from the tile and region
	 * @return DenRegion
	 */
	public DenRegion(int aRegionID)
	{
		theRegionID         = aRegionID;
		theTerrains         = new ArrayList<Terrain>();
		theTigers           = new ArrayList<TigerObject>();
		theType             = 'D';
		theNeighboringTiles = new ArrayList<Terrain>();
	}

}
