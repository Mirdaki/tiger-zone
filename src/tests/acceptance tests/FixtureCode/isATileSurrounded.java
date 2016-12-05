package entities;

import entities.BoardObject;
import entities.Location;
import entities.SquareTile;

public class isATileSurrounded {
	
	private int isY, isX;
	private BoardObject testGame = new BoardObject();
	private Location testSite = new Location(0,0);

	//Fitnesse requires variables be passed into void functions doOrDont 
	//just means should the method run in fitnesse
	//a value of 0 allows method to run
	public void setSurroundATile(int doOrDont) {
		//starts game on testGame and then creates tiles to surround first placed tile
		if(doOrDont == 0) {
			this.testGame.start();

			//hard coded the tiles here rather than in fitnesse
			//only need to test that a tile can recogize it is surrounded
			SquareTile tileOne = new SquareTile("TJTJ-", 0);
			Location locationOne = new Location(0, 1);

			SquareTile tileTwo = new SquareTile("TJTJ-", 0);
			Location locationTwo = new Location(0, -1);

			SquareTile tileThree = new SquareTile("JLJL-", 0);
			Location locationThree = new Location(1, 0);

			SquareTile tileFour = new SquareTile("JLLJ-", 0);
			Location locationFour = new Location(1, -1);

			SquareTile tileFive = new SquareTile("JLLJ-", 3);
			Location locationFive = new Location(1, 1);

			SquareTile tileSix = new SquareTile("JJJJ-", 0);
			Location locationSix = new Location(0, -1);

			SquareTile tileSeven = new SquareTile("LJJJ-", 0);
			Location locationSeven = new Location(-1, 1);

			SquareTile tileEight = new SquareTile("LJJJ-", 2);
			Location locationEight = new Location(-1, -1);

			this.testGame.place(tileOne, locationOne);
			this.testGame.place(tileTwo, locationTwo);
			this.testGame.place(tileThree, locationThree);
			this.testGame.place(tileFour, locationFour);
			this.testGame.place(tileFive, locationFive);
			this.testGame.place(tileSix, locationSix);
			this.testGame.place(tileSeven, locationSeven);
			this.testGame.place(tileEight, locationEight);
		}
		else {

		}
	}

	//has testGame check that the first tile placed is surrounded
	public boolean surrounded() {
		return this.testGame.isSurrounded(this.testSite);
	}
}