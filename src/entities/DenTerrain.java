package entities;

import java.util.ArrayList;

/**
 * A Den terrain. Part of a tile.
 */
public class DenTerrain extends Terrain
{

	// Constructors

	/**
	 * A den terrain that can be completed and number of tiles.
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @param theNumberOfNeighboringTiles Number of tiles around the
	 *                                    den.
	 * @return DenTerrain
	 */
	public DenTerrain(ArrayList<Integer> aTileConnections)
	{
		theTerrainID       = terrainNum++;
		theRegionID 	   = theTerrainID;
		theTileConnections = aTileConnections;
		theType            = 'D';
	}

	public DenTerrain() {
		theTerrainID	   = terrainNum++;
		theType = 'D';
	}
	// Deprecated

	/**
	 * DO NOT USE, testing only. A den terrain that can be completed and number of tiles.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @param theNumberOfNeighboringTiles Number of tiles around the
	 *                                    den.
	 * @return DenTerrain
	 */
	public DenTerrain(ArrayList<Integer> aTileConnections, int aTerrainID) {
		theTerrainID       = aTerrainID;
		theRegionID 	   = theTerrainID;
		theTileConnections = aTileConnections;
		theType            = 'D';
	}

}
