package entities;

import java.util.ArrayList;

/**
 * A Lake Region. Collection of terrain that exist on a board.
 */
public class LakeRegion extends Region
{

	// Lake specific properties
	protected ArrayList<Animal> theAnimals;
<<<<<<< HEAD
	protected boolean isLakeEnd;
	protected int numEndsNeeded;
=======
	protected int endsNeeded;
	protected ArrayList<CrocodileObject> theCrocodiles; // Must add Crocodiles in addTerrain

>>>>>>> origin/master
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

		int num = aTerrain.getTileConnections().size();
		numEndsNeeded = adjust(num);
	}
	
	public int adjust(int num) { 
		switch(num) {
			case 1: return 1;  
			case 3: return 2; 
			case 5: return 3; 
			case 8: return 4; 
			default: return num;
		}
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
			if (!uniqueAnimals.contains(tempType))
			{
				uniqueAnimals.add(tempType);
			}
		}
		return uniqueAnimals.size();
	}

	// Mutators
	
	public void markComplete() {

<<<<<<< HEAD
		if(!isLakeEnd) { 
			ArrayList<Integer> checker = new ArrayList<Integer>();
			checker.addAll(theTerrains.get(0).getTileConnections());
			
			for (int i = 1; i < theTerrains.size(); i++) { 
				ArrayList<Integer> terrainConnect = theTerrains.get(i).getTileConnections();
				int adjustment = 2 * theTerrains.get(i).getOrientation();
				
				for (Integer spot : terrainConnect) {
					if(checker.get(checker.size()-1) == (Integer)Math.floorMod(spot - adjustment + 4,8)) {
						checker.remove((Integer)Math.floorMod(spot - adjustment + 4,8));
					}
					checker.add((Integer)Math.floorMod(spot - adjustment, 8));
				}
			}
			
			if(checker.isEmpty()) theCompleted = true;
		}
		else { 
			System.out.println(numEndsNeeded);
			int numEnds = 0;
			for (int i = 0; i < theTerrains.size(); i++) { 
				if(((LakeTerrain) theTerrains.get(i)).isEndOfLake()) { 
					numEnds++;
				}			
=======
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
>>>>>>> origin/master
			}
			if (numEnds >= numEndsNeeded) theCompleted = true;
		}
		
	}

	public void addTerrain(ArrayList<Terrain> aTerrains, int regionID) {

		int neededSize = aTerrains.size();
		for (int i = 0; i < neededSize; i++) {
			Terrain terrain = aTerrains.get(i);
			this.addTerrain(terrain, regionID);
			
			int num = terrain.getTileConnections().size();

			if (!((LakeTerrain) terrain).isEndOfLake()) numEndsNeeded += adjust(num) - 2;
		}
		markComplete();
	}
	
	/**
	 * Check if a single terrain is valid, and adds Tigers, shields and terrain
	 * to region. Updates completion status.
	 * @param aTerrain A single terrain
	 */
		public void addTerrain(Terrain aTerrain, int regionID)
		{
	//		// Check if the type is right
	//		if (theType != aTerrain.getType())
	//		{
	//			throw new IllegalArgumentException("Mismatch terrain");
	//		}
	
			// Check if region is already complete
			if (theCompleted == true)
			{
				throw new IllegalArgumentException("Road already complete");
			}
	
			// Add terrain
			aTerrain.setRegionID(regionID);
			theTerrains.add(aTerrain);
			if (!isLakeEnd) isLakeEnd = ((LakeTerrain) aTerrain).isEndOfLake();

	
			// Add Tiger
			if (aTerrain.hasTiger() == true)
			{
				theTigers.add(aTerrain.getTiger());
			}
	
			// Add animals
			if (((LakeTerrain) aTerrain).hasAnimal() == true)
			{
				theAnimals.add(((LakeTerrain) aTerrain).getAnimal());
			}
	
	//		updateCompletion();
		}

	public int getNumberOfAnimals() {
		return theAnimals.size();
	}


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

	@Override
	public String toString() {
		String regionID = String.valueOf(theRegionID);
		char regionType = theType;
		String numberOfTigers = String.valueOf(theTigers.size());
		String numberOfTerrain = String.valueOf(theTerrains.size());
		String numOfAnimals = String.valueOf(getNumberOfAnimals());
		String numOfUniqueAnimals = String.valueOf(getUniqueAnimals());
		return "The region " + regionID + " of type " + regionType + " has " +
		numberOfTigers + " Meeple(s), " + numOfAnimals + " animal(s), " + numOfUniqueAnimals + " unique, and " + numberOfTerrain + " Terrain(s)";
	}
}
