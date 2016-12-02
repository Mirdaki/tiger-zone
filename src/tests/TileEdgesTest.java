package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import entities.JungleTerrain;
import entities.LakeTerrain;
import entities.Terrain;
import entities.TileEdges;
import entities.TrailTerrain;

public class TileEdgesTest {

	@Test
	public void tileEdgeConstructorAndGetterTest() {
		Terrain[] terrainPoints = new Terrain[8];
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		LakeTerrain t1 = new LakeTerrain(tileConnections, true, null);
		LakeTerrain t2 = new LakeTerrain(tileConnections, true, null);
		LakeTerrain t3 = new LakeTerrain(tileConnections, true, null);
		terrainPoints[0] = t1;
		terrainPoints[1] = t2;
		terrainPoints[2] = t3;
		Terrain[] uniqueTerrains = new Terrain[8];
		uniqueTerrains[0] = t1;
		TileEdges t = new TileEdges(terrainPoints, uniqueTerrains, 0);
		
		assertEquals('L', t.getType(0));
		assertEquals(8, t.getTerrains().length);
		assertEquals(8, t.getUniqueTerrains().length);
	}
	
	@Test
	public void tileEdgeEqualsTest() {
		Terrain[] terrainPoints = new Terrain[8];
		Terrain[] terrainPoints2 = new Terrain[8];
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		LakeTerrain t1 = new LakeTerrain(tileConnections, true, null);
		LakeTerrain t2 = new LakeTerrain(tileConnections, true, null);
		LakeTerrain t3 = new LakeTerrain(tileConnections, true, null);
		JungleTerrain t4 = new JungleTerrain(tileConnections, 4);
		terrainPoints[0] = t1;
		terrainPoints[1] = t2;
		terrainPoints[2] = t3;
		terrainPoints2[0] = t1;
		terrainPoints2[1] = t2;
		terrainPoints2[2] = t4;
		Terrain[] uniqueTerrains = new Terrain[8];
		uniqueTerrains[0] = t1;
		TileEdges t = new TileEdges(terrainPoints, uniqueTerrains, 0);
		TileEdges s = new TileEdges(terrainPoints2, uniqueTerrains, 0);
		
		assertFalse(t.equals(s));
	}

	@Test
	public void tileEntireEdgeTest() {
		Terrain[] terrainPoints = new Terrain[8];
		ArrayList<Integer> tileConnections = new ArrayList<>(Arrays.asList(2, 5, 6));
		LakeTerrain t1 = new LakeTerrain(tileConnections, true, null);
		LakeTerrain t2 = new LakeTerrain(tileConnections, true, null);
		LakeTerrain t3 = new LakeTerrain(tileConnections, true, null);
		terrainPoints[0] = t1;
		terrainPoints[1] = t2;
		terrainPoints[2] = t3;
		Terrain[] uniqueTerrains = new Terrain[8];
		uniqueTerrains[0] = t1;
		TileEdges t = new TileEdges(terrainPoints, uniqueTerrains, 0);
		char[] points = t.getEntireEdge(0);
		
		assertEquals('L', points[0]);
	}
}
