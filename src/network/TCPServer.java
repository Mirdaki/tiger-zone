package network;

import java.io.*;
import java.net.*;

class TCPServer
{
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("Usage: java KnockKnockServer <port number>");
			System.exit(1);
		}

		int portNumber = Integer.parseInt(args[0]);

		System.out.println("The server is currently awaiting a connection from a client...");

		try (
				ServerSocket serverSocket = new ServerSocket(portNumber);
				Socket clientSocket = serverSocket.accept();
				PrintWriter out =
						new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));
				) {

			

			String inputLine, outputLine;
			
			// Initiate conversation with client
			TigerZoneProtocol kkp = new TigerZoneProtocol();
			outputLine = kkp.processInput(null);
			out.println(outputLine);

			while ((inputLine = in.readLine()) != null) {
				outputLine = kkp.processInput(inputLine);
				out.println(outputLine);
				if (outputLine.equals("THANK YOU FOR PLAYING! GOODBYE"))
					break;
			}
		} catch (IOException e) {
			System.out.println("Exception caught when trying to listen on port "
					+ portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
	}
}

//		try (
//				ServerSocket serverSocket = new ServerSocket(portNumber);
//				Socket clientSocket = serverSocket.accept();
//				PrintWriter out =
//						new PrintWriter(clientSocket.getOutputStream(), true);
//				BufferedReader in = new BufferedReader(
//						new InputStreamReader(clientSocket.getInputStream()));
//				) {
//
//
//			String inputLine, outputLine;
//
//			ServerSocket welcomeSocket = new ServerSocket(6789);
//			System.out.println("Server  ready for chatting");
//			Socket connectionSocket = welcomeSocket.accept();
//			BufferedReader inFromClient =
//					new BufferedReader(new InputStreamReader(System.in));
//			OutputStream outToClient = connectionSocket.getOutputStream();
//			PrintWriter pwrite = new PrintWriter(outToClient, true);
//			InputStream istream = connectionSocket.getInputStream();
//			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(istream));
//			String receiveMessage, sendMessage;
//			while(true)
//			{
//				sendMessage= inFromClient.readLine();
//				pwrite.println(sendMessage);
//				receiveMessage = inFromServer.readLine();
//				if (receiveMessage != null) {
//					System.out.println(receiveMessage);
//				}
//
//				//pwrite.flush();
//			}
//		}
//	}
//}