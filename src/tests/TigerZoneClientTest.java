package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import entities.Location;
import game.Game;

import org.junit.Test;

public class TigerZoneClientTest {

	@Test
	//Test for proper usage in terminal
	public void usageTest() {
		String[] args = new String[5];
		args[0] = "host";
		args[1] = "4444";
		args[2] = "pass";
		args[3] = "TEAME";
		args[4] = "IAME";
		if (args.length != 5) {
			System.err.println(
					"Usage: java TigerZoneClient <host name> <port number> <server password> <username> <password>");
			System.exit(1);
		}

		//take in administrative information
		String hostName = args[0];
		int portNumber = Integer.parseInt(args[1]);
		String serverPass = args[2];
		String userName = args[3];
		String userPass = args[4];
		
		assertEquals("host", hostName);
		assertEquals(4444, portNumber);
		assertEquals("pass", serverPass);
		assertEquals("TEAME", userName);
		assertEquals("IAME", userPass);
	}

	@Test
	//Test for proper response to join server
	public void clientJoinTest() {
		String serverPass = "hello";
		String response = null;
		//display information from server
		String fromTourneyServer = "THIS IS SPARTA!";
		System.out.println("Server: " + fromTourneyServer);

		if (fromTourneyServer.equals("THIS IS SPARTA!")) { //if first message, send join request
			response = ("JOIN " + serverPass + "\r");
			System.out.println("Client: " + "JOIN " + serverPass + "\r");
		}
		
		assertEquals("JOIN hello\r", response);
	}
	
	@Test
	//Test for proper response to login to server
	public void clientLoginTest() {
		String userName = "TEAME";
		String userPass = "IAME";
		String response = null;
		String fromTourneyServer = "HELLO!";
		System.out.println("Server: " + fromTourneyServer);
		if (fromTourneyServer.equals("HELLO!")) {  //if request accepted, send authentication
			response = ("I AM " + userName + " " + userPass + "\r");
			System.out.println("Client: " + "I AM " + userName + " " + userPass + "\r");
		}
		
		assertEquals("I AM TEAME IAME\r", response);
	}
	
	@Test
	//Test to take in challenge ID and number of rounds
	public void cidNumRoundsTest() {
		String fromTourneyServer = "NEW CHALLENGE 1 YOU WILL PLAY 1 MATCH";
		String[] tokenizedMessage = fromTourneyServer.split("\\s+");
		String command = tokenizedMessage[0];
		int challengeID = 0, numRounds = 0;
		
		switch(command){
		case "NEW": //if new tournament created (might not need)
			challengeID = Integer.parseInt(tokenizedMessage[2]);
			numRounds = Integer.parseInt(tokenizedMessage[6]);
			break;
		}
		
		assertEquals(1, challengeID);
		assertEquals(1, numRounds);
	}
	
	@Test
	//Test to take in opponent information and send to game
	public void opponentInfoTest() {
		String fromTourneyServer = "YOUR OPPONENT IS PLAYER Blue";
		String[] tokenizedMessage = fromTourneyServer.split("\\s+");
		String command = tokenizedMessage[0];
		Game gameA = new Game("A");
		Game gameB = new Game("B");
		String opponentName = null, userName = "Red";
		
		switch(command){
		case "YOUR": //take in opponent information
			opponentName = tokenizedMessage[4];
			gameA.setPlayers(userName, opponentName);
			gameB.setPlayers(userName, opponentName);
			break;
		}

		assertEquals("Red", gameA.getBoardObject().getPlayer(0).getID());
		assertEquals("Blue", gameA.getBoardObject().getPlayer(1).getID());
		assertEquals("Red", gameB.getBoardObject().getPlayer(0).getID());
		assertEquals("Blue", gameB.getBoardObject().getPlayer(1).getID());
	}
	
	@Test
	//Test to place starting tile
	public void startTileTest() {
		String fromTourneyServer = "STARTING TILE IS TLTJ- AT 0 0 0";
		String[] tokenizedMessage = fromTourneyServer.split("\\s+");
		String command = tokenizedMessage[0];
		Game gameA = new Game("A");
		Game gameB = new Game("B");
		String startingTile = null;
		int startingX, startingY, startingOrientation;
		gameA.setPlayers("Red", "Blue");
		gameB.setPlayers("Red", "Blue");
		
		switch(command){
		case "STARTING": //take in starting tile information
			startingTile = tokenizedMessage[3];
			startingX = Integer.parseInt(tokenizedMessage[5]);
			startingY = Integer.parseInt(tokenizedMessage[6]);
			startingOrientation = Integer.parseInt(tokenizedMessage[7]);
			// Give games starting spots
			gameA.setStartTile(startingTile, startingX, startingY, startingOrientation);
			gameB.setStartTile(startingTile, startingX, startingY, startingOrientation);
			break;
		}
		
		assertEquals("TLTJ-", gameA.getBoardObject().getTile(new Location(0, 0)).getType());
		assertEquals(0, gameA.getBoardObject().getStartX());
		assertEquals(0, gameA.getBoardObject().getStartY());
		assertEquals("TLTJ-", gameB.getBoardObject().getTile(new Location(0, 0)).getType());
		assertEquals(0, gameB.getBoardObject().getStartX());
		assertEquals(0, gameB.getBoardObject().getStartY());
	}
	
	@Test
	//Test to receive tile deck
	public void tileDeckTest() {
		String fromTourneyServer = "THE REMAINING 6 TILES ARE [ TLTTP LJTJ- JLJL- JJTJX JLTTB TLLT- ]";
		String[] tokenizedMessage = fromTourneyServer.split("\\s+");
		String command = tokenizedMessage[0];
		Game gameA = new Game("A");
		Game gameB = new Game("B");
		ArrayList<String> tiles = new ArrayList<String>();
		int numTiles;
		
		switch(command){
		case "THE": //take in the randomized tile list
			numTiles = Integer.parseInt(tokenizedMessage[2]);

			for (int i = 0; i < numTiles; i++)
				tiles.add(tokenizedMessage[i+6]);

			// Give games remaining tiles
			gameA.setTileStack(tiles);
			gameB.setTileStack(tiles);
			break;
		}
		
		assertEquals("TLTTP", gameA.getBoardObject().getTile(0).getType());
		assertEquals("TLTTP", gameB.getBoardObject().getTile(0).getType());
	}
	
	@Test
	//Test to take in planning time
	public void planTimeTest() {
		String fromTourneyServer = "MATCH BEGINS IN 15 SECONDS";
		String[] tokenizedMessage = fromTourneyServer.split("\\s+");
		String command = tokenizedMessage[0];
		Game gameA = new Game("A");
		Game gameB = new Game("B");
		int countDown = 0;
		
		switch(command){
		case "MATCH": //begin match
			countDown = Integer.parseInt(tokenizedMessage[3]);
			//begin two simultaneous games using information given up to this point
			break;
		}
		
		assertEquals(15, countDown);
	}
	
	@Test
	//Test if client sent info to and received a move from AI
	public void aiMoveTest() {
		//Initialize game
		String fromTourneyServer = "THE REMAINING 6 TILES ARE [ TLTTP LJTJ- JLJL- JJTJX JLTTB TLLT- ]";
		String[] tokenizedMessage = fromTourneyServer.split("\\s+");
		Game gameA = new Game("A");
		Game gameB = new Game("B");
		gameA.setPlayers("Red", "Blue");
		gameB.setPlayers("Red", "Blue");
		gameA.setStartTile("TLTJ-", 0, 0, 0);
		gameB.setStartTile("TLTJ-", 0, 0, 0);
		ArrayList<String> tiles = new ArrayList<String>();
		for (int i = 0; i < 6; i++){
			tiles.add(tokenizedMessage[i+6]);
		}
		// Give games remaining tiles
		gameA.setTileStack(tiles);
		gameB.setTileStack(tiles);
		
		//Test message
		fromTourneyServer = "MAKE YOUR MOVE IN GAME A WITHIN 1 SECOND: MOVE 1 PLACE TLTTP";
		tokenizedMessage = fromTourneyServer.split("\\s+");
		String command = tokenizedMessage[0];
		String gameID, GameA = null, GameB = null; // IDs for games
		String response, testresponse = null, tileToPlace;
		// Flag for first game
		boolean firstGame = false;
		boolean secondGame = false;
		// Game move count
		int moveANum = 1;
		int moveBNum = 1;
		
		switch(command){
		case "MAKE": //send off move based on current tile
			gameID = tokenizedMessage[5];
			String tempMove = tokenizedMessage[10];
			// Set the right values for the first and second game
			if (!firstGame)
			{
				GameA = gameID;
				firstGame = true;
			}
			if (!secondGame)
			{
				GameB = gameID;
				secondGame = true;
				moveBNum++;
				gameB.inc();
			}

			tileToPlace = tokenizedMessage[12];
			response = "";
			// Pass the move to the game
			if (gameID.equals(GameA))
			{
				response = gameA.makeMove();
				// Add the starting information to the move
				response = "GAME " + GameA + " MOVE " + moveANum + " " + response;
			}
			else if (gameID.equals(GameB))
			{
				response = gameB.makeMove();
				// Add the starting information to the move
				response = "GAME " + GameB + " MOVE " + moveBNum + " " + response;
			}

			// Send our move
			testresponse = (response + "\r");
			System.out.println("Client: " + response + "\r");
			break;
		}
		
		assertEquals("GAME A MOVE 1 PLACE TLTTP", testresponse.substring(0, 25));
	}
	
	@Test
	//Test if client can tell board to place tile down
	public void placeTest() {
		//Initialize game
		String fromTourneyServer = "THE REMAINING 6 TILES ARE [ TLTTP LJTJ- JLJL- JJTJX JLTTB TLLT- ]";
		String[] tokenizedMessage = fromTourneyServer.split("\\s+");
		Game gameA = new Game("A");
		Game gameB = new Game("B");
		String userName = "Red";
		String opponentName = "Blue";
		gameA.setPlayers(userName, opponentName);
		gameB.setPlayers(userName, opponentName);
		gameA.setStartTile("TLTJ-", 0, 0, 0);
		gameB.setStartTile("TLTJ-", 0, 0, 0);
		ArrayList<String> tiles = new ArrayList<String>();
		for (int i = 0; i < 6; i++){
			tiles.add(tokenizedMessage[i+6]);
		}
		// Give games remaining tiles
		gameA.setTileStack(tiles);
		gameB.setTileStack(tiles);
		
		//Test message
		fromTourneyServer = "GAME B MOVE 1 PLAYER Blue PLACED TLTTP AT 0 1 90 TIGER 8";
		tokenizedMessage = fromTourneyServer.split("\\s+");
		String command = tokenizedMessage[0];
		String gameID, currentPlayerID;
		String tilePlaced;
		int tilePlacedX, tilePlacedY, tileOrientation, animalZone, moveANum = 1, moveBNum = 1;
		String animal;
		String GameA = "A", GameB = "B";
		switch(command){
		case "GAME": //game logic
			gameID = tokenizedMessage[1];
			currentPlayerID = tokenizedMessage[5];
			if (tokenizedMessage[2].equals("OVER") || tokenizedMessage[6].equals("FORFEITED:")) {
				//game over logic - i.e. tell AI to stop the two games
				//or forfeited game logic - ie. tell AI to stop the two games due to forfeit
				// Get the ended game
				if (gameID.equals(GameA))
				{
					GameA = null;
					if (gameA != null) gameA.endGame();
					gameA = null;
				}
				else if (gameID.equals(GameB))
				{
					GameB = null;
					if (gameB != null) gameB.endGame();
					gameB = null;
				}
			}
			else if (tokenizedMessage[6].equals("PLACED"))
			{
				//a move was made - place onto own board(s)
				tilePlaced = tokenizedMessage[7];
				tilePlacedX = Integer.parseInt(tokenizedMessage[9]);
				tilePlacedY = Integer.parseInt(tokenizedMessage[10]);
				tileOrientation = Integer.parseInt(tokenizedMessage[11]);
				animal = tokenizedMessage[12];
				animalZone = -1; // Default value

				// Check if the tiger has a zone
				if (animal.equals("TIGER"))
				{
					animalZone = Integer.parseInt(tokenizedMessage[13]);
				}

				// Place the tile in the game
				if (gameID.equals(GameA)) {
					moveANum++;
					gameA.placeTile(tilePlacedX, tilePlacedY, tileOrientation, animal,
							userName.equals(currentPlayerID), animalZone);
				} else if (gameID.equals(GameB)) {
					moveBNum++;
					gameB.placeTile(tilePlacedX, tilePlacedY, tileOrientation, animal,
							userName.equals(currentPlayerID), animalZone);
				}

			}
			else if (tokenizedMessage[6].equals("TILE"))
			{
				if (tokenizedMessage[7].equals("PASS"))
				{
					// Place the tile in the game
					if (gameID.equals(GameA)) {
						moveANum++;
						gameA.pass();
					} else if (gameID.equals(GameB)) {
						moveBNum++;
						gameB.pass();
					}
				}
				else
				{
					// When a tiger is added or retrived
					String addOrReplace = tokenizedMessage[9];
					boolean addTiger = false;
					tilePlacedX = 0;
					tilePlacedY = 0;

					if (addOrReplace.equals("RETRIEVED"))
					{
						addTiger = false;
						tilePlacedX = Integer.parseInt(tokenizedMessage[12]);
						tilePlacedY = Integer.parseInt(tokenizedMessage[13]);
					}
					else if (addOrReplace.equals("ADDED"))
					{
						addTiger = true;
						tilePlacedX = Integer.parseInt(tokenizedMessage[13]);
						tilePlacedY = Integer.parseInt(tokenizedMessage[14]);
					}

					// Place the tile in the game
					if (gameID.equals(GameA)) {
						moveANum++;
						gameA.unplaceableTile(userName.equals(currentPlayerID), addTiger,
								tilePlacedX, tilePlacedY);
					} else if (gameID.equals(GameB)) {
						moveBNum++;
						gameB.unplaceableTile(userName.equals(currentPlayerID), addTiger,
								tilePlacedX, tilePlacedY);
					}
				}
			}
			break;
		}
		gameB.getBoardObject().print();
		assertEquals("TLTTP", gameB.getBoardObject().getTile(new Location(0,1)).getType());
		//assertEquals(1, gameB.getBoardObject().getTile(new Location(0,1)).getTigers().size());
	}
}
