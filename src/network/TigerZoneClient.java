package network;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TigerZoneClient {

	private final String username = "Red";
	private final String password = "Obiwan77";

	public static void main(String[] args) throws Exception {

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
				Socket kkSocket = new Socket(hostName, portNumber);
				PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(
						new InputStreamReader(kkSocket.getInputStream()));
				) {
			BufferedReader stdIn =
					new BufferedReader(new InputStreamReader(System.in));
			String fromServer;
			String fromUser;

			
			//variable declarations - information from server
			String gameID;
			String opponentName; 
			int challengeID, numChallenges;
			int roundID, numRounds;
			int countDown;
			ArrayList<String> tiles = new ArrayList<String>();
			int numTiles;

			//starting tile information
			String startingTile; 
			int startingX, startingY, startingOrientation;

			//tile recently placed by opponent
			String tilePlaced;
			int tilePlacedX;
			int tilePlacedY;
			int tileOrientation;
			String animal;
			int animalZone;
			
			//tile AI wants to place
			String tileToPlace;
			

			//continuously wait for input from server
			while ((fromServer = in.readLine()) != null) {
				
				//display information from server
				System.out.println("Server: " + fromServer);

				
				if (fromServer.equals("THIS IS SPARTA!")) { //if first message, send join request
					out.println("JOIN " + serverPass);
				}
				else if (fromServer.equals("HELLO!")) {  //if request accepted, send authentication
					out.println("I AM " + userName + " " + userPass);
				}
				else if (fromServer.equals("THANK YOU FOR PLAYING!")) { //if end of tournament, exit from this
					//kill AI process and children
					break;
				}
				else { //otherwise, message must be parsed from erver
					
					//tokenize it
					String[] tokenizedMessage = fromServer.split("\\s+");
					String command = tokenizedMessage[0];

					switch(command){

					case "NEW": //if new tournament created (might not need)
						challengeID = Integer.parseInt(tokenizedMessage[2]);
						numRounds = Integer.parseInt(tokenizedMessage[6]);
						break;

					case "BEGIN": //if round begun (might not need)
						roundID = Integer.parseInt(tokenizedMessage[2]);
						numRounds = Integer.parseInt(tokenizedMessage[4]);
						break;

					case "YOUR": //take in opponent information
						opponentName = tokenizedMessage[4];

						//send off opponent name
						
						break;

					case "STARTING": //take in starting tile information (hope we dont need)
						startingTile = tokenizedMessage[3];
						startingX = Integer.parseInt(tokenizedMessage[5]);
						startingY = Integer.parseInt(tokenizedMessage[6]);
						startingOrientation = Integer.parseInt(tokenizedMessage[7]);
						
						//send off starting tile
						break;

					case "THE": //take in the randomized tile list
						numTiles = Integer.parseInt(tokenizedMessage[2]);

						for (int i = 0; i < numTiles; i++) 
							tiles.add(tokenizedMessage[i+6]);

						//send off tiles to AI
						
						break;

					case "MATCH": //begin match
						countDown = Integer.parseInt(tokenizedMessage[3]);
						//begin two simultaneous games using information given up to this point
						
						break;

					case "MAKE": //send off move based on current tile
						gameID = tokenizedMessage[5];
						tileToPlace = tokenizedMessage[12];
						
						//send off the tileToPlace to the AI for the specified game
						//get best move from AI for requested gameID
						//send off information to server
												
						/*
						//general
						if AI wants to place a CROCODILE or NONE
						out.println("GAME " + gameID + " PLACE " + tileToPlace + " AT " + tileToPlaceX + " " + tileToPlaceY + " " + tileToPlaceOrientation + " " + tileToPlaceAnimal)
						
						if AI wants to place a TIGER
						out.println("GAME " + gameID + " PLACE " + tileToPlace + " AT " + tileToPlaceX + " " + tileToPlaceY + " " + tileToPlaceOrientation + " " + tileToPlaceAnimal + " " + tileToPlaceAnimalZone)

						//discarded tile
						out.println("GAME " + gameID + " TILE " + tileToPlace + " UNPLACEABLE PASS")
						out.println("GAME " + gameID + " TILE " + tileToPlace + " UNPLACEABLE RETRIEVE TIGER AT " + locationX + " " + locationY)
						out.println("GAME " + gameID + " TILE " + tileToPlace + " UNPLACEABLE ADD ANOTHER TIGER TO " + locationX + " " + locationY)
						 */					
						break;
						
					case "GAME": //game logic
						if (tokenizedMessage[2].equals("OVER")) { 
							//game over logic - i.e. tell AI to stop the two games
						}
						else if (tokenizedMessage[6].equals("FORFEITED")) {
							//forfeited game logic - ie. tell AI to stop the two games due to forfeit
						}
						else { //a move was made - place onto own board(s)
							
							gameID = tokenizedMessage[2];
							tilePlaced = tokenizedMessage[7];
							tilePlacedX = Integer.parseInt(tokenizedMessage[9]);
							tilePlacedY = Integer.parseInt(tokenizedMessage[10]);
							tileOrientation = Integer.parseInt(tokenizedMessage[11]) / 90;							
							animal = tokenizedMessage[12];
							
							if (!animal.equals("NONE")) 
								animalZone = Integer.parseInt(tokenizedMessage[13]);

							//send off information to the AI/game manager for the game associated with gameID									
						}
						break;
					
					default: break;
					}
				}				
			}
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

//		TigerZoneClient client = new TigerZoneClient();
//		client.run();
//	}
//	
//	public void run() throws Exception
//	{
//		//Requires IP and port information
//		Socket socket = new Socket("localhost", 4444);
//		OutputStream outToServer = socket.getOutputStream();
//		PrintWriter pwrite = new PrintWriter(outToServer, true);
//		InputStream istream = socket.getInputStream();
//		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(istream));
//		String receiveMessage;
//		
//		//Continuously fetch message from server
//		while (true) {
//			receiveMessage = inFromServer.readLine();
//			while (receiveMessage != null){
//				respond(receiveMessage, pwrite);
//			}
//		}
//	}
//	
//}
