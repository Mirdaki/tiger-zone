package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import entities.BoardObject;
import entities.Location;
import entities.Player;
import entities.Region;
import entities.SquareTile;

public class ScoringSystemTest {

	@Test
	public void trailMidGameScoringTest() {
		BoardObject test = new BoardObject(); //create the board
		test.start();
		//Create 2 players
		Player p1 = new Player(0);
		Player p2 = new Player(1);
		//Tell board about the players
		test.setPlayer(0, p1);
		test.setPlayer(1, p2);
		//Create a 3 tile complete trail from (0,1) to (0,-1)
		SquareTile tile1 = test.getTile("TJTT-",2);
		test.place(tile1,new Location(0,1));
		//Place tiger from player 1 on trail at (1,0)
		//
		//
		//Complete trail
		SquareTile tile2 = test.getTile("TTTT-",0);
		test.place(tile2,new Location(0,-1));
		//Create trail region r1 if not automatically updated
		//
		//
		
		assertEquals(3, test.getPlayer(0).getScore());
		
		//Add trail with an animal on it with tiles at (0,-1), (0,-2), and (-1,-2)
		SquareTile tile3 = test.getTile("TLJTP",0);
		test.place(tile3,new Location(0,-2));
		//Place tiger from player 2 on trail at (0,-2)
		//
		//
		//Complete trail
		SquareTile tile4 = test.getTile("TJTT-",1);
		test.place(tile4,new Location(-1,-2));
		//Create trail region r2 if not automatically updated
		//
		//
		
		assertEquals(4, test.getPlayer(1).getScore());
	}

	@Test
	public void lakeMidGameScoringTest() {
		BoardObject test = new BoardObject(); //create the board
		test.start();
		//Create 2 players
		Player p1 = new Player(0);
		Player p2 = new Player(1);
		//Tell board about the players
		test.setPlayer(0, p1);
		test.setPlayer(1, p2);
		//Create a 3 tile complete lake from (0,0) to (2,0)
		SquareTile tile1 = test.getTile("JLJL-",0);
		test.place(tile1,new Location(1,0));
		//Place tiger from player 1 on lake at (1,0)
		//
		//
		//Complete lake
		SquareTile tile2 = test.getTile("LJJJ-",3);
		test.place(tile2,new Location(2,0));
		//Create lake region r1 if not automatically updated
		//
		//
		
		assertEquals(6, test.getPlayer(1).getScore());
		
		//Create a 2 tile complete lake from (1,0) to (1,1)
		SquareTile tile3 = test.getTile("JLTTB",0);
		test.place(tile3,new Location(0,1));
		//Place tiger from player 2 on lake at (0,1)
		//
		//
		//Complete lake
		SquareTile tile4 = test.getTile("LJJJ-",3);
		test.place(tile4,new Location(1,1));
		//Create lake region r2 if not automatically updated
		//
		//
		
		assertEquals(8, test.getPlayer(1).getScore());
	}
	
	@Test
	public void denMidGameScoringTest() {
		BoardObject test = new BoardObject(); //create the board
		test.start();
		//Create 2 players
		Player p1 = new Player(0);
		Player p2 = new Player(1);
		//Tell board about the players
		test.setPlayer(0, p1);
		test.setPlayer(1, p2);
		//Create a 9 tile complete den surrounding (-1,0)
		SquareTile tile1 = test.getTile("TJTJ-",0);
		test.place(tile1,new Location(0,1));
		SquareTile tile2 = test.getTile("JJJJ-",0);
		test.place(tile2,new Location(-1,1));
		SquareTile tile3 = test.getTile("LJJJ-",0);
		test.place(tile3,new Location(-2,1));
		SquareTile tile4 = test.getTile("LJJJ-",3);
		test.place(tile4,new Location(-2,0));
		SquareTile tile5 = test.getTile("JJJJX-",0);
		test.place(tile5,new Location(-1,0));
		//Place tiger from player 1 on den at (-1,0)
		//
		//
		SquareTile tile6 = test.getTile("TJJT-",3);
		test.place(tile6,new Location(-2,-1));
		SquareTile tile7 = test.getTile("LJJJ-",2);
		test.place(tile7,new Location(-1,-1));
		//Complete den
		SquareTile tile8 = test.getTile("TJTJ-",0);
		test.place(tile8,new Location(0,-1));
		//Create den region r1 if not automatically updated
		//
		//
		
		assertEquals(9, test.getPlayer(0).getScore());
	}
	
	@Test
	public void jungleEndGameScoringTest() {
		BoardObject test = new BoardObject(); //create the board
		test.start();
		//Create 2 players
		Player p1 = new Player(0);
		Player p2 = new Player(1);
		//Tell board about the players
		test.setPlayer(0, p1);
		test.setPlayer(1, p2);
		//Create a 3 tile complete lake from (0,0) to (2,0)
		SquareTile tile1 = test.getTile("JLJL-", 0);
		test.place(tile1, new Location(1, 0));
		//Place tiger from player 1 on jungle at (1,0)
		//
		//
		SquareTile tile2 = test.getTile("LJJJ-", 3);
		test.place(tile2, new Location(2, 0));
		//Create lake region r1 if not automatically updated
		//
		//
		//Create a 2 tile complete lake from (1,0) to (1,1)
		SquareTile tile3 = test.getTile("JLTTB", 0);
		test.place(tile3, new Location(0, 1));
		SquareTile tile4 = test.getTile("LJJJ-", 3);
		test.place(tile4, new Location(1, 1));
		//Create lake region r2 if not automatically updated
		//
		//
		//Create jungle region r3 if not automatically updated
		//Create ArrayList of regions to be scored if not already done by board
		ArrayList<Region> scorethese = new ArrayList<>(Arrays.asList(r3));
		
		assertEquals(6, test.ScoringEndGame(scorethese));
	}
	
	@Test
	public void jungleEndGameWithDenScoringTest() {
		BoardObject test = new BoardObject(); //create the board
		test.start();
		//Create 2 players
		Player p1 = new Player(0);
		Player p2 = new Player(1);
		//Tell board about the players
		test.setPlayer(0, p1);
		test.setPlayer(1, p2);
		//Create a 9 tile complete den surrounding (-1,0)
		SquareTile tile1 = test.getTile("TJTJ-",0);
		test.place(tile1,new Location(0,1));
		SquareTile tile2 = test.getTile("JJJJ-",0);
		test.place(tile2,new Location(-1,1));
		SquareTile tile3 = test.getTile("LJJJ-",0);
		test.place(tile3,new Location(-2,1));
		SquareTile tile4 = test.getTile("LJJJ-",3);
		test.place(tile4,new Location(-2,0));
		SquareTile tile5 = test.getTile("JJJJX-",0);
		test.place(tile5,new Location(-1,0));
		//Place tiger from player 1 on jungle at (-1,0)
		//
		//
		SquareTile tile6 = test.getTile("TJJT-",3);
		test.place(tile6,new Location(-2,-1));
		SquareTile tile7 = test.getTile("LJJJ-",2);
		test.place(tile7,new Location(-1,-1));
		//Complete den
		SquareTile tile8 = test.getTile("TJTJ-",0);
		test.place(tile8,new Location(0,-1));
		//Create den region r1 if not automatically updated
		//
		//
		//Complete 2 lakes
		SquareTile tile9 = test.getTile("LJJJ-",0);
		test.place(tile9,new Location(-1,-2));
		SquareTile tile10 = test.getTile("LJJJ-",2);
		test.place(tile10,new Location(-2,2));
		//Create lake region r2 and r3 if not automatically updated
		//
		//
		//Create jungle region r4 if not automatically updated
		//
		//
		//Create ArrayList of regions to be scored if not already done by board
		ArrayList<Region> scorethese = new ArrayList<>(Arrays.asList(r4));
		
		assertEquals(11, test.ScoringEndGame(scorethese));
	}
	
	@Test
	public void trailEndGameScoringTest() {
		BoardObject test = new BoardObject(); //create the board
		test.start();
		//Create 2 players
		Player p1 = new Player(0);
		Player p2 = new Player(1);
		//Tell board about the players
		test.setPlayer(0, p1);
		test.setPlayer(1, p2);
		//Create a 2 tile incomplete trail from (0,1) to (0,0)
		SquareTile tile1 = test.getTile("TJTJ-",0);
		test.place(tile1,new Location(0,1));
		//Place tiger from player 1 on trail at (0,1)
		//
		//
		//Create trail region r1 if not automatically updated
		//
		//
		//Create ArrayList of regions to be scored if not already done by board
		ArrayList<Region> scorethese = new ArrayList<>(Arrays.asList(r1));
				
		assertEquals(2, test.ScoringEndGame(scorethese));
	}
	
	@Test
	public void trailEndGameWithAnimalScoringTest() {
		BoardObject test = new BoardObject(); //create the board
		test.start();
		//Create 2 players
		Player p1 = new Player(0);
		Player p2 = new Player(1);
		//Tell board about the players
		test.setPlayer(0, p1);
		test.setPlayer(1, p2);
		//Create a 2 tile incomplete trail from (0,1) to (0,0)
		SquareTile tile1 = test.getTile("TJTJ-",0);
		test.place(tile1,new Location(0,1));
		//Place tiger from player 1 on trail at (0,1)
		//
		//
		//Add tile and animal to current trail
		SquareTile tile2 = test.getTile("TLTJD",0);
		test.place(tile2,new Location(0,-1));
		//Create trail region r1 if not automatically updated
		//
		//
		//Create ArrayList of regions to be scored if not already done by board
		ArrayList<Region> scorethese = new ArrayList<>(Arrays.asList(r1));
		
		assertEquals(4, test.ScoringEndGame(scorethese));
	}
	
	@Test
	public void lakeEndGameScoringTest() {
		BoardObject test = new BoardObject(); //create the board
		test.start();
		//Create 2 players
		Player p1 = new Player(0);
		Player p2 = new Player(1);
		//Tell board about the players
		test.setPlayer(0, p1);
		test.setPlayer(1, p2);
		//Create a 2 tile incomplete lake
		SquareTile tile1 = test.getTile("JLJL-",0);
		test.place(tile1,new Location(0,1));
		//Place tiger from player 1 on lake at (1,0)
		//
		//
		//Create lake region r1 if not automatically updated
		//
		//
		//Create ArrayList of regions to be scored if not already done by board
		ArrayList<Region> scorethese = new ArrayList<>(Arrays.asList(r1));
		
		assertEquals(2, test.ScoringEndGame(scorethese));
	}
	
	@Test
	public void lakeEndGameWithAnimalScoringTest() {
		BoardObject test = new BoardObject(); //create the board
		test.start();
		//Create 2 players
		Player p1 = new Player(0);
		Player p2 = new Player(1);
		//Tell board about the players
		test.setPlayer(0, p1);
		test.setPlayer(1, p2);
		//Create a 3 tile incomplete lake with an animal
		SquareTile tile1 = test.getTile("LLLL-",0);
		test.place(tile1,new Location(1,0));
		//Place tiger from player 1 on lake at (1,0)
		//
		//
		SquareTile tile2 = test.getTile("TLLTB",0);
		test.place(tile2,new Location(1,1));
		//Create lake region r1 if not automatically updated
		//
		//
		//Create ArrayList of regions to be scored if not already done by board
		ArrayList<Region> scorethese = new ArrayList<>(Arrays.asList(r1));
		
		assertEquals(6, test.ScoringEndGame(scorethese));
	}
	
	@Test
	public void denEndGameScoringTest() {
		BoardObject test = new BoardObject(); //create the board
		test.start();
		//Create 2 players
		Player p1 = new Player(0);
		Player p2 = new Player(1);
		//Tell board about the players
		test.setPlayer(0, p1);
		test.setPlayer(1, p2);
		//Create a 6 tile incomplete den surrounding (-1,0)
		SquareTile tile1 = test.getTile("TJTJ-",0);
		test.place(tile1,new Location(0,1));
		SquareTile tile2 = test.getTile("JJJJ-",0);
		test.place(tile2,new Location(-1,1));
		SquareTile tile3 = test.getTile("LJJJ-",0);
		test.place(tile3,new Location(-2,1));
		SquareTile tile4 = test.getTile("LJJJ-",3);
		test.place(tile4,new Location(-2,0));
		SquareTile tile5 = test.getTile("JJJJX-",0);
		test.place(tile5,new Location(-1,0));
		//Place tiger from player 1 on den at (-1,0)
		//
		//
		//Create den region r1 if not automatically updated
		//
		//
		//Create ArrayList of regions to be scored if not already done by board
		ArrayList<Region> scorethese = new ArrayList<>(Arrays.asList(r1));
		
		assertEquals(6, test.ScoringEndGame(scorethese));
	}
}
