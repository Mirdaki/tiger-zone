package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import entities.Animal;
import entities.CrocodileObject;
import entities.LakeTerrain;
import entities.TigerObject;
import entities.Player;

public class LakeTerrainTest {

	@Test
	public void lakeTerrainConstructorAndGetterTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		LakeTerrain t1 = new LakeTerrain(tileConnections, true, null);

		assertEquals(3, t1.getZoneMin());
		assertEquals('L', t1.getType());
		assertEquals(null, t1.getTiger());
		assertEquals(null, t1.getCrocodile());
		assertEquals(null, t1.getAnimal());
		int x = t1.getTileConnections().get(1);
		assertEquals(5, x);
	}

	@Test
	//Tests if tiger can be added to terrain and then removed
	public void lakeTerrainTigerTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		LakeTerrain t1 = new LakeTerrain(8, tileConnections);
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
	public void lakeTerrainCrocodileTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		LakeTerrain t1 = new LakeTerrain(8, tileConnections);
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
	public void lakeAnimalTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		Animal a1 = new Animal('P');
		LakeTerrain t1 = new LakeTerrain(tileConnections, true, a1);
		
		assertTrue(t1.hasAnimal());
		assertEquals('P', t1.getAnimalType());
	}
	
	@Test
	//Tests if terrain is an end of lake
	public void endOfLakeTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		LakeTerrain t1 = new LakeTerrain(tileConnections, true, null);
		
		assertTrue(t1.isEndOfLake());
	}
	
	@Test
	//Tests if terrains match
	public void equalsTest() {
		ArrayList<Integer> tileConnections1 = new ArrayList<>(Arrays.asList(2, 5, 6));
		LakeTerrain t1 = new LakeTerrain(8, tileConnections1);
		ArrayList<Integer> tileConnections2 = new ArrayList<>(Arrays.asList(2, 5, 6));
		LakeTerrain t2 = new LakeTerrain(9, tileConnections2);

		assertTrue(t1.equals(t2));
	}
	
	/*@Test
	//Tests if exception is thrown when trying to place second tiger on same terrain
	public void addTigerExceptionTest() {
		boolean thrown = false;

		try {
			ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
			LakeTerrain t1 = new LakeTerrain(8, tileConnections);
			Player p1 = new Player("Red", true);
			TigerObject tiger = new TigerObject(p1);
			TigerObject tiger2 = new TigerObject(p1);
			t1.addTiger(tiger);
			t1.addTiger(tiger2);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}*/

	/*@Test
	public void lakeTerrainToStringTest() {
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		LakeTerrain t1 = new LakeTerrain(8, tileConnections);
		Player p1 = new Player(1);
		TigerObject tiger = new TigerObject(1, 'K', p1, 8);
		t1.addTiger(tiger);

		assertEquals("The terrain 8 of type Lake has a Meepel and 3 tile connection(s)", t1.toString());
	}*/
}

