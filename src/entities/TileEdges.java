package entities;
import java.util.ArrayList;
import java.util.List;
/*
 * This class is to maintain any and all information associated with a given tile's edges.
 * Right now, when a tile is created it is assumed to be in orientation 0. So, 
 * whenever a different orientation is needed, only its orientation variable is set. 
 * A mapping is given by the linear() function that will isomorphically map to counter
 * clockwise coordinates.
 * 
 * NOTE: tile edge points are numbered 0 to 7, with 0 being the northwest corner and 
 * working clockwise.
 */
public class TileEdges {

	//class members
	public static final int NORTHWEST = 0, NORTH = 1, NORTHEAST = 2, EAST = 3;	
	public static final int SOUTHEAST = 4, SOUTH = 5, SOUTHWEST = 6, WEST = 7;

	//tile edge attributes
	protected Terrain[] uniqueTerrains; //the individual terrains found from parsing the XML
	protected Terrain[] terrainPoints; //each individual point, terrains in multiple spots are merely copies
	protected int orientation; //orientation for mapping purposes (may remove)

	//constructors
	public TileEdges() {

	}

	public TileEdges(Terrain[] terrainPoints, Terrain[] uniqueTerrains) {
		this.terrainPoints = terrainPoints;
		this.uniqueTerrains = uniqueTerrains;
		this.orientation = 0;
	}

	//ACCESSORSS
	public char getType(int index) {
		return terrainPoints[linear(index)].getType();
	}

	public Terrain[] getTerrains() {
		return terrainPoints;
	}
	
	public Terrain[] getUniqueTerrains() { 
		return uniqueTerrains;
	}

	//get a terrain at a specific edge point
	public Terrain getTerrain(int index) {
		return terrainPoints[linear(index)];
	}

	public char getEdge(int index) {
		return terrainPoints[linear(index)].getType();
	}
	
	public char[] getEntireEdge(int index) { 
		if (index == 0) { 
			return new char[]{getEdge(0), getEdge(1), getEdge(2)};
		}
		else if (index == 1) { 
			return new char[]{getEdge(2), getEdge(3), getEdge(4)};			
		}
		else if (index == 2) { 
			return new char[]{getEdge(4), getEdge(5), getEdge(6)};
		}
		else if (index == 3) { 
			return new char[]{getEdge(6), getEdge(7), getEdge(0)};
		}
		return null;
	}
	
	public boolean equals(char[] edge1, char[] edge2) { 
		if(edge1[0] == edge2[0] && edge1[1] == edge2[1] && edge1[2] == edge2[2]) { 
			return true;
		}
		return false;
	}
		
	//MUTATORS

	//set an edge point's region ID to the new specified region
	public void setEdge(int index, int newRegionID) { 
		terrainPoints[linear(index)].setRegionID(newRegionID);
	}

	//set the orientation and update all terrain's orientations too
	public void setOrientation(int orientation) { 
		this.orientation = orientation;
		updateTerrainOrientation(orientation);
	}

	//update all of the unique terrain's orientations
	public void updateTerrainOrientation(int orientation) { 
		for (Terrain terrain : uniqueTerrains) { 
			terrain.setOrientation(orientation);			
		}
	}

	//METHODS
	
	//this method adjusts the mapping of the tiles based on a tile's orientation
	public int linear(int num) { 
		return Math.floorMod((num + 2 * this.orientation),8);
	}

	@Override
	public String toString() {
		return	"TOP:\t" + terrainPoints[linear(NORTHWEST)].getType() + "\t" + terrainPoints[linear(NORTH)].getType() + "\t" + terrainPoints[linear(NORTHEAST)].getType() + "\n" +
				"MID:\t" + terrainPoints[linear(WEST)].getType() + "\t\t" + terrainPoints[linear(EAST)].getType() + "\n" +
				"BOT:\t" + terrainPoints[linear(SOUTHWEST)].getType() + "\t" + terrainPoints[linear(SOUTH)].getType() + "\t" + terrainPoints[SOUTHEAST].getType();
	}
}
