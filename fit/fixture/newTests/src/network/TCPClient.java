package network;

import java.io.*;
import java.net.*;

class TCPClient
{
 public static void main(String argv[]) throws Exception
 {
  BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
  Socket clientSocket = new Socket("localhost", 6789);
  OutputStream outToServer = clientSocket.getOutputStream();
  PrintWriter pwrite = new PrintWriter(outToServer, true);
  InputStream istream = clientSocket.getInputStream();
  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(istream));
  
  //System.out.println("Start the chitchat, type and press Enter key");
  
  String receiveMessage, sendMessage;
  //char a = 'A';
  //sendMessage = "MOVE MEEPLE TO " + a;
  while (true) {
	  receiveMessage = inFromServer.readLine();
	  if(receiveMessage != null){
		  System.out.println(receiveMessage);
	  }
	  sendMessage=inFromUser.readLine();
	  pwrite.println(sendMessage);
	  //pwrite.flush();
	  
	 // pwrite.println(sendMessage);
	 
  }
  
  //clientSocket.close();
 }
}