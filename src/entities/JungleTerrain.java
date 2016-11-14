import java.util.ArrayList;

/**
 * A Jungle terrain. Part of a tile.
 */
public class JungleTerrain extends Terrain
{

	// Properties
	// protected int theNumberOfNeighboringCities;

	/**
	 * A Jungle terrain that can have a number of neighboring cities.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @param theNumberOfNeighboringCities Number of cities around the Jungle.
	 * @return JungleTerrain
	 */

	public JungleTerrain(int aTerrainID)
	{
			theTerrainID = aTerrainID;
	}
	public JungleTerrain(int aTerrainID, ArrayList<Integer> aTileConnections)
	{
		theTerrainID                 = aTerrainID;
		theTileConnections           = aTileConnections;
		theType                      = "Jungle";
		// theNumberOfNeighboringCities = 0;
	}

	// // These may be better in the JungleRegion
	// // Getters and setters
	// /**
	//  * Get number of neighboring cities
	//  * @return int
	//  */
	// public int getNumberOfNeighboringCities()
	// {
	// 	return theNumberOfNeighboringCities;
	// }
	//
	// /**
	//  * Set the number of neighboring cities
	//  * @param aNumberOfNeighboringCities int
	//  */
	// public void setNumberOfNeighboringCities(int aNumberOfNeighboringCities)
	// {
	// 	theNumberOfNeighboringCities = aNumberOfNeighboringCities;
	// }

}
