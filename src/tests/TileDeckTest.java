package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import entities.TigerTile;
import entities.TileDeck;

public class TileDeckTest {

	@Test
	public void tileDeckConstructorAndGetterTest() {
		TileDeck t = new TileDeck();
		
		assertEquals(77, t.getTiles().size());
		assertEquals(77, t.getTileCount());
	}
	
	
	@Test
	//Tests if a random tile was able to be pulled from the deck
	public void tileGetRandomTest() {
		TileDeck t = new TileDeck();
		
		assertEquals(0, t.getRandom().getOrientation());
	}
	
	@Test
	//Tests if start properly takes the start tile away from deck
	public void startTest() {
		TileDeck t = new TileDeck();
		t.start("TLTJ-");
		
		assertEquals(76, t.getTiles().size());
	}

	@Test
	//Tests if tiledeck can be set and if getNext retrieves the next tile in the deck
	public void tiledeckSetAndGetNextTest() {
		TileDeck t = new TileDeck();
		ArrayList<TigerTile> remainingTiles = new ArrayList<TigerTile>();
		TigerTile t1 = new TigerTile("JJJJ-", 0);
		TigerTile t2 = new TigerTile("JJJJX", 0);
		remainingTiles.add(t1);
		remainingTiles.add(t2);

		t.setActual(remainingTiles);

		assertEquals("JJJJ-", t.getNext(0));
		assertEquals("JJJJX", t.getNext(1));
	}

}
