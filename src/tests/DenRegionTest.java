package tests;

/*import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import entities.DenRegion;
import entities.DenTerrain;
import entities.LakeRegion;
import entities.LakeTerrain;
import entities.Player;
import entities.TigerObject;

public class DenRegionTest {

	//Tests constructor and getter after a terrain is added to the region
	@Test
	public void denConstructorAndGetterTest() {
		//Create DenTerrain t1 which is connected to t2
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
		DenTerrain t1 = new DenTerrain(7, c1);
		//Create DenTerrain t2 which is connected to t1
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(7));
		DenTerrain t2 = new DenTerrain(8, c2);
		//Create DenRegion r1 which consists of DenTerrain t1
		DenRegion r1 = new DenRegion(t1);
		//Add DenTerrain t2 to DenRegion r1
		r1.addTerain(t2);
		r1.updateTigers();

		assertEquals(7, r1.getRegionID());
		assertEquals("Den", r1.getType());
		//Should number of terrains be 2 or 1 since they are the same type?
		assertEquals(2, r1.getNumberOfTerrains());
		assertEquals(false, r1.hasTigers());
	}

	@Test
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
	}

	@Test
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
	}

	@Test
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
	}

	@Test
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
	}

	@Test
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
	}

	//TODO
	// Not complete/correct yet - need to actually place tiles on board in order to test
	@Test
	public void getNumberOfNeighboringTilesTest() {
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2));
		DenTerrain t1 = new DenTerrain(1, c1);
		DenRegion r1 = new DenRegion(t1);
		ArrayList<Integer> c2 = new ArrayList<>();
		LakeTerrain t2 = new LakeTerrain(2, c2);
		ArrayList<Integer> c3 = new ArrayList<>(Arrays.asList(1));
		DenTerrain t3 = new DenTerrain(3, c3);
		r1.addTerain(t3);

		assertEquals(1, r1.getNumberOfNeighboringTiles());
	}

	//TODO
	@Test
	public void updateNeighboringTilesTest() {

	}

	@Test
	public void toStringDenRegionTest() {
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 6));
		DenTerrain t1 = new DenTerrain(7, c1);
		DenRegion r1 = new DenRegion(t1);

		assertEquals("The region 7 of type Den has 0 Meepel(s) and 1 Terrain(s)", r1.toString());
	}
}
*/
