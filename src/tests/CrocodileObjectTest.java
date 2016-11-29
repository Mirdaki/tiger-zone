package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.CrocodileObject;
import entities.Player;

public class CrocodileObjectTest {

	@Test
	public void crocConstructorAndGetterTest() {
		Player p1 = new Player("Red", true);
		CrocodileObject croc = new CrocodileObject(p1);
		
		assertEquals("Red", croc.getCrocodileOwner().getID());
		assertEquals(-1, croc.getRegionID());
	}

	/*@Test
	public void crocToStringTest() {
		Player p1 = new Player("Red", true);
		CrocodileObject croc = new CrocodileObject(p1);
		
		assertEquals("This crocodile belongs to Player ID: Red in region -1", croc.toString());
	}*/
}
