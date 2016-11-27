package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import entities.BoardObject;
import entities.Location;
import entities.TigerTile;
import entities.TileStack;
import entities.edge;


public class SquareTileTest {

	//Doesn't test getTileNum, getTileID, getOwner, getTiger
	@Test
	public void squareTileConstructorAndGetterTest() {
		//Create the board
		BoardObject test = new BoardObject(); 
		test.start();
		TigerTile test1 = test.getTile("JJTJX", 0);
		
		assertEquals('X', test1.getSpecial());
		assertEquals(0, test1.getOrientation());
		assertEquals("JJTJX", test1.getType());
		assertEquals('X', test1.getCenter());
		assertEquals(0, test1.getCoord().getRow());
		assertEquals(0, test1.getCoord().getCol());
		assertEquals("Top: J Mid: J Bot: J", test1.getEdge(0).toString());
		//Should this be 3 since there are 3 different terrains?
		assertEquals(3, test1.getTerrains());
	}

	@Test
	public void squareTileSetOrientationTest() {
		//Create the board
		BoardObject test = new BoardObject();
		test.start();
		TigerTile test1 = test.getTile("JJTJX", 0);
		//Rotate 90 degrees
		test1.setOrientation(1);
		
		//Orientation should be 90 degrees
		assertEquals(1, test1.getOrientation());
		//Original south edge should now be east edge
		assertEquals("Top: J Mid: T Bot: J", test1.getEdge(1).toString());
	}
	
	@Test
	public void squareTileSetCoordTest() {
		//Create the board
		BoardObject test = new BoardObject();
		test.start();
		TigerTile test1 = test.getTile("JJTJX", 0);
		Location c1 = new Location(1, 1);
		//Set location of this tile to be (1,1)
		test1.setCoord(c1);
		
		//Coordinates should be (1,1) or row 6, column 6
		assertEquals(6, test1.getCoord().getRow());
		assertEquals(6, test1.getCoord().getRow());
	}
	
	@Test
	public void squareTileSimilarEdgeTest() {
		//Create the board
		BoardObject test = new BoardObject();
		test.start();
		TigerTile test1 = test.getTile("JJTJX", 0);
		edge e1 = new edge('J', 'J', 'J');
		
		assertTrue(test1.similarEdge(e1));
	}
	
	//TODO
	@Test
	public void squareTileToStringTest() {
		
	}
}
