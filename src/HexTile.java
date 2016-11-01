public class HexTile extends TileObject {
	
	public HexTile()
	{
		
	}
	
	public HexTile(int tileID, int value, int x, int y)
	{
		super();
		this.tileID = tileID;
		this.value = value;
		this.x = x;
		this.y = y;
		this.numEdges = 6;
		this.numVertices = 6;
		this.orientation = 0;
	}
	
	public String toString() {
		return "ID: " + this.tileID + "\nCard value: " + this.value + "\n(x,y) coordinate: (" + this.x + "," + this.y + ")\nEdge count: " 
				+ this.numEdges + "\nVertex count: " + this.numVertices + "\nOrientation: " + this.orientation;
	}
	
}//end SquareTile