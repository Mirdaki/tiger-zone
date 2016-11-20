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

		// SquareTile tile = new SquareTile("JJJJ-",0);
		// SquareTile tile2 = new SquareTile("TLTJ-",0);
		// SquareTile tile3 = new SquareTile("TLTJ-",3);
		//
		// System.out.println(tile.toString());
		// System.out.println(tile2.toString());
		// System.out.println(tile3.toString());

		BoardObject test = new BoardObject(); //create the board
		test.start();

		SquareTile test1 = test.getTile("JJTJX",0);
		SquareTile test2 = test.getTile("TJTJ-",0);
		SquareTile test3 = test.getTile("JLJL-",0);
		SquareTile test4 = test.getTile("JJJJ-",0);
		SquareTile test5 = test.getTile("JJJJX",0);

		if (test.place(test1,new Location(-1,0)) == false) {
			System.out.println("Invalid placement!");
		}

		if (test.place(test1,new Location(0,-1)) == false) {
			System.out.println("Invalid placement!");
		}

		if (test.place(test2,new Location(1,0)) == false) {
			System.out.println("Invalid placement!");
		}

		if (test.place(test3,new Location(0,1)) == false) {
			System.out.println("Invalid placement!");
		}

		if (test.place(test4,new Location(-1,1)) == false) {
			System.out.println("Invalid placement!");
		}

		if (test.place(test5,new Location(-2,1)) == false) {
			System.out.println("Invalid placement!");
		}

		if (test.place(test5,new Location(-2,0)) == false) {
			System.out.println("Invalid placement!");
		}

		if (test.place(test5,new Location(-2,-1)) == false) {
			System.out.println("Invalid placement!");
		}

		if (test.place(test5,new Location(-1,-1)) == false) {
			System.out.println("Invalid placement!");
		}

		System.out.println(test.isSurrounded(new Location(-1,0)));

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
