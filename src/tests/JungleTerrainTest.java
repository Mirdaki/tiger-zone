package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import entities.JungleTerrain;
import entities.TigerObject;
import entities.Player;

public class JungleTerrainTest {

	@Test
	public void jungleTerrainConstructorAndGetterTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		JungleTerrain t1 = new JungleTerrain(tileConnections, 6);

		assertEquals(6, t1.getTerrainID());
		assertEquals(3, t1.getZoneMin());
		assertEquals('J', t1.getType());
		assertEquals(null, t1.getTiger());
		assertEquals(null, t1.getCrocodile());
		assertEquals(null, t1.getAnimal());
		int x = t1.getTileConnections().get(1);
		assertEquals(5, x);
	}

	@Test
	public void jungleTerrainTigerTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		JungleTerrain t1 = new JungleTerrain(tileConnections, 6);
		Player p1 = new Player("Red", true);
		TigerObject tiger = new TigerObject(p1);
		t1.addTiger(tiger);

		//check if tiger was added to terrain
		assertEquals(true, t1.hasTiger());
		//check if tiger can be removed
		t1.removeTiger();
		assertEquals(false, t1.hasTiger());
	}
	
	@Test
	//Tests if terrains match
	public void equalsTest() {
		ArrayList<Integer> tileConnections1 = new ArrayList<>(Arrays.asList(2, 5, 6));
		JungleTerrain t1 = new JungleTerrain(tileConnections1, 6);
		ArrayList<Integer> tileConnections2 = new ArrayList<>(Arrays.asList(2, 5, 6));
		JungleTerrain t2 = new JungleTerrain(tileConnections2, 6);

		assertTrue(t1.equals(t2));
	}

	/*@Test
	public void addTigerExceptionTest() {
		boolean thrown = false;

		try {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		JungleTerrain t1 = new JungleTerrain(6, tileConnections);
		Player p1 = new Player("Red", true);
		TigerObject tiger = new TigerObject(1, 'F', p1, 6);
		TigerObject tiger2 = new TigerObject(2, 'F', p1, 6);
		t1.addTiger(tiger);
		t1.addTiger(tiger2);
		}
		catch (IllegalArgumentException e) {
			thrown = true;
		}
	}

	@Test
	public void jungleTerrainToStringTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		JungleTerrain t1 = new JungleTerrain(6, tileConnections);
		Player p1 = new Player(1);
		TigerObject tiger = new TigerObject(1, 'F', p1, 6);
		t1.addTiger(tiger);

		assertEquals("The terrain 6 of type Jungle has a Meepel and 3 tile connection(s)", t1.toString());
	}*/
}

