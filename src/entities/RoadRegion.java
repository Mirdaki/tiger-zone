import java.util.ArrayList;

/**
 * A monastery Region. Collection of terrain that exist on a board.
 */
public class RoadRegion extends Region
{

	// Field specifc properties
	protected boolean theCompleted;

	/**
	 * RoadRegion is an object of the board that describes fields regions.
	 * Use this if there is no starting terrain.
	 * @param aRegionID A unique ID derived from the tile and region
	 * @return RoadRegion
	 */
	public RoadRegion(double aRegionID)
	{
		theRegionID      = aRegionID;
		theTerrains      = new ArrayList<Terrain>();
		theMeeples       = new ArrayList<MeepleObject>();
		theType          = "Field";
		theCompleted     = false;
	}

	/**
	 * RoadRegion is an object of the board that describes fields regions.
	 * Use this if there is no starting terrain.
	 * @param aTerrain Single terrain that is included in the region.
	 * @return RoadRegion
	 */
	public RoadRegion(Terrain aTerrain)
	{
		// Region ID becomes the terrain's ID
		theRegionID      = aTerrain.getTerrainID();
		theTerrains      = new ArrayList<Terrain>();
		theMeeples       = new ArrayList<MeepleObject>();
		theType          = "Field";
		theCompleted     = false;
		// Add and update meepels
		addTerain(aTerrain);
	}

	/**
	 * RoadRegion is an object of the board that describes fields regions.
	 * Use this if there is no starting terrain.
	 * @param aTerrains Set of terrain that is included in the region.
	 * @return RoadRegion
	 */
	public RoadRegion(ArrayList<Terrain> aTerrains)
	{
		// Region ID becomes the first terrain's ID
		theRegionID      = aTerrains.get(0).getTerrainID();
		theTerrains      = new ArrayList<Terrain>();
		theMeeples       = new ArrayList<MeepleObject>();
		theType          = "Field";
		theCompleted     = false;
		// Add all and update meepels
		addTerain(aTerrains);
	}

	// Getters
	/**
	 * Return the completed status
	 * @return boolean
	 */
	public boolean isCompleted()
	{
		return theCompleted;
	}

	// Mutators
	/**
	 * Updates the status of road completion by checking if every segment has
	 * two connections. Requires that endOfRoads don't count as null.
	 */
	public void updateCompletion()
	{
		theCompleted = true;
		for (int i = 0; i < theTerrains.size(); i++)
		{
			// Check for a a front and back to everything
			// TODO: getInFront() and getBehind() for terrain in front and behind
			// beind the road
			/*if (theTerrains.get(i).getInFront() == null ||
			theTerrains.get(i).getBehind() == null)
			{
				theCompleted = false;
				break;
			}*/
		}
	}

	/**
	 * Check if a single terrain is valid, and adds meeples, and terrain
	 * to region. Updates completion status.
	 * @param aTerrain A single terrain
	 */
	public void addTerain(Terrain aTerrain)
	{
		// Check if the type is right
		if (theType != aTerrain.getType())
		{
			throw new IllegalArgumentException("Mismatch terrain");
		}

		// Check if region is already complete
		if (theCompleted == true)
		{
			throw new IllegalArgumentException("Road already complete");
		}

		// Add terrain
		theTerrains.add(aTerrain);

		// Add meeple
		if (aTerrain.hasMeeple() == true)
		{
			theMeeples.add(aTerrain.getMeeple());
		}

		// Update neighboring tiles
		updateCompletion();
	}

}
