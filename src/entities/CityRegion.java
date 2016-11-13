import java.util.ArrayList;

/**
 * A city Region. Collection of terrain that exist on a board.
 */
public class CityRegion extends Region
{

	// City specifc properties
	protected boolean theCompleted;
	protected int numberOfShields;

	/**
	 * CityRegion is an object of the board that describes cities regions.
	 * Use this if there is no starting terrain.
	 * @param aRegionID A unique ID derived from the tile and region
	 * @return CityRegion
	 */
	public CityRegion(double aRegionID)
	{
		theRegionID     = aRegionID;
		theTerrains     = new ArrayList<Terrain>();
		theMeeples      = new ArrayList<MeepleObject>();
		theCompleted    = false;
		theType         = "City";
		numberOfShields = 0;
	}

	/**
	 * CityRegion is an object of the board that describes cities regions.
	 * Use this if there is no starting terrain.
	 * @param aTerrain Single terrain that is included in the region.
	 * @return CityRegion
	 */
	public CityRegion(Terrain aTerrain)
	{
		// Region ID becomes the terrain's ID
		theRegionID     = aTerrain.getTerrainID();
		theTerrains     = new ArrayList<Terrain>();
		theMeeples      = new ArrayList<MeepleObject>();
		theType         = "City";
		theCompleted    = false;
		numberOfShields = 0;
		// Add and update meepels and shields
		addTerain(aTerrain);
	}

	/**
	 * CityRegion is an object of the board that describes cities regions.
	 * Use this if there is no starting terrain.
	 * @param aTerrains Set of terrain that is included in the region.
	 * @return CityRegion
	 */
	public CityRegion(ArrayList<Terrain> aTerrains)
	{
		// Region ID becomes the first terrain's ID
		theRegionID     = aTerrains.get(0).getTerrainID();
		theTerrains     = new ArrayList<Terrain>();
		theMeeples      = new ArrayList<MeepleObject>();
		theType         = "City";
		theCompleted    = false;
		numberOfShields = 0;
		// Add all and update meepels and shields
		addTerain(aTerrains);
	}

	// Getters
	/**
	 * Checks if the city is complete
	 * @return boolean
	 */
	public boolean isCompleted()
	{
		return theCompleted;
	}

	/**
	 * Get number of shileds in the region
	 * @return int
	 */
	public int getShields()
	{
		return numberOfShields;
	}

	// Setters
	/**
	 * Makes the city complete. Does not remove meeples.
	 */
	public void makeCompleted()
	{
		theCompleted = true;
	}

	// Mutators
	/**
	 * Updates the status of city completion by checking if every segment has
	 * cities attached to it's connection points.
	 */
	public void updateCompletion()
	{
		theCompleted = true;
		ArrayList<Integer> currentCityConnections;
		for (int i = 0; i < theTerrains.size(); i++)
		{
			// Check every cities terrain connections
			currentCityConnections = theTerrains.get(i).getTileConnections();
			for (int j = 0; j < currentCityConnections.size(); j++)
			{
				// TODO: Get types of connections from tile connections
				/*if (currentCityConnections.get(j).getType() != "City")
				{
					theCompleted = false;
					break;
				}*/
			}
		}
	}

	/**
	 * Check if a single terrain is valid, and adds meeples, shields and terrain
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

		// Add shield
		if (((CityTerrain) aTerrain).hasShield() == true)
		{
			numberOfShields++;
		}

		updateCompletion();
	}

}
