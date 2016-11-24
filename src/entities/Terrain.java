package entities;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Terrain is the parent class of all the subregions: cities, fields,
 * roads, and monasteries. These are a part of the tiles.
 */
public abstract class Terrain
{
	// The attributes
	protected int theTerrainID;
	protected int theRegionID;
	protected TigerObject theTiger;
	protected char theType;
	protected ArrayList<Integer> theTileConnections;
	public static int terrainNum = 0;
	protected int orientation;
	protected int terrainMin; 
	
	// Constructors
	public Terrain()
	{
		// Empty for inheretance
	}
	
	public int getMin() {
		return terrainMin;
	}
	public int getMin2() {
		if(terrainMin == 0) return 1;
		else if (terrainMin == 1) return 2;
		else if (terrainMin == 2) return 3;
		else if (terrainMin == 3) return 6;
		else if (terrainMin == 4) return 9;
		else if (terrainMin == 5) return 8;
		else if (terrainMin == 6) return 7;
		else if (terrainMin == 7) return 4;
		else return terrainMin;	
	}
	
	public void setMin(int terrainMin) { 
		this.terrainMin = terrainMin;
	}

	public boolean equals(Terrain terrain) {
		if (this.theType == terrain.getType()) return true;
		return false;
	}

	public int getRegionID() {
		return theRegionID;
	}

	public void setRegionID(int theRegionID) {
		this.theRegionID = theRegionID;
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
		theRegionID 	   = -1;
	}

	/**
	 * Terrain is an object of tiles that describes cities, fields,
	 * roads, and monasteries.
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @return Terrain
	 */
	public Terrain(ArrayList<Integer> aTileConnections)
	{
		theTerrainID       = terrainNum++;
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
	public void setTerrainID(int terrainID)
	{
		this.theTerrainID = terrainID;
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
	public char getType()
	{
		// String result = theType;
		// if (theType == null)
		// {
		// 	result = "None";
		// }
		return theType;
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
	public String toString() {
		String terrainID = String.valueOf(theTerrainID);
		char terrainType = theType;
		String hasTiger = "no";
		if (hasTiger())
		{
			hasTiger = "a";
		}
		String numberOfConnections = String.valueOf(theTileConnections.size());
		return "The terrain " + terrainID + " of type " + terrainType + " in region " + theRegionID + " has " +
				hasTiger + " Tigers and " + numberOfConnections + " tile connection(s) (min= " + getMin2() + ")";
	}


	
	public int getOrientation() {
		return orientation;
	}
	
	public void setOrientation(int orientation) {
		
		for (int i = 0; i < theTileConnections.size(); i++) {
			int newConnect = Math.floorMod((theTileConnections.get(i) -	 2 * orientation),8);
			theTileConnections.set(i, newConnect);
		}
		int newMin = Collections.min(theTileConnections);
		terrainMin = newMin;
	}
}
