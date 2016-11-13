import java.util.ArrayList;

/**
 * A city terrain. Part of a tile.
 */
public class CityTerrain extends Terrain
{

	// City specifc properties
	protected boolean theShield;
	// protected boolean theCompleted;

	/**
	 * A city terrain that can be completed and have a shield.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @param aShield Shield property for a city.
	 * @return CityTerrain
	 */
	public CityTerrain(double aTerrainID, ArrayList<Integer> aTileConnections,
			boolean aShield)
	{
		theTerrainID       = aTerrainID;
		theTileConnections = aTileConnections;
		theType            = "City";
		theShield          = aShield;
		// theCompleted       = false;
	}

	// Getters
	/**
	 * Checks if the city has a shield
	 * @return boolean
	 */
	public boolean hasShield()
	{
		return theShield;
	}

	// /**
	//  * Checks if the city is complete
	//  * @return boolean
	//  */
	// public boolean isCompleted()
	// {
	// 	return theCompleted;
	// }
	//
	// // Setters
	// /**
	//  * Makes the city complete. Does not remove meeples.
	//  */
	// public void makeCompleted()
	// {
	// 	theCompleted = true;
	// }

}
