import java.io.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TigerZone {
		
	public static void main(String[] args) {
		TileObject test;

		test = new SquareTile(5,2,3,0);
		
		System.out.println(test.toString());
		
		HexTile testHex;
		testHex = new HexTile(5,2,3,0);
		
		System.out.println(testHex.toString());
		
		/*Scanner input = new Scanner(System.in);
		String test = "";
		
		do 
		{
			test = input.nextLine();
			System.out.println(test);
		} while (test.compareTo("quit") != 0);*/
		
		
	} //end of main
	
} //end of class
