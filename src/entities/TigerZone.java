package entities;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;
public class TigerZone {


	public TigerZone() {

	}

	public static void main(String[] args) {


		BoardObject test = new BoardObject(); //create the board
		test.start();

		SquareTile test1 = test.getTile('A',0);

		if (test.place(test1,new Location(6,5)) == false) {
			System.out.println("Invalid placement!");
		}

		test.print();
		test.printSpots();

		//SquareTile test1 = test.getTile('A',0);
		//SquareTile test2 = test.getTile('A',0);
		//System.out.println(test1.similarEdge(test2));

		//Scanner in = new Scanner(System.in);
		//String input = in.nextLine();
		// while(!input.equalsIgnoreCase("exit")) {
		// 	test0 = test.getTile('G',Integer.parseInt(input));
		// 	System.out.println(test0.toString());
		// 	input = in.nextLine();
		// }
		//example of getting a tile: 'G' (last one on first row)
//		SquareTile test0 = test.getTile('G',0); //0 degree rotation
		//SquareTile test90 = test.getTile('G',1); //90
		//SquareTile test180 = test.getTile('G',2); //180
		//SquareTile test270 = test.getTile('G',3); //270

//		System.out.println(test0.toString());
		//System.out.println(test90.toString());
		//System.out.println(test180.toString());
		//System.out.println(test270.toString());


	} //end of main

} //end of class
