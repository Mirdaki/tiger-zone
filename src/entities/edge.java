package entities;

import java.util.ArrayList;
import java.util.List;

public class edge {

	protected ArrayList<Terrain> terrainPoints;
	protected char type;

	//constructor
	public edge(Terrain top, Terrain mid, Terrain bot) {
		terrainPoints = new ArrayList<Terrain>();
		terrainPoints.add(top);
		terrainPoints.add(mid);
		terrainPoints.add(bot);
		this.type = mid.getType();
	}

	//getters and setters, will comment later
	// public char getType() {
	// 	return type;
	// }

	public ArrayList<Terrain> getPoints() {
		return terrainPoints;
	}

	public char getTopType() {
		return terrainPoints.get(0).getType();
	}

	public char getMidType() {
		return terrainPoints.get(1).getType();
	}

	public char getBotType() {
		return terrainPoints.get(2).getType();
	}

	public Terrain getTop() {
		return terrainPoints.get(0);
	}

	public Terrain getMid() {
		return terrainPoints.get(1);
	}

	public Terrain getBot() {
		return terrainPoints.get(2);
	}

	public void setTop(Terrain top) {
		terrainPoints.set(0,top);
	}

	public void setMid(Terrain mid) {
		terrainPoints.set(1,mid);
	}

	public void setBot(Terrain bot) {
		terrainPoints.set(2,bot);
	}

	/**
	 * equals() compares two edges with one another
	 * @param edge the edge to compare
	 * @return true or false based on if they were equal or not
	 */

	public boolean equals(edge edge) {

		//if equals
		if (this.getTopType() == edge.getTopType() && this.getMidType() == edge.getMidType() && this.getBotType() == edge.getBotType()) {
			return true;
		}

		//if not equal
		return false;
	}

	/**
	 * toString() formats the edge information to be printable
	 * @return the String of formatted information
 	 */
	public String toString() {
//		return "Top: " + points.get(0) + " Mid: " + points.get(1) + " Bot: " + points.get(2);
		Terrain top = terrainPoints.get(0);
		Terrain mid = terrainPoints.get(1);
		Terrain bot = terrainPoints.get(2);

		return "Top: " + top.getType() + " (" + top.getTerrainID() + ") Mid: " + mid.getType() + " (" + mid.getTerrainID() + ") Bot: " + bot.getType() + " (" + bot.getTerrainID() + ")";
		// return "test";
	}
}
