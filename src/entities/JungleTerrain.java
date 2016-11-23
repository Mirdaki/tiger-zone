package entities;

import java.util.ArrayList;

/**
 * A Jungle terrain. Part of a tile.
 */
public class JungleTerrain extends Terrain
{

	// Constructor

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
