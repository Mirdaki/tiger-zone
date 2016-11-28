package entities;

import java.util.ArrayList;
import java.util.List;

public class TileEdges {

	public static final int NORTHWEST = 0;
	public static final int NORTH = 1;
	public static final int NORTHEAST = 2;
	public static final int EAST = 3;
	public static final int SOUTHEAST = 4;
	public static final int SOUTH = 5;
	public static final int SOUTHWEST = 6;
	public static final int WEST = 7;

	protected Terrain[] terrainPoints;
	protected Terrain[] uniqueTerrains;
	protected int orientation; 

	public TileEdges() {
		terrainPoints = new Terrain[8];
		orientation = 0;
	}

	public TileEdges(Terrain[] terrainPoints, Terrain[] uniqueTerrains) {
		//assert equal size
		this.terrainPoints = terrainPoints;
		this.uniqueTerrains = uniqueTerrains;
		this.orientation = 0;
	}

	//	public void placeTerrain(ArrayList<Integer> connects, Terrain terrain) {
	//		for (Integer location : connects) {
	//			terrainPoints[Math.floorMod((location - 2 * orientation) + 8,8)] = terrain;
	//		}
	//	}
	//
	//	public void placeTerrain(int location, Terrain terrain) {
	//		terrainPoints[Math.floorMod((location - 2 * orientation) + 8,8)] = terrain;
	//	}

	public void updateTerrains(ArrayList<Integer> connects, int newTerrainID, int newOrientation) {
		for (Integer entry : connects) {
			terrainPoints[linear(entry)].setTerrainID(newTerrainID);
//			terrainPoints[linear(entry)].setOrientation(newOrientation);
		}
	}

	public void updateRegions(ArrayList<Integer> connects, int newRegionID) {
		for (Integer entry : connects) {
			terrainPoints[linear(entry)].setRegionID(newRegionID);
		}
	}

	public char getType(int index) {
		return terrainPoints[linear(index)].getType();
	}

	public Terrain[] getTerrains() {
		return terrainPoints;
	}
	
	public Terrain[] getUniqueTerrains() { 
		return uniqueTerrains;
	}

	public Terrain getTerrain(int index) {
		return terrainPoints[linear(index)];
	}

	public boolean equals(TileEdges edges, int index) {
		/*
		 * This has to be used very carefully. "this" will refer to the Tile we are currently placing.
		 * If we are checking the edge to our left, index should be EAST. Then "this" will be checking
		 * against its WEST.
		 *
		 * Since all of the tiles are symmetric, we only have to check against the standard four
		 * cardinal directions. This method would not work for intermediate directions. It can be
		 * modified, however.
		 *
		 * Comparisons would be:
		 * NORTH == SOUTH
		 * WEST == EAST
		 * NORTHEAST == SOUTHEAST
		 * NORTHWEST == SOUTHWEST


		 0:
		 */
		if (edges.getType(linear(index) % 8) == this.getType((linear(index) + 4) % 8)) return true;
		return false;
	}

	public char getEdge(int index) {
		return terrainPoints[linear(index)].getType();
	}

	public void setEdge(int index, int newRegionID) { 
		terrainPoints[linear(index)].setRegionID(newRegionID);
	}

	public void setOrientation(int orientation) { 
		this.orientation = orientation;
		updateTerrainOrientation(orientation);
	}

	public void updateTerrainOrientation(int orientation) { 
		for (Terrain terrain : uniqueTerrains) { 
			terrain.setOrientation(orientation);			
		}
	}
	
	public int linear(int num) { 
		int test = Math.floorMod((num + 2 * this.orientation),8);
		return test;
	}

	@Override
	public String toString() {

		return	"TOP:\t" + terrainPoints[linear(NORTHWEST)].getType() + "\t" + terrainPoints[linear(NORTH)].getType() + "\t" + terrainPoints[linear(NORTHEAST)].getType() + "\n" +
				"MID:\t" + terrainPoints[linear(WEST)].getType() + "\t\t" + terrainPoints[linear(EAST)].getType() + "\n" +
				"BOT:\t" + terrainPoints[linear(SOUTHWEST)].getType() + "\t" + terrainPoints[linear(SOUTH)].getType() + "\t" + terrainPoints[SOUTHEAST].getType();
	}
}
