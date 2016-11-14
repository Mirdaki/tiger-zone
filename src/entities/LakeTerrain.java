import java.util.ArrayList;

/**
 * A Lake terrain. Part of a tile.
 */
public class LakeTerrain extends Terrain
{

	// Lake specifc properties
	protected boolean theShield;
	// protected boolean theCompleted;

	/**
	 * A Lake terrain that can be completed and have a shield.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @param aShield Shield property for a Lake.
	 * @return LakeTerrain
	 */

	public LakeTerrain() {
 		theTerrainID = terrainNum++;
 	}

	public LakeTerrain(int aTerrainID) {
		theTerrainID = aTerrainID;
	}

	public LakeTerrain(int aTerrainID, ArrayList<Integer> aTileConnections, boolean aShield) {
		theTerrainID       = aTerrainID;
		theTileConnections = aTileConnections;
		theType            = "Lake";
		theShield          = aShield;
		// theCompleted       = false;
	}

	// Getters
	/**
	 * Checks if the Lake has a shield
	 * @return boolean
	 */
	public boolean hasShield()
	{
		return theShield;
	}

	// /**
	//  * Checks if the Lake is complete
	//  * @return boolean
	//  */
	// public boolean isCompleted()
	// {
	// 	return theCompleted;
	// }
	//
	// // Setters
	// /**
	//  * Makes the Lake complete. Does not remove Tigers.
	//  */
	// public void makeCompleted()
	// {
	// 	theCompleted = true;
	// }

}
