package game;

import java.util.ArrayList;

import entities.*;

public class Game {

	protected String gameID;
	protected Player[] players;
	protected BoardObject board;

	public Game(String gameID) {
		this.gameID = gameID;
		players = new Player[2];
		board = new BoardObject();
	}

	//ACCESSORS
	public String getGameID() {
		return gameID;
	}

	public Player[] getPlayers() {
		return players;
	}

	public BoardObject getBoardObject() {
		return board;
	}

	//MUTATORS
	public void setGameID(String gameID) {
		this.gameID = gameID;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public void setBoard(BoardObject board) {
		this.board = board;
	}

	public void setPlayers(String username, String opponentName) {
		players = new Player[2];
		Player p1 = new Player(username, true);
		Player p2 = new Player(opponentName, false);
		players[0] = p1;
		players[1] = p2;
		board.setPlayers(players);
	}

	public void setStartTile(String startType, int startX, int startY, int startOrientation) {
		board.start(startType, startX, startY, startOrientation);
	}


	public void setTileStack(ArrayList<String> tiles) {
		ArrayList<TigerTile> givenDeck = new ArrayList<TigerTile>();

		for (String strings : tiles) {
			TigerTile newTile = new TigerTile(strings, 0);
			givenDeck.add(newTile);
		}
		board.setTileDeck(givenDeck);
	}

	public String makeMove(String tileType) {
		//AI will let this method know if tile is unplaceable.
		//If unplaceable, AI will decide what to do with current turn.
		//Pass this action to client.
		//If placeable, pass tile string to AI, get the move, and pass to client.
		return;
	}

	//If player1 == true, it is player 1's turn
	public void placeTile(String tileType, int tileX, int tileY, int orientation, String animal, boolean player1, int tigerZone) {
		Location loc = new Location(tileX, tileY);
		TigerTile tile = board.getTile(tileType, orientation);
		if (player1 == true){
			board.switchToActivePlayer(players[0]);
		}
		else if (player1 == false) {
			board.switchToActivePlayer(players[1]);
		}
		board.place(tile, loc);
		if (animal.equals("TIGER")){
			board.placeTiger(tigerZone);
		}
		else if (animal.equals("CROCODILE")){
			board.placeCrocodile();
		}
		board.confirm();
	}

	//if boolean addTiger = true, add a tiger to the zone
	public void unplaceableTile(boolean player1, boolean addTiger, int tileX, int tileY) {
		if (addTiger == true){
			if (player1 == true){
				board.switchToActivePlayer(players[0]);
				board.placeTiger(tileX, tileY);
			}
			else if (player1 == false) {
				board.switchToActivePlayer(players[1]);
				board.placeTiger(tileX, tileY);
			}
		}
		else if (addTiger == false){
			if (player1 == true){
				board.switchToActivePlayer(players[0]);
				board.removeTiger(tileX, tileY);
			}
			else if (player1 == false){
				board.switchToActivePlayer(players[1]);
				board.removeTiger(tileX, tileY);
			}
		}
		board.confirm();
	}

	public void endGame() {
		board.end();
	}
}
