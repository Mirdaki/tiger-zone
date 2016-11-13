import java.util.ArrayList;

/**
 * A road terrain. Part of a tile.
 */
public class RoadTerrain extends Terrain
{

	// Properties
	protected boolean theEndOfRoad;

	/**
	 * A road terrain that can be completed and have an end.
	 * @param aTerrainID A unique ID derived from the tile and type
	 * @param aTileConnections A set of connections the terrain makes with
	 *                         the tile
	 * @param aEndOfRoad This road ends.
	 * @return  RoadTerrain
	 */
	public RoadTerrain(double aTerrainID, ArrayList<Integer> aTileConnections,
			boolean aEndOfRoad)
	{
		theTerrainID       = aTerrainID;
		theTileConnections = aTileConnections;
		theType            = "Road";
		theEndOfRoad       = aEndOfRoad;
	}

	// Getters
	/**
	 * Check if the road terminates on this terrain.
	 * @return boolean
	 */
	public boolean isEndOfRoad()
	{
		return theEndOfRoad;
	}

}
