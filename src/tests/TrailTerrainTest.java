package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import entities.Animal;
import entities.CrocodileObject;
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
		assertEquals(2, t1.getMin());
		assertEquals('T', t1.getType());
		assertEquals(null, t1.getTiger());
		assertEquals(null, t1.getCrocodile());
		assertEquals(null, t1.getAnimal());
		int x = t1.getTileConnections().get(1);
		assertEquals(5, x);
	}

	@Test
	//Tests if tiger can be added to terrain and then removed
	public void trailTerrainTigerTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		TrailTerrain t1 = new TrailTerrain(7, tileConnections, false);
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
	//Tests if crocodile can be added to terrain and then removed
	public void trailTerrainCrocodileTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		Animal a1 = new Animal('D');
		TrailTerrain t1 = new TrailTerrain(tileConnections, false, a1);
		Player p1 = new Player("Red", true);
		CrocodileObject croc = new CrocodileObject(p1);
		t1.addCrocodile(croc);

		//check if tiger was added to terrain
		assertEquals(true, t1.hasCrocodile());
		//check if tiger can be removed
		t1.removeCrocodile();
		assertEquals(false, t1.hasCrocodile());
	}
	
	@Test
	//Tests if animal can be added to terrain
	public void trailAnimalTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		Animal a1 = new Animal('P');
		TrailTerrain t1 = new TrailTerrain(tileConnections, true, a1);
		
		assertTrue(t1.hasAnimal());
		assertEquals('P', t1.getAnimalType());
	}
	
	@Test
	//Tests if terrain is an end of lake
	public void endOfTrailTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		Animal a1 = new Animal('P');
		TrailTerrain t1 = new TrailTerrain(tileConnections, true, a1);
		
		assertTrue(t1.isEndOfTrail());
	}
	
	@Test
	//Tests if terrains match
	public void equalsTest() {
		ArrayList<Integer> tileConnections1 = new ArrayList<>(Arrays.asList(2, 5, 6));
		Animal a1 = new Animal('P');
		TrailTerrain t1 = new TrailTerrain(tileConnections1, true, a1);
		ArrayList<Integer> tileConnections2 = new ArrayList<>(Arrays.asList(2, 5, 6));
		Animal a2 = new Animal('P');
		TrailTerrain t2 = new TrailTerrain(tileConnections2, true, a2);

		assertTrue(t1.equals(t2));
	}
	
	/*@Test
	//Tests if exception is thrown when trying to place second tiger on same terrain
	public void addTigerExceptionTest() {
		boolean thrown = false;

		try {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		TrailTerrain t1 = new TrailTerrain(7, tileConnections, false);
		Player p1 = new Player("Red", true);
		TigerObject tiger = new TigerObject(p1);
		TigerObject tiger2 = new TigerObject(p1);
		t1.addTiger(tiger);
		t1.addTiger(tiger2);
		}
		catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}*/

	/*@Test
	public void trailTerrainToStringTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		TrailTerrain t1 = new TrailTerrain(7, tileConnections, true);
		Player p1 = new Player("Red", true);
		TigerObject tiger = new TigerObject(p1);
		t1.addTiger(tiger);

		assertEquals("The terrain 7 of type T in region 7 has 1 Tigers and 3 tile connection(s) (min= ", t1.toString());
	}*/
}

