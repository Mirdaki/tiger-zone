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


		SquareTile tile = new SquareTile("JJJJ-",0);
		SquareTile tile2 = new SquareTile("TLTJ-",0);
		SquareTile tile3 = new SquareTile("TLTJ-",3);

		System.out.println(tile.toString());
		System.out.println(tile2.toString());
		System.out.println(tile3.toString());

		// BoardObject test = new BoardObject(); //create the board
		// test.start();
		//
		// SquareTile test1 = test.getTile('S',2);
		// SquareTile test2 = test.getTile('S',0);
		//
		// System.out.println(test1.toString());
		// System.out.println(test2.toString());
		//
		// // if (test.place(test1,new Location(5,6)) == false) {
		// // 	System.out.println("Invalid placement!");
		// // }
		//
		// // if (test.place(test2,new Location(5,7)) == false) {
		// // 	System.out.println("Invalid placement!");
		// // }
		//
		// test.print();
		// test.printSpots();

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
