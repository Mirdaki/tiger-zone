package network;
/*
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TigerZoneServer
{
	public static void main(String args[]) throws Exception
  {

		if (args.length != 1)
		{
				System.err.println("Usage: java KnockKnockServer <port number>");
				System.exit(1);
		}

		int portNumber = Integer.parseInt(args[0]);

		System.out.println("The server is currently awaiting a connection from a client...");


		try
		(
	    ServerSocket serverSocket = new ServerSocket(portNumber);
	    Socket clientSocket = serverSocket.accept();
	    PrintWriter out =
	        new PrintWriter(clientSocket.getOutputStream(), true);
	    BufferedReader in = new BufferedReader(
	        new InputStreamReader(clientSocket.getInputStream()));
		)
		{
			clientSocket = serverSocket.accept();
  	}

		try
		(
	    PrintWriter out =
	        new PrintWriter(clientSocket.getOutputStream(), true);
	    BufferedReader in = new BufferedReader(
	        new InputStreamReader(clientSocket.getInputStream()));
		)
		{
	    String inputLine, outputLine;

	    // Initiate conversation with client
	    KnockKnockProtocol kkp = new KnockKnockProtocol();
	    outputLine = kkp.processInput(null);
	    out.println(outputLine);

	    while ((inputLine = in.readLine()) != null)
			{



        outputLine = kkp.processInput(inputLine);
        out.println(outputLine);
        if (outputLine.equals("Bye."))
            break;
	    }
		}

	}
}
*/
