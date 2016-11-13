import java.util.ArrayList;

/**
 * A field terrain. Part of a tile.
 */
public class FieldTerrain extends Terrain
{

	// Properties
	// protected int theNumberOfNeighboringCities;

	/**
	 * A field terrain that can have a number of neighboring cities.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @param theNumberOfNeighboringCities Number of cities around the field.
	 * @return FieldTerrain
	 */
	public FieldTerrain(double aTerrainID, ArrayList<Integer> aTileConnections)
	{
		theTerrainID                 = aTerrainID;
		theTileConnections           = aTileConnections;
		theType                      = "Field";
		// theNumberOfNeighboringCities = 0;
	}

	// // These may be better in the FieldRegion
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
