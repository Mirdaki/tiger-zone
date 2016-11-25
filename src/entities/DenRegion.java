package entities;

import java.util.ArrayList;

/**
 * A den Region. Collection of terrain that exist on a board.
 */
public class DenRegion extends Region
{

	// Den specifc properties

	protected boolean theCompleted;
	protected ArrayList<Location> mooreHood;
	protected Location location;

	// Constructor

	//	/**
	//	 * DenRegion is an object of the board that describes dens regions.
	//	 * Use this if there is no starting terrain.
	//	 * @param aTerrain Single terrain that is included in the region.
	//	 * @return DenRegion
	//	 */
	//	public DenRegion(Terrain aTerrain)
	//	{
	//		// Region ID becomes the terrain's ID
	//		theRegionID         = aTerrain.getTerrainID();
	//		theTerrains         = new ArrayList<Terrain>();
	//		theTigers           = new ArrayList<TigerObject>();
	//		theType             = 'D';
	//		theCompleted        = false;
	//		theNeighboringTiles = new ArrayList<Location>();
	//		// Add and update meepels
	//		addTerrain(aTerrain, theRegionID);
	//	}

	// Getters

	/**
	 * Get number of neighboring tiles
	 * @return int
	 */
	public int getNumberOfNeighboringTiles()
	{
		//		updateNeighboringTiles();
		return mooreHood.size();
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
	public DenRegion(int aRegionID) {
		theRegionID         = aRegionID;
		theTerrains         = new ArrayList<Terrain>();
		theTigers           = new ArrayList<TigerObject>();
		theType             = 'D';
		mooreHood = new ArrayList<Location>();
	}


	public DenRegion(ArrayList<Location> mooreHood) {
//		theRegionID         = Terrain.terrainNum++;
		theTerrains         = new ArrayList<Terrain>();
		theTigers           = new ArrayList<TigerObject>();
		theType             = 'D';
		this.mooreHood = mooreHood;
	}

	public DenRegion(Location location) {
//		theRegionID         = Terrain.terrainNum++;
		theTerrains         = new ArrayList<Terrain>();
		theTigers           = new ArrayList<TigerObject>();
		theType             = 'D';
		this.location 		= location;
	}
	
	public Location getLocation() { 
		return location;
	}
	
	public ArrayList<Location> getMoore() { 
		return mooreHood;
	}
	
	public void setMoore(ArrayList<Location> mooreHood) { 
		this.mooreHood = mooreHood;
		if (mooreHood.size() == 9) theCompleted = true;
	}
	
	
	public String toString()
	{
		String regionID = String.valueOf(theRegionID);
		char regionType = theType;
		String numberOfTigers = String.valueOf(theTigers.size());
		String numberOfTerrain = String.valueOf(mooreHood.size());
		return "The region " + regionID + " of type " + regionType + " has " +
		numberOfTigers + " Meepel(s) and " + numberOfTerrain + " Terrain(s)";
	}

}
