package entities;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import game.*;
public class TigerZone {

	public static void main(String[] args) {


		Game game = new Game("wtf");
		TileDeck deck = new TileDeck();
		Object[] randomDeck = deck.getRandomDeck();
		ArrayList<TigerTile> holyshit = new ArrayList<TigerTile>();
		for (Object random : randomDeck) { 
			holyshit.add((TigerTile) random);
		}

		BoardObject board = game.getBoardObject();
		game.setPlayers("fuck", "you");

		ArrayList<String> what = new ArrayList<String>();
		what.add("TLJT-");
		what.add("JLLJ-");
		what.add("LLLL-");
		what.add("TTTT-");


		game.setStartTile("TLTJ-",0,0,0);
		game.setTileStack(what);

		String wtf = "";
		//		String[] values = wtf.split("\\s+");


		System.out.println(game.makeMove());
		System.out.println(game.makeMove());
		System.out.println(game.makeMove());
		System.out.println(game.makeMove());

		//		System.out.println(values[0]);
		//		 game.placeTile(Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5]), values[6], true, Integer.parseInt(values[7]));
		//
		//		values = game.makeMove().split("\\s+");
		//		 System.out.println(values[0]);
		//		game.placeTile(Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5]), "", true, -1);
		//
		//		values = game.makeMove().split("\\s+");
		//		 System.out.println(values[0]);
		//		game.placeTile(Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5]), "", true, -1);
		//
		//		values = game.makeMove().split("\\s+");
		//		 System.out.println(values[0]);
		//		game.placeTile(Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5]), "", true, -1);
		//		values = game.makeMove().split("\\s+");
		//		 System.out.println(values[0]);
		//		game.placeTile(Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5]), "", true, -1);
		//		values = game.makeMove().split("\\s+");
		//		 System.out.println(values[0]);
		//		game.placeTile(Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5]), "", true, -1);
		//		values = game.makeMove().split("\\s+");
		//		 System.out.println(values[0]);
		//		game.placeTile(Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5]), "", true, -1);
		//		values = game.makeMove().split("\\s+");
		//		 System.out.println(values[0]);
		//		game.placeTile(Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5]), "", true, -1);
		//		values = game.makeMove().split("\\s+");
		//		 System.out.println(values[0]);
		//		game.placeTile(Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5]), "", true, -1);
		//		values = game.makeMove().split("\\s+");
		//		 System.out.println(values[0]);
		//		game.placeTile(Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5]), "", true, -1);

		//		values = game.makeMove().split("\\s+");
		//		game.placeTile(Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5]), values[6], true, Integer.parseInt(values[7]));
		//
		//		values = game.makeMove().split("\\s+");
		//		game.placeTile(Integer.parseInt(values[3]), Integer.parseInt(values[4]), Integer.parseInt(values[5]), values[6], true, Integer.parseInt(values[7]));

		board.printScores();
		//		System.out.println(game.makeMove());
		//		System.out.println(game.makeMove());
		//		System.out.println(game.makeMove());
		//		System.out.println(game.makeMove());

		board.print();



























		//		TileDeck deck = new TileDeck();
		//		deck.start("TLTJ-");
		//		TigerTile tile = deck.getRandom();		
		//
		//		BoardObject test = new BoardObject(); //create the board
		//		test.start("TTTT-",0,0,0);
		//		test.print();
		//


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
