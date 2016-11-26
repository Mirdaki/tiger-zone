package entities;

import java.util.ArrayList;

/**
 * A Jungle terrain. Part of a tile.
 */
public class JungleTerrain extends Terrain
{
	protected CrocodileObject theCrocodile;
	protected Animal theAnimal;
	protected ArrayList<Integer> adjacentLakes;
	
	// Constructors

	/**
	 * A Jungle terrain
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @param theNumberOfNeighboringCities Number of cities around the Jungle.
	 * @return JungleTerrain
	 */
	public JungleTerrain(ArrayList<Integer> aTileConnections) {
		theTerrainID       = terrainNum++;
		theRegionID 	   = theTerrainID;
		theTileConnections = aTileConnections;
		theType            = 'J';
		terrainMin 		   = aTileConnections.get(0);
		adjacentLakes	   = new ArrayList<Integer>();
	}

	public void addLake(int lakeRegionID) { 
		adjacentLakes.add(lakeRegionID);
	}
	
	public void removeLake(int lakeRegionID) { 
		adjacentLakes.remove(lakeRegionID);
	}
	
	public ArrayList<Integer> getLakes() { 
		return adjacentLakes;
	}

	/**
	 * Gets the animal type from the terrain.
	 * @return char of animal, null if nothing
	 */
	public char getAnimalType()
	{
		char result = '-';
		if (theAnimal != null)
		{
			result = theAnimal.getType();
		}
		return result;
	}

	/**
	 * Gets the animal
	 * @return Animal null if none
	 */
	public Animal getAnimal()
	{
		return theAnimal;
	}

	/**
	 * Checks if the Lake has an animal
	 * @return boolean
	 */
	public boolean hasAnimal() {
		boolean result = false;
		if (theAnimal != null)
		{
			result = true;
		}
		return result;
	}

	/**
	 * Get Crocodile on terrain
	 * @return CrocodileObject
	 */
	public CrocodileObject getCrocodile()
	{
		return theCrocodile;
	}

	/**
	 * Checks if there is a Crocodile on this terrain
	 * @return boolean True if there, false if not
	 */
	public boolean hasCrocodile()
	{
		boolean result = true;
		if (theCrocodile == null)
		{
			result = false;
		}
		return result;
	}

	/**
	 * Adds a Crocodile to the terrain. Can only be one per.
	 * @param aCrocodile CrocodileObject
	 */
	public void addCrocodile(CrocodileObject aCrocodile) {
		// Check that there isn't an exisiting Crocodile
		if (this.hasCrocodile() == true) {
			throw new IllegalArgumentException("Tile already has a Crocodile!");
		}
		else {
			theCrocodile = aCrocodile;
		}
	}

	/**
	 * Removes the Crocodile on this terrain.
	 * @return TigerObject Null if no Crocodile on terrain.
	 */
	public CrocodileObject removeCrocodile()
	{
		CrocodileObject result = theCrocodile;
		theCrocodile = null;
		return result;
	}
	
	
	
	
	// Deprecated

	/**
	 * DO NOT USE, testing only. A Jungle terrain
	 * @return JungleTerrain
	 */
	public JungleTerrain()
	{
		theTerrainID = terrainNum++;
		theRegionID 	   = theTerrainID;
	}

	/**
	 * DO NOT USE, testing only. A Jungle terrain
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @return JungleTerrain
	 */
	public JungleTerrain(int aTerrainID)
	{
			theTerrainID = aTerrainID;
			theRegionID 	   = theTerrainID;
	}

	/**
	 * DO NOT USE, testing only. A Jungle terrain
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @param theNumberOfNeighboringCities Number of cities around the Jungle.
	 * @return JungleTerrain
	 */
	public JungleTerrain(ArrayList<Integer> aTileConnections, int aTerrainID) {
		theTerrainID       = aTerrainID;
		theRegionID 	   = theTerrainID;
		theTileConnections = aTileConnections;
		theType            = 'J';
	}

}
