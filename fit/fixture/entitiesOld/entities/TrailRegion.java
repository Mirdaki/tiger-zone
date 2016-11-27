package entities;

import java.util.ArrayList;

/**
 * A monastery Region. Collection of terrain that exist on a board.
 */
public class TrailRegion extends Region
{

	// Field specifc properties
	protected ArrayList<Animal> theAnimals;
	protected char regionType;
	protected boolean isTrailEnd;
	protected ArrayList<CrocodileObject> theCrocodiles; // Must add Crocodiles in addTerrain

	// Constructor

	/**
	 * TrailRegion is an object of the board that describes fields regions.
	 * Use this if there is no starting terrain.
	 * @param aTerrain Single terrain that is included in the region.
	 * @return TrailRegion
	 */
	public TrailRegion(Terrain aTerrain) {
		// Region ID becomes the terrain's ID
		theRegionID  = aTerrain.getTerrainID();
		theTerrains  = new ArrayList<Terrain>();
		theTigers    = new ArrayList<TigerObject>();
		theType      = 'T';
		theAnimals = new ArrayList<Animal>();
		isTrailEnd = ((TrailTerrain) aTerrain).isEndOfTrail();
		theCompleted = false;
		theAnimals   = new ArrayList<Animal>();
		theCrocodiles = new ArrayList<CrocodileObject>();
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
		theCrocodiles = new ArrayList<CrocodileObject>();

		addTerrain(aTerrains, theRegionID);

	}

	// Getters



	/**
	 * Get number of aniamls in the region
	 * @return int
	 */
	public int getNumberOfAnimals() {
		return theAnimals.size();
	}

	/**
	 * Returns array list of Crocodiles in this region.
	 * @return ArrayList<CrocodilesObject>
	 */
	public ArrayList<CrocodileObject> getCrocodiles() {
		return theCrocodiles;
	}

	// Mutators

	/**
	 * Check if there are any Crocodiles in this region.
	 * @return boolean
	 */
	public boolean hasCrocodiles() {
		boolean result = false;
		// Are any Crocodile in the array
		if (theCrocodiles.size() != 0) {
			result = true;
		}
		return result;
	}

	/**
	 * Goes through the current train and updates the held Crocodile.
	 */
	public void updateCrocodiles() {
		// Go through all the Terrain adding Crocodile
		for (int i = 0; i < theTerrains.size(); i++) {
			if (((TrailTerrain) theTerrains.get(i)).hasCrocodile() == true) {
				theCrocodiles.add(((TrailTerrain) theTerrains.get(i)).getCrocodile());
			}
		}
	}

	/**
	 * Removes all Crocodile from this region and terrain.
	 */
	public void removeAllCrocodile() {
		theCrocodiles.clear();
		for (int i = 0; i < theTerrains.size(); i++) {
			if (((TrailTerrain) theTerrains.get(i)).hasCrocodile()) {
				((TrailTerrain) theTerrains.get(i)).removeCrocodile();
			}
		}
	}

	/**
	 * Updates the status of Trail completion by checking if every segment has
	 * two connections. Requires that endOfTrails don't count as null.
	 */
	public void markComplete() {

		if(!isTrailEnd) { 
			//connecting case
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
			int numEnds = 0;
			for (int i = 0; i < theTerrains.size(); i++) { 
				if(((TrailTerrain) theTerrains.get(i)).isEndOfTrail()) { 
					numEnds++;
				}			
			}
			if (numEnds == 2) theCompleted = true;
		}
		
	}
	
	/**
	 * Check if a single terrain is valid, and adds Tigers, and terrain
	 * to region. Updates completion status.
	 * @param aTerrain A single terrain
	 */
	public void addTerrain(Terrain aTerrain, int regionID) {
		// Check if the type is right
		if (theType != aTerrain.getType())
		{
			throw new IllegalArgumentException("Mismatch terrain");
		}

		// Check if region is already complete
//		if (theCompleted == true)
//		{
//			throw new IllegalArgumentException("Trail already complete");
//		}

		// Add terrain
		aTerrain.setRegionID(regionID);
		theTerrains.add(aTerrain);
		if (!isTrailEnd) isTrailEnd = ((TrailTerrain) aTerrain).isEndOfTrail();


		// Add animals
		if (((TrailTerrain) aTerrain).hasAnimal() == true) {
			theAnimals.add(((TrailTerrain) aTerrain).getAnimal());
		}

		// Add Tiger
		if (aTerrain.hasTiger() == true) {
			theTigers.add(aTerrain.getTiger());
		}

		if (((TrailTerrain) aTerrain).hasCrocodile() == true) {
			theCrocodiles.add(((TrailTerrain) aTerrain).getCrocodile());
		}

		if (aTerrain.getMin2() < getMin()) { 
			recentMin = aTerrain.getMin();
		}

	}

	public void addTerrain(ArrayList<Terrain> aTerrains, int regionID) {

		int neededSize = aTerrains.size();
		for (int i = 0; i < neededSize; i++) {
			Terrain terrain = aTerrains.get(i);
			this.addTerrain(terrain, regionID);
		}
		markComplete();
	}

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

	@Override
	public String toString() {
		String regionID = String.valueOf(theRegionID);
		char regionType = theType;
		String numberOfTigers = String.valueOf(theTigers.size());
		String numberOfTerrain = String.valueOf(theTerrains.size());
		String numOfAnimals = String.valueOf(getNumberOfAnimals());
		String isisTrailEnd = (isTrailEnd) ? "end" : "connecting";
		String minPlacement = String.valueOf(getMin());

		return "The " + isisTrailEnd + " region " + regionID + " of type " + regionType + " has " +
		numberOfTigers + " Meeple(s), " + numOfAnimals + " animal(s) and " + numberOfTerrain + " Terrain(s). Min = " + minPlacement;
	}
}
