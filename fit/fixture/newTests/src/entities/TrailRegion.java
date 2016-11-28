package entities;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 * A monastery Region. Collection of terrain that exist on a board.
 */
public class TrailRegion extends Region {

	// Field specifc properties
	protected char regionType;
	protected boolean isTrailEnd;

	@Override
	public int getPotential() { 
		int potential = 0;
		
		int numAnimals = getNumOfAnimals()[0] ;
		int numCrocs = getNumOfAnimals()[1];
		int numTiles = getTileListSize();
		int adjustedUnique = Math.max(0,numAnimals - numCrocs);

		if(isCompleted) { 
			potential = (numTiles) * (1 + adjustedUnique); 
		} 
		else {
			potential = (numTiles) * (1 + adjustedUnique);
		}
		return potential;
	}

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
		isCompleted = false;
		theAnimals   = new ArrayList<Animal>();
		theCrocodiles = new ArrayList<CrocodileObject>();
		tileList	= new LinkedHashSet<Integer>();
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
		isCompleted = false;
		theAnimals   = new ArrayList<Animal>();
		theCrocodiles = new ArrayList<CrocodileObject>();

		addTerrain(aTerrains, theRegionID);

	}

	// Getters

	// MUTATORS

	/**
	 * This version of addTerrain() will add all terrains provided through an ArrayList.
	 * This makes use of the overloaded addTerrain() for single cases.
	 * @param aTerrains Set of terrain that is included in the region
	 * @param regionID All terrains' new region ID
	 */
	public void addTerrain(ArrayList<Terrain> aTerrains, int regionID) {

		int neededSize = aTerrains.size();
		for (int i = 0; i < neededSize; i++) {
			Terrain terrain = aTerrains.get(i);
			this.addTerrain(terrain, regionID);
		}
		markComplete();
	}

	/**
	 * This version of addTerrain() will add an individual terrain.
	 * @param aTerrain a single terrain that is included in the region
	 * @param regionID The terrain's new region ID
	 */
	public void addTerrain(Terrain aTerrain, int regionID) {

		// Add terrain
		aTerrain.setRegionID(regionID);
		theTerrains.add(aTerrain);
		if (!isTrailEnd) isTrailEnd = ((TrailTerrain) aTerrain).isEndOfTrail();
		tileList.add(aTerrain.getTileID());

		// Add animals
		if (((TrailTerrain) aTerrain).hasAnimal() == true) {
			Animal theAnimal = ((TrailTerrain) aTerrain).getAnimal();			
			theAnimals.add(theAnimal);
		}

		// Add Tiger
		if (aTerrain.hasTiger() == true) {
			theTigers.add(aTerrain.getTiger());
		}

		if (((TrailTerrain) aTerrain).hasCrocodile() == true) {
			theCrocodiles.add(((TrailTerrain) aTerrain).getCrocodile());
		}

		if (aTerrain.getTerrainMin() < getRecentMin()) { 
			recentMin = aTerrain.getMin();
		}
	}

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

			if(checker.isEmpty()) isCompleted = true;
		}
		else { 
			int numEnds = 0;
			for (int i = 0; i < theTerrains.size(); i++) { 
				if(((TrailTerrain) theTerrains.get(i)).isEndOfTrail()) { 
					numEnds++;
				}			
			}
			if (numEnds == 2) isCompleted = true;
		}

	}
	
	@Override
	public String toString() {

		int regionID = theRegionID;
		char regionType = theType;
		int numOfTigers = getNumOfTigers();
		int numOfTerrains = getNumOfTerrains();
		int numOfCrocs = getNumOfCrocs();
		int numOfAnimals = Math.max(0,getNumOfAnimals()[0] - getNumOfAnimals()[1]);
		int numOfUniqueAnimals = getUniqueAnimals();
		String outTrailEnd = (isTrailEnd) ? "end" : "connecting";
		
		return "The " + outTrailEnd + " region " + regionID + " of type " + regionType + " has " +
		numOfTigers + " Tiger(s), " + numOfCrocs + " crocodile(s), " + numOfAnimals + " animal(s), " + numOfUniqueAnimals + " unique, and " + numOfTerrains + " Terrain(s).";		
	}

	// Deprecated

	/**
	 * DO NOT USE, testing only. TrailRegion is an object of the board that describes fields regions.
	 * Use this if there is no starting terrain.
	 * @param aRegionID A unique ID derived from the tile and region
	 * @return TrailRegion
	 */
	public TrailRegion(int aRegionID) {
		theRegionID  = aRegionID;
		theTerrains  = new ArrayList<Terrain>();
		theTigers    = new ArrayList<TigerObject>();
		theType      = 'T';
		isCompleted = false;
		theAnimals   = null;
	}
}
