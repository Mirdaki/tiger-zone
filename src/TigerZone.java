import java.io.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TigerZone {
		
	public static void main(String[] args) {
		SquareTile test;

		test = new SquareTile(1,2,3,4,'A');
		
		//System.out.println(test.toString() + "\n");
		
		test.printOut();

		System.out.println();
		
		test.rotateRight();
//		test.rotateLeft();
		
		test.printOut();
		
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
