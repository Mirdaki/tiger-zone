package network;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class TigerZoneClient {

	private final String username = "Red";
	private final String password = "Obiwan77";
	
	public static void main(String[] args) throws Exception
	{
		TigerZoneClient client = new TigerZoneClient();
		client.run();
	}
	
	public void run() throws Exception
	{
		//Requires IP and port information
		Socket socket = new Socket("localhost", 4444);
		OutputStream outToServer = socket.getOutputStream();
		PrintWriter pwrite = new PrintWriter(outToServer, true);
		InputStream istream = socket.getInputStream();
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(istream));
		String receiveMessage;
		
		//Continuously fetch message from server
		while (true) {
			receiveMessage = inFromServer.readLine();
			while (receiveMessage != null){
				respond(receiveMessage, pwrite);
			}
		}
	}
	
	public void respond(String serverMessage, PrintWriter pwrite) {
		if (serverMessage == "THIS IS SPARTA!")
		{
			pwrite.println("JOIN PersiaRocks!");
		}
		if (serverMessage == "HELLO!")
		{
			pwrite.println("I AM " + username + " " + password);
		}
		if (serverMessage == "WELCOME " + username + " PLEASE WAIT FOR THE NEXT CHALLENGE")
		{
			//Do something, possibly restart our game, while waiting for next challenge
		}
		String[] tokenizedMessage = serverMessage.split("\\s");
		switch (serverMessage.substring(0,3)) {
		case "NEW ": 
			int cid = Integer.parseInt(tokenizedMessage[2]);
			int rounds = Integer.parseInt(tokenizedMessage[6]);
			//Do something with cid and rounds
			break;
		case "BEGI":
			int rid = Integer.parseInt(tokenizedMessage[2]);
			//Do something with rid
			break;
		case "YOUR":
			String opponentID = tokenizedMessage[4];
			//Do something with opponentID
			break;
		case "STAR":
			String startingTile = tokenizedMessage[3];
			int startingTileX = Integer.parseInt(tokenizedMessage[5]);
			int startingTileY = Integer.parseInt(tokenizedMessage[6]);
			//startingTileOrientation gives orientation in 0-90-18-270 format
			int startingTileOrientation = Integer.parseInt(tokenizedMessage[7]);
			//Initiate board with above information
			break;
		case "THE ":
			int numRemainTiles = Integer.parseInt(tokenizedMessage[2]);
			for (int i=0; i<numRemainTiles; i++){
				String[] remainingTiles = new String[numRemainTiles];
				remainingTiles[i] = tokenizedMessage[i+6];
			}
			//Do something with remainingTiles array
			break;
		case "MATC":
			int planTime = Integer.parseInt(tokenizedMessage[3]);
			//Do something with planTime
			break;
		case "MAKE":
			String gameID = tokenizedMessage[5];
			int moveTime = Integer.parseInt(tokenizedMessage[7]);
			int moveNum = Integer.parseInt(tokenizedMessage[10]);
			String tileToBePlaced = tokenizedMessage[12];
			//Do something with above information
			break;
		case "GAME":
			gameID = tokenizedMessage[1];
			if (tokenizedMessage[2] == "OVER"){
				String playerID = tokenizedMessage[4];
				int score = Integer.parseInt(tokenizedMessage[5]);
				//Do something with above player's information
				playerID = tokenizedMessage[7];
				score = Integer.parseInt(tokenizedMessage[8]);
			}
			else{
				moveNum = Integer.parseInt(tokenizedMessage[3]);
				//playerID used to distinguish which player in the game just moved
				String playerID = tokenizedMessage[5];
				if (tokenizedMessage[6] == "FORFEITED:"){
					if(tokenizedMessage[7] == "ILLEGAL"){
						switch (tokenizedMessage[8]) {
						case "TILE":
							boolean illegalTilePlacement = true;
							break;
						case "MEEPLE":
							boolean illegalMeeplePlacement = true;
							break;
						case "MESSAGE":
							boolean illegalMessageReceoved = true;
							break;
						}
					}
					else if(tokenizedMessage[7] == "INVALID"){
						boolean invalidMeeplePlacement = true;
					}
					else if(tokenizedMessage[7] == "TIMEOUT"){
						boolean timeout = true;
					}
				}
				else{
					tileToBePlaced = tokenizedMessage[7];
					int coordX = Integer.parseInt(tokenizedMessage[9]);
					int coordY = Integer.parseInt(tokenizedMessage[10]);
					int orientation = Integer.parseInt(tokenizedMessage[11]);
					String animal = tokenizedMessage[12];
					if (animal == "TIGER") {
						int zoneOfTiger = Integer.parseInt(tokenizedMessage[12]);
					}
				}
			}
			//Do something with above info
			break;
		case "END ":
			rid = Integer.parseInt(tokenizedMessage[3]);
			rounds = Integer.parseInt(tokenizedMessage[5]);
			//There is a case where PLEASE WAIT FOR THE NEXT MATCH is appended to 
			//END OF ROUND <rid> OF <rounds>. Do we need to check for that or just always wait for next match?
			if(tokenizedMessage[2] == "CHALLENGES"){
				//Do something if message is END OF CHALLENGES
			}
			break;
		case "PLEA":
			//Do something if message is PLEASE WAIT FOR THE NEXT CHALLENGE TO BEGIN
			break;
		case "THAN":
			//Close socket?
			break;
		}
	}
}
