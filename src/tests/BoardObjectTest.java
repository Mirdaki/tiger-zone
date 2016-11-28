package tests;

/*import static org.junit.Assert.*;

import org.junit.Test;

import entities.BoardObject;
import entities.Location;
import entities.Player;
import entities.TigerTile;

public class BoardObjectTest {

	@Test
	public void boardObjectConstructorAndGetterTest() {
		BoardObject test = new BoardObject(); //create the board

		//Test if first available spot is (0,0) after constructed
		assertEquals(5, test.getAS().get(0).getRow());
		assertEquals(5, test.getAS().get(0).getCol());
	}

	@Test
	public void validTest() {
		BoardObject test = new BoardObject(); //create the board
		test.start();
		TigerTile s = test.getTile("JJTJX",0);

		//Placement at (-1,0) should be valid
		assertTrue(test.valid(s, new Location(-1, 0)));
		//Placement at (-3,0) should not be valid
		assertFalse(test.valid(s, new Location(-3, 0)));
	}

	@Test
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
	}
}
*/
