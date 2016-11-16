package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import entities.JungleRegion;
import entities.JungleTerrain;
import entities.LakeRegion;
import entities.LakeTerrain;
import entities.Player;
import entities.TigerObject;

public class JungleRegionTest {

	//Tests constructor and getter after a terrain is added to the region
	@Test
	public void jungleConstructorAndGetterTest() {
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 8));
		JungleTerrain t1 = new JungleTerrain(7, c1);
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 5, 7));
		JungleTerrain t2 = new JungleTerrain(8, c2);
		JungleRegion r1 = new JungleRegion(t1);
		r1.addTerain(t2);
		r1.updateTigers();

		assertEquals(7, r1.getRegionID());
		assertEquals("Jungle", r1.getType());
		assertEquals(2, r1.getNumberOfTerrains());
		assertEquals(false, r1.hasTigers());
	}
	
	@Test
	public void updateTigersTest() {
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 8));
		JungleTerrain t1 = new JungleTerrain(7, c1);
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 5, 7));
		JungleTerrain t2 = new JungleTerrain(8, c2);
		Player p1 = new Player(1);
		TigerObject tiger = new TigerObject(1, 'F', p1, 7);
		t1.addTiger(tiger);
		JungleRegion r1 = new JungleRegion(t1);
		r1.updateTigers();
		
		//check if tiger was added to r1's tiger list
		assertEquals(true, r1.hasTigers());
		
		//check if two tigers can be added to r1's tiger list
		TigerObject tiger2 = new TigerObject(2, 'F', p1, 8);
		t2.addTiger(tiger2);
		r1.addTerain(t2);
		assertEquals(2, r1.getTigers().size());
	}

	@Test
	public void removeAllTigersTest() {
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 8));
		JungleTerrain t1 = new JungleTerrain(7, c1);
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 5, 7));
		JungleTerrain t2 = new JungleTerrain(8, c2);
		Player p1 = new Player(1);
		TigerObject tiger = new TigerObject(1, 'F', p1, 7);
		t1.addTiger(tiger);
		JungleRegion r1 = new JungleRegion(t1);
		r1.updateTigers();
		TigerObject tiger2 = new TigerObject(2, 'F', p1, 8);
		t2.addTiger(tiger2);
		r1.addTerain(t2);
		r1.removeAllTigers();
		
		assertEquals(false, r1.hasTigers());
	}
	
	@Test
	public void addRegionTest() {
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 6));
		JungleTerrain t1 = new JungleTerrain(7, c1);
		ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 5, 9));
		JungleTerrain t2 = new JungleTerrain(8, c2);
		JungleRegion r1 = new JungleRegion(t1);
		JungleRegion r2 = new JungleRegion(t2);
		r1.addRegion(r2);
		
		assertEquals(2, r1.getNumberOfTerrains());
	}
	
	@Test
	public void addRegionExceptionTest() {
		boolean thrown = false;
		try {
			ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 6));
			JungleTerrain t1 = new JungleTerrain(7, c1);
			ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 5, 9));
			LakeTerrain t2 = new LakeTerrain(8, c2, false);
			JungleRegion r1 = new JungleRegion(t1);
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
			JungleTerrain t1 = new JungleTerrain(7, c1);
			ArrayList<Integer> c2 = new ArrayList<>(Arrays.asList(3, 5, 9));
			LakeTerrain t2 = new LakeTerrain(8, c2, false);
			JungleRegion r1 = new JungleRegion(t1);
			r1.addTerain(t2);
		} catch (IllegalArgumentException e){
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	// Not complete/correct yet - need to actually place tiles on board in order to test
	@Test
	public void getNumberOfNeighboringLakesTest() {
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 3));
		JungleTerrain t1 = new JungleTerrain(1, c1);
		ArrayList<Integer> c2 = new ArrayList<>();
		LakeTerrain t2 = new LakeTerrain(2, c2, false);
		JungleRegion r1 = new JungleRegion(t1);
		ArrayList<Integer> c3 = new ArrayList<>();
		LakeTerrain t3 = new LakeTerrain(3, c3, false);
		ArrayList<Integer> c4 = new ArrayList<>(Arrays.asList(1));
		JungleTerrain t4 = new JungleTerrain(4, c4);
		r1.addTerain(t4);
		ArrayList<Integer> c5 = new ArrayList<>(Arrays.asList(1));
		JungleTerrain t5 = new JungleTerrain(5, c5);
		r1.addTerain(t5);
		
		assertEquals(2, r1.getNumberOfNeighboringLakes());
	}
	
	@Test
	public void toStringJungleRegionTest() {
		ArrayList<Integer> c1 = new ArrayList<>(Arrays.asList(2, 5, 6));
		JungleTerrain t1 = new JungleTerrain(7, c1);
		JungleRegion r1 = new JungleRegion(t1);
		
		assertEquals("The region 7 of type Jungle has 0 Meepel(s) and 1 Terrain(s)", r1.toString());
	}
}
