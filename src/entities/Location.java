package entities;

public class Location {

	//location attributes - adjust ROWSIZE and COLSIZE as needed
	protected int row, col;
	protected static final int ROWSIZE = 11, COLSIZE = 11;

	//locations are relative to starting location. assume center of board is (0,0),
	//so halve the ROWSIZE/COLSIZE for relative locations
	public Location() {
		this.row = BoardObject.ROWSIZE/2;
		this.col = BoardObject.COLSIZE/2;
	}

	public Location(int row, int col) {
		this.row = row + BoardObject.ROWSIZE/2;
		this.col = col + BoardObject.COLSIZE/2;
	}

	public Location(Location coord) {
		row = coord.row;
		col = coord.col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	public boolean equals(Location coord) {
		if (this.row != coord.row || this.col != coord.col) return false;
		return true;
	}

	public String toString() {
		return "(" + (this.row - BoardObject.ROWSIZE/2) + "," + (this.col - BoardObject.COLSIZE/2) + ")";
	}
}
