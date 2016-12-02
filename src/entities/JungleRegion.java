package entities;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
/**
 * A Jungle Region. Collection of terrain that exist on a board.
 */
public class JungleRegion extends Region {

	// Jungle specific properties
	protected Set<Integer> adjacentLakes;
	protected Set<Integer> adjacentDens;

	// Constructor

	/**
	 * JungleRegion is an object of the board that describes Jungles regions.
	 * Use this if there is no starting terrain.
	 * @param aTerrain Single terrain that is included in the region.
	 * @return JungleRegion
	 */
	public JungleRegion(Terrain aTerrain) {
		// Region ID becomes the terrain's ID
		theRegionID         = aTerrain.getTerrainID();
		theTerrains         = new ArrayList<Terrain>();
		theTigers           = new ArrayList<TigerObject>();
		theCrocodiles = new ArrayList<CrocodileObject>();

		theType             = 'J';
		adjacentLakes 		= new LinkedHashSet<Integer>();
		adjacentDens		= new LinkedHashSet<Integer>();

		// Add and update meepels
		addTerrain(aTerrain, theRegionID);
	}

	/**
	 * JungleRegion is an object of the board that describes Jungles regions.
	 * Use this if there is no starting terrain.
	 * @param aTerrains Set of terrain that is included in the region.
	 * @return JungleRegion
	 */
	public JungleRegion(ArrayList<Terrain> aTerrains) {
		// Region ID becomes the first terrain's ID
		theRegionID         = aTerrains.get(0).getTerrainID();
		theTerrains         = new ArrayList<Terrain>();
		theTigers           = new ArrayList<TigerObject>();
		theCrocodiles = new ArrayList<CrocodileObject>();

		theType             = 'J';
		// Add all and update meepels
		addTerrain(aTerrains, theRegionID);
	}

	// Getters

	/**
	 * getDens() will get the dens adjacent to the calling jungle
	 * @return Set of adjacent dens
	 */
	public Set<Integer> getDens() { 
		return adjacentDens;
	}

	/**
	 * getLakes() will get the lakes adjacent to the calling jungle
	 * @return Set of adjacent lakes
	 */
	public Set<Integer> getLakes() { 
		return adjacentLakes;
	}

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
	}

	/**
	 * This version of addTerrain() will add all terrains provided through an ArrayList
	 * @param aTerrain a single terrain that is included in the region
	 * @param regionID The terrain's new region ID
	 */
	public void addTerrain(Terrain aTerrain, int regionID) {

		// Check if region is already complete
		if (isCompleted == true) {
			throw new IllegalArgumentException("Road already complete");
		}

		if(aTerrain instanceof JungleTerrain) { 
			// Add terrain to the regions list of terrains, reset its region ID, and add any lakes found from the Terrain
			aTerrain.setRegionID(regionID);
			theTerrains.add(aTerrain);
			adjacentLakes.addAll(((JungleTerrain) aTerrain).getLakes());

			// Add the new tiger to the family (if there are any!)
			if (aTerrain.hasTiger() == true) {
				theTigers.add(aTerrain.getTiger());
			}

			// Add the animals to the prey family (if there are any!)
			if (((JungleTerrain) aTerrain).hasAnimal() == true) {
				theAnimals.add(((JungleTerrain) aTerrain).getAnimal());
			}

			// Add crocodiles to the croc family (if there are any!)
			if (((JungleTerrain) aTerrain).hasCrocodile() == true) {
				theCrocodiles.add(((JungleTerrain) aTerrain).getCrocodile());
			}

			//if the just added min is less than the most recent min, reset
			recentMin = aTerrain.getZoneMin();
		}
	}

	/**
	 * This function adds any dens found from Region merging into the den list.
	 * It's only necessary to store the ID since the board class keeps a list of the
	 * regions.
	 * @param the new den's region ID
	 */
	public void addDen(int denRegionID) { 
		adjacentDens.add(denRegionID);
	}

	/**
	 * This function removes the specified den from the region list (if found).
	 * It's only necessary to store the ID since the board class keeps a list of the
	 * regions.
	 * @param the den region to remove
	 */
	public void removeDen(int denRegionID) { 
		if (adjacentDens.contains(denRegionID)) { adjacentDens.remove(denRegionID); }
	}

	// Deprecated

	/**
	 * DO NOT USE, testing only. JungleRegion is an object of the board that describes Jungles regions.
	 * Use this if there is no starting terrain.
	 * @param aRegionID A unique ID derived from the tile and region
	 * @return JungleRegion
	 */
	public JungleRegion(int aRegionID) {
		theRegionID         = aRegionID;
		theTerrains         = new ArrayList<Terrain>();
		theTigers           = new ArrayList<TigerObject>();
		theType             = 'J';
		adjacentLakes		= new LinkedHashSet<Integer>();
		adjacentDens		= new LinkedHashSet<Integer>();
	}
}
