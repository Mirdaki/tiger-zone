package entities;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Element;
import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

/**
 * SquareTile is an object of the board that represents a tile.
 * There are 27 tile types and correspond to the tiles described
 * in the TigerZone rules.
 * SquareTile is inherited from the TileObject class. See that file
 * for object breakdown.
 */
public class SquareTile extends TileObject {

	/**
	 * Creates a square tile object based on XML element.
	 * Each square tile has an associated list of representations.
	 * @param eElement An XML element that contains all relevant information
	 * of a given tile extracted from an XML document.
	 * @return SquareTile
	 */
	public SquareTile(Element eElement) {

		//initialize member variables
		tileID = tileNum++; //ensures uniqueID to a tile
		orientation = 0;
		coord = new Location();
		type = eElement.getAttribute("type").charAt(0);
		edges = new edge[4];

		//setup edge data, mappings: {i=O:north, i=1:east, i=2:south, i=4:west}
		String north = eElement.getElementsByTagName("north").item(0).getTextContent();
		String east = eElement.getElementsByTagName("east").item(0).getTextContent();
		String south = eElement.getElementsByTagName("south").item(0).getTextContent();
		String west = eElement.getElementsByTagName("west").item(0).getTextContent();
		String mid = eElement.getElementsByTagName("center").item(0).getTextContent();
		edges[0] = new edge(north.charAt(0), north.charAt(2), north.charAt(4));
		edges[1] = new edge(east.charAt(0), east.charAt(2), east.charAt(4));
		edges[2] = new edge(south.charAt(0), south.charAt(2), south.charAt(4));
		edges[3] = new edge(west.charAt(0), west.charAt(2), west.charAt(4));
		center = mid.charAt(0);

		//setup terrain data
		NodeList jungles = eElement.getElementsByTagName("jungle"); //find all jungle terrains
		NodeList trails = eElement.getElementsByTagName("trail"); //find all trail terrains
		NodeList lakes = eElement.getElementsByTagName("lake"); //find all lake terrains
		terrains = new Terrain[jungles.getLength() + trails.getLength() + lakes.getLength()];
		int i = 0;

		//find all jungles
		for (int j = 0; j < jungles.getLength(); j++) {
			Element element = (Element)jungles.item(j);
			terrains[i++] = new JungleTerrain();
		}

		//find all trails
		for (int j = 0; j < trails.getLength(); j++) {
			Element element = (Element)trails.item(j);
			terrains[i++] = new TrailTerrain();
		}

		//find all lakes
		for (int j = 0; j < lakes.getLength(); j++) {
			Element element = (Element)lakes.item(j);
			terrains[i++] = new LakeTerrain();
		}

	}//end constructor

	/**
	 * Creates a SquareTile object based on an existing SquareTile.
	 * This is mostly used for creating the alternative orientations.
	 * @param tile an existing SquareTile object to copy.
	 * @return a SquareTile matching the data
	 * NOTE: MAY NO LONGER NEED
	 */
	public SquareTile(SquareTile tile) {
		tileID = tile.tileID;
		coord = tile.coord;
		orientation = tile.orientation;
		type = tile.type;
		owner = tile.owner;
		tiger = tile.tiger;
		center = tile.center;
		edges = Arrays.copyOf(tile.edges, tile.edges.length);
		terrains = Arrays.copyOf(tile.terrains, tile.terrains.length);
	}//end constructor

	//checks to see if there are any similar edges to place against
	public boolean similarEdge(SquareTile edge) {
		edge[] edges1 = this.getEdges(this.orientation);
		edge[] edges2 = edge.getEdges(edge.orientation);

		//check to see if there is a similar edge on any of the edges
		for(int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(edges1[i].equals(edges2[j])) return true;
			}
		}

		return false;
	}//end similarEdge

	@Override
	public String toString() {

		return "ID: " + this.tileID +
		"\n(x,y) coordinate: " + this.coord.toString() +
		"\nOrientation: " + this.orientation +
		"\nType: " + this.type +
		"\nCenter: " + this.center +
		"\nOwner: " + this.owner +
		"\n\nEdges:\n" + edges[Math.floorMod((0 - orientation + 4),4)].toString() + "\n" +
				edges[Math.floorMod((1 - orientation + 4),4)].toString() + "\n" +
				edges[Math.floorMod((2 - orientation + 4),4)].toString() + "\n" +
				edges[Math.floorMod((3 - orientation + 4),4)].toString() + "\n";

	}//end printOut
}//end SquareTile
