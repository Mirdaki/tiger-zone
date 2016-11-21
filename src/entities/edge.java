package entities;

import java.util.ArrayList;
import java.util.List;

public class edge {

	protected List<Character> points;

	protected char type;

	//constructor
	public edge(char top, char mid, char bot) {
		points = new ArrayList<Character>();
		points.add(top);
		points.add(mid);
		points.add(bot);
		this.type = mid;
	}

	//getters and setters, will comment later
	public char getType() {
		return type;
	}

	public char getTop() {
		return points.get(0);
	}

	public char getMid() {
		return points.get(1);
	}

	public char getBot() {
		return points.get(2);
	}

	public void setTop(char top) {
		points.set(0, top);
	}

	public void setMid(char mid) {
		points.set(1, mid);
	}

	public void setBot(char bot) {
		points.set(2, bot);
	}

	public void setType(char type) {
		this.type = type;
	}

	/**
	 * equals() compares two edges with one another
	 * @param edge the edge to compare
	 * @return true or false based on if they were equal or not
	 */
	public boolean equals(edge edge) {

		//if equals
		if (this.getTop() == edge.getTop() && this.getMid() == edge.getMid() && this.getBot() == edge.getBot()) {
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
		return "Top: " + points.get(0) + " Mid: " + points.get(1) + " Bot: " + points.get(2);
	}
}
