package entities;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A Lake terrain. Part of a tile.
 */
public class LakeTerrain extends Terrain {

	// Lake specific properties
	protected boolean theEndOfLake;
	protected char theLakeType;

	// Constructors

	/**
	 * A Lake terrain that can be completed and have an animal.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @param anAnimal Animal property for a Lake.
	 * @return LakeTerrain
	 */
	public LakeTerrain(ArrayList<Integer> aTileConnections, boolean aEndOfLake, Animal anAnimal) {
		theTerrainID       = terrainNum++;
		theRegionID 	   = theTerrainID;
		theTileConnections = aTileConnections;
		theType            = 'L';
		theAnimal          = anAnimal;
		theEndOfLake 	   = aEndOfLake;
		theCrocodile 	   = null;
		zoneMin 		   = zoneListMin(aTileConnections);
	}

	// Getters

	public boolean isEndOfLake() { 
		return theEndOfLake;
	}

	// Deprecated

	/**
	 * DO NOT USE, testing only. A Lake terrain that can be completed and have an animal.
	 * @return LakeTerrain
	 */
	public LakeTerrain() {
		theTerrainID = terrainNum++;
	}

	/**
	 * DO NOT USE, testing only. A Lake terrain that can be completed and have an animal.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @return LakeTerrain
	 */
	public LakeTerrain(int aTerrainID) {
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
	public LakeTerrain(int aTerrainID, ArrayList<Integer> aTileConnections, Animal anAnimal) {
		theTerrainID       = aTerrainID;
		theRegionID 	   = theTerrainID;
		theTileConnections = aTileConnections;
		theType            = 'L';
		theAnimal          = anAnimal;
		zoneMin 		   = zoneListMin(aTileConnections);

	}

	/**
	 * DO NOT USE, testing only. A Lake terrain that can be completed and does not have an animal.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @return LakeTerrain
	 */
	public LakeTerrain(int aTerrainID, ArrayList<Integer> aTileConnections) {
		theTerrainID       = aTerrainID;
		theRegionID 	   = theTerrainID;
		theTileConnections = aTileConnections;
		theType            = 'L';
		theAnimal          = null;
		zoneMin 		   = zoneListMin(aTileConnections);
	}
}
