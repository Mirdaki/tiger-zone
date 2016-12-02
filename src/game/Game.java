package game;

import java.util.ArrayList;
import java.util.Map;

import entities.*;

public class Game {

	// Properties

	protected String gameID;
	protected Player[] players;
	protected BoardObject board;
	protected int move;
	protected AI ai;
	protected TileDeck randomDeck;

	// Constructor

	/**
	 * Creat game
	 * @param  gameID
	 * @return        Game
	 */
	public Game(String gameID) {
		this.gameID = gameID;
		players = new Player[2];
		board = new BoardObject();
		ai = new AI(board);
		move = 0;
		randomDeck = new TileDeck();
	}

	/**
	 * For testing, to play a move and see if out of tiles
	 * @return boolean Possible to make move
	 */
	public boolean play() {

		System.out.print(makeMove2());
		System.out.println();
		if (move < 77) return true;
		return false;
	}

	/**
	 * For testing, to place a tile and meeple
	 * @return String for sending to server
	 */
	public String makeMove2() {
		//AI will let this method know if tile is unplaceable.
		//If unplaceable, AI will decide what to do with current turn.
		//If placeable, pass tile string to AI, get the move, and pass to client.

		TigerTile tile = null;
//		if (move < 9) tile = new TigerTile("JJJJ-",0);
//		else if (move == 9) tile = new TigerTile("LLLL-",0);
		tile = randomDeck.getRandom();
//		TigerTile tile = new TigerTile("JJJJ-",0);
		move++;
		String value = ai.getMove(tile);

		String[] results = value.split("\\s+");
//		System.out.println(results[0]);
		if (results[0].equals("PLACE")) {
			if (results[6].equals("TIGER")) {
				board.placeTiger(Integer.parseInt(results[7]));
			}
			else if (results[6].equals("CROCODILE")) {
				board.placeCrocodile();
			}
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

	/**
	 * Get game ID
	 * @return String
	 */
	public String getGameID() {
		return gameID;
	}

	/**
	 * Get players
	 * @return Players[]
	 */
	public Player[] getPlayers() {
		return players;
	}

	/**
	 * Get board object
	 * @return board
	 */
	public BoardObject getBoardObject() {
		return board;
	}

	//MUTATORS

	/**
	 * Set game ID
	 * @param gameID String
	 */
	public void setGameID(String gameID) {
		this.gameID = gameID;
	}

	/*public void setPlayers(Player[] players) {
		this.players = players;
	}*/

	/**
	 * Set board
	 * @param board BoardObject
	 */
	public void setBoard(BoardObject board) {
		this.board = board;
	}

	/**
	 * Set players
	 * @param  username String
	 * @param  opponentName String
	 */
	public void setPlayers(String username, String opponentName) {
		players = new Player[2];
		Player p1 = new Player(username, true);
		Player p2 = new Player(opponentName, false);
		players[0] = p1;
		players[1] = p2;
		board.setPlayers(players);
	}

	/**
	 * Start Tile
	 * @param startType String
	 * @param startX int
	 * @param startY int
	 * @param startOrientation int
	 */
	public void setStartTile(String startType, int startX, int startY, int startOrientation) {

		TigerTile startTile = new TigerTile(startType, startOrientation / 90);
		board.start(startTile, startX, startY);
	}

	/**
	 * Set tiles Stack
	 * @param tiles ArrayList<String>
	 */
	public void setTileStack(ArrayList<String> tiles) {
		ArrayList<TigerTile> givenDeck = new ArrayList<TigerTile>();

		for (String strings : tiles) {
			TigerTile newTile = new TigerTile(strings, 0);
			givenDeck.add(newTile);
		}
		board.setTileDeck(givenDeck);
	}

	/**
	 * Set tiles with TigerTiles
	 * @param tiles ArrayList<TigerTiles>
	 */
	public void setTileStack2(ArrayList<TigerTile> tiles) {
		board.setTileDeck(tiles);
	}

	/**
	 * Makes move and place tile
	 * @return String
	 */
	public String makeMove() {
		//AI will let this method know if tile is unplaceable.
		//If unplaceable, AI will decide what to do with current turn.
		//If placeable, pass tile string to AI, get the move, and pass to client.
		TigerTile tile = board.getTile(move);
	//	artificialIntelligence AI = new artificialIntelligence(board);
		String value = ai.getMove(tile);

		//System.out.println("**LOG**:This is AI move: " + move + ". With tile: " + tile.getType());

		// move++;
		return value;
	}

	/**
	 * Incremnt move counter
	 */
	public void inc() {
		move++;
	}

	/**
	 * Place tile
	 * @param  tileType String
	 * @param  tileX int
	 * @param  tileY int
	 * @param  orientation int
	 */
	public void placeTile(String tileType, int tileX, int tileY, int orientation) {

		TigerTile tile = new TigerTile(tileType, orientation);

		board.place(tile, new Location(tileX, tileY));

	}

	/**
	 * Place the tile
	 * @param  tileX int
	 * @param  tileY int
	 * @param  orientation int
	 * @param  animal String
	 * @param  player1 boolean
	 * @param  tigerZone int
	 */
	//If player1 == true, it is player 1's turn
	public void placeTile(int tileX, int tileY, int orientation, String animal, boolean player1, int tigerZone) {

		Location loc = new Location(tileX, tileY);
		TigerTile tile = board.getTile(move++);
		tile.setOrientation(orientation / 90);
//		System.out.println(tile);

		if (player1 == true){
			board.switchToActivePlayer(players[0]);
			board.place(tile, loc);

		}
		else if (player1 == false) {
			board.switchToActivePlayer(players[1]);
			board.place(tile, loc);
		}

		if (animal.equals("TIGER")){
			System.out.println(board.placeTiger(tigerZone));
		}
		else if (animal.equals("CROCODILE")){
			board.placeCrocodile();
		}

		board.confirm();
		// move++;
	}

	/**
	 * Pass on moving tile, so increment tile stack
	 */
	public void pass() {
		board.getTile(move);
		board.confirm();
		move++;
	}

	/**
	 * Adds only a tiger or crocodile, no tile
	 * @param  player1 boolean
	 * @param  addTiger boolean
	 * @param  tileX int
	 * @param  tileY int
	 */
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

	// End the game and baord
	public void endGame() {
		board.end();
	}

	/**
	 * Test function for game
	 */
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
//		gameB.placeTile(0, 1, 90, "TIGER", false, 8);
		gameA.board.print();
		System.out.println();
//		gameB.board.print();
//		System.out.println();

//		gameB.placeTile(0, 2, 180, "TIGER", true, 8);
		gameA.placeTile(0, 2, 180, "TIGER", false, 8);
		gameA.board.print();
//		System.out.println();
//		gameB.board.print();
//		System.out.println();
//
		gameA.placeTile(1, 0, 0, "NONE", true, -1);
//		gameB.placeTile(1, 0, 0, "TIGER", false, 4);
		gameA.board.print();
//		System.out.println();
//		gameB.board.print();
//		System.out.println();
//
//		gameB.placeTile(1, 1, 270, "TIGER", true, 5);
		gameA.placeTile(1, 1, 270, "TIGER", false, 5);
		gameA.board.print();
//		System.out.println();
//		gameB.board.print();
//		System.out.println();
//
		gameA.placeTile(2, 0, 180, "TIGER", true, 1);
//		gameB.placeTile(2, 0, 180, "TIGER", false, 1);
		gameA.board.print();
//		System.out.println();
//		gameB.board.print();
//		System.out.println();
//
//		gameB.placeTile(0, -1, 180, "TIGER", true, 3);
		gameA.placeTile(0, -1, 270, "CROCODILE", false, -1);
		gameA.board.printSpots();
//		System.out.println();
//		gameB.board.print();
//		System.out.println();
//
		System.out.println(gameA.board.getPlayers()[0]);
		System.out.println(gameA.board.getPlayers()[1]);
		
		
		System.out.println("COMPLETE REGIONS");				
		if (gameA.board.getComplete().size() == 0) System.out.println("No complete regions");
		else for (Region region : gameA.board.getComplete()) System.out.println(region);

		System.out.println("INCOMPLETE REGIONS");
		for (Map.Entry<Integer, Region> entry : gameA.board.getIncomplete().entrySet()) {
			System.out.println(entry.getKey() + "/" + entry.getValue());

			for (Terrain terrain : entry.getValue().getTerrains())
				System.out.println("\t" + terrain);
		}

		gameA.endGame();
//		gameB.endGame();
	}
}
