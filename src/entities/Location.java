
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
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
}
