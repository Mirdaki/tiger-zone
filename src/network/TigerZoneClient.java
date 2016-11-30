package network;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entities.TigerTile;
import entities.TileDeck;
import game.Game;

public class TigerZoneClient {

	public static void main(String[] args) throws Exception {

		Game gameC = new Game("C");
		System.out.println("After gameC");

		//if format not followed, specify
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

		//attempt connection with server specified by product owner
		try (
				//attempt connection with the Tournament TCP server
				Socket serverSocket = new Socket(hostName, portNumber);
				PrintWriter out = new PrintWriter(serverSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

				//wait for connection from the "AI"
				//ServerSocket handlerSocket = new ServerSocket(6789);
				//Socket ai_Socket = handlerSocket.accept();
				//PrintWriter ai_OUT = new PrintWriter(ai_Socket.getOutputStream(), true);
				//BufferedReader ai_IN = new BufferedReader(new InputStreamReader(ai_Socket.getInputStream()));

				) {

			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String fromTourneyServer = "", fromHandler, fromAI;

			//variable declarations - information from server
			String gameID, GameA = null, GameB = null;
			String currentPlayerID;
			String opponentName;
			int challengeID, numChallenges;
			int roundID, numRounds;
			int countDown;
			ArrayList<String> tiles = new ArrayList<String>();
			int numTiles;

			//starting tile information
			String startingTile;
			int startingX, startingY, startingOrientation;

			// Current move
			int moveANum;
			int moveBNum;

			//tile recently placed by opponent
			String tilePlaced;
			int tilePlacedX;
			int tilePlacedY;
			int tileOrientation;
			String animal;
			int animalZone;

			// Games themselves
			Game gameA;
			Game gameB;
			gameA = null;
			gameB = null;
			moveANum = 1;
			moveBNum = 1;

			//tile AI wants to place
			String tileToPlace;
			String response;


			//continuously wait for input from server
			while ((fromTourneyServer = in.readLine()) != null) {

				//display information from server
				System.out.println("Server: " + fromTourneyServer);

				if (fromTourneyServer.equals("THIS IS SPARTA!")) { //if first message, send join request
					out.println("JOIN " + serverPass);
					System.out.println("Client: " + "JOIN " + serverPass);
				}
				else if (fromTourneyServer.equals("HELLO!")) {  //if request accepted, send authentication
					out.println("I AM " + userName + " " + userPass);
					System.out.println("Client: " + "I AM " + userName + " " + userPass);
				}
				else if (fromTourneyServer.equals("THANK YOU FOR PLAYING! GOODBYE")) { //if end of tournament, exit from this
					// Exit everything
					break;
					//return;
				}
				else { //otherwise, message must be parsed from server

					//tokenize it
					String[] tokenizedMessage = fromTourneyServer.split("\\s+");
					String command = tokenizedMessage[0];

					switch(command){

					case "NEW": //if new tournament created (might not need)
						challengeID = Integer.parseInt(tokenizedMessage[2]);
						numRounds = Integer.parseInt(tokenizedMessage[6]);
						break;

					case "BEGIN": //if round begun (might not need)
						roundID = Integer.parseInt(tokenizedMessage[2]);
						numRounds = Integer.parseInt(tokenizedMessage[4]);
						// Create games
						gameA = new Game("A");
						gameB = new Game("B");
						moveANum = 1;
						moveBNum = 1;
						break;

					case "YOUR": //take in opponent information
						opponentName = tokenizedMessage[4];
						System.out.println(userName + " " + opponentName);
						gameA.setPlayers(userName, opponentName);
						gameB.setPlayers(userName, opponentName);
						break;

					case "STARTING": //take in starting tile information (hope we dont need)
						startingTile = tokenizedMessage[3];
						startingX = Integer.parseInt(tokenizedMessage[5]);
						startingY = Integer.parseInt(tokenizedMessage[6]);
						startingOrientation = Integer.parseInt(tokenizedMessage[7]);
						// Give games starting spots
						gameA.setStartTile(startingTile, startingX, startingY, startingOrientation);
						gameB.setStartTile(startingTile, startingX, startingY, startingOrientation);
						break;

					case "THE": //take in the randomized tile list
						numTiles = Integer.parseInt(tokenizedMessage[2]);

						for (int i = 0; i < numTiles; i++)
							tiles.add(tokenizedMessage[i+6]);

						// Gove games remaining tiles
						gameA.setTileStack(tiles);
						gameB.setTileStack(tiles);
						break;

					case "MATCH": //begin match
						countDown = Integer.parseInt(tokenizedMessage[3]);
						//begin two simultaneous games using information given up to this point
						break;

					case "MAKE": //send off move based on current tile
						gameID = tokenizedMessage[5];
						if(GameA == null) GameA = gameID;
						else if(GameB == null) GameB = gameID;

						tileToPlace = tokenizedMessage[12];
						response = "";
						// Pass the move to the game
						if (gameID.equals(GameA))
						{
							response = gameA.makeMove(/*tileToPlace*/);
							// Add the starting information to the move
							response = "GAME A MOVE " + moveANum + " " + response;
						}
						else if (gameID.equals(GameB))
						{
							response = gameB.makeMove(/*tileToPlace*/);
							// Add the starting information to the move
							response = "GAME B MOVE " + moveBNum + " " + response;
						}

						// Send our move
						out.println(response);
						System.out.println("Client: " + response);
						break;

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
								gameA.endGame();
								gameA = null;
							}
							else if (gameID.equals(GameB))
							{
								GameB = null;
								gameB.endGame();
								gameB = null;
							}
						}
						else if (tokenizedMessage[6].equals("PLACED"))
						{
							//a move was made - place onto own board(s)
							tilePlaced = tokenizedMessage[7];
							tilePlacedX = Integer.parseInt(tokenizedMessage[9]);
							tilePlacedY = Integer.parseInt(tokenizedMessage[10]);
							tileOrientation = Integer.parseInt(tokenizedMessage[11]) / 90;
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

					default:
					//break;
					}
				}
			}
			//System.out.println("While ended");
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " +
					hostName);
			System.exit(1);
		}
	}
}
