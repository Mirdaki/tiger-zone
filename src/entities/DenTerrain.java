package entities;

import java.util.ArrayList;

/**
 * A Den terrain. Part of a tile.
 */
public class DenTerrain extends Terrain {

	// Constructors

	/**
	 * A den terrain that can be completed and number of tiles.
	 * @param aTileConnections A set of connections the terrain makes with the tile
	 * @return a DenTerrain
	 */
	public DenTerrain(ArrayList<Integer> aTileConnections) {
		theTerrainID       = terrainNum++;
		theRegionID 	   = theTerrainID;
		theTileConnections = aTileConnections;
		theType            = 'D';
		zoneMin 		   = 5;
	}

	// Deprecated

	/**
	 * DO NOT USE, testing only. An indepdent den terrain that can't have any attributes set.
	 * @return a DenTerrain
	 */
	public DenTerrain() {
		theTerrainID	   = terrainNum++;
		theRegionID		   = 0;
		theTileConnections = null;
		theType			   = 'D';
		zoneMin		   = 5;
	}

	/**
	 * DO NOT USE, testing only. A den terrain that can be completed and number of tiles.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @return DenTerrain
	 */
	public DenTerrain(ArrayList<Integer> aTileConnections, int aTerrainID) {
		theTerrainID       = aTerrainID;
		theRegionID 	   = theTerrainID;
		theTileConnections = aTileConnections;
		theType            = 'D';
		zoneMin 		   = 5;
	}
}
