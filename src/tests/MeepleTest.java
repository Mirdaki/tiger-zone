package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.MeepleObject;
import entities.Player;

public class MeepleTest {

	
	@Test
	public void meepleConstructorAndGetterTest()
	{
		Player p1 = new Player(1);
		MeepleObject testMeeple = new MeepleObject(01, 'K', p1, 23);
		
		assertEquals(01, testMeeple.getID());
		assertEquals('K', testMeeple.getType());
		assertEquals(p1, testMeeple.getMeepleOwner());
		assertEquals(23, testMeeple.getTerrainID());
	}
	
	@Test
	public void meepleSetTypeTest() {
		Player p1 = new Player(1);
		MeepleObject testMeeple = new MeepleObject(01, 'K', p1, 23);
		
		testMeeple.setType('F');
		char resultType = testMeeple.getType();
		assertEquals('F', resultType);
	}
	
	@Test
	public void meepleSetTerrainIDTest() {
		Player p1 = new Player(1);
		MeepleObject testMeeple = new MeepleObject(01, 'K', p1, 23);
		
		testMeeple.setTerrainID(12);
		int resultTerrainID = testMeeple.getTerrainID();
		assertEquals(12, resultTerrainID);
	}
}
