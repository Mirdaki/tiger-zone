package entities;


public class seeAvailableTiles {

	private BoardObject testGame = new BoardObject();
	private TileStack setMyCount = testGame.tileStack; //adjusting count tests
	private TileStack changed = testGame.tileStack; //will have things removed to check against unchanged
	private TileStack unchanged = testGame.tileStack; //tile stack to check against to make sure a tile has been removed

	public seeAvailableTiles() {

	}

	//even though the setTileCount shouldn't be used for the sake of this test it works
	public void setTileCountTest(int tileCount) {
		this.setMyCount.setTileCount(tileCount);
	}

	public int getTileCountTest() {
		return this.setMyCount.getTileCount();
	}

	public void setTileToBeRemoved(String type) {
		this.changed.removeTile(type);
	}

	//looks to see if tiles were removed from the stack after a player uses a tile
	public boolean tileRemoved() {
		if(this.unchanged.getTileCount() > this.changed.getTileCount()) {
			return true;
		}
		else {
			return false;
		}
	}

	//tests for existence of tile stacks the player can see
	public boolean seeTileStack() {
		if(this.changed != null || this.unchanged != null) {
			return true;
		}
		else {
			return false;
		}
	}
}