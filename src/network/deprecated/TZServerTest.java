package network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TZServerTest
{
	public static void main(String argv[]) throws Exception
    {
       ServerSocket welcomeSocket = new ServerSocket(4444);
       System.out.println("Server ready for chatting");
       Socket connectionSocket = welcomeSocket.accept();
       BufferedReader inFromClient =
               new BufferedReader(new InputStreamReader(System.in));
      OutputStream outToClient = connectionSocket.getOutputStream();
      PrintWriter pwrite = new PrintWriter(outToClient, true);
       InputStream istream = connectionSocket.getInputStream();
       BufferedReader inFromServer = new BufferedReader(new InputStreamReader(istream));
      String receiveMessage, sendMessage;
       while(true)
       {
      	 sendMessage= inFromClient.readLine();
           pwrite.println(sendMessage);
      	 receiveMessage = inFromServer.readLine();
          if (receiveMessage != null) {
          	System.out.println(receiveMessage);
						System.out.println("IF");
          }
					System.out.println("LOOP");

          pwrite.flush();
       }
			 //System.out.println("END WHILE");
    }
}
