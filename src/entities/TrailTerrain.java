package entities;

import java.util.ArrayList;

/**
 * A Trail terrain. Part of a tile.
 */
public class TrailTerrain extends Terrain
{

	// Properties

	protected boolean theEndOfTrail;
	protected Animal theAnimal;

	// Constructors

	/**
	 * A Trail terrain that can be completed and have an end.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @param aEndOfTrail This Trail ends.
	 * @return  TrailTerrain
	 */
	public TrailTerrain(ArrayList<Integer> aTileConnections, boolean aEndOfTrail) {
		theTerrainID       = terrainNum++;
		theRegionID 	   = theTerrainID;
		theTileConnections = aTileConnections;
		theType            = 'T';
		theEndOfTrail      = aEndOfTrail;
		theAnimal          = null;
	}

	/**
	 * A Trail terrain that can be completed and have an end.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @param anAnimal Animal that is placed on the terrain
	 * @param aEndOfTrail This Trail ends.
	 * @return  TrailTerrain
	 */
	public TrailTerrain( ArrayList<Integer> aTileConnections, boolean aEndOfTrail, Animal anAnimal)
	{
		theTerrainID       = terrainNum++;
		theRegionID 	   = theTerrainID;
		theTileConnections = aTileConnections;
		theType            = 'T';
		theEndOfTrail      = aEndOfTrail;
		theAnimal          = anAnimal;
	}


	// Getters

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
	 * Check if the Trail terminates on this terrain.
	 * @return boolean
	 */
	public boolean isEndOfTrail()
	{
		return theEndOfTrail;
	}

	// Deprecated

	/**
	 * DO NOT USE, testing only. A Trail terrain.
	 * @return  TrailTerrain
	 */
	public TrailTerrain() {
 		theTerrainID = terrainNum++;
		theRegionID 	   = theTerrainID;
		theType            = 'T';
 	}

	/**
	 * DO NOT USE, testing only. A Trail terrain.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @return  TrailTerrain
	 */
	public TrailTerrain(int aTerrainID) {
		theTerrainID = aTerrainID;
		theRegionID 	   = theTerrainID;
	}

	/**
	 * DO NOT USE, testing only. A Trail terrain that can be completed and have an end.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @param aEndOfTrail This Trail ends.
	 * @return  TrailTerrain
	 */
	public TrailTerrain(int aTerrainID, ArrayList<Integer> aTileConnections, boolean aEndOfTrail) {
		theTerrainID       = aTerrainID;
		theRegionID 	   = theTerrainID;
		theTileConnections = aTileConnections;
		theType            = 'T';
		theEndOfTrail      = aEndOfTrail;
	}

}
