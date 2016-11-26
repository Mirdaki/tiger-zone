package network;

import java.io.*;
import java.net.*;

class TCPServer
{
   public static void main(String argv[]) throws Exception
      {
         ServerSocket welcomeSocket = new ServerSocket(6789);
         System.out.println("Server  ready for chatting");
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
            }
            
            //pwrite.flush();
         }
      }
}