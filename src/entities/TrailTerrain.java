import java.util.ArrayList;

/**
 * A Trail terrain. Part of a tile.
 */
public class TrailTerrain extends Terrain
{

	// Properties
	protected boolean theEndOfTrail;

	/**
	 * A Trail terrain that can be completed and have an end.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @param aEndOfTrail This Trail ends.
	 * @return  TrailTerrain
	 */

	public TrailTerrain(int aTerrainID) {
		theTerrainID = aTerrainID;
	}

	public TrailTerrain(int aTerrainID, ArrayList<Integer> aTileConnections,
			boolean aEndOfTrail)
	{
		theTerrainID       = aTerrainID;
		theTileConnections = aTileConnections;
		theType            = "Trail";
		theEndOfTrail      = aEndOfTrail;
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

}
