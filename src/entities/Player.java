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
		this.isFirst = isFirst;

		//add in 7 tiger objects
		for (int i = 0; i < 2; i++) { 
			theTigers.add(new TigerObject(this));
		}

		//add in 2 crocodile objects
		for (int i = 0; i < 2; i++) { 
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
	
	public boolean hasTigers() { 
		if (theTigers.size() > 0) return true;
		return false;
	}

	public boolean hasCrocs() { 
		if (theCrocodiles.size() > 0) return true;
		return false;
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
	
	public void addTiger(TigerObject stray) { 
		theTigers.add(stray);
	}
	
	public void addCroc(CrocodileObject hatchling) { 
		theCrocodiles.add(hatchling);
	}

	public TigerObject removeTiger() { 
		return theTigers.remove(0);
	}
	
	public CrocodileObject removeCroc() { 
		return theCrocodiles.remove(0);
	}

	
	//METHODS
	public boolean equals(Player player) { 
		if (player.getID().equals(this.theID)) return true;
		return false;
	}
	
	@Override
	public String toString() {
		String isFirst = "This is the second player.";
		if (this.isFirst) isFirst = "This is the first player.";

		String id = getID();
		int score = getScore();
		int numTigers = getNumOfTigers();
		int numCrocs = getNumOfCrocs(); 

		return "Player ID: " + id + "\nScore: " + score + "\nTigers left: " + numTigers + "\nCrocs left: " + numCrocs + "\n" + isFirst + "\n"; 
	}
}
