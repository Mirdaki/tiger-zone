import java.awt.GridLayout;
import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;


public class TigerZone extends JFrame {
	
	
	public TigerZone() {

	}
	
	public static void main(String[] args) {

		
		
		
		
		BoardObject test = new BoardObject();
		
		Map<String, SquareTile> testing = test.getMap();

		Player player1 = test.getPlayer(0),
				player2 = test.getPlayer(1);

		
		player1.setFirst(false);
		player1.setFirst(true);
		
		player2.setAI(true);
		
		player1.setMeeple(player1.getMeeple()-5);
		
		player2.addScore(50);
		player2.addScore(150);

		System.out.println(player1.toString());
		System.out.println(player2.toString());

		test.setPlayer(1, player1);

		System.out.println(player1.toString());
		System.out.println(player2.toString());
		
		
		
		
		
		
		
//		Player player1 = test.getPlayer(0);
//		Player player2 = test.getPlayer(1);
//		
//		player1.setFirst(false);
//		player1.setFirst(true);
//		
//		player2.setAI(true);
//		
//		player1.setMeeple(player1.getMeeple()-5);
//		
//		player2.addScore(50);
//		player2.addScore(150);
//
//		System.out.println(player1.toString());
//		System.out.println(player2.toString());
		
		//System.out.println(testing.get("P0").toString() + "\n");
		//System.out.println(testing.get("P90").toString() + "\n");
		//System.out.println(testing.get("P180").toString() + "\n");
		//System.out.println(testing.get("P270").toString() + "\n");
		
		SquareTile tester = testing.get("A0");
		//System.out.println(tester.toString());
		
		//edge edges[] = tester.getEdges();
		
		//for (int i = 0; i < edges.length; i++) 
		//{
		//	System.out.println(edges[i].toString());
		//}
		
	/*	SquareTile test = new SquareTile(1,new Location(),'A');
		
		
		System.out.println(test.toString() + "\n");
		
		test.rotateRight();
		test.rotateRight();
		test.rotateRight();

		System.out.println(test.toString() + "\n");
		
		test.rotateLeft();
		System.out.println(test.toString() + "\n");
*/
		
		/*SquareTile test;

		test = new SquareTile(1,2,3,4,'A');
		
		//System.out.println(test.toString() + "\n");
		
		test.printOut();

		System.out.println();
		
		test.rotateRight();
//		test.rotateLeft();
		
		test.printOut();*/
		
		/*HexTile testHex;
		testHex = new HexTile(5,1,7,8);
		
		System.out.println("\n"+testHex.toString());
		
		Scanner input = new Scanner(System.in);
		String test = "";
		
		do 
		{
			test = input.nextLine();
			System.out.println(test);
		} while (test.compareTo("quit") != 0);*/
		
		
	} //end of main
	
} //end of class
