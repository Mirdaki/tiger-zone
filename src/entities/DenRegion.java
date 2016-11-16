package entities;
import java.util.ArrayList;

/**
 * A den Region. Collection of terrain that exist on a board.
 */
public class DenRegion extends Region
{

	// Field specifc properties
	protected ArrayList<Terrain> theNeighboringTiles;

	/**
	 * DenRegion is an object of the board that describes fields regions.
	 * Use this if there is no starting terrain.
	 * @param aRegionID A unique ID derived from the tile and region
	 * @return DenRegion
	 */
	public DenRegion(int aRegionID)
	{
		theRegionID         = aRegionID;
		theTerrains         = new ArrayList<Terrain>();
		theTigers           = new ArrayList<TigerObject>();
		theType             = "Den";
		theNeighboringTiles = new ArrayList<Terrain>();
	}

	/**
	 * DenRegion is an object of the board that describes fields regions.
	 * Use this if there is no starting terrain.
	 * @param aTerrain Single terrain that is included in the region.
	 * @return DenRegion
	 */
	public DenRegion(Terrain aTerrain)
	{
		// Region ID becomes the terrain's ID
		theRegionID         = aTerrain.getTerrainID();
		theTerrains         = new ArrayList<Terrain>();
		theTigers           = new ArrayList<TigerObject>();
		theType             = "Den";
		theNeighboringTiles = new ArrayList<Terrain>();
		// Add and update meepels
		addTerain(aTerrain);
	}

	// /**
	//  * DenRegion is an object of the board that describes fields regions.
	//  * Use this if there is no starting terrain.
	//  * @param aTerrains Set of terrain that is included in the region.
	//  * @return DenRegion
	//  */
	// public DenRegion(ArrayList<Terrain> aTerrains)
	// {
	// 	// Region ID becomes the first terrain's ID
	// 	theRegionID      = aTerrains.get(0).getTerrainID();
	// 	theTerrains      = new ArrayList<Terrain>();
	// 	theTigers       = new ArrayList<TigerObject>();
	// 	theType          = "Field";
	// 	theNeighboringTiles = new ArrayList<Terrain>();
	// 	// Add all and update meepels
	// 	addTerain(aTerrains);
	// }

	// Getters
	/**
	 * Get number of neighboring tiles
	 * @return int
	 */
	public int getNumberOfNeighboringLakes()
	{
		updateNeighboringTiles();
		return theNeighboringTiles.size();
	}

	// Mutators
	/**
	 * Update the list of neighboring tiles. NOT WORKING YET.
	 */
	public void updateNeighboringTiles()
	{
		// TODO: Some method to add neighboriung tiles
	}

	/**
	 * Check if a single terrain is valid, and adds Tigers, tiles and terrain
	 * to region.
	 * @param aTerrain A single terrain
	 */
	public void addTerain(Terrain aTerrain)
	{
		// Check if the type is right
		if (theType != aTerrain.getType())
		{
			throw new IllegalArgumentException("Mismatch terrain");
		}

		// Add terrain
		theTerrains.add(aTerrain);

		// Add Tiger
		if (aTerrain.hasTiger() == true)
		{
			theTigers.add(aTerrain.getTiger());
		}

		// Update neighboring tiles
		updateNeighboringTiles();
	}

}
