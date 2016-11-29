package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.TileDeck;

public class TileDeckTest {

	@Test
	public void tileDeckConstructorAndGetterTest() {
		TileDeck t = new TileDeck();
		
		assertEquals(77, t.getTiles().size());
		assertEquals(77, t.getTileCount());
	}
	
	
	@Test
	public void tileGetRandomTest() {
		TileDeck t = new TileDeck();
		
		assertEquals(0, t.getRandom().getOrientation());
	}

	@Test
	public void tileGetNextTest() {
		TileDeck t = new TileDeck();

		assertEquals(false, t.getNext(1).isEmpty());
	}

}
