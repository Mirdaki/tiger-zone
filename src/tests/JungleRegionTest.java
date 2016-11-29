package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import entities.CrocodileObject;
import entities.DenRegion;
import entities.DenTerrain;
import entities.JungleRegion;
import entities.JungleTerrain;
import entities.LakeRegion;
import entities.LakeTerrain;
import entities.Player;
import entities.Terrain;
import entities.TigerObject;

public class JungleRegionTest {

	//Tests constructor and getter after a terrain is added to the region
	/*@Test
	public void jungleConstructorAndGetterTest() {
		//Create JungleTerrain t1
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
		JungleTerrain t1 = new JungleTerrain(c1, 7);
		//Create JungleRegion r1 which consists of JungleTerrain t1
		JungleRegion r1 = new JungleRegion(t1);

		assertEquals(1, r1.getRegionID());
		assertEquals('J', r1.getType());
		assertEquals(false, r1.hasTigers());
		assertEquals(0, r1.getNumOfTigers());
		assertEquals(0, r1.getNumOfTerrains());
	}*/
	
	/*@Test
	//Test if 2 terrains can be added to a region
	public void addTerrainTest() {
		//Create JungleTerrain t1 which is connected to t2
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
		JungleTerrain t1 = new JungleTerrain(c1, 7);
		//Create JungleTerrain t2 which is connected to t1
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(7));
		JungleTerrain t2 = new JungleTerrain(c2, 8);
		//Create JungleRegion r1 which consists of JungleTerrain t1 and t2
		ArrayList<Terrain> terrains = new ArrayList<Terrain>();
		terrains.add(t1);
		terrains.add(t2);
		JungleRegion r1 = new JungleRegion(1);
		//Add JungleTerrain t2 to JungleRegion r1
		r1.addTerrain(terrains, 1);
		
		assertEquals(2, r1.getTerrains().size());
	}*/
	
	@Test
	//Test if tiger can be added and removed from region
	public void addRemoveTigerTest() {
		JungleRegion r1 = new JungleRegion(1);
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
		JungleRegion r1 = new JungleRegion(1);
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
		JungleRegion r1 = new JungleRegion(1);
		Player p1 = new Player("Red", true);
		TigerObject t1 = new TigerObject(p1);
		r1.addTiger(t1);
		
		assertEquals(1, r1.getNumOfTigers());
		
		r1.removeAllTigers();
		
		assertEquals(0, r1.getNumOfTigers());
	}
	
	@Test
	//Test if den can be added from region
	public void denAddTest() {
		DenRegion r1 = new DenRegion(1);
		JungleRegion r2 = new JungleRegion(2);
		r2.addDen(1);
		
		assertEquals(1, r2.getDens().size());
	}
	
	@Test
	//Test if den can be added from region
	public void denRemoveTest() {
		DenRegion r1 = new DenRegion(1);
		JungleRegion r2 = new JungleRegion(2);
		r2.addDen(1);
		
		assertEquals(1, r2.getDens().size());
		
		r2.removeDen(1);
		
		assertEquals(0, r2.getDens().size());
	}

	/*@Test
	public void toStringJungleRegionTest() {
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 6));
		JungleTerrain t1 = new JungleTerrain(c1, 7);
		JungleRegion r1 = new JungleRegion(t1);

		assertEquals("The region 1 of type J has 0 Tiger(s), and 1 Terrain(s).", r1.toString());
	}*/
	
	//DEPRECATED
	/*@Test
	public void updateTigersTest() {
		//Create JungleTerrain t1 which is connected to t2
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 8));
		JungleTerrain t1 = new JungleTerrain(7, c1);
		//Create JungleTerrain t2 which is connected to t1
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 5, 7));
		JungleTerrain t2 = new JungleTerrain(8, c2);
		Player p1 = new Player(1);
		TigerObject tiger = new TigerObject(1, 'F', p1, 7);
		//Add tiger to JungleTerrain t1
		t1.addTiger(tiger);
		JungleRegion r1 = new JungleRegion(t1);
		r1.updateTigers();

		//Check if tiger was added to r1's tiger list
		assertEquals(true, r1.hasTigers());

		//Check if two tigers can be added to r1's tiger list
		TigerObject tiger2 = new TigerObject(2, 'F', p1, 8);
		t2.addTiger(tiger2);
		r1.addTerain(t2);
		assertEquals(2, r1.getTigers().size());
	}*/

	/*@Test
	public void removeAllTigersTest() {
		//Create JungleTerrain t1 which is connected to t2
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 8));
		JungleTerrain t1 = new JungleTerrain(7, c1);
		//Create JungleTerrain t2 which is connected to t1
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 5, 7));
		JungleTerrain t2 = new JungleTerrain(8, c2);
		Player p1 = new Player(1);
		TigerObject tiger = new TigerObject(1, 'F', p1, 7);
		//Add tiger to JungleTerrain t1
		t1.addTiger(tiger);
		JungleRegion r1 = new JungleRegion(t1);
		r1.updateTigers();
		TigerObject tiger2 = new TigerObject(2, 'F', p1, 8);
		//Add tiger to JungleTerrain t2
		t2.addTiger(tiger2);
		r1.addTerain(t2);
		r1.removeAllTigers();

		assertEquals(false, r1.hasTigers());
	}*/

	/*@Test
	public void addRegionTest() {
		//Create JungleTerrain t1 which is connected to t2
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
		JungleTerrain t1 = new JungleTerrain(7, c1);
		//Create JungleTerrain t2 which is connected to t1
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(7));
		JungleTerrain t2 = new JungleTerrain(8, c2);
		//Create separate regions for each terrain
		JungleRegion r1 = new JungleRegion(t1);
		JungleRegion r2 = new JungleRegion(t2);
		//Merge regions
		r1.addRegion(r2);

		assertEquals(2, r1.getNumberOfTerrains());
	}*/

	/*@Test
	public void addRegionExceptionTest() {
		boolean thrown = false;
		try {
			//Create JungleTerrain t1 which is connected to t2
			ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
			JungleTerrain t1 = new JungleTerrain(7, c1);
			//Create LakeTerrain t2 which is connected to t1
			ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(7));
			LakeTerrain t2 = new LakeTerrain(8, c2);
			//Create separate regions for each terrain
			JungleRegion r1 = new JungleRegion(t1);
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
			//Create JungleTerrain t1 which is connected to t2
			ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
			JungleTerrain t1 = new JungleTerrain(7, c1);
			//Create LakeTerrain t2 which is connected to t1
			ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(7));
			LakeTerrain t2 = new LakeTerrain(8, c2);
			JungleRegion r1 = new JungleRegion(t1);
			//Add LakeTerrain to JungleRegion
			r1.addTerain(t2);
		} catch (IllegalArgumentException e){
			thrown = true;
		}
		assertTrue(thrown);
	}*/

	/*@Test
	public void getNumberOfNeighboringLakesTest() {
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 3));
		JungleTerrain t1 = new JungleTerrain(1, c1);
		ArrayList<Integer> c2 = new ArrayList<>();
		LakeTerrain t2 = new LakeTerrain(2, c2);
		JungleRegion r1 = new JungleRegion(t1);
		ArrayList<Integer> c3 = new ArrayList<>();
		LakeTerrain t3 = new LakeTerrain(3, c3);
		ArrayList<Integer> c4 = new ArrayList<>(Arrays.asList(1));
		JungleTerrain t4 = new JungleTerrain(4, c4);
		r1.addTerain(t4);
		ArrayList<Integer> c5 = new ArrayList<>(Arrays.asList(1));
		JungleTerrain t5 = new JungleTerrain(5, c5);
		r1.addTerain(t5);

		assertEquals(2, r1.getNumberOfNeighboringLakes());
	}*/
}

