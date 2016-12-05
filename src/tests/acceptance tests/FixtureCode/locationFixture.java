package tigerFixture;
import entities.Location;

public class locationFixture {
	private int row, col, rowEq, colEq;
	Location testPlane = new Location();

	//Why is rowsize/2 and colsize/2 being added?

	public void setRowTest(int row) {
		Location testPlane = new Location(row, 0);
		this.row = testPlane.getRow();
		this.rowEq = testPlane.getRow();	
	}

	public int getRow() {
		return row;
	}

	public void setColTest(int col) {
		Location testPlane = new Location(0, col);
		this.col = testPlane.getCol();
		this.colEq = testPlane.getCol();
	}

	public int getCol() {
		return col;
	}

	public boolean equals() {
		//Location isEqualTo = new Location(this.x, this.y);
		//return testPlane.equals(isEqualTo);
		if (this.row != this.rowEq || this.col != this.colEq) return false;
		return true;
	}
	
	public String toString() {
		//return testPlane.toString();
		return "(" + this.row + "," + this.col + ")";
	}

}