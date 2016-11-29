package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import entities.CrocodileObject;
import entities.DenRegion;
import entities.LakeRegion;
import entities.LakeTerrain;
import entities.Player;
import entities.Terrain;
import entities.TigerObject;
import entities.TrailRegion;
import entities.TrailTerrain;

public class TrailRegionTest {

	//Tests constructor and getter after a terrain is added to the region
	@Test
	public void trailRegionConstructorAndGetterTest() {
		//Create TrailTerrain t1 which is connected to t2
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
		TrailTerrain t1 = new TrailTerrain(1, c1, false);
		//Create TrailRegion r1 which consists of TrailTerrain t1
		TrailRegion r1 = new TrailRegion(t1);

		assertEquals(1, r1.getRegionID());
		assertEquals('T', r1.getType());
		assertEquals(false, r1.hasTigers());
		assertEquals(false, r1.isCompleted());
		assertEquals(0, r1.getNumOfTigers());
		assertEquals(1, r1.getNumOfTerrains());
		assertFalse(r1.isScored());
	}

	/*@Test
	//Test if 2 terrains can be added to a region
	public void addTerrainTest() {
		//Create TrailTerrain t1 which is connected to t2
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
		TrailTerrain t1 = new TrailTerrain(7, c1, false);
		//Create TrailTerrain t2 which is connected to t1
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(7));
		TrailTerrain t2 = new TrailTerrain(8, c2, false);
		//Create TrailRegion r1 which consists of TrailTerrain t1
		ArrayList<Terrain> terrains = new ArrayList<Terrain>();
		terrains.add(t1);
		terrains.add(t2);
		TrailRegion r1 = new TrailRegion(1);
		//Add TrailTerrain t2 to TrailRegion r1
		r1.addTerrain(terrains, 1);
		
		assertEquals(2, r1.getTerrains().size());
	}*/
	
	@Test
	//Test if tiger can be added and removed from region
	public void addRemoveTigerTest() {
		TrailRegion r1 = new TrailRegion(1);
		Player p1 = new Player("Red", true);
		TigerObject t1 = new TigerObject(p1);
		r1.addTiger(t1);
		
		assertTrue(r1.hasTigers());
		
		r1.removeTiger(0);
		
		assertFalse(r1.hasTigers());
	}
	
	/*@Test
	//Test if croc can be added and removed from region
	public void addRemoveCrocTest() {
		TrailRegion r1 = new TrailRegion(1);
		Player p1 = new Player("Red", true);
		CrocodileObject c1 = new CrocodileObject(p1);
		r1.addCrocodile(c1);
		
		assertTrue(r1.hasCrocodiles());
		
		r1.removeCrocodile();
		
		assertFalse(r1.hasCrocodiles());
	}*/
	
	@Test
	//Test if all tigers can be removed from region
	public void removeAllTigerTest() {
		TrailRegion r1 = new TrailRegion(1);
		Player p1 = new Player("Red", true);
		TigerObject t1 = new TigerObject(p1);
		r1.addTiger(t1);
		
		assertEquals(1, r1.getNumOfTigers());
		
		r1.removeAllTigers();
		
		assertEquals(0, r1.getNumOfTigers());
	}
	
	/*@Test
	//Tests trail's potential score
	public void getPotentialTest() {
		TrailRegion r1 = new TrailRegion(1);
		
		assertEquals(0, r1.getPotential());
	}*/
	
	@Test
	public void toStringTrailRegionTest() {
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 6));
		TrailTerrain t1 = new TrailTerrain(1, c1, false);
		TrailRegion r1 = new TrailRegion(t1);

		assertEquals("The connecting region 1 of type T has 0 Tiger(s), 0 crocodile(s), 0 animal(s), 0 unique, and 1 Terrain(s).", r1.toString());
	}
	
	//DEPRECATED
	/*@Test
	public void updateTigersTest() {
		//Create TrailTerrain t1 which is connected to t2
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 8));
		TrailTerrain t1 = new TrailTerrain(7, c1, false);
		//Create TrailTerrain t2 which is connected to t1
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 5, 7));
		TrailTerrain t2 = new TrailTerrain(8, c2, false);
		Player p1 = new Player(1);
		TigerObject tiger = new TigerObject(1, 'T', p1, 7);
		//Add tiger to TrailTerrain t1
		t1.addTiger(tiger);
		TrailRegion r1 = new TrailRegion(t1);
		r1.updateTigers();

		//Check if tiger was added to r1's tiger list
		assertEquals(true, r1.hasTigers());

		//Check if two tigers can be added to r1's tiger list
		TigerObject tiger2 = new TigerObject(2, 'T', p1, 8);
		t2.addTiger(tiger2);
		r1.addTerain(t2);
		assertEquals(2, r1.getTigers().size());
	}*/

	/*@Test
	public void removeAllTigersTest() {
		//Create TrailTerrain t1 which is connected to t2
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 8));
		TrailTerrain t1 = new TrailTerrain(7, c1, false);
		//Create TrailTerrain t2 which is connected to t1
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 5, 7));
		TrailTerrain t2 = new TrailTerrain(8, c2, false);
		Player p1 = new Player(1);
		TigerObject tiger = new TigerObject(1, 'T', p1, 7);
		//Add tiger to TrailTerrain t1
		t1.addTiger(tiger);
		TrailRegion r1 = new TrailRegion(t1);
		r1.updateTigers();
		TigerObject tiger2 = new TigerObject(2, 'T', p1, 8);
		//Add tiger to TrailTerrain t2
		t2.addTiger(tiger2);
		r1.addTerain(t2);
		r1.removeAllTigers();

		assertEquals(false, r1.hasTigers());
	}*/

	/*@Test
	public void addRegionTest() {
		//Create TrailTerrain t1 which is connected to t2
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
		TrailTerrain t1 = new TrailTerrain(7, c1, false);
		//Create TrailTerrain t2 which is connected to t1
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(7));
		TrailTerrain t2 = new TrailTerrain(8, c2, false);
		//Create separate regions for each terrain
		TrailRegion r1 = new TrailRegion(t1);
		TrailRegion r2 = new TrailRegion(t2);
		//Merge regions
		r1.addRegion(r2);

		assertEquals(2, r1.getNumberOfTerrains());
	}*/

	/*@Test
	public void addRegionExceptionTest() {
		boolean thrown = false;
		try {
			//Create TrailTerrain t1 which is connected to t2
			ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
			TrailTerrain t1 = new TrailTerrain(7, c1, false);
			//Create LakeTerrain t2 which is connected to t1
			ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(7));
			LakeTerrain t2 = new LakeTerrain(8, c2);
			//Create separate regions for each terrain
			TrailRegion r1 = new TrailRegion(t1);
			LakeRegion r2 = new LakeRegion(t2);
			//Merge regions
			r1.addRegion(r2);
		} catch (IllegalArgumentException e){
			thrown = true;
		}
		assertTrue(thrown);
	}*/

	/*@Test
	public void addTerrainExceptionTest() {
		boolean thrown = false;
		try {
			//Create TrailTerrain t1 which is connected to t2
			ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
			TrailTerrain t1 = new TrailTerrain(7, c1, false);
			//Create LakeTerrain t2 which is connected to t1
			ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(7));
			LakeTerrain t2 = new LakeTerrain(8, c2);
			TrailRegion r1 = new TrailRegion(t1);
			//Add LakeTerrain to TrailRegion
			r1.addTerain(t2);
		} catch (IllegalArgumentException e){
			thrown = true;
		}
		assertTrue(thrown);
	}*/

	/*@Test
	public void addTerrainWhenCompleteExceptionTest() {
		boolean thrown = false;
		try {
			ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(1, 2, 8));
			TrailTerrain t1 = new TrailTerrain(7, c1, true);
			ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 4, 7));
			TrailTerrain t2 = new TrailTerrain(8, c2, true);
			TrailRegion r1 = new TrailRegion(t1);
			r1.addTerain(t2);
			r1.updateCompletion();
			ArrayList<Integer> c3 = new ArrayList<>(Arrays.asList(5, 6, 10));
			TrailTerrain t3 = new TrailTerrain(9, c3, false);
			r1.addTerain(t3);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}

		assertTrue(thrown);
	}*/

	/*@Test
	public void updateCompletionTest() {
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(1, 2, 8));
		TrailTerrain t1 = new TrailTerrain(7, c1, true);
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 4, 7));
		TrailTerrain t2 = new TrailTerrain(8, c2, false);
		TrailRegion r1 = new TrailRegion(t1);
		r1.addTerain(t2);

		assertFalse(r1.isCompleted());
	}*/
}

