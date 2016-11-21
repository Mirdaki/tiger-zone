package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import entities.BoardObject;
import entities.SquareTile;
import entities.TileObject;
import entities.TileStack;

public class TileStackTest {

	@Test
	public void tileStackConstructorAndGetterTest() {
		TileStack t = new TileStack();
		ArrayList<SquareTile> a = t.getList("JJJJ-");
		
		assertEquals(77, t.getTileCount());
		//One tile should be retrieved from Map
		assertEquals(1, a.size());
	}

	@Test
	public void removeTileTest() {
		TileStack t = new TileStack();
		t.removeTile("JJJJ-");
		
		//One tile should be removed from TileStack
		assertEquals(76, t.getTileCount());
	}
	
	@Test
	public void getTileTest() {
		TileStack t = new TileStack();
		//Retrieve the JJTJX tile with one rotation from the TileStack
		SquareTile s = t.getTile("JJTJX", 1);
		
		assertEquals("JJTJX", s.getType());
		assertEquals(1, s.getOrientation());
	}
}
