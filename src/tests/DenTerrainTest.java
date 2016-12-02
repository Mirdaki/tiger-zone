package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import entities.DenTerrain;
import entities.TigerObject;
import entities.Player;

public class DenTerrainTest {

	@Test
	public void denTerrainConstructorAndGetterTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		DenTerrain t1 = new DenTerrain(tileConnections, 5);

		assertEquals(5, t1.getTerrainID());
		assertEquals(5, t1.getZoneMin());
		assertEquals('D', t1.getType());
		assertEquals(null, t1.getTiger());
		assertEquals(null, t1.getCrocodile());
		assertEquals(null, t1.getAnimal());
		int x = t1.getTileConnections().get(1);
		assertEquals(5, x);
	}

	@Test
	public void denTerrainTigerTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		DenTerrain t1 = new DenTerrain(tileConnections, 5);
		Player p1 = new Player("Red", true);
		TigerObject tiger = new TigerObject(p1);
		t1.addTiger(tiger);

		//check if tiger was added to terrain
		assertEquals(true, t1.hasTiger());
		//check if tiger can be removed
		t1.removeTiger();
		assertEquals(false, t1.hasTiger());
	}

	/*@Test
	public void addTigerExceptionTest() {
		boolean thrown = false;

		try {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		DenTerrain t1 = new DenTerrain(tileConnections, 5);
		Player p1 = new Player(1);
		TigerObject tiger = new TigerObject(1, 'M', p1, 5);
		TigerObject tiger2 = new TigerObject(2, 'M', p1, 5);
		t1.addTiger(tiger);
		t1.addTiger(tiger2);
		}
		catch (IllegalArgumentException e) {
			thrown = true;
		}
	}*/

	/*@Test
	public void denTerrainToStringTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		DenTerrain t1 = new DenTerrain(tileConnections, 5);
		Player p1 = new Player(1);
		TigerObject tiger = new TigerObject(1, 'M', p1, 5);
		t1.addTiger(tiger);

		assertEquals("The terrain 5 of type Den has a Meepel and 3 tile connection(s)", t1.toString());
	}*/
}

