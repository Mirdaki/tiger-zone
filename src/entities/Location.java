package entities;

public class Location {

	//location attributes - adjust ROWSIZE and COLSIZE as needed
	protected int x, y;
	protected static final int ROWSIZE = 11, COLSIZE = 11;

	//locations are relative to starting location. assume center of board is (0,0),
	//so halve the ROWSIZE/COLSIZE for relative locations
	public Location() {
		this.x = BoardObject.COLSIZE/2;
		this.y = BoardObject.COLSIZE/2;
	}

	public Location(int x, int y) {
		this.x = BoardObject.COLSIZE/2 + x;
		this.y = BoardObject.ROWSIZE/2 - y;
	}

	public Location(Location coord) {
		x = coord.x;
		y = coord.y;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}
	public boolean equals(Location coord) {
		if (this.y != coord.y || this.x != coord.x) return false;
		return true;
	}

	public String toString() {
		return "(" + (this.x - BoardObject.COLSIZE/2) + "," + (BoardObject.ROWSIZE/2 - this.y) + ")";
	}
}
