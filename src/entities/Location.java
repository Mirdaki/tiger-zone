package entities;

/*
 * This class represents a location on the coordinate system. It is based on the
 * ROWSIZE and COLSIZE specified in the BoardObject!
 * 
 * NOTE: Locations are relative to the starting location. The center of the board
 * is assumed to always be (0,0) and spread outwards in the cartersian coordinate
 * system. 
 * 
 * Keep in mind that "up/down (y)" is essentially our row, while "left/right (x)"
 * is our column.
 * 
 * "(0,0)" is therefore just (COWSIZE/2, ROLSIZE/2)
 */
public class Location {

	//location attributes - adjust ROWSIZE and COLSIZE as needed
	protected int x, y;
	
	//locations are relative to starting location. assume center of board is (0,0),
	//so halve the ROWSIZE/COLSIZE for relative locations

	//constructors
	public Location() {
		this.x = BoardObject.COLSIZE/2;
		this.y = BoardObject.ROWSIZE/2;
	}

	public Location(int x, int y) {
		this.x = (BoardObject.COLSIZE/2 + x);
		this.y = (BoardObject.ROWSIZE/2 - y);
	}
	
	//ACCESSORSS

	public int getY() {
		return y + BoardObject.startY;
	}

	public int getX() {
		return x - BoardObject.startX;
	}
	
	//MUTATORS
	public void setX(int x) { 
		this.x = x;
	}
	
	public void setY(int y) { 
		this.y = y;
	}
	
	//METHODS
	
	public boolean equals(Location coord) {
		if (this.y != coord.y || this.x != coord.x) return false;
		return true;
	}

	@Override
	public String toString() {
		String one = "(" + ((this.x - BoardObject.COLSIZE/2)) + "," + ((BoardObject.ROWSIZE/2 - this.y)) + ")";
		//String two = "(" + (this.x) + "," + (this.y) + ")";
		return one;
	}
}
