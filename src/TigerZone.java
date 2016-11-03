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
		
		System.out.println(testing.get("P0").toString() + "\n");
		System.out.println(testing.get("P90").toString() + "\n");
		System.out.println(testing.get("P180").toString() + "\n");
		System.out.println(testing.get("P270").toString() + "\n");
		
		
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
