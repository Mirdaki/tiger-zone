package entities;

import java.util.ArrayList;

/**
 * A monastery Region. Collection of terrain that exist on a board.
 */
public class TrailRegion extends Region
{

	// Field specifc properties
	protected boolean theCompleted;
	protected ArrayList<Animal> theAnimals;
	protected char regionType;

	// Constructor

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
		theType      = 'T';
		theCompleted = false;
		theAnimals   = new ArrayList<Animal>();
		// Add and update meepels
		addTerrain(aTerrain, theRegionID);
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
		theRegionID  = aTerrains.get(0).getTerrainID();
		theTerrains  = new ArrayList<Terrain>();
		theTigers    = new ArrayList<TigerObject>();
		theType      = 'T';
		theCompleted = false;
		theAnimals   = new ArrayList<Animal>();
		// Add all and update meepels
		addTerrain(aTerrains, theRegionID);
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

	/**
	 * Get number of aniamls in the region
	 * @return int
	 */
	public int getNumberOfAnimals()
	{
		return theAnimals.size();
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
//	public void addTerrain(Terrain aTerrain, int regionID)
//	{
//		// Check if the type is right
//		if (theType != aTerrain.getType())
//		{
//			throw new IllegalArgumentException("Mismatch terrain");
//		}
//
//		// Check if region is already complete
//		if (theCompleted == true)
//		{
//			throw new IllegalArgumentException("Trail already complete");
//		}
//
//		// Add terrain
//		aTerrain.setRegionID(regionID);
//		theTerrains.add(aTerrain);
//
//		// Add Tiger
//		if (aTerrain.hasTiger() == true)
//		{
//			theTigers.add(aTerrain.getTiger());
//		}
//
//		// Update neighboring tiles
//		updateCompletion();
//	}

	// Deprecated

	/**
	 * DO NOT USE, testing only. TrailRegion is an object of the board that describes fields regions.
	 * Use this if there is no starting terrain.
	 * @param aRegionID A unique ID derived from the tile and region
	 * @return TrailRegion
	 */
	public TrailRegion(int aRegionID)
	{
		theRegionID  = aRegionID;
		theTerrains  = new ArrayList<Terrain>();
		theTigers    = new ArrayList<TigerObject>();
		theType      = 'T';
		theCompleted = false;
		theAnimals   = null;
	}

}
