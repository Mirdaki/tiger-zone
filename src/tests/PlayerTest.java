package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.CrocodileObject;
import entities.Player;
import entities.TigerObject;

public class PlayerTest {

	@Test
	public void playerConstructorAndGetterTest()
	{
		Player p1 = new Player("Red", true);

		assertEquals("Red", p1.getID());
		assertEquals(7, p1.getTigers().size());
		assertEquals(7, p1.getNumOfTigers());
		assertEquals(2, p1.getCrocs().size());
		assertEquals(2, p1.getNumOfCrocs());
		assertEquals(0, p1.getScore());
	}

	@Test
	//Test if player has tigers or crocodiles
	public void playerHasTigerCrocTest() {
		Player p1 = new Player("Red", true);
		
		assertTrue(p1.hasCrocs());
		assertTrue(p1.hasTigers());
	}
	
	@Test
	//Test if removal of tiger and crocodile works
	public void removeTigerCrocTest() {
		Player p1 = new Player("Red", true);
		p1.removeCroc();
		p1.removeTiger();
		
		assertEquals(6, p1.getTigers().size());
		assertEquals(6, p1.getNumOfTigers());
		assertEquals(1, p1.getCrocs().size());
		assertEquals(1, p1.getNumOfCrocs());
	}
	
	@Test
	//Test if player comparison works
	public void playerEqualsTest() {
		Player p1 = new Player("Red", true);
		Player p2 = new Player("Blue", true);
		
		assertFalse(p1.equals(p2));
	}
	
	@Test
	//Test if player scoring works
	public void playerSetAndAddScoreTest()
	{
		Player p1 = new Player("Red", false);

		p1.setScore(300);
		p1.addScore(100);
		assertEquals(400, p1.getScore());
	}

	@Test
	//Test if player is first
	//May not need
	public void playersFirstTest()
	{
		Player p1 = new Player("Red", true);

		p1.setFirst(false);
		assertEquals(false, p1.isFirst());
	}

	@Test
	public void playersToStringTest()
	{
		Player p1 = new Player("Red", true);

		p1.setScore(200);
		assertEquals("Player ID: Red\nScore: 200\nTigers left: 7\nCrocs left: 2\nThis is the first player.\n", p1.toString());
	}
}
