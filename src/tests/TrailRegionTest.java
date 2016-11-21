package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import entities.LakeRegion;
import entities.LakeTerrain;
import entities.Player;
import entities.TigerObject;
import entities.TrailRegion;
import entities.TrailTerrain;

public class TrailRegionTest {

	//Tests constructor and getter after a terrain is added to the region
	@Test
	public void trailRegionConstructorAndGetterTest() {
		//Create TrailTerrain t1 which is connected to t2
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
		TrailTerrain t1 = new TrailTerrain(7, c1, false);
		//Create TrailTerrain t2 which is connected to t1
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(7));
		TrailTerrain t2 = new TrailTerrain(8, c2, false);
		//Create TrailRegion r1 which consists of TrailTerrain t1
		TrailRegion r1 = new TrailRegion(t1);
		//Add TrailTerrain t2 to TrailRegion r1
		r1.addTerain(t2);
		r1.updateTigers();
		
		assertEquals(7, r1.getRegionID());
		assertEquals("Trail", r1.getType());
		//Should number of terrains be 2 or 1 since they are the same type?
		assertEquals(2, r1.getNumberOfTerrains());
		assertEquals(false, r1.hasTigers());
		assertEquals(false, r1.isCompleted());
	}

	@Test
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
	}
	
	@Test
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
	}
	
	@Test
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
	}
	
	@Test
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
	}
	
	@Test
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
	}
	
	//TODO
	//Not complete/correct yet
	@Test
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
	}
	
	//TODO
	//Not complete/correct yet
	@Test
	public void updateCompletionTest() {
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(1, 2, 8));
		TrailTerrain t1 = new TrailTerrain(7, c1, true);
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 4, 7));
		TrailTerrain t2 = new TrailTerrain(8, c2, false);
		TrailRegion r1 = new TrailRegion(t1);
		r1.addTerain(t2);
		
		assertFalse(r1.isCompleted());
	}
	
	@Test
	public void toStringTrailRegionTest() {
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 6));
		TrailTerrain t1 = new TrailTerrain(7, c1, false);
		TrailRegion r1 = new TrailRegion(t1);
		
		assertEquals("The region 7 of type Trail has 0 Meepel(s) and 1 Terrain(s)", r1.toString());
	}
}
