package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import entities.Terrain;
import entities.TrailTerrain;
import entities.TigerObject;
import entities.Player;

public class TrailTerrainTest {

	@Test
	public void trailTerrainConstructorAndGetterTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		TrailTerrain t1 = new TrailTerrain(7, tileConnections, false);
		
		assertEquals(false, t1.isEndOfTrail());
		assertEquals(7, t1.getTerrainID());
		assertEquals("Trail", t1.getType());
		assertEquals(false, t1.hasTiger());
		int x = t1.getTileConnections().get(1);
		assertEquals(5, x);
	}

	@Test
	public void trailTerrainTigerTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		TrailTerrain t1 = new TrailTerrain(7, tileConnections, false);
		Player p1 = new Player(1);
		TigerObject tiger = new TigerObject(1, 'T', p1, 7);
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
		TrailTerrain t1 = new TrailTerrain(7, tileConnections, false);
		Player p1 = new Player(1);
		TigerObject tiger = new TigerObject(1, 'T', p1, 7);
		TigerObject tiger2 = new TigerObject(2, 'T', p1, 7);
		t1.addTiger(tiger);
		t1.addTiger(tiger2);
		}
		catch (IllegalArgumentException e) {
			thrown = true;
		}
	}
	
	@Test
	public void trailTerrainToStringTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		TrailTerrain t1 = new TrailTerrain(7, tileConnections, true);
		Player p1 = new Player(1);
		TigerObject tiger = new TigerObject(1, 'T', p1, 7);
		t1.addTiger(tiger);
		
		assertEquals("The terrain 7 of type Trail has a Meepel and 3 tile connection(s)", t1.toString());
	}
}
