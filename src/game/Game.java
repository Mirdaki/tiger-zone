package game;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

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
	 * For testing, to place a tile and meeple
	 * @return String for sending to server
	 */

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
	 * Makes move and place tile
	 * @return String
	 */
	public String makeMove(int move) {
		//AI will let this method know if tile is unplaceable.
		//If unplaceable, AI will decide what to do with current turn.
		//If placeable, pass tile string to AI, get the move, and pass to client.

		board.switchToActivePlayer(players[0]);
		TigerTile tile = board.getTile(move-1);
		String value = ai.getMove(tile);
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
	public void placeTile(int tileX, int tileY, int orientation, String animal, boolean player1, int tigerZone, int move) {

		Location loc = new Location(tileX, tileY);
		TigerTile tile = board.getTile(move-1);
		tile.setOrientation(orientation / 90);
		//		System.out.println(tile);


		if (player1 == true){
			board.switchToActivePlayer(players[0]);
			//board.place(tile, loc);

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

	// End the game and board
	public void endGame() {
		board.end();
	}

	public int getMove() { 
		return move;
	}
	/**
	 * Test function for game
	 */
	public static void main(String[] args) {

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
		
		gameA.placeTile(0, 1, 90, "TIGER", true, 8,1);
		gameB.placeTile(0, 1, 90, "TIGER", false, 8,1);
		gameA.board.print();
		System.out.println();
		gameB.board.print();
		System.out.println();
		
		gameB.placeTile(0, 2, 180, "TIGER", true, 8,2);
		gameA.placeTile(0, 2, 180, "TIGER", false, 8,2);
		gameA.board.print();
		System.out.println();
		gameB.board.print();
		System.out.println();
		
		gameA.placeTile(1, 0, 0, "NONE", true, -1,3);
		gameB.placeTile(1, 0, 0, "TIGER", false, 4,3);
		gameA.board.print();
		System.out.println();
		gameB.board.print();
		System.out.println();
		
		gameB.placeTile(1, 1, 270, "TIGER", true, 5,4);
		gameA.placeTile(1, 1, 270, "TIGER", false, 5,4);
		gameA.board.print();
		System.out.println();
		gameB.board.print();
		System.out.println();
		
		gameA.placeTile(2, 0, 180, "TIGER", true, 1,5);
		gameB.placeTile(2, 0, 180, "TIGER", false, 1,5);
		gameA.board.print();
		System.out.println();
		gameB.board.print();
		System.out.println();
		
		gameB.placeTile(0, -1, 270, "TIGER", true, 3,6);
		gameA.placeTile(0, -1, 270, "CROCODILE", false, -1,6);
		gameA.board.print();
		System.out.println();
		gameB.board.print();
		System.out.println();
		
		gameA.endGame();
		gameB.endGame();
		
		/* 
		 * Example game against AI. You are player 1, the AI is player 2. All moves are final,
		 * will display invalid move and quit the game.
		 * 
		 * This creates the game, sets the players, deck (randomized), etc. AI will make all of 
		 * its moves automatically. This will treat the starting tile as the first one from the 
		 * deck. 
		 */
//		Game game = new Game("test");
//		BoardObject board = game.getBoardObject();
//		TileDeck deck = new TileDeck();
//		deck.shuffle(); //randomize the deck
//		ArrayList<String> deckList = deck.getDeck();
//
//		String player1 = "You";
//		String player2 = "AI";
//		game.setPlayers(player1, player2);
//		game.setTileStack(deck.getDeck());
//		game.setStartTile(deckList.remove(0),0,0,0);
//		int move = 1;
//		Scanner in = new Scanner(System.in);
//		String input = "";
//
//
//		while(true) { 
//			System.out.println("MENU");
//			System.out.println("Type 0 to see the board and available spots." +
//					"\nType 1 to place a tile" + 
//					"\nType 2 to see incomplete regions" + 
//					"\nType 3 to see complete regions" + 
//					"\nType 4 to print scores" + 
//					"\nType 5 to end game"
//					);
//
//			int choice = in.nextInt();
//			in.nextLine();
//			switch(choice) { 
//			case 0: 
//				board.print();
//				board.printSpots();
//				break;
//			case 1: 
//				TigerTile testTile = board.getTile(move++);
//				System.out.println("Your tile is: " + testTile.getType());
//				board.canPlace(testTile);
//
//				System.out.println("Your tile to place is: " + testTile.getType() + "\nAvailable spots and orientations: ");
//				System.out.println(board.getPossibleSpots());
//				System.out.println("SYNTAX: [X] [Y] [ORIENTATION] [TIGER/CROC/NONE] [TIGER INDEX] (leave index empty if nothing)");
//				input = in.nextLine();
//
//				String[] result = input.split("\\s");
//				Location location = new Location(Integer.parseInt(result[0]),Integer.parseInt(result[1]));					
//				testTile.setOrientation(Integer.parseInt(result[2]) / 90);
//
//				if(!board.placeTest(testTile, location)) { 
//					System.out.println("Couldn't place tile. REASON: " + board.getReason());
//					return;
//				}
//				else { 
//					if (result.length > 3)
//						if (result[3].equalsIgnoreCase("CROC")) { 
//							if(!board.placeCrocodile()) { 
//								System.out.println("Couldn't place Crocodile. REASON: " + board.getReason()); 
//								return;
//							}
//						}
//						else if (result[3].equalsIgnoreCase("TIGER")) 
//							if(!board.placeTiger(Integer.parseInt(result[4]))) {
//								System.out.println("Couldn't place Tiger. REASON: " + board.getReason()); 
//								return; 
//							}
//					System.out.println("Successfully \"placed\" tile! AI will now make its move.");
//
//					//					game.inc();
//					//
//					//					String aiMove = game.makeMove();
//					//					String[] aiMoveTokens = aiMove.split("\\s+");
//					//					System.out.println("AI did the following: " + aiMove);
//					//
//					//					int tilePlacedX = Integer.parseInt(aiMoveTokens[3]);
//					//					int tilePlacedY = Integer.parseInt(aiMoveTokens[4]);
//					//					int tileOrientation = Integer.parseInt(aiMoveTokens[5]);
//					//					String animal = aiMoveTokens[6];
//					//					int animalZone = -1; // Default value
//					//
//					//					// Check if the tiger has a zone
//					//					if (animal.equals("TIGER")) {
//					//						animalZone = Integer.parseInt(aiMoveTokens[7]);
//					//					}
//					//					game.placeTile(tilePlacedX, tilePlacedY, tileOrientation, animal,false, animalZone);
//					//					move++;
//				}
//				break;
//			case 2:
//				System.out.println("INCOMPLETE REGIONS");
//				for (Map.Entry<Integer, Region> entry : board.getIncomplete().entrySet()) {
//					System.out.println(entry.getKey() + "/" + entry.getValue());
//
//					for (Terrain terrain : entry.getValue().getTerrains())
//						System.out.println("\t" + terrain);
//				}
//				break;
//
//			case 3:
//				System.out.println("COMPLETE REGIONS");
//				if (board.getComplete().size() == 0) System.out.println("No complete regions");
//				else for (Region region : board.getComplete()) System.out.println(region);
//				break;
//			case 4:
//				board.printScores();
//				break;
//			case 7:
//				board.end();
//				System.exit(0);
//				break;
//			default: 
//				break;
//			}
//			System.out.println();

			//		gameB.placeTile(0, -1, 180, "TIGER", true, 3);
			//		gameA.placeTile(0, -1, 270, "CROCODILE", false, -1);
			//		gameA.board.printSpots();
			//		System.out.println();
			//		gameB.board.print();
			//		System.out.println();
			//
			//		System.out.println(gameA.board.getPlayers()[0]);
			//		System.out.println(gameA.board.getPlayers()[1]);


			//		System.out.println("COMPLETE REGIONS");
			//		if (gameA.board.getComplete().size() == 0) System.out.println("No complete regions");
			//		else for (Region region : gameA.board.getComplete()) System.out.println(region);
			//
			//		System.out.println("INCOMPLETE REGIONS");
			//		for (Map.Entry<Integer, Region> entry : gameA.board.getIncomplete().entrySet()) {
			//			System.out.println(entry.getKey() + "/" + entry.getValue());
			//
			//			for (Terrain terrain : entry.getValue().getTerrains())
			//				System.out.println("\t" + terrain);



			/* EXAMPLE GAME FROM DAVE'S SITE */
			//Things to address: our moves not being made on board b/c placetile not being called - what is placing for us?
			//when placetile is called for player1, NullPtrException?
			//Initialize pre-game
			//		String username = "Red";
			//		String opponent = "Blue";
			//		Game gameA = new Game("A");
			//		Game gameB = new Game("B");
			//		gameA.setPlayers(username, opponent);
			//		gameB.setPlayers(username, opponent);
			//		ArrayList<String> remainingTiles = new ArrayList<String>();
			//		remainingTiles.add("TLTTP");
			//		remainingTiles.add("LJTJ-");
			//		remainingTiles.add("JLJL-");
			//		remainingTiles.add("JJTJX");
			//		remainingTiles.add("JLTTB");
			//		remainingTiles.add("TLLT-");
			//		gameA.setTileStack(remainingTiles);
			//		gameB.setTileStack(remainingTiles);
			//		gameA.setStartTile("TLTJ-", 0, 0, 0);
			//		gameB.setStartTile("TLTJ-", 0, 0, 0);
			//
			//		gameA.placeTile(0, 1, 90, "TIGER", true, 8);
			////		gameB.placeTile(0, 1, 90, "TIGER", false, 8);
			//		gameA.board.print();
			//		System.out.println();
			////		gameB.board.print();
			////		System.out.println();
			//
			////		gameB.placeTile(0, 2, 180, "TIGER", true, 8);
			//		gameA.placeTile(0, 2, 180, "TIGER", false, 8);
			//		gameA.board.print();
			////		System.out.println();
			////		gameB.board.print();
			////		System.out.println();
			////
			//		gameA.placeTile(1, 0, 0, "NONE", true, -1);
			////		gameB.placeTile(1, 0, 0, "TIGER", false, 4);
			//		gameA.board.print();
			////		System.out.println();
			////		gameB.board.print();
			////		System.out.println();
			////
			////		gameB.placeTile(1, 1, 270, "TIGER", true, 5);
			//		gameA.placeTile(1, 1, 270, "TIGER", false, 5);
			//		gameA.board.print();
			////		System.out.println();
			////		gameB.board.print();
			////		System.out.println();
			////
			//		gameA.placeTile(2, 0, 180, "TIGER", true, 1);
			////		gameB.placeTile(2, 0, 180, "TIGER", false, 1);
			//		gameA.board.print();
			////		System.out.println();
			////		gameB.board.print();
			////		System.out.println();
			////
			////		gameB.placeTile(0, -1, 180, "TIGER", true, 3);
			//		gameA.placeTile(0, -1, 270, "CROCODILE", false, -1);
			//		gameA.board.printSpots();
			////		System.out.println();
			////		gameB.board.print();
			////		System.out.println();
			////
			//		System.out.println(gameA.board.getPlayers()[0]);
			//		System.out.println(gameA.board.getPlayers()[1]);
			//		
			//		
			//		System.out.println("COMPLETE REGIONS");				
			//		if (gameA.board.getComplete().size() == 0) System.out.println("No complete regions");
			//		else for (Region region : gameA.board.getComplete()) System.out.println(region);
			//
			//		System.out.println("INCOMPLETE REGIONS");
			//		for (Map.Entry<Integer, Region> entry : gameA.board.getIncomplete().entrySet()) {
			//			System.out.println(entry.getKey() + "/" + entry.getValue());
			//
			//			for (Terrain terrain : entry.getValue().getTerrains())
			//				System.out.println("\t" + terrain);
			//		}
			//
			//		gameA.endGame();
			////		gameB.endGame();
		}
	}
