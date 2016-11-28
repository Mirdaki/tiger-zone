package entities;

import java.util.ArrayList;

/**
 * A Trail terrain. Part of a tile.
 */
public class TrailTerrain extends Terrain
{

	// Properties

	protected boolean theEndOfTrail;

	
	// Constructors

	/**
	 * A Trail terrain that can be completed and have an end.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @param aEndOfTrail This Trail ends.
	 * @return  TrailTerrain
	 */
	public TrailTerrain(ArrayList<Integer> aTileConnections, boolean aEndOfTrail, CrocodileObject aCrocodile) {
		theTerrainID       = terrainNum++;
		theRegionID 	   = theTerrainID;
		theTileConnections = aTileConnections;
		theType            = 'T';
		theEndOfTrail      = aEndOfTrail;
		theAnimal          = null;
		theCrocodile       = aCrocodile;
		terrainMin 		   = aTileConnections.get(0);
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
	public TrailTerrain(ArrayList<Integer> aTileConnections, boolean aEndOfTrail, Animal anAnimal) {

		theTerrainID       = terrainNum++;
		theRegionID 	   = theTerrainID;
		theTileConnections = aTileConnections;
		theType            = 'T';
		theEndOfTrail      = aEndOfTrail;
		theAnimal          = anAnimal;
		theCrocodile       = null;
		terrainMin 		   = aTileConnections.get(0);
	}

	// Getters

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
		terrainMin 		   = aTileConnections.get(0);
	}

}
