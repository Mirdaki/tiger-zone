package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import entities.CrocodileObject;
import entities.DenRegion;
import entities.DenTerrain;
import entities.Location;
import entities.Player;
import entities.Region;
import entities.Terrain;
import entities.TigerObject;

public class DenRegionTest {

	@Test
	public void denConstructorAndGetterTest() {
		DenRegion r1 = new DenRegion(1);

		assertEquals(1, r1.getRegionID());
		assertEquals('D', r1.getType());
		assertEquals(false, r1.hasTigers());
		assertEquals(0, r1.getNumOfTigers());
		assertEquals(0, r1.getNumOfTerrains());
		assertFalse(r1.isCompleted());
		assertFalse(r1.isScored());
	}

	@Test
	//Test if 2 terrains can be added to a region
	public void addTerrainTest() {
		//Create DenTerrain t1 which is connected to t2
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
		DenTerrain t1 = new DenTerrain(c1, 7);
		//Create DenTerrain t2 which is connected to t1
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(7));
		DenTerrain t2 = new DenTerrain(c2, 8);
		//Create DenRegion r1 which consists of DenTerrain t1
		ArrayList<Terrain> terrains = new ArrayList<Terrain>();
		terrains.add(t1);
		terrains.add(t2);
		DenRegion r1 = new DenRegion(1);
		//Add DenTerrain t2 to DenRegion r1
		r1.addTerrain(terrains, 1);
		
		assertEquals(2, r1.getTerrains().size());
	}
	
	@Test
	//Test if tiger can be added and removed from region
	public void addRemoveTigerTest() {
		DenRegion r1 = new DenRegion(1);
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
		DenRegion r1 = new DenRegion(1);
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
		DenRegion r1 = new DenRegion(1);
		Player p1 = new Player("Red", true);
		TigerObject t1 = new TigerObject(p1);
		r1.addTiger(t1);
		
		assertEquals(1, r1.getNumOfTigers());
		
		r1.removeAllTigers();
		
		assertEquals(0, r1.getNumOfTigers());
	}
	
	@Test
	//Tests number of neighboring tiles to a den
	public void getNumberOfNeighboringTilesTest() {
		DenRegion r1 = new DenRegion(1);
		Location l1 = new Location(0,0);
		Location l2 = new Location(1,0);
		ArrayList<Location> neighbors = new ArrayList<Location>();
		neighbors.add(l1);
		neighbors.add(l2);
		r1.setMoore(neighbors);

		assertEquals(2, r1.getNumberOfNeighboringTiles());
	}
	
	@Test
	//Tests if den is complete
	public void denCompleteTest() {
		DenRegion r1 = new DenRegion(1);
		Location l1 = new Location(0,0);
		Location l2 = new Location(1,0);
		Location l3 = new Location(2,0);
		Location l4 = new Location(3,0);
		Location l5 = new Location(4,0);
		Location l6 = new Location(5,0);
		Location l7 = new Location(6,0);
		Location l8 = new Location(7,0);
		Location l9 = new Location(8,0);
		ArrayList<Location> neighbors = new ArrayList<Location>();
		neighbors.add(l1);
		neighbors.add(l2);
		neighbors.add(l3);
		neighbors.add(l4);
		neighbors.add(l5);
		neighbors.add(l6);
		neighbors.add(l7);
		neighbors.add(l8);
		neighbors.add(l9);
		r1.setMoore(neighbors);

		assertEquals(9, r1.getNumberOfNeighboringTiles());
		assertTrue(r1.isCompleted());
	}
	
	@Test
	public void toStringDenRegionTest() {
		DenRegion r1 = new DenRegion(1);

		assertEquals("The region 1 of type D has 0 Tiger(s), and 0 Terrain(s).", r1.toString());
	}
	
	//DEPRECATED
	/*@Test
	public void updateTigersTest() {
		//Create DenTerrain t1 which is connected to t2
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 8));
		DenTerrain t1 = new DenTerrain(7, c1);
		//Create DenTerrain t2 which is connected to t1
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 5, 7));
		DenTerrain t2 = new DenTerrain(8, c2);
		Player p1 = new Player(1);
		TigerObject tiger = new TigerObject(1, 'M', p1, 7);
		//Add tiger to DenTerrain t1
		t1.addTiger(tiger);
		DenRegion r1 = new DenRegion(t1);
		r1.updateTigers();

		//Check if tiger was added to r1's tiger list
		assertEquals(true, r1.hasTigers());

		//Check if two tigers can be added to r1's tiger list
		TigerObject tiger2 = new TigerObject(2, 'M', p1, 8);
		t2.addTiger(tiger2);
		r1.addTerain(t2);
		assertEquals(2, r1.getTigers().size());
	}*/

	/*@Test
	public void removeAllTigersTest() {
		//Create DenTerrain t1 which is connected to t2
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 8));
		DenTerrain t1 = new DenTerrain(7, c1);
		//Create DenTerrain t2 which is connected to t1
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 5, 7));
		DenTerrain t2 = new DenTerrain(8, c2);
		Player p1 = new Player(1);
		TigerObject tiger = new TigerObject(1, 'M', p1, 7);
		//Add tiger to DenTerrain t1
		t1.addTiger(tiger);
		DenRegion r1 = new DenRegion(t1);
		r1.updateTigers();
		TigerObject tiger2 = new TigerObject(2, 'M', p1, 8);
		//Add tiger to DenTerrain t2
		t2.addTiger(tiger2);
		r1.addTerain(t2);
		r1.removeAllTigers();

		assertEquals(false, r1.hasTigers());
	}*/

	/*@Test
	public void addRegionTest() {
		//Create DenTerrain t1 which is connected to t2
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
		DenTerrain t1 = new DenTerrain(7, c1);
		//Create DenTerrain t2 which is connected to t1
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(7));
		DenTerrain t2 = new DenTerrain(8, c2);
		//Create separate regions for each terrain
		DenRegion r1 = new DenRegion(t1);
		DenRegion r2 = new DenRegion(t2);
		//Merge regions
		r1.addRegion(r2);

		assertEquals(2, r1.getNumberOfTerrains());
	}*/

	/*@Test
	public void addRegionExceptionTest() {
		boolean thrown = false;
		try {
			//Create DenTerrain t1 which is connected to t2
			ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
			DenTerrain t1 = new DenTerrain(7, c1);
			//Create LakeTerrain t2 which is connected to t1
			ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(7));
			LakeTerrain t2 = new LakeTerrain(8, c2);
			//Create separate regions for each terrain
			DenRegion r1 = new DenRegion(t1);
			LakeRegion r2 = new LakeRegion(t2);
			//Merge regions
			r1.addRegion(r2);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}*/

	/*@Test
	public void addTerrainExceptionTest() {
		boolean thrown = false;
		try {
			//Create DenTerrain t1 which is connected to t2
			ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
			DenTerrain t1 = new DenTerrain(7, c1);
			//Create LakeTerrain t2 which is connected to t1
			ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(7));
			LakeTerrain t2 = new LakeTerrain(8, c2);
			DenRegion r1 = new DenRegion(t1);
			//Add LakeTerrain to DenRegion
			r1.addTerain(t2);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}*/
}

