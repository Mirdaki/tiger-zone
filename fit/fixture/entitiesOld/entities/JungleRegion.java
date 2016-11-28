package entities;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
/**
 * A Jungle Region. Collection of terrain that exist on a board.
 */
public class JungleRegion extends Region
{

	// Jungle specifc properties
	protected ArrayList<LakeRegion> theNeighboringLakes;
	protected ArrayList<Animal> theAnimals;
	protected ArrayList<CrocodileObject> theCrocodiles; // Must add Crocodiles in addTerrain
	protected Set<Integer> adjacentLakes;
	protected Set<Integer> adjacentDens;

	// Constructor

	/**
	 * JungleRegion is an object of the board that describes Jungles regions.
	 * Use this if there is no starting terrain.
	 * @param aTerrain Single terrain that is included in the region.
	 * @return JungleRegion
	 */
	public JungleRegion(Terrain aTerrain)
	{
		// Region ID becomes the terrain's ID
		theRegionID         = aTerrain.getTerrainID();
		theTerrains         = new ArrayList<Terrain>();
		theTigers           = new ArrayList<TigerObject>();
		theType             = 'J';
		theNeighboringLakes = new ArrayList<LakeRegion>();
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
	public JungleRegion(ArrayList<Terrain> aTerrains)
	{
		// Region ID becomes the first terrain's ID
		theRegionID         = aTerrains.get(0).getTerrainID();
		theTerrains         = new ArrayList<Terrain>();
		theTigers           = new ArrayList<TigerObject>();
		theType             = 'J';
		theNeighboringLakes = new ArrayList<LakeRegion>();
		// Add all and update meepels
		addTerrain(aTerrains, theRegionID);
	}

	// Getters
	/**
	 * Get number of neighboring cities
	 * @return int
	 */
	public int getNumberOfNeighboringLakes()
	{
		updateNeighboringLakes();
		return theNeighboringLakes.size();
	}

	// Mutators
	/**
	 * Update the list of neighboring cities. NOT WORKING YET.
	 */
	public void updateNeighboringLakes()
	{
		// TODO: Some method to check neighboriung tiles for cities
	}

	/**
	 * Check if a single terrain is valid, and adds Tigers, cities and terrain
	 * to region.
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
//		// Update neignoring cities
//		updateNeighboringLakes();
//	}

	public void addTerrain(Terrain aTerrain, int regionID)
	{
//		// Check if the type is right
//		if (theType != aTerrain.getType())
//		{
//			throw new IllegalArgumentException("Mismatch terrain");
//		}

		// Check if region is already complete
		if (theCompleted == true) {
			throw new IllegalArgumentException("Road already complete");
		}

		// Add terrain
		aTerrain.setRegionID(regionID);
		theTerrains.add(aTerrain);
		adjacentLakes.addAll(((JungleTerrain) aTerrain).getLakes());

		// Add Tiger
		if (aTerrain.hasTiger() == true) {
			theTigers.add(aTerrain.getTiger());
		}

		// Add animals
		if (((JungleTerrain) aTerrain).hasAnimal() == true) {
			theAnimals.add(((JungleTerrain) aTerrain).getAnimal());
		}
		
		if (((JungleTerrain) aTerrain).hasCrocodile() == true) {
			theCrocodiles.add(((JungleTerrain) aTerrain).getCrocodile());
		}
		
		if (aTerrain.getMin2() < getMin()) { 
			recentMin = aTerrain.getMin();
		}


	}
	public void addDen(int denRegionID) { 
		adjacentDens.add(denRegionID);
	}
	
	public void removeDen(int denRegionID) { 
		adjacentDens.remove(denRegionID);
	}
	
	public Set<Integer> getDens() { 
		return adjacentDens;
	}
	
	public void addTerrain(ArrayList<Terrain> aTerrains, int regionID) {

		int neededSize = aTerrains.size();
		for (int i = 0; i < neededSize; i++) {
			Terrain terrain = aTerrains.get(i);
			this.addTerrain(terrain, regionID);
		}
	}
	// Deprecated

	/**
	 * DO NOT USE, testing only. JungleRegion is an object of the board that describes Jungles regions.
	 * Use this if there is no starting terrain.
	 * @param aRegionID A unique ID derived from the tile and region
	 * @return JungleRegion
	 */
	public JungleRegion(int aRegionID)
	{
		theRegionID         = aRegionID;
		theTerrains         = new ArrayList<Terrain>();
		theTigers           = new ArrayList<TigerObject>();
		theType             = 'J';
		theNeighboringLakes = new ArrayList<LakeRegion>();
	}

}