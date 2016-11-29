package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.Location;
import entities.TigerObject;
import entities.Player;

public class TigerObjectTest {


	@Test
	public void tigerConstructorAndGetterTest() {
		Player p1 = new Player("Red", false);
		TigerObject testTiger = new TigerObject(p1);

		assertEquals(p1, testTiger.getTigerOwner());
		assertEquals(-1, testTiger.getRegionID());
		
	}
	
	@Test
	public void tigerSetLocationTest() {
		Player p1 = new Player("Red", false);
		TigerObject testTiger = new TigerObject(p1);
		Location x = new Location(1, 2);
		testTiger.setLocation(x);
		
		assertEquals(6, testTiger.getLocation().getX());
		assertEquals(3, testTiger.getLocation().getY());
	}
	
	/*@Test
	public void tigerToStringTest() {
		Player p1 = new Player("Red", false);
		TigerObject testTiger = new TigerObject(p1);
		
		
	}*/

	//DEPRECATED
	/*@Test
	public void TigerSetTypeTest() {
		Player p1 = new Player(1);
		TigerObject testTiger = new TigerObject(01, 'K', p1, 23);

		testTiger.setType('F');
		char resultType = testTiger.getType();
		assertEquals('F', resultType);
	}*/

	/*@Test
	public void TigerSetTerrainIDTest() {
		Player p1 = new Player(1);
		TigerObject testTiger = new TigerObject(01, 'K', p1, 23);

		testTiger.setTerrainID(12);
		int resultTerrainID = testTiger.getTerrainID();
		assertEquals(12, resultTerrainID);
	}*/
}