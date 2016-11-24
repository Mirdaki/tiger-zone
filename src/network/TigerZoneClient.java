package network;

import java.io.*;
import java.net.*;

public class TigerZoneClient {

	public static void main(String[] args) throws Exception
	{
		TigerZoneClient client = new TigerZoneClient();
		client.run();
	}
	
	public void run() throws Exception
	{
		//Requires IP and port information
		Socket socket = new Socket("", );
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
		String sendMessage;
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
			continue;
		}
		switch (serverMessage) {
		
		}
	}
}
