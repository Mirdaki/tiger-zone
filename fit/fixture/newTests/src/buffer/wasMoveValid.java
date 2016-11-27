package entities;


public class wasMoveValid {
	
	private int orientation, isX;
	private String tileType;
	private BoardObject testGame = new BoardObject();
	private TileStack testStack = testGame.tileStack;
	private TigerTile testSquare;
	private Location testSite;

	//sets orientation for tile that will be created
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	//sets x value
	public void setX(int x) {
		this.isX = x;
	}

	//sets y value and creates location
	public void setLocation(int y) {
		this.testSite = new Location(this.isX, y);
	}

	//creates tile
	public void setSquareTile(String type) {
		int orientBuffer = this.orientation;
		TigerTile buffer = this.testStack.getTile(type, orientBuffer);
		this.tileType = type;
		this.testSquare = buffer;
	}
	
	//checks validity
	public boolean makeValidMove() {
		this.testGame.start();
		return this.testGame.valid(testSquare, testSite);
	}
}