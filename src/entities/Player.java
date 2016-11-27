package entities;

import java.util.ArrayList;

public class Player {

	//player attributes
	protected String theID;
	protected boolean isFirst;
	protected int theScore;
	protected ArrayList<TigerObject> theTigers = new ArrayList<TigerObject>();
	protected ArrayList<CrocodileObject> theCrocodiles = new ArrayList<CrocodileObject>();

	//constructors
	public Player(String anID, boolean isFirst) {
		this.theID = anID;
		theScore = 0;

		//add in 7 tiger objects
		for (int i = 0; i < 7; i++) { 
			theTigers.add(new TigerObject(this));
		}

		//add in 2 crocodile objects
		for (int i = 0; i < 7; i++) { 
			theCrocodiles.add(new CrocodileObject(this));
		}		
	}

	//ACCESSORSS
	public int getScore() {
		return theScore;
	}

	public String getID() {
		return theID;
	}

	public ArrayList<TigerObject> getTigers() {
		return theTigers;
	}

	public int getNumOfTigers() { 
		return theTigers.size();
	}

	public ArrayList<CrocodileObject> getCrocs() {
		return theCrocodiles;
	}

	public int getNumOfCrocs() { 
		return theCrocodiles.size();
	}

	public boolean isFirst() {
		return isFirst;
	}

	//MUTATORS 
	public void setScore(int theScore) {
		this.theScore = theScore;
	}
	public void addScore(int theScore) {
		this.theScore += theScore;
	}

	public void setID(String theID) {
		this.theID = theID;
	}

	public void setTigers(ArrayList<TigerObject> theTigers) { 
		this.theTigers = theTigers;
	}

	public void setCrocodiles(ArrayList<CrocodileObject> theCrocodiles) { 
		this.theCrocodiles = theCrocodiles;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	
	//METHODS
	@Override
	public String toString() {
		String isFirst = "This is the second player.";
		if (this.isFirst) isFirst = "This is the first player.";

		String id = getID();
		int score = getScore();
		int numTigers = getNumOfTigers();
		int numCrocs = getNumOfCrocs(); 

		return "Player ID: " + id + "\nScore: " + score + "\nTigers left: " + numTigers + "\n" + "\nCrocs left: " + numCrocs + "\n" + isFirst; 
	}
}
