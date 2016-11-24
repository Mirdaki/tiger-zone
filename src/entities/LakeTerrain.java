package entities;

import java.util.ArrayList;

/**
 * A Lake terrain. Part of a tile.
 */
public class LakeTerrain extends Terrain
{

	// Lake specifc properties
	protected boolean theEndOfLake;
	protected Animal theAnimal;
<<<<<<< HEAD
=======
	protected char theLakeType;
	protected CrocodileObject theCrocodile;
>>>>>>> origin/master

	// Constructors

	public LakeTerrain(ArrayList<Integer> aTileConnections, boolean aEndOfLake, CrocodileObject aCrocodile) {
		theTerrainID       = terrainNum++;
		theRegionID 	   = theTerrainID;
		theTileConnections = aTileConnections;
		theType            = 'L';
		theAnimal          = null;
<<<<<<< HEAD
		theEndOfLake = aEndOfLake;
=======
		theCrocodile       = aCrocodile;

		if (aEndOfLake == true) theLakeType = 'E';
		else theLakeType = 'C';
>>>>>>> origin/master
	}


	public LakeTerrain(ArrayList<Integer> aTileConnections, boolean aEndOfLake, Animal anAnimal) {
		theTerrainID       = terrainNum++;
		theRegionID 	   = theTerrainID;
		theTileConnections = aTileConnections;
		theType            = 'L';
		theAnimal          = anAnimal;
		theEndOfLake = aEndOfLake;
	}

	/**
	 * A Lake terrain that can be completed and have an animal.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @param anAnimal Animal property for a Lake.
	 * @return LakeTerrain
	 */
	public LakeTerrain(ArrayList<Integer> aTileConnections, Animal anAnimal, CrocodileObject aCrocodile)
	{
		theTerrainID       = terrainNum++;
		theRegionID 	   = theTerrainID;
		theTileConnections = aTileConnections;
		theType            = 'L';
		theAnimal          = anAnimal;
		theCrocodile       = aCrocodile;
	}

	/**
	 * A Lake terrain that can be completed and does not have an animal.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @return LakeTerrain
	 */
	public LakeTerrain(ArrayList<Integer> aTileConnections, CrocodileObject aCrocodile)
	{
		theTerrainID       = terrainNum++;
		theRegionID 	   = theTerrainID;
		theTileConnections = aTileConnections;
		theType            = 'L';
		theAnimal          = null;
		theCrocodile       = aCrocodile;
	}

	// Getters

	public boolean isEndOfLake() { 
		return theEndOfLake;
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
	public boolean hasAnimal()
	{
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
	public TigerObject getCrocodile()
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
	public void addCrocodile(CrocodileObject aCrocodile)
	{
		// Check that there isn't an exisiting Crocodile
		if (this.hasCrocodile() == true)
		{
			throw new IllegalArgumentException("Tile already has a Crocodile!");
		}
		else
		{
			theTiger = aTiger;
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
	 * DO NOT USE, testing only. A Lake terrain that can be completed and have an animal.
	 * @return LakeTerrain
	 */
	public LakeTerrain()
	{
		theTerrainID = terrainNum++;
	}

	/**
	 * DO NOT USE, testing only. A Lake terrain that can be completed and have an animal.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @return LakeTerrain
	 */
	public LakeTerrain(int aTerrainID)
	{
		theTerrainID = aTerrainID;
	}

	/**
	 * DO NOT USE, testing only. A Lake terrain that can be completed and have an animal.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @param anAnimal Animal property for a Lake.
	 * @return LakeTerrain
	 */
	public LakeTerrain(int aTerrainID, ArrayList<Integer> aTileConnections, Animal anAnimal)
	{
		theTerrainID       = aTerrainID;
		theRegionID 	   = theTerrainID;
		theTileConnections = aTileConnections;
		theType            = 'L';
		theAnimal          = anAnimal;
	}

	/**
	 * DO NOT USE, testing only. A Lake terrain that can be completed and does not have an animal.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @return LakeTerrain
	 */
	public LakeTerrain(int aTerrainID, ArrayList<Integer> aTileConnections)
	{
		theTerrainID       = aTerrainID;
		theRegionID 	   = theTerrainID;
		theTileConnections = aTileConnections;
		theType            = 'L';
		theAnimal          = null;
	}
	
	
}
