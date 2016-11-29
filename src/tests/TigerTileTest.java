package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.BoardObject;
import entities.Player;
import entities.TigerObject;
import entities.TigerTile;

public class TigerTileTest {

	@Test
	public void tigerTileConstructorAndGetterTest() {
		TigerTile test1 = new TigerTile("JJTJX", 0);
		
		assertEquals('X', test1.getSpecial());
		assertEquals(0, test1.getOrientation());
		assertEquals("JJTJX", test1.getType());
		assertEquals('X', test1.getCenter());
		assertEquals(2, test1.getTerrains().length);
	}
	
	@Test
	public void tigerTileAddRemoveTigerTest() {
		TigerTile test1 = new TigerTile("JJTJX", 0);
		Player p1 = new Player("Red", true);
		TigerObject tiger = new TigerObject(p1);
		test1.addTiger(tiger);
		
		assertEquals(1, test1.getTigers().size());
		
		test1.removeTiger();
		
		assertEquals(0, test1.getTigers().size());
	}
	
	/*@Test
	public void tigerTileToStringTest() {
		TigerTile test1 = new TigerTile("JJTJX", 0);
		
		assertEquals("ID: 1\n(x,y) coordinate: \nOrientation: 0\nType: T\nCenter: X\nOwner: \nSpecialty: X\nTerrain(s): 2\n\nEdges:\n", test1.toString());
	}*/
}
