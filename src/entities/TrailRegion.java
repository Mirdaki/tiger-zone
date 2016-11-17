package entities;

import java.util.ArrayList;

/**
 * A monastery Region. Collection of terrain that exist on a board.
 */
public class TrailRegion extends Region
{

	// Field specifc properties
	protected boolean theCompleted;

	/**
	 * TrailRegion is an object of the board that describes fields regions.
	 * Use this if there is no starting terrain.
	 * @param aRegionID A unique ID derived from the tile and region
	 * @return TrailRegion
	 */
	public TrailRegion(int aRegionID)
	{
		theRegionID  = aRegionID;
		theTerrains  = new ArrayList<Terrain>();
		theTigers    = new ArrayList<TigerObject>();
		theType      = "Trail";
		theCompleted = false;
	}

	/**
	 * TrailRegion is an object of the board that describes fields regions.
	 * Use this if there is no starting terrain.
	 * @param aTerrain Single terrain that is included in the region.
	 * @return TrailRegion
	 */
	public TrailRegion(Terrain aTerrain)
	{
		// Region ID becomes the terrain's ID
		theRegionID  = aTerrain.getTerrainID();
		theTerrains  = new ArrayList<Terrain>();
		theTigers    = new ArrayList<TigerObject>();
		theType      = "Trail";
		theCompleted = false;
		// Add and update meepels
		addTerain(aTerrain);
	}

	/**
	 * TrailRegion is an object of the board that describes fields regions.
	 * Use this if there is no starting terrain.
	 * @param aTerrains Set of terrain that is included in the region.
	 * @return TrailRegion
	 */
	public TrailRegion(ArrayList<Terrain> aTerrains)
	{
		// Region ID becomes the first terrain's ID
		theRegionID      = aTerrains.get(0).getTerrainID();
		theTerrains      = new ArrayList<Terrain>();
		theTigers        = new ArrayList<TigerObject>();
		theType          = "Trail";
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
	 * Updates the status of Trail completion by checking if every segment has
	 * two connections. Requires that endOfTrails don't count as null.
	 */
	public void updateCompletion()
	{
		theCompleted = true;
		for (int i = 0; i < theTerrains.size(); i++)
		{
			// Check for a a front and back to everything
			// TODO: getInFront() and getBehind() for terrain in front and behind
			// beind the Trail
			/*if (theTerrains.get(i).getInFront() == null ||
			theTerrains.get(i).getBehind() == null)
			{
				theCompleted = false;
				break;
			}*/
		}
	}

	/**
	 * Check if a single terrain is valid, and adds Tigers, and terrain
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
			throw new IllegalArgumentException("Trail already complete");
		}

		// Add terrain
		theTerrains.add(aTerrain);

		// Add Tiger
		if (aTerrain.hasTiger() == true)
		{
			theTigers.add(aTerrain.getTiger());
		}

		// Update neighboring tiles
		updateCompletion();
	}

}
