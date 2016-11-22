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
	protected char theLakeType;

	// Constructors

	public LakeTerrain(ArrayList<Integer> aTileConnections, boolean aEndOfLake) {
		theTerrainID       = terrainNum++;
		theTileConnections = aTileConnections;
		theType            = 'L';
		theAnimal          = null;

		if (aEndOfLake == true) theLakeType = 'E';
		else theLakeType = 'C';
	}


	/**
	 * A Lake terrain that can be completed and have an animal.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @param anAnimal Animal property for a Lake.
	 * @return LakeTerrain
	 */
	public LakeTerrain(ArrayList<Integer> aTileConnections, Animal anAnimal)
	{
		theTerrainID       = terrainNum++;
		theTileConnections = aTileConnections;
		theType            = 'L';
		theAnimal          = anAnimal;
	}

	/**
	 * A Lake terrain that can be completed and does not have an animal.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @return LakeTerrain
	 */
	public LakeTerrain(ArrayList<Integer> aTileConnections)
	{
		theTerrainID       = terrainNum++;
		theTileConnections = aTileConnections;
		theType            = 'L';
		theAnimal          = null;
	}

	// Getters

	/**
	* Gets the animal type from the terrain.
	* @return String of animal, null if nothing
	*/
	public String getAnimalType()
	{
		String result = null;
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
		theTileConnections = aTileConnections;
		theType            = 'L';
		theAnimal          = null;
	}
}
