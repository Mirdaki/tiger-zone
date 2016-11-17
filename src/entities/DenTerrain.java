package entities;

import java.util.ArrayList;

/**
 * A Den terrain. Part of a tile.
 */
public class DenTerrain extends Terrain
{

	// Properties
	// protected int theNumberOfNeighboringTiles;
	// protected boolean theCompleted;
	// protected static final int NUMBEROFTILESTOSURROUND = 8;

	/**
	 * A den terrain that can be completed and number of tiles.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @param theNumberOfNeighboringTiles Number of tiles around the
	 *                                    den.
	 * @return DenTerrain
	 */
	public DenTerrain(int aTerrainID, ArrayList<Integer> aTileConnections)
	{
		theTerrainID       = aTerrainID;
		theTileConnections = aTileConnections;
		theType            = "Den";
		// theNumberOfNeighboringTiles = 0;
	}

	// // Getters and Setters
	// /**
	//  * Get the number of neighboring tiles
	//  * @return int
	//  */
	// public int getNumberOfNeighboringTiles()
	// {
	// 	return theNumberOfNeighboringTiles;
	// }
	//
	// /**
	//  * Sets the number of neighboring tiles
	//  * @param aNumberOfNeighboringTiles [description]
	//  */
	// public void setNumberOfNeighboringTiles(int aNumberOfNeighboringTiles)
	// {
	// 	theNumberOfNeighboringTiles = aNumberOfNeighboringTiles;
	// 	// Check if completely surrounded
	// 	if (theNumberOfNeighboringTiles == NUMBEROFTILESTOSURROUND)
	// 	{
	// 		theCompleted = true;
	// 	}
	// }

	// // Mutators
	// /**
	//  * Add one neighboring tile
	//  */
	// public void addNeighboringTile()
	// {
	// 	theNumberOfNeighboringTiles++;
	// 	// Check if completely surrounded
	// 	if (theNumberOfNeighboringTiles == NUMBEROFTILESTOSURROUND)
	// 	{
	// 		theCompleted = true;
	// 	}
	// }
	//
	// /**
	//  * Check if it is surrounded
	//  * @return boolean
	//  */
	// public boolean isCompleted()
	// {
	// 	return theCompleted;
	// }

}
