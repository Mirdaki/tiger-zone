package game;

import java.util.ArrayList;

import entities.*;

public class Game {

	protected String gameID;
	protected Player[] players;
	protected BoardObject board;
	protected int move;
	protected AI ai;
	protected TileDeck randomDeck;

	public Game(String gameID) {
		this.gameID = gameID;
		players = new Player[2];
		board = new BoardObject();
		ai = new AI(board);
		move = 0;
		randomDeck = new TileDeck();
	}


	public boolean play() {

		System.out.print(makeMove2());
		System.out.println();
		if (move < 77) return true;
		return false;
	}

	public String makeMove2() {
		//AI will let this method know if tile is unplaceable.
		//If unplaceable, AI will decide what to do with current turn.
		//If placeable, pass tile string to AI, get the move, and pass to client.
		TigerTile tile = randomDeck.getRandom();
		move++;
		String value = ai.getMove(tile);

		String[] results = value.split("\\s+");
//		System.out.println(results[0]);
		if (results[0].equals("PLACE")) {
			if (results[6].equals("TIGER")) {
				board.placeTiger(Integer.parseInt(results[7]));
			}
//			else if (results[6].equals("CROCODILE")) {
//				board.placeCrocodile();
//			}
		}

		//		if (animal.equals("TIGER")){
		//			board.placeTiger(tigerZone);
		//		}
		//		else if (animal.equals("CROCODILE")){
		//			board.placeCrocodile();
		//		}




		board.confirm();
		return value;
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

	/*public void setPlayers(Player[] players) {
		this.players = players;
	}*/

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

		TigerTile startTile = new TigerTile(startType, startOrientation / 90);
		System.out.println(startTile);
		board.start(startTile, startX, startY, startOrientation / 90);
	}


	public void setTileStack(ArrayList<String> tiles) {
		ArrayList<TigerTile> givenDeck = new ArrayList<TigerTile>();

		for (String strings : tiles) {
			TigerTile newTile = new TigerTile(strings, 0);
			givenDeck.add(newTile);
		}
		board.setTileDeck(givenDeck);
	}

	public void setTileStack2(ArrayList<TigerTile> tiles) {
		board.setTileDeck(tiles);
	}

	public String makeMove() {
		//AI will let this method know if tile is unplaceable.
		//If unplaceable, AI will decide what to do with current turn.
		//If placeable, pass tile string to AI, get the move, and pass to client.
		TigerTile tile = board.getTile(move);
		artificialIntelligence AI = new artificialIntelligence(board);
		String value = AI.getMove(tile);
		return value;
	}


	public void placeTile(String tileType, int tileX, int tileY, int orientation) {

		TigerTile tile = new TigerTile(tileType, orientation);

		board.place(tile, new Location(tileX, tileY));

	}

	//If player1 == true, it is player 1's turn
	public void placeTile(int tileX, int tileY, int orientation, String animal, boolean player1, int tigerZone) {

		Location loc = new Location(tileX, tileY);
		TigerTile tile = board.getTile(move++);
		tile.setOrientation(orientation / 90);
		if (player1 == true){
			board.switchToActivePlayer(players[0]);
		}
		else if (player1 == false) {
			board.switchToActivePlayer(players[1]);
			board.place(tile, loc);
		}

		if (animal.equals("TIGER")){
			board.placeTiger(tigerZone);
		}
		else if (animal.equals("CROCODILE")){
			board.placeCrocodile();
		}

		board.confirm();
	}

	public void pass() {
		board.getTile(move);
		board.confirm();
		move++;
	}

	//if boolean addTiger = true, add a tiger to the zone
	public void unplaceableTile(boolean player1, boolean addTiger, int tileX, int tileY) {
		board.getTile(move);
		if (addTiger == true){
			if (player1 == true){
				board.switchToActivePlayer(players[0]);
				board.placeTiger(new Location(tileX, tileY));
			}
			else if (player1 == false) {
				board.switchToActivePlayer(players[1]);
				board.placeTiger(new Location(tileX, tileY));
			}
		}
		else if (addTiger == false){
			if (player1 == true){
				board.switchToActivePlayer(players[0]);
				board.removeTiger(new Location(tileX, tileY));
			}
			else if (player1 == false){
				board.switchToActivePlayer(players[1]);
				board.removeTiger(new Location(tileX, tileY));
			}
		}
		board.confirm();
		move++;
	}

	public void endGame() {
		board.end();
	}

	public static void main(String[] args) {

		//Things to address: our moves not being made on board b/c placetile not being called - what is placing for us?
		//when placetile is called for player1, NullPtrException?
		//Initialize pre-game
		String username = "Red";
		String opponent = "Blue";
		Game gameA = new Game("A");
		Game gameB = new Game("B");
		gameA.setPlayers(username, opponent);
		gameB.setPlayers(username, opponent);
		ArrayList<String> remainingTiles = new ArrayList<String>();
		remainingTiles.add("TLTTP");
		remainingTiles.add("LJTJ-");
		remainingTiles.add("JLJL-");
		remainingTiles.add("JJTJX");
		remainingTiles.add("JLTTB");
		remainingTiles.add("TLLT-");
		gameA.setTileStack(remainingTiles);
		gameB.setTileStack(remainingTiles);
		gameA.setStartTile("TLTJ-", 0, 0, 0);
		gameB.setStartTile("TLTJ-", 0, 0, 0);

		gameA.placeTile(0, 1, 90, "TIGER", true, 8);
		gameB.placeTile(0, 1, 90, "TIGER", false, 8);
		gameA.board.print();
		System.out.println();
		gameB.board.print();
		System.out.println();

		gameB.placeTile(0, 2, 180, "TIGER", true, 8);
		gameA.placeTile(0, 2, 180, "TIGER", false, 8);
		gameA.board.print();
		System.out.println();
		gameB.board.print();
		System.out.println();

		gameA.placeTile(1, 0, 0, "NONE", true, -1);
		gameB.placeTile(1, 0, 0, "TIGER", false, 4);
		gameA.board.print();
		System.out.println();
		gameB.board.print();
		System.out.println();

		gameB.placeTile(1, 1, 270, "TIGER", true, 5);
		gameA.placeTile(1, 1, 270, "TIGER", false, 5);
		gameA.board.print();
		System.out.println();
		gameB.board.print();
		System.out.println();

		gameA.placeTile(2, 0, 180, "TIGER", true, 1);
		gameB.placeTile(2, 0, 180, "TIGER", false, 1);
		gameA.board.print();
		System.out.println();
		gameB.board.print();
		System.out.println();

		gameB.placeTile(0, -1, 180, "TIGER", true, 3);
		gameA.placeTile(0, -1, 180, "CROCODILE", false, -1);
		gameA.board.print();
		System.out.println();
		gameB.board.print();
		System.out.println();

		gameA.endGame();
		gameB.endGame();
	}
}
