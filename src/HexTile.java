public class HexTile extends TileObject {
	
	public HexTile() {
	} //end constructor
	
	public HexTile(int tileID, int value, int x, int y) {
		super(tileID, value, x, y);
		this.numEdges = 6;
		this.numVertices = 6;
		this.orientation = 0;
	} //end constructor
	
}//end SquareTile