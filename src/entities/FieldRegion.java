import java.util.ArrayList;

/**
 * A field Region. Collection of terrain that exist on a board.
 */
public class FieldRegion extends Region
{

	// Field specifc properties
	protected ArrayList<CityRegion> neighboringCities;

	/**
	 * FieldRegion is an object of the board that describes fields regions.
	 * Use this if there is no starting terrain.
	 * @param aRegionID A unique ID derived from the tile and region
	 * @return FieldRegion
	 */
	public FieldRegion(double aRegionID)
	{
		theRegionID       = aRegionID;
		theTerrains       = new ArrayList<Terrain>();
		theMeeples        = new ArrayList<MeepleObject>();
		theType           = "Field";
		neighboringCities = new ArrayList<CityRegion>();
	}

	/**
	 * FieldRegion is an object of the board that describes fields regions.
	 * Use this if there is no starting terrain.
	 * @param aTerrain Single terrain that is included in the region.
	 * @return FieldRegion
	 */
	public FieldRegion(Terrain aTerrain)
	{
		// Region ID becomes the terrain's ID
		theRegionID       = aTerrain.getTerrainID();
		theTerrains       = new ArrayList<Terrain>();
		theMeeples        = new ArrayList<MeepleObject>();
		theType           = "Field";
		neighboringCities = new ArrayList<CityRegion>();
		// Add and update meepels
		addTerain(aTerrain);
	}

	/**
	 * FieldRegion is an object of the board that describes fields regions.
	 * Use this if there is no starting terrain.
	 * @param aTerrains Set of terrain that is included in the region.
	 * @return FieldRegion
	 */
	public FieldRegion(ArrayList<Terrain> aTerrains)
	{
		// Region ID becomes the first terrain's ID
		theRegionID       = aTerrains.get(0).getTerrainID();
		theTerrains       = new ArrayList<Terrain>();
		theMeeples        = new ArrayList<MeepleObject>();
		theType           = "Field";
		neighboringCities = new ArrayList<CityRegion>();
		// Add all and update meepels
		addTerain(aTerrains);
	}

	// Getters
	/**
	 * Get number of neighboring cities
	 * @return int
	 */
	public int getNumberOfNeighboringCities()
	{
		updateNeighboringCities();
		return neighboringCities.size();
	}

	// Mutators
	/**
	 * Update the list of neighboring cities. NOT WORKING YET.
	 */
	public void updateNeighboringCities()
	{
		// TODO: Some method to check neighboriung tiles for cities
	}

	/**
	 * Check if a single terrain is valid, and adds meeples, cities and terrain
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

		// Add meeple
		if (aTerrain.hasMeeple() == true)
		{
			theMeeples.add(aTerrain.getMeeple());
		}

		// Update neignoring cities
		updateNeighboringCities();
	}

}
