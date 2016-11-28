package entities;

public class Location {

	protected int row, col;
	protected static final int ROWSIZE = 11, COLSIZE = 11;

	public Location() {
		this.row = 0;
		this.col = 0;
	}

	public Location(int row, int col) {
		this.row = row + ROWSIZE/2;
		this.col = col + COLSIZE/2;
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
		return "(" + (this.row - ROWSIZE/2) + "," + (this.col - COLSIZE/2) + ")";
	}
}
