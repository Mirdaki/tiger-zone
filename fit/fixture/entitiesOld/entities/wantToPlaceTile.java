package entities;


public class wantToPlaceTile {
	private int orientation;
	private String tileType;
	private BoardObject testGame = new BoardObject();
	private TileStack testStack = testGame.tileStack;
	private TigerTile testSquare;
	private Location testSite = new Location(0,0);

	//sets orientation for tile that will be created
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	//creates the tile
	public void setSquareTile(String type) {
		int orientBuffer = this.orientation;
		TigerTile buffer = this.testStack.getTile(type, orientBuffer);
		this.tileType = type;
		this.testSquare = buffer;
	}

	//tests to see if the tile was placed
	public boolean wasTilePlaced() {
		TigerTile tileBuffer = this.testSquare;
		Location placeBuffer = this.testSite;
		if(!testGame.place(tileBuffer,placeBuffer)){
			return false;
		}
		else{
			return true;
		}
	}
}