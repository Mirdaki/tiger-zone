
public class Location {

	protected int x, y;

	public Location() {
		this.x = 0;
		this.y = 0;
	}

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Location(Location coord) {
		x = coord.x;
		y = coord.y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	public boolean equals(Location coord) {
		if (this.x != coord.x || this.y != coord.y) return false;
		return true;
	}
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
}
