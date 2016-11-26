package entities;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
public class TigerZone {

	public TigerZone() {

	}

	public static void main(String[] args) {

//		SquareTile test = new SquareTile("TLLL-",0);
//		System.out.println(test);
		
		BoardObject test = new BoardObject(); //create the board
		test.start();
		
		Scanner in = new Scanner(System.in);
		String input = "";

		while(true) { 

			System.out.println("MENU");
			System.out.println("Type 0 to see the board\nType 1 to place a tile\nType 2 to see incomplete regions\nType 3 to see complete regions\nType 4 to place Tiger on most recent tile\nType 5 to confirm\nType 6 to exit");

			int choice = in.nextInt();
			in.nextLine();
			switch(choice) { 
			case 0: 
				test.print();
				test.printSpots();
				break;
			case 1: 
				if (test.getPending()) { 
					System.out.println("Your last move is still pending!");
				}
				else { 
					System.out.println("Tile to place: [TYPE] [ORIENTATION] [X] [Y]");
					input = in.nextLine();

					String[] result = input.split("\\s");
					SquareTile testTile = test.getTile(result[0],Integer.parseInt(result[1]));
					Location location = new Location(Integer.parseInt(result[2]),Integer.parseInt(result[3]));

					if(!test.place(testTile, location)) { 
						System.out.println("Couldn't place tile. REASON: " + test.getReason());
					}
					else { 
						System.out.println("Successfully \"placed\" tile! Hit 5 to confirm.");
						test.setPending();
					}
				}
				break;
			case 2:
				System.out.println("INCOMPLETE REGIONS");
				for (Map.Entry<Integer, Region> entry : test.getIncomplete().entrySet()) {
					System.out.println(entry.getKey() + "/" + entry.getValue());

					for (Terrain terrain : entry.getValue().getTerrains())
						System.out.println("\t" + terrain);
				}
				break;

			case 3:
				System.out.println("COMPLETE REGIONS");
				if (test.getComplete().size() == 0) System.out.println("No complete regions");
				else for (Region region : test.getComplete()) System.out.println(region);
				break;
			case 4: 
				System.out.println("Place meeple at (1-9): ");
				int index = in.nextInt();
				in.nextLine();
				if(!test.placeTiger(index)) System.out.println("Couldn't place Tiger. REASON: " + test.getReason());
				else { 
					System.out.println("Successfully placed Tiger on tile at index " + index);
				}
				break;
			case 5:
				test.confirm();			
				break;
			case 6:
				System.exit(0);
				break;
			default: 
				break;
			}
			System.out.println();

		}

	} //end of main

} //end of class
