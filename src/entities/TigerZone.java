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

		// SquareTile test = new SquareTile("TLTJ-",0);
		// System.out.println(test);
		//
		// SquareTile test1 = new SquareTile("JLTT-",0);
		// System.out.println(test1);
		//
		// TileEdges edges = test.getEdges();
		// TileEdges edges1 = test1.getEdges();

		// Terrain testTerrain = edges.getTerrain(3);
		// edges.updateTerrains(testTerrain.getTileConnections(),100);
		// System.out.println();

		// for (Terrain terrain : edges.getTerrains()) {
		// 	System.out.println(terrain);
		// }


		// System.out.println(edges1.equals(edges,TileEdges.NORTH));
		// System.out.println(edges1.equals(edges,TileEdges.EAST));
		// System.out.println(edges1.equals(edges,TileEdges.SOUTH));
		// System.out.println(edges1.equals(edges,TileEdges.WEST));

		// SquareTile tile = new SquareTile("TLLL-",0);
		// SquareTile tile2 = new SquareTile("TLLL-",0);
		// Terrain[] terrains = tile.getTerrains();
		// Terrain[] terrains2 = tile2.getTerrains();
		//
		//
		// System.out.println(tile2);
		// for (Terrain terrain : terrains) {
		// 	ArrayList<Integer> connections = terrain.getTileConnections();
		// 	System.out.println(terrain);
		// 	for (Integer spot : connections)
		// 		System.out.print(spot + " " );
		// 	System.out.println();
		// }


		// System.out.println(tile.toString());

		// SquareTile tile2 = new SquareTile("TLTJ-",0);
		// SquareTile tile3 = new SquareTile("TLTJ-",3);

		// System.out.println(tile.toString());
		// System.out.println(tile2.toString());
		// System.out.println(tile3.toString());

		BoardObject test = new BoardObject(); //create the board
		test.start();
		// SquareTile test1 = test.getTile("TLTJ-",2);
		// SquareTile test2 = test.getTile("JJJJX",0);
		// // SquareTile test3 = test.getTile("JJJJX",1);
		//
		//
		//
		// //
		// if (test.place(test1, new Location(0,1)) == false) {
		// 	System.out.println("invalid");
		// }
		//
		// if (test.place(test2, new Location(0,2)) == false) {
		// 	System.out.println("invalid");
		// }
		//
		// test2 = test.getTile("JJJJX",0);
		//
		// if (test.place(test2, new Location(0,3)) == false) {
		// 	System.out.println("invalid");
		// }

		// if (test.place(test3, new Location(0,3)) == false) {
		// 	System.out.println("invalid");
		// }

		//Map<Integer, Region> incomplete = test.getIncomplete();


		// for (Map.Entry<Integer, Region> entry : test.getIncomplete().entrySet()) {
		//     System.out.println(entry.getKey() + "/" + entry.getValue());
		//
		// 	for (Terrain terrain : entry.getValue().getTerrains())
		// 		System.out.println("\t" + terrain);
		// }


		// for (Region region : incomplete) {
		// 	System.out.println(region);
		// 	for (Terrain terrain : region.getTerrains())
		// 		System.out.println("\t" + terrain);
		// }
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

		 test.print();
		 test.printSpots();
		 System.out.println("\n" + test.isSurrounded(new Location(-1,0)));

	} //end of main

} //end of class
