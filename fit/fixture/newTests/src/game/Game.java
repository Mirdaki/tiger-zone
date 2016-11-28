package game;

import entities.*;

public class Game {

	protected String gameID;
	protected Player[] players;
	protected BoardObject board; 
	
	Game(String gameID) { 
		this.gameID = gameID;
		players = new Player[2];
		board = new BoardObject();
	}
	
	//ACCESSORS
	public void setGameID() { 
		this.gameID = gameID;
	}
	
	//MUTATORS
	public String getGameID() { 
		return gameID;
	}
	
}
