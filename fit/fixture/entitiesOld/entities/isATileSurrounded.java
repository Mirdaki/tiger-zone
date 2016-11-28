package entities;

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
			TigerTile tileOne = new TigerTile("TJTJ-", 0);
			Location locationOne = new Location(0, 1);

			TigerTile tileTwo = new TigerTile("TJTJ-", 0);
			Location locationTwo = new Location(0, -1);

			TigerTile tileThree = new TigerTile("JLJL-", 0);
			Location locationThree = new Location(1, 0);

			TigerTile tileFour = new TigerTile("JLLJ-", 0);
			Location locationFour = new Location(1, -1);

			TigerTile tileFive = new TigerTile("JLLJ-", 3);
			Location locationFive = new Location(1, 1);

			TigerTile tileSix = new TigerTile("JJJJ-", 0);
			Location locationSix = new Location(0, -1);

			TigerTile tileSeven = new TigerTile("LJJJ-", 0);
			Location locationSeven = new Location(-1, 1);

			TigerTile tileEight = new TigerTile("LJJJ-", 2);
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