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

	public TileEdges() {
		terrainPoints = new Terrain[8];
	}

	public TileEdges(Terrain[] terrainPoints) {
		//assert equal size
		this.terrainPoints = terrainPoints;
	}

	public void placeTerrain(ArrayList<Integer> connects, Terrain terrain) {
		for (Integer entry : connects) {
			terrainPoints[entry] = terrain;
		}
	}

	public void placeTerrain(int location, Terrain terrain) {
		terrainPoints[location] = terrain;
	}

    public void updateTerrains(ArrayList<Integer> connects, int newTerrainID) {
        for (Integer entry : connects) {
            terrainPoints[entry].setTerrainID(newTerrainID);
        }
    }

	public char getType(int index) {
		return terrainPoints[index].getType();
	}

	public Terrain[] getTerrains() {
		return terrainPoints;
	}

	public Terrain getTerrain(int index) {
		return terrainPoints[index];
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

		if (edges.getType(index % 9) == this.getType((index + 4) % 9)) return true;
		return false;
	}

	@Override
	public String toString() {

		return	"TOP:\t" + terrainPoints[NORTHWEST].getType() + "\t" + terrainPoints[NORTH].getType() + " " + terrainPoints[NORTHEAST].getType() + "\n" +
		"MID:\t" + terrainPoints[WEST].getType() + "\t\t" + terrainPoints[EAST].getType() + "\n" +
		"BOT:\t" + terrainPoints[SOUTHWEST].getType() + "\t" + terrainPoints[SOUTH].getType() + "\t" + terrainPoints[SOUTHEAST].getType();
	}
}
