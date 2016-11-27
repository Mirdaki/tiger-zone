package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import entities.Animal;
import entities.LakeTerrain;
import entities.TigerObject;
import entities.Player;

public class LakeTerrainTest {

	@Test
	public void lakeTerrainConstructorAndGetterTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		Animal a1 = new Animal('D');
		LakeTerrain t1 = new LakeTerrain(8, tileConnections, a1);
		
		assertEquals(true, t1.hasAnimal());
		assertEquals(8, t1.getTerrainID());
		assertEquals("Lake", t1.getType());
		assertEquals(false, t1.hasTiger());
		int x = t1.getTileConnections().get(1);
		assertEquals(5, x);
	}

	@Test
	public void lakeTerrainTigerTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		LakeTerrain t1 = new LakeTerrain(8, tileConnections);
		Player p1 = new Player(1);
		TigerObject tiger = new TigerObject(1, 'K', p1, 8);
		t1.addTiger(tiger);
		
		//check if tiger was added to terrain
		assertEquals(true, t1.hasTiger());
		//check if tiger can be removed
		t1.removeTiger();
		assertEquals(false, t1.hasTiger());
	}
	
	@Test
	public void addTigerExceptionTest() {
		boolean thrown = false;

		try {
			ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
			LakeTerrain t1 = new LakeTerrain(8, tileConnections);
			Player p1 = new Player(1);
			TigerObject tiger = new TigerObject(1, 'K', p1, 8);
			TigerObject tiger2 = new TigerObject(2, 'K', p1, 8);
			t1.addTiger(tiger);
			t1.addTiger(tiger2);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void lakeTerrainToStringTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		LakeTerrain t1 = new LakeTerrain(8, tileConnections);
		Player p1 = new Player(1);
		TigerObject tiger = new TigerObject(1, 'K', p1, 8);
		t1.addTiger(tiger);
		
		assertEquals("The terrain 8 of type Lake has a Meepel and 3 tile connection(s)", t1.toString());
	}
}
