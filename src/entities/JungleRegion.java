import java.util.ArrayList;

/**
 * A Jungle Region. Collection of terrain that exist on a board.
 */
public class JungleRegion extends Region
{

	// Jungle specifc properties
	protected ArrayList<CityRegion> neighboringCities;

	/**
	 * JungleRegion is an object of the board that describes Jungles regions.
	 * Use this if there is no starting terrain.
	 * @param aRegionID A unique ID derived from the tile and region
	 * @return JungleRegion
	 */
	public JungleRegion(double aRegionID)
	{
		theRegionID       = aRegionID;
		theTerrains       = new ArrayList<Terrain>();
		theTigers        = new ArrayList<TigerObject>();
		theType           = "Jungle";
		neighboringCities = new ArrayList<CityRegion>();
	}

	/**
	 * JungleRegion is an object of the board that describes Jungles regions.
	 * Use this if there is no starting terrain.
	 * @param aTerrain Single terrain that is included in the region.
	 * @return JungleRegion
	 */
	public JungleRegion(Terrain aTerrain)
	{
		// Region ID becomes the terrain's ID
		theRegionID       = aTerrain.getTerrainID();
		theTerrains       = new ArrayList<Terrain>();
		theTigers        = new ArrayList<TigerObject>();
		theType           = "Jungle";
		neighboringCities = new ArrayList<CityRegion>();
		// Add and update meepels
		addTerain(aTerrain);
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
		theRegionID       = aTerrains.get(0).getTerrainID();
		theTerrains       = new ArrayList<Terrain>();
		theTigers        = new ArrayList<TigerObject>();
		theType           = "Jungle";
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
	 * Check if a single terrain is valid, and adds Tigers, cities and terrain
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

		// Update neignoring cities
		updateNeighboringCities();
	}

}
