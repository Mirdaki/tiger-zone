package entities;

import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;
/**
 * Region is the parent class of all the regions: lakes, jungles,
 * game trails, and dens. These are a component of the board.
 */
public abstract class Region {

	// region attributes
	// recentMin is used for valid zone placement. it is updated on a play by play basis and is reset after every valid placement
	protected int theRegionID;
	protected int recentMin = Integer.MAX_VALUE; 
	protected int potentialScore; //a potential score based on underlying attributes - for AI

	protected char theType;
	protected boolean isCompleted;
	protected boolean isScored;
	protected ArrayList<Terrain> theTerrains;
	protected ArrayList<TigerObject> theTigers;
	protected ArrayList<CrocodileObject> theCrocodiles; 
	protected ArrayList<Animal> theAnimals;
	protected Set<Integer> tileList; //list of tiles associated with a region

	// Constructors

	public Region() {
		// Empty for inheretance
	}

	/**
	 * Region is an object of the board that describes cities, fields,
	 * roads, and monasteries. Use this if there is no starting terrain.
	 * @param aRegionID A unique ID derived from the tile and region
	 * @return Region
	 */
	public Region(int aRegionID) {
		theRegionID = aRegionID;
		theTerrains = new ArrayList<Terrain>();
		theTigers   = new ArrayList<TigerObject>();
		tileList	= new LinkedHashSet<Integer>();
		theCrocodiles = new ArrayList<CrocodileObject>();
	}

	/**
	 * Region is an object of the board that describes cities, fields,
	 * roads, and monasteries. Use this if there is a single terrain.
	 * @param aTerrain Single terrain that is included in the region.
	 * @return Region
	 */
	public Region(Terrain aTerrain) {

		// Region ID becomes the terrain's ID
		theRegionID = aTerrain.getTerrainID();
		theTerrains = new ArrayList<Terrain>();
		theTigers   = new ArrayList<TigerObject>();
		tileList	= new LinkedHashSet<Integer>();
		theCrocodiles = new ArrayList<CrocodileObject>();
		addTerrain(aTerrain, theRegionID);
	}

	/**
	 * Region is an object of the board that describes cities, fields,
	 * roads, and monasteries. Use this if there is a set of terrain.
	 * @param aTerrains Set of terrain that is included in the region.
	 * @return Region
	 */
	public Region(ArrayList<Terrain> aTerrains) {
		// Region ID becomes the first terrain's ID
		theRegionID = aTerrains.get(0).getTerrainID();
		theTerrains = new ArrayList<Terrain>();
		theTigers   = new ArrayList<TigerObject>();
		tileList	= new LinkedHashSet<Integer>();
		theCrocodiles = new ArrayList<CrocodileObject>();

		// Add all and update objects
		addTerrain(aTerrains, theRegionID);
	}


	// ACCESSORSS

	/**
	 * Returns array list of Crocodiles in this region.
	 * @return ArrayList<CrocodilesObject>
	 */
	public ArrayList<CrocodileObject> getCrocodiles() {
		return theCrocodiles;
	}

	public int getPotential() { 
		return potentialScore;
	}

	public Set<Integer> getTileList() { 
		return tileList;
	}

	public int getTileListSize() { 
		return tileList.size();
	}

	public int[] getNumOfAnimals() {

		//int[0] will store the TOTAL number of PREY
		//int[1] will store the TOTAL number of CROCODILES
		int numPrey = 0;
		int numCrocs = 0;

		for (int i = 0; i < theAnimals.size(); i++) { 
			if (theAnimals.get(i).getType() != 'C') numPrey++;
			else numCrocs++;
		}

		return new int[]{numPrey,numCrocs};
	}

	/**
	 * Gets number of unique animals in region
	 * @return int
	 */
	public int getUniqueAnimals() {
		// Add only one animal of each type to uniqueAnimals
		ArrayList<Character> uniqueAnimals = new ArrayList<Character>();
		char tempType;
		for (int i = 0; i < theAnimals.size(); i++) {
			tempType = theAnimals.get(i).getType();
			if (tempType != 'C' && !uniqueAnimals.contains(tempType)) { uniqueAnimals.add(tempType); }
		}
		return uniqueAnimals.size();
	}

	public int getNumOfCrocs() {
		return theCrocodiles.size();
	}

	public int getNumOfTigers() { 
		return theTigers.size();
	}

	public int getRecentMin() { 
		if(recentMin == 0) return 1;
		else if (recentMin == 1) return 2;
		else if (recentMin == 2) return 3;
		else if (recentMin == 3) return 6;
		else if (recentMin == 4) return 9;
		else if (recentMin == 5) return 8;
		else if (recentMin == 6) return 7;
		else if (recentMin == 7) return 4;
		else return recentMin;	
	}

	/**
	 * Get region ID
	 * @return int
	 */
	public int getRegionID() {
		return theRegionID;
	}

	/**
	 * Returns array list of terrains in this region.
	 * @return ArrayList<Terrain>
	 */
	public ArrayList<Terrain> getTerrains() {
		return theTerrains;
	}

	/**
	 * Returns array list of Tigers in this region.
	 * @return ArrayList<TigerObject>
	 */
	public ArrayList<TigerObject> getTigers() {
		return theTigers;
	}

	/**
	 * Get type of region
	 * @return String
	 */
	public char getType() {
		return theType;
	}

	/**
	 * Gets number of terrain in a region
	 * @return int
	 */
	public int getNumOfTerrains() {
		return theTerrains.size();
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public boolean isScored() { 
		return isScored;
	}

	/**
	 * Check if there are any Tigers in this region.
	 * @return boolean
	 */
	public boolean hasTigers() {
		// Are any Tigers in the array
		if (theTigers.size() != 0) { return true; }
		return false;
	}

	// MUTATORS
	public void setScored(boolean scored) {
		isScored = scored;
	}

	public void setPotential(int potentialScore) { 
		this.potentialScore = potentialScore;
	}

	public void setTileList(Set<Integer> tileList) { 
		this.tileList = tileList;
	}

	public void setMin(int recentMin) { 
		this.recentMin = recentMin;
	}

	public void addTiger(TigerObject stray) { 
		theTigers.add(stray);
	}

	public void addCrocodile(CrocodileObject hatchling) { 
		theCrocodiles.add(hatchling);
	}

	public TigerObject removeTiger() { 
		
		TigerObject stray = theTigers.get(0);
		theTigers.remove(0);
		
		return stray;
	}
	
	public CrocodileObject removeCrocodile() { 
		
		CrocodileObject hatchling = theCrocodiles.get(0);
		theCrocodiles.remove(0);
		
		return hatchling;
	}

	public void removeTiger(int index) { 
		
		TigerObject stray = theTigers.get(index);
		theTigers.remove(index);

		Player owner = stray.getTigerOwner();
		owner.addTiger(stray);
		
	}
	
	// Remove all Tigers
	/**
	 * Removes all Tigers from this region and adds them back to the associated players
	 */
	public void removeAllTigers() {
		for (int i = 0; i < theTigers.size(); i++) {

			TigerObject stray = theTigers.get(i);
			theTigers.remove(i);

			Player owner = stray.getTigerOwner();
			owner.addTiger(stray);
		}
	}

	/**
	 * Removes all Crocodile from this region and terrain and returns to associated players
	 */
	public void removeAllCrocodile() {
		for (int i = 0; i < theCrocodiles.size(); i++) {

			CrocodileObject hatchling = theCrocodiles.get(i);
			theCrocodiles.remove(i);

			Player owner = hatchling.getCrocodileOwner();
			owner.addCroc(hatchling);
		}
	}

	/**
	 * Check if a single terrain is valid, and adds Tigers and terrain to region.
	 * @param aTerrain A single terrain
	 */
	public void addTerrain(Terrain aTerrain, int regionID) {

		// Add terrain
		theTerrains.add(aTerrain);

		// Add Tiger
		if (aTerrain.hasTiger() == true) { theTigers.add(aTerrain.getTiger()); }
	}

	/**
	 * Check if an array list of terrain is valid, and adds Tigers and terrain
	 * to region.
	 * @param aTerrains An arrayList of terrain
	 */
	public void addTerrain(ArrayList<Terrain> aTerrains, int regionID) {

		int neededSize = aTerrains.size();
		for (int i = 0; i < neededSize; i++) { 
			this.addTerrain(aTerrains.get(i), regionID);
		}
	}

	// methods 

	/**
	 * Check if there are any Crocodiles in this region.
	 * @return boolean
	 */
	public boolean hasCrocodiles() {
		boolean result = false;
		// Are any Crocodile in the array
		if (theCrocodiles.size() != 0) { result = true; }
		return result;
	}


	/**
	 * Prints out the region ID, type, number of Tigers, and number of terrains
	 * @return String description
	 */
	public String toString() {
		int regionID = theRegionID;
		char regionType = theType;
		int numOfTigers = getNumOfTigers();
		int numOfTerrains = getNumOfTerrains();
		int numOfCrocs = getNumOfCrocs();
		int minPlacement = getRecentMin();

		return "The region " + regionID + " of type " + regionType + " has " +
		numOfTigers + " Tigers(s), + " + numOfCrocs + " crocodiles, and " + numOfTerrains + " Terrain(s). Min = " + minPlacement;
	}

}
