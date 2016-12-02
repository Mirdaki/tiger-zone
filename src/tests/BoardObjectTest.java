package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.BoardObject;
import entities.Location;
import entities.Player;
import entities.TigerTile;

public class BoardObjectTest {

	@Test
	public void boardObjectConstructorAndGetterTest() {
		BoardObject board = new BoardObject(); //create the board
		TigerTile t = new TigerTile("TLTJ-", 0);
		Player[] players = new Player[2];
		Player p1 = new Player("Red", true);
		Player p2 = new Player("Blue", false);
		players[0] = p1;
		players[1] = p2;
		board.setPlayers(players);
		board.start(t, 0, 0);
		
		assertEquals(13, board.getAS().get(0).getX());
		assertEquals(12, board.getAS().get(0).getY());
		assertEquals(2, board.getPlayers().length);
		assertEquals("TLTJ-", board.getTile(new Location(0, 0)).getType());
		assertEquals(0, board.getStartX());
		assertEquals(0, board.getStartY());
	}

	@Test
	//Test if board's players are set
	public void boardSetPlayersTest() {
		Player[] players = new Player[2];
		Player p1 = new Player("Red", true);
		Player p2 = new Player("Blue", false);
		players[0] = p1;
		players[1] = p2;
		BoardObject board = new BoardObject();
		board.setPlayers(players);
		
		assertEquals("Red", board.getPlayer(0).getID());
		assertEquals("Blue", board.getPlayer(1).getID());
	}
	
	@Test
	//Test if switchToActivePlayer works correctly
	public void boardActivePlayerTest() {
		BoardObject board = new BoardObject(); //create the board
		TigerTile t = new TigerTile("TLTJ-", 0);
		Player[] players = new Player[2];
		Player p1 = new Player("Red", true);
		Player p2 = new Player("Blue", false);
		players[0] = p1;
		players[1] = p2;
		board.setPlayers(players);
		board.start(t, 0, 0);
		
		assertEquals("Red", board.getActivePlayer().getID());
		
		board.switchToActivePlayer(p2);
		
		assertEquals("Blue", board.getActivePlayer().getID());
	}
	
	@Test
	//Test if confirm correctly changes active player
	public void boardConfirmTest() {
		BoardObject board = new BoardObject(); //create the board
		TigerTile t = new TigerTile("TLTJ-", 0);
		Player[] players = new Player[2];
		Player p1 = new Player("Red", true);
		Player p2 = new Player("Blue", false);
		players[0] = p1;
		players[1] = p2;
		board.setPlayers(players);
		board.start(t, 0, 0);
		board.confirm();
		
		assertEquals("Blue", board.getActivePlayer().getID());
	}
	
	@Test
	//Test if a tile was placed on board
	public void boardPlaceTest() {
		BoardObject board = new BoardObject(); //create the board
		TigerTile t = new TigerTile("TLTJ-", 0);
		Player[] players = new Player[2];
		Player p1 = new Player("Red", true);
		Player p2 = new Player("Blue", false);
		players[0] = p1;
		players[1] = p2;
		board.setPlayers(players);
		board.start(t, 0, 0);
		TigerTile t2 = new TigerTile("JJJJ-", 0);
		Location l = new Location(-1, 0);
		board.place(t2, l);
		
		assertEquals("JJJJ-", board.getTile(l).getType());
	}
	
	@Test
	//Test if tiger can be placed on board and removed
	public void boardPlaceRemoveTigerTest() {
		BoardObject board = new BoardObject(); //create the board
		TigerTile t = new TigerTile("TLTJ-", 0);
		Player[] players = new Player[2];
		Player p1 = new Player("Red", true);
		Player p2 = new Player("Blue", false);
		players[0] = p1;
		players[1] = p2;
		board.setPlayers(players);
		board.start(t, 0, 0);
		TigerTile t2 = new TigerTile("JJJJ-", 0);
		Location l = new Location(-1, 0);
		board.place(t2, l);
		board.placeTiger(1);
		
		assertEquals(1, board.getTile(l).getTigers().size());
		
		board.removeTiger(new Location(-1, 0));
		
		assertEquals(0, board.getTile(l).getTigers().size());
	}
	
	@Test
	//Test if adjacent tiles can accept incoming tile
	public void validTest() {
		BoardObject board = new BoardObject(); //create the board
		TigerTile t = new TigerTile("TLTJ-", 0);
		Player[] players = new Player[2];
		Player p1 = new Player("Red", true);
		Player p2 = new Player("Blue", false);
		players[0] = p1;
		players[1] = p2;
		board.setPlayers(players);
		board.start(t, 0, 0);
		TigerTile s = new TigerTile("JJTJX", 0);

		//Placement at (-1,0) should be valid
		assertTrue(board.valid(s, new Location(-1, 0)));
	}
	
	@Test
	//Test for placing start tile and setting start location
	public void startTest() {
		BoardObject board = new BoardObject(); //create the board
		TigerTile t = new TigerTile("TLTJ-", 0);
		Player[] players = new Player[2];
		Player p1 = new Player("Red", true);
		Player p2 = new Player("Blue", false);
		players[0] = p1;
		players[1] = p2;
		board.setPlayers(players);
		board.start(t, 0, 0);
		
		assertEquals(0, board.getStartX());
		assertEquals(0, board.getStartY());
		assertEquals("TLTJ-", board.getTile(new Location(0, 0)).getType());
		
		board.setStart(1, 1);
		assertEquals(1, board.getStartX());
		assertEquals(1, board.getStartY());
	}
	
	@Test
	//Test if we can check all permuatations of a tile according to the available spots and place it
	public void canPlaceTest() {
		BoardObject board = new BoardObject(); //create the board
		TigerTile t = new TigerTile("TLTJ-", 0);
		Player[] players = new Player[2];
		Player p1 = new Player("Red", true);
		Player p2 = new Player("Blue", false);
		players[0] = p1;
		players[1] = p2;
		board.setPlayers(players);
		board.start(t, 0, 0);
		TigerTile s = new TigerTile("JJTJX", 0);
		
		assertTrue(board.canPlace(s));
	}
	
	/*@Test
	public void boardPlaceCrocodileTest() {
		BoardObject board = new BoardObject(); //create the board
		TigerTile t = new TigerTile("TLTJ-", 0);
		Player[] players = new Player[2];
		Player p1 = new Player("Red", true);
		Player p2 = new Player("Blue", false);
		players[0] = p1;
		players[1] = p2;
		board.setPlayers(players);
		board.start(t, 0, 0);
		TigerTile t2 = new TigerTile("JLTTB", 2);
		Location l = new Location(1, 0);
		board.place(t2, l);
		board.placeCrocodile();
		
		assertEquals(, board.getTile(l).getCroc());
	}*/

	//DEPRECATED
	/*@Test
	public void isSurroundedTest() {
		BoardObject test = new BoardObject(); //create the board
		test.start();

		//Test if tile is surrounded by north, east, south, west
		TigerTile tile1 = test.getTile("LJTJ-",0);
		test.place(tile1,new Location(0,1));
		TigerTile tile2 = test.getTile("LLLL-",0);
		test.place(tile2,new Location(1,0));
		TigerTile tile3 = test.getTile("TJTJ-",0);
		test.place(tile3,new Location(0,-1));
		//(0,0) should not be surrounded at this point
		assertFalse(test.isSurrounded(new Location()));
		TigerTile tile4 = test.getTile("JJTJX-",0);
		test.place(tile4,new Location(-1,0));
		//(0,0) should now be surrounded
		assertTrue(test.isSurrounded(new Location()));

		//Test if Den tile is surrounded
		TigerTile tile5 = test.getTile("LJJJ-",0);
		test.place(tile5,new Location(-1,1));
		TigerTile tile6 = test.getTile("TJTJ-",0);
		test.place(tile6,new Location(-1,-1));
		TigerTile tile7 = test.getTile("JJJJ-",0);
		test.place(tile7,new Location(-2,-1));
		TigerTile tile8 = test.getTile("TJJT-",0);
		test.place(tile8,new Location(-2,0));
		//Den at (-1,0) should not be surrounded at this point
		assertFalse(test.isSurrounded(new Location(-1, 0)));
		TigerTile tile9 = test.getTile("TJTT-",0);
		test.place(tile9,new Location(-2,1));
		//Den at (-1,0) should now be surrounded
		assertTrue(test.isSurrounded(new Location(-1, 0)));
	}

	@Test
	public void placeTest() {
		BoardObject test = new BoardObject(); //create the board
		test.start();
		TigerTile tile1 = test.getTile("LJTJ-",0);

		//Test if place is successful
		assertTrue(test.place(tile1,new Location(0,1)));
		//Available spots should now be 6
		assertEquals(6, test.getAS().size());
	}

	@Test
	public void startAndGetBoardTest() {
		BoardObject test = new BoardObject(); //create the board
		test.start();
		//Get tile in center of board
		TigerTile tile = test.getBoard()[5][5];
		//Test if designated starting tile was placed in center of board
		assertEquals("TLTJ-", tile.getType());
	}

	@Test
	public void setAndGetPlayersTest() {
		BoardObject test = new BoardObject(); //create the board
		//Create 2 players
		Player p1 = new Player(0);
		Player p2 = new Player(1);

		//Tell board about the players
		test.setPlayer(0, p1);
		test.setPlayer(1, p2);

		//Get player info
		assertEquals(0, test.getPlayer(0).getID());
		assertEquals(1, test.getPlayer(1).getID());
	}*/
}

