package entities;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
public class TigerZone {

	public TigerZone() {

	}

	public static void main(String[] args) {


		BoardObject test = new BoardObject(); //create the board
		test.start();

//		SquareTile test2 = test.getTile("TTTT-",0);
//		test.place(test2,new Location(-1,0));
//
//		SquareTile test3 = test.getTile("TJTT-",0);
//		test.place(test3,new Location(-1,1));
//
//		SquareTile test4 = test.getTile("TJTT-",0);
//		test.place(test4,new Location(-2,0));
//
//		SquareTile test5 = test.getTile("TJTT-",2);
//		test.place(test5,new Location(-2,1));
//
//		SquareTile test6 = test.getTile("TLTJ-",2);
//		if (test.place(test6,new Location(0,1)) == false) {
//			System.out.println("Invalid placement!");
//		}
//
//		SquareTile test7 = test.getTile("TLLT-",2);
//		if (test.place(test7,new Location(-1,-1)) == false) {
//			System.out.println("Invalid placement!");
//		}
//
//		SquareTile test8 = test.getTile("TLLT-",3);
//		if (test.place(test8,new Location(-2,-1)) == false) {
//			System.out.println("Invalid placement!");
//		}
//
//		SquareTile test9 = test.getTile("TJTT-",0);
//		if (test.place(test9,new Location(0,-1)) == false) {
//			System.out.println("Invalid placement!");
//		}
//
//
//		System.out.println(test.getIncomplete().size());
//		for (Map.Entry<Integer, Region> entry : test.getIncomplete().entrySet()) {
//			System.out.println(entry.getKey() + "/" + entry.getValue());
//
//			for (Terrain terrain : entry.getValue().getTerrains())
//				System.out.println("\t" + terrain);
//		}
//
//		test.print();
//		test.printSpots();
//		System.out.println("\n" + test.isSurrounded(new Location(-1,0)));

	} //end of main

} //end of class
