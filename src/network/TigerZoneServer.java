package network;

import java.net.*;
import java.io.*;

/**
 * This will act as a dumb server to communicate with the client 
 */
public class TigerZoneServer
{
	public static void main(String args[]) throws Exception
  {

		// Check the usage
		if (args.length != 1)
		{
				System.err.println("Usage: java KnockKnockServer <port number>");
				System.exit(1);
		}

		int portNumber = Integer.parseInt(args[0]);

		System.out.println("The server is currently awaiting a connection from a client...");

		// Attempt connecting
		try
		(
			ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));
			BufferedReader consoleIn =
					new BufferedReader(new InputStreamReader(System.in));
		)
		{
			System.out.println("Client connected");
			String toSend, recived;
			while (true)
			{
				// Get in the console string, only do if it can
				if (consoleIn.ready() && (toSend = consoleIn.readLine()) != null)
				{
					out.println(toSend);
					System.out.println("Server: " + toSend);
				}
				toSend = null;
				// Check if command was recived and output it to the console
				if (in.ready() && ((recived = in.readLine()) != null))
				{
					System.out.println("Client: " + recived);
				}
				recived = null;
			}
		}
		catch (IOException e)
		{
			System.out.println("Exception caught when trying to listen on port "
					+ portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}

	}
}
