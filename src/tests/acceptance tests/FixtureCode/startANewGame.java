package entities;

import entities.BoardObject;
import entities.TileStack;

public class startANewGame {
	//private int playerID;  //the player's id
	//private String initial; //starting type of first tile
	//private int orientation; //orientation mappings: {i=0:0, 1:90, 2:180, 3:270}
	private BoardObject testGame = new BoardObject();
	//private Location testSite = new Location(0,0);
	private TileStack testStack;
	//private ArrayList<SquareTile> testMatches;

	//private SquareTile testSquare; 
	//private Player tester;
	public startANewGame() {

	}

	/*public void setPlayer(int playerID) {
		Player buffer = new Player(playerID);
		this.playerID = playerID;
		this.tester = buffer;
	}
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	public void setSquareTile(String type) {
		int orientBuffer = this.orientation;
		SquareTile buffer = this.testStack.getTile(type, orientBuffer);
		this.initial = type;
		this.testSquare = buffer;
	}*/

	public boolean didGameStart() {
		int beforeCount = this.testGame.tileStack.getTileCount();
		this.testGame.start();
		this.testStack = this.testGame.tileStack;
		int afterCount = this.testStack.getTileCount();
		if(beforeCount >= afterCount) {
			return false;
		}
		else{
			return true;
		}
	}
}