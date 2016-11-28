package entities;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
public class TigerZone {

	public static void main(String[] args) {

		TileDeck deck = new TileDeck();
		deck.start("TLTJ-");
		TigerTile tile = deck.getRandom();		

		BoardObject test = new BoardObject(); //create the board
		test.start("TTTT-",0,0,0);
		test.print();

		

//		Scanner in = new Scanner(System.in);
//		String input = "";
//
//		while(true) { 
//
//			System.out.println("MENU");
//			System.out.println("Type 0 to see the board\nType 1 to place a tile" + 
//					"\nType 2 to see incomplete regions" + 
//					"\nType 3 to see complete regions" + 
//					"\nType 4 to place Tiger on most recent tile" +
//					"\nType 5 to print scores" + 
//					"\nType 6 to confirm move" + 
//					"\nType 7 to end game"
//					);
//
//			int choice = in.nextInt();
//			in.nextLine();
//			switch(choice) { 
//			case 0: 
//				test.print();
//				test.printSpots();
//				break;
//			case 1: 
//				TigerTile random = deck.getRandom();
//
//				System.out.println("Your tile to place is " + random.getType());
//
//
//				System.out.println("Tile to place: [TYPE]");
//				input = in.nextLine();
//				TigerTile testTile = test.getTile(input,0);
//
//				test.canPlace(testTile);
//
//
//
//				System.out.println(test.getPossibleSpots());
//
//
//				System.out.println("[X] [Y] [ORIENTATION] [TIGER INDEX]");
//				input = in.nextLine();
//
//				String[] result = input.split("\\s");
//				Location location = new Location(Integer.parseInt(result[0]),Integer.parseInt(result[1]));					
//				testTile.setOrientation(Integer.parseInt(result[2]) / 90);
//
//
//				if(!test.place(testTile, location)) { 
//					System.out.println("Couldn't place tile. REASON: " + test.getReason());
//				}
//				else { 
//					if (result.length > 3)
//						if (result[3].equals("CROC")) { 
//							if(!test.placeCrocodile()) { System.out.println("Couldn't place Crocodile. REASON: " + test.getReason()); break; }
//						}
//						else 
//							if(!test.placeTiger(Integer.parseInt(result[3]))) { System.out.println("Couldn't place Tiger. REASON: " + test.getReason()); test.setPending(false); break; }
//
//					System.out.println("Successfully \"placed\" tile! Hit 6 to confirm.");
//				}
//
//				break;
//			case 2:
//				System.out.println("INCOMPLETE REGIONS");
//				for (Map.Entry<Integer, Region> entry : test.getIncomplete().entrySet()) {
//					System.out.println(entry.getKey() + "/" + entry.getValue());
//
//					for (Terrain terrain : entry.getValue().getTerrains())
//						System.out.println("\t" + terrain);
//				}
//				break;
//
//			case 3:
//				System.out.println("COMPLETE REGIONS");
//				if (test.getComplete().size() == 0) System.out.println("No complete regions");
//				else for (Region region : test.getComplete()) System.out.println(region);
//				break;
//			case 4: 
//				System.out.println("Place meeple at (1-9): ");
//				int index = in.nextInt();
//				in.nextLine();
//				if(!test.placeTiger(index)) System.out.println("Couldn't place Tiger. REASON: " + test.getReason());
//				else { 
//					System.out.println("Successfully placed Tiger on tile at index " + index);
//				}
//				break;
//			case 5:
//				test.printScores();
//				break;
//			case 6:
//				test.confirm();
//				break;
//			case 7:
//				test.end();
//				System.exit(0);
//				break;
//
//			default: 
//				break;
//			}
//			System.out.println();
//
//		}

	} //end of main

} //end of class
