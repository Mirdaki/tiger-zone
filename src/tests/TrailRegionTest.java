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
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 8));
		TrailTerrain t1 = new TrailTerrain(7, c1, false);
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 5, 7));
		TrailTerrain t2 = new TrailTerrain(8, c2, false);
		TrailRegion r1 = new TrailRegion(t1);
		r1.addTerain(t2);
		r1.updateTigers();
		
		assertEquals(7, r1.getRegionID());
		assertEquals("Trail", r1.getType());
		assertEquals(2, r1.getNumberOfTerrains());
		assertEquals(false, r1.hasTigers());
		assertEquals(false, r1.isCompleted());
	}

	@Test
	public void updateTigersTest() {
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 8));
		TrailTerrain t1 = new TrailTerrain(7, c1, false);
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 5, 7));
		TrailTerrain t2 = new TrailTerrain(8, c2, false);
		Player p1 = new Player(1);
		TigerObject tiger = new TigerObject(1, 'T', p1, 7);
		t1.addTiger(tiger);
		TrailRegion r1 = new TrailRegion(t1);
		r1.updateTigers();
		
		//check if tiger was added to r1's tiger list
		assertEquals(true, r1.hasTigers());
		
		//check if two tigers can be added to r1's tiger list
		TigerObject tiger2 = new TigerObject(2, 'T', p1, 8);
		t2.addTiger(tiger2);
		r1.addTerain(t2);
		assertEquals(2, r1.getTigers().size());
	}
	
	@Test
	public void removeAllTigersTest() {
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 8));
		TrailTerrain t1 = new TrailTerrain(7, c1, false);
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 5, 7));
		TrailTerrain t2 = new TrailTerrain(8, c2, false);
		Player p1 = new Player(1);
		TigerObject tiger = new TigerObject(1, 'T', p1, 7);
		t1.addTiger(tiger);
		TrailRegion r1 = new TrailRegion(t1);
		r1.updateTigers();
		TigerObject tiger2 = new TigerObject(2, 'T', p1, 8);
		t2.addTiger(tiger2);
		r1.addTerain(t2);
		r1.removeAllTigers();
		
		assertEquals(false, r1.hasTigers());
	}
	
	@Test
	public void addRegionTest() {
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 6));
		TrailTerrain t1 = new TrailTerrain(7, c1, false);
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 5, 9));
		TrailTerrain t2 = new TrailTerrain(8, c2, false);
		TrailRegion r1 = new TrailRegion(t1);
		TrailRegion r2 = new TrailRegion(t2);
		r1.addRegion(r2);
		
		assertEquals(2, r1.getNumberOfTerrains());
	}
	
	@Test
	public void addRegionExceptionTest() {
		boolean thrown = false;
		try {
			ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 6));
			TrailTerrain t1 = new TrailTerrain(7, c1, false);
			ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 5, 9));
			LakeTerrain t2 = new LakeTerrain(8, c2, false);
			TrailRegion r1 = new TrailRegion(t1);
			LakeRegion r2 = new LakeRegion(t2);
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
			ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 6));
			TrailTerrain t1 = new TrailTerrain(7, c1, false);
			ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 5, 9));
			LakeTerrain t2 = new LakeTerrain(8, c2, false);
			TrailRegion r1 = new TrailRegion(t1);
			r1.addTerain(t2);
		} catch (IllegalArgumentException e){
			thrown = true;
		}
		assertTrue(thrown);
	}
	
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
