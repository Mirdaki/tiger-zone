/*
 * This SquareTile object extends off of the TileObject for when we may have to change tile types
*/
public class SquareTile extends TileObject {
	
	public SquareTile()
	{
		
	}
	
	public SquareTile(int tileID, int value, int x, int y)
	{
		super();
		this.tileID = tileID;
		this.value = value;
		this.x = x;
		this.y = y;
		this.numEdges = 4;
		this.numVertices = 4;
		this.orientation = 0;
	}
	
	public String toString() {
		return "ID: " + this.tileID + "\nCard value: " + this.value + "\n(x,y) coordinate: (" + this.x + "," + this.y + ")\nEdge count: " 
				+ this.numEdges + "\nVertex count: " + this.numVertices + "\nOrientation: " + this.orientation;
	}
	
}//end SquareTile
