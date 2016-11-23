package entities;

import java.util.ArrayList;

/**
 * A Lake Region. Collection of terrain that exist on a board.
 */
public class LakeRegion extends Region
{

	// Lake specifc properties
	protected boolean theCompleted;
	protected ArrayList<Animal> theAnimals;
	protected int endsNeeded;
	protected ArrayList<CrocodileObject> theCrocodiles; // Must add Crocodiles in addTerrain

	// Constructor

	/**
	 * LakeRegion is an object of the board that describes cities regions.
	 * Use this if there is no starting terrain.
	 * @param aTerrain Single terrain that is included in the region.
	 * @return LakeRegion
	 */
	public LakeRegion(Terrain aTerrain)
	{
		// Region ID becomes the terrain's ID
		theRegionID  = aTerrain.getTerrainID();
		theTerrains  = new ArrayList<Terrain>();
		theTigers    = new ArrayList<TigerObject>();
		theType      = 'L';
		theCompleted = false;
		theAnimals   = new ArrayList<Animal>();
		theCrocodiles = new ArrayList<Crocodile>();
		// Add and update meepels and shields
		addTerrain(aTerrain, theRegionID);
	}

	/**
	 * LakeRegion is an object of the board that describes cities regions.
	 * Use this if there is no starting terrain.
	 * @param aTerrains Set of terrain that is included in the region.
	 * @return LakeRegion
	 */
	public LakeRegion(ArrayList<Terrain> aTerrains)
	{
		// Region ID becomes the first terrain's ID
		theRegionID  = aTerrains.get(0).getTerrainID();
		theTerrains  = new ArrayList<Terrain>();
		theTigers    = new ArrayList<TigerObject>();
		theType      = 'L';
		theCompleted = false;
		theAnimals   = new ArrayList<Animal>();
		theCrocodiles = new ArrayList<Crocodile>();
		// Add all and update meepels and shields
		addTerrain(aTerrains, theRegionID);
	}

	// Getters

	/**
	 * Checks if the Lake is complete
	 * @return boolean
	 */
	public boolean isCompleted()
	{
		return theCompleted;
	}

	/**
	 * Returns array list of Crocodiles in this region.
	 * @return ArrayList<CrocodilesObject>
	 */
	public ArrayList<CrocodileObject> getCrocodiles()
	{
		return theCrocodiles;
	}

	/**
	 * Gets number of unique animals in region
	 * @return int
	 */
	public int getUniqueAnimals()
	{
		// Add only one animal of each type to uniqueAnimals
		ArrayList<Character> uniqueAnimals = new ArrayList<Character>();
		char tempType;
		for (int i = 0; i < theAnimals.size(); i++)
		{
			tempType = theAnimals.get(i).getType();
			if (uniqueAnimals.contains(tempType))
			{
				uniqueAnimals.add(tempType);
			}
		}
		return uniqueAnimals.size();
	}

	// Mutators

	/**
	 * Check if there are any Crocodiles in this region.
	 * @return boolean
	 */
	public boolean hasCrocodiles()
	{
		boolean result = false;
		// Are any Crocodile in the array
		if (theCrocodiles.size() != 0)
		{
			result = true;
		}
		return result;
	}

	/**
	 * Goes through the current train and updates the held Crocodile.
	 */
	public void updateCrocodiles()
	{
		// Go through all the Terrain adding Crocodile
		for (int i = 0; i < theTerrains.size(); i++)
		{
			if (theTerrains.get(i).hasCrocodile() == true)
			{
				theCrocodiles.add(theTerrains.get(i).getCrocodile());
			}
		}
	}

	/**
	 * Removes all Crocodile from this region and terrain.
	 */
	public void removeAllCrocodile()
	{
		theCrocodiles.clear();
		for (int i = 0; i < theTerrains.size(); i++)
		{
			if (theTerrains.get(i).hasCrocodile())
			{
				theTerrains.get(i).removeCrocodile();
			}
		}
	}

	/**
	 * Updates the status of Lake completion by checking if every segment has
	 * lakes attached to it's connection points.
	 */
	public void updateCompletion()
	{
		theCompleted = true;
		ArrayList<Integer> currentLakeConnections;
		for (int i = 0; i < theTerrains.size(); i++)
		{
			// Check every lakes terrain connections
			currentLakeConnections = theTerrains.get(i).getTileConnections();
			for (int j = 0; j < currentLakeConnections.size(); j++)
			{
				// TODO: Get types of connections from tile connections
				/*if (currentLakeConnections.get(j).getType() != 'L')
				{
					theCompleted = false;
					break;
				}*/
			}
		}
	}

	/**
	 * Check if a single terrain is valid, and adds Tigers, shields and terrain
	 * to region. Updates completion status.
	 * @param aTerrain A single terrain
	 */
//	public void addTerrain(Terrain aTerrain, int regionID)
//	{
////		// Check if the type is right
////		if (theType != aTerrain.getType())
////		{
////			throw new IllegalArgumentException("Mismatch terrain");
////		}
//
//		// Check if region is already complete
//		if (theCompleted == true)
//		{
//			throw new IllegalArgumentException("Road already complete");
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
////
////		// Add animals
////		if (((LakeTerrain) aTerrain).hasAnimal() == true)
////		{
////			theAnimals.add(((LakeTerrain) aTerrain).getAnimal());
////		}
//
////		updateCompletion();
//	}

	// Deprecated

	/**
	 * DO NOT USE, testing only. LakeRegion is an object of the board that describes cities regions.
	 * Use this if there is no starting terrain.
	 * @param aRegionID A unique ID derived from the tile and region
	 * @return LakeRegion
	 */
	public LakeRegion(int aRegionID)
	{
		theRegionID  = aRegionID;
		theTerrains  = new ArrayList<Terrain>();
		theTigers    = new ArrayList<TigerObject>();
		theCompleted = false;
		theType      = 'L';
		theAnimals   = null;
	}

}
