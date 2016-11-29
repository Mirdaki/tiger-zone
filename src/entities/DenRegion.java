package entities;

import java.util.ArrayList;

/**
 * A den Region. Collection of terrain that exist on a board.
 */
public class DenRegion extends Region {

	// Den specific properties

	protected boolean theCompleted;
	protected ArrayList<Location> mooreHood;
	protected Location location;

	//ACCESSORS

	/**
	 * Get number of neighboring tiles
	 * @return int
	 */
	public int getNumberOfNeighboringTiles() {
		return mooreHood.size();
	}

	@Override
	public int getPotential() { 
		int potential = 0;
		
		if(isCompleted) { potential = 9; } 
		else { potential = getNumberOfNeighboringTiles(); }
		return potential;
	}
	
	/**
	 * Checks if the den is complete
	 * @return boolean
	 */
	public boolean isCompleted() {
		return theCompleted;
	}

	/**
	 * Gets the location of the calling region
	 * @return location
	 */
	public Location getLocation() { 
		return location;
	}
	
	/**
	 * Returns the Moore neighborhood of the calling region
	 * @return an ArrayList of locations
	 */
	public ArrayList<Location> getMoore() { 
		return mooreHood;
	}
	
	// MUTATORS
	
	/**
	 * Sets the Moore neighborhood of the calling region 
	 * @param mooreHood - an ArrayList of surrounding locations
	 */
	public void setMoore(ArrayList<Location> mooreHood) { 
		this.mooreHood = mooreHood;
		
		//if the new Moore neighborhood is complete, the region is complete
		if (mooreHood.size() == 9) theCompleted = true;
	}

	//METHODS
	
	/**
	 * toString() method to return the attributes associated with a Den Region
	 * @return int
	 */
	@Override
	public String toString() {
		String regionID = String.valueOf(theRegionID);
		char regionType = theType;
		String numberOfTigers = String.valueOf(theTigers.size());
		String numberOfTerrain = String.valueOf(theTerrains.size());
		
		return "The region " + regionID + " of type " + regionType + " has " +
		numberOfTigers + " Tiger(s), and " + numberOfTerrain + " Terrain(s).";
	}
	
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
		theCrocodiles		= new ArrayList<CrocodileObject>();
		theType             = 'D';
		mooreHood = new ArrayList<Location>();
	}


	public DenRegion(ArrayList<Location> mooreHood) {
		theRegionID         = Terrain.terrainNum++;
		theTerrains         = new ArrayList<Terrain>();
		theTigers           = new ArrayList<TigerObject>();
		theCrocodiles		= new ArrayList<CrocodileObject>();
		theType             = 'D';
		this.mooreHood = mooreHood;
	}

	public DenRegion(Location location) {
		theRegionID         = Terrain.terrainNum++;
		theTerrains         = new ArrayList<Terrain>();
		theTigers           = new ArrayList<TigerObject>();
		theCrocodiles		= new ArrayList<CrocodileObject>();
		theType             = 'D';
		this.location 		= location;
	}

}
