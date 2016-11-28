package tests;

/*import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import entities.Animal;
import entities.LakeRegion;
import entities.LakeTerrain;
import entities.Player;
import entities.TigerObject;
import entities.TrailRegion;
import entities.TrailTerrain;

public class LakeRegionTest {

	//Tests constructor and getter after a terrain is added to the region
	@Test
	public void LakeRegionConstructorAndGetterTest() {
		//Create LakeTerrain t1 which is connected to t2
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
		LakeTerrain t1 = new LakeTerrain(7, c1);
		//Create LakeTerrain t2 which is connected to t1
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(7));
		LakeTerrain t2 = new LakeTerrain(8, c2);
		//Create LakeRegion r1 which consists of LakeTerrain t1
		LakeRegion r1 = new LakeRegion(t1);
		//Add LakeTerrain t2 to LakeRegion r1
		r1.addTerain(t2);
		r1.updateTigers();

		assertEquals(7, r1.getRegionID());
		assertEquals("Lake", r1.getType());
		//Should number of terrains be 2 or 1 since they are the same type?
		assertEquals(2, r1.getNumberOfTerrains());
		assertEquals(false, r1.hasTigers());
		assertEquals(false, r1.isCompleted());
	}

	@Test
	public void updateTigersTest() {
		//Create LakeTerrain t1 which is connected to t2
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 8));
		LakeTerrain t1 = new LakeTerrain(7, c1);
		//Create LakeTerrain t2 which is connected to t1
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 5, 7));
		LakeTerrain t2 = new LakeTerrain(8, c2);
		Player p1 = new Player(1);
		TigerObject tiger = new TigerObject(1, 'K', p1, 7);
		//Add tiger to LakeTerrain t1
		t1.addTiger(tiger);
		LakeRegion r1 = new LakeRegion(t1);
		r1.updateTigers();

		//Check if tiger was added to r1's tiger list
		assertEquals(true, r1.hasTigers());

		//Check if two tigers can be added to r1's tiger list
		TigerObject tiger2 = new TigerObject(2, 'K', p1, 8);
		t2.addTiger(tiger2);
		r1.addTerain(t2);
		assertEquals(2, r1.getTigers().size());
	}

	@Test
	public void removeAllTigersTest() {
		//Create LakeTerrain t1 which is connected to t2
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 8));
		LakeTerrain t1 = new LakeTerrain(7, c1);
		//Create LakeTerrain t2 which is connected to t1
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 5, 7));
		LakeTerrain t2 = new LakeTerrain(8, c2);
		Player p1 = new Player(1);
		TigerObject tiger = new TigerObject(1, 'K', p1, 7);
		//Add tiger to LakeTerrain t1
		t1.addTiger(tiger);
		LakeRegion r1 = new LakeRegion(t1);
		r1.updateTigers();
		TigerObject tiger2 = new TigerObject(2, 'K', p1, 8);
		//Add tiger to LakeTerrain t2
		t2.addTiger(tiger2);
		r1.addTerain(t2);
		r1.removeAllTigers();

		assertEquals(false, r1.hasTigers());
	}

	@Test
	public void addRegionTest() {
		//Create LakeTerrain t1 which is connected to t2
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
		LakeTerrain t1 = new LakeTerrain(7, c1);
		//Create LakeTerrain t2 which is connected to t1
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(7));
		LakeTerrain t2 = new LakeTerrain(8, c2);
		//Create separate regions for each terrain
		LakeRegion r1 = new LakeRegion(t1);
		LakeRegion r2 = new LakeRegion(t2);
		//Merge regions
		r1.addRegion(r2);

		assertEquals(2, r1.getNumberOfTerrains());
	}

	@Test
	public void addRegionExceptionTest() {
		boolean thrown = false;
		try {
			//Create LakeTerrain t1 which is connected to t2
			ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
			LakeTerrain t1 = new LakeTerrain(7, c1);
			//Create TrailTerrain t2 which is connected to t1
			ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(7));
			TrailTerrain t2 = new TrailTerrain(8, c2, false);
			//Create separate regions for each terrain
			LakeRegion r1 = new LakeRegion(t1);
			TrailRegion r2 = new TrailRegion(t2);
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
			//Create LakeTerrain t1 which is connected to t2
			ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
			LakeTerrain t1 = new LakeTerrain(7, c1);
			//Create TrailTerrain t2 which is connected to t1
			ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(7));
			TrailTerrain t2 = new TrailTerrain(8, c2, false);
			LakeRegion r1 = new LakeRegion(t1);
			//Add TrailTerrain to LakeRegion
			r1.addTerain(t2);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}

	//TODO
	@Test
	public void setCompletedTest() {
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(1, 2, 8));
		LakeTerrain t1 = new LakeTerrain(7, c1);
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 4, 7));
		LakeTerrain t2 = new LakeTerrain(8, c2);
		LakeRegion r1 = new LakeRegion(t1);
		r1.addTerain(t2);
		r1.updateCompletion();

		assertEquals(true, r1.isCompleted());
	}

	//TODO
	// Not complete/correct yet
	@Test
	public void addTerrainWhenCompleteExceptionTest() {
		boolean thrown = false;
		try {
			ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
			LakeTerrain t1 = new LakeTerrain(7, c1);
			ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(7));
			LakeTerrain t2 = new LakeTerrain(8, c2);
			LakeRegion r1 = new LakeRegion(t1);
			r1.addTerain(t2);
			r1.updateCompletion();
			ArrayList<Integer> c3 = new ArrayList<>(Arrays.asList(5, 6, 10));
			LakeTerrain t3 = new LakeTerrain(9, c3);
			r1.addTerain(t3);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}

		assertTrue(thrown);
	}

	@Test
	public void getUniqueAnimalsTest() {
		//Create LakeTerrain t1 which is connected to t2 with Deer
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(8));
		Animal a1 = new Animal('D');
		LakeTerrain t1 = new LakeTerrain(7, c1, a1);
		//Create LakeTerrain t2 which is connected to t1 with Deer
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(7));
		Animal a2 = new Animal('D');
		LakeTerrain t2 = new LakeTerrain(8, c2);
		//Create separate regions for each terrain
		LakeRegion r1 = new LakeRegion(t1);
		LakeRegion r2 = new LakeRegion(t2);
		//Merge regions
		r1.addRegion(r2);

		assertEquals(1, r1.getUniqueAnimals());
	}

	//TODO
	// Not complete/correct yet
	@Test
	public void updateCompletionTest() {
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(1, 2, 8));
		LakeTerrain t1 = new LakeTerrain(7, c1);
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 4, 7));
		LakeTerrain t2 = new LakeTerrain(8, c2);
		LakeRegion r1 = new LakeRegion(t1);
		r1.addTerain(t2);

		assertFalse(r1.isCompleted());
	}

	@Test
	public void toStringLakeRegionTest() {
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 6));
		LakeTerrain t1 = new LakeTerrain(7, c1);
		LakeRegion r1 = new LakeRegion(t1);

		assertEquals("The region 7 of type Lake has 0 Meepel(s) and 1 Terrain(s)", r1.toString());
	}
}
*/
