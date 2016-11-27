package entities;

import java.util.ArrayList;


public class seeAvailableSpots {
	private BoardObject testGame = new BoardObject();
	private ArrayList<Location> theSpots = new ArrayList<Location>();

	//Fitnesse requires variables be passed into void functions doOrDont 
	//just means should the method run in fitnesse
	//a value of 0 allows method to run
	//passing complex types through fitnesse methods is more trouble than its worth
	public void setAddAvailableSpot(int doOrDont) {
		if(doOrDont == 0) {
			//I am adding the four spots around the intial tile for testing purposes
			//System.out.println(this.theSpots.size());
			this.theSpots.add(new Location(0,1));
			//System.out.println(this.theSpots.size());
			this.theSpots.add(new Location(0,-1));
			//System.out.println(this.theSpots.size());
			this.theSpots.add(new Location(1,0));
			//System.out.println(this.theSpots.size());
			this.theSpots.add(new Location(-1,0));
			//System.out.println(this.theSpots.size());
			this.testGame.setAS(theSpots);
		}
		else {

		}
	}

	//removes one location from available spots
	public void setRemoveAvailableSpot(int doOrDont) {
		if(doOrDont == 0) {
			//I am testing to see if the boardObject can see if it has less available spots than before
			this.theSpots.remove(0);
			//System.out.println(this.theSpots.size());
			this.testGame.setAS(theSpots);
		}
		else {

		}
	}

	public int countAvailableSpots() {
		return this.testGame.availableSpots.size();
	}

	/*public void main(String args[]) {
		setAddAvailableSpot(0);
		setRemoveAvailableSpot(0);
	}*/
}