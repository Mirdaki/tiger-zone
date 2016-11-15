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

/*
 * This SquareTile object extends off of the TileObject for when we may have to change tile types
*/
public class SquareTile extends TileObject {

	protected ArrayList<SquareTile> representations;

	public ArrayList<SquareTile> getReps() {

		return representations;
	}

	public SquareTile(SquareTile tile) {

		coord = tile.coord;
		orientation = tile.orientation;
		type = tile.type;
		owner = tile.owner;
		Tiger = tile.Tiger;
		center = tile.center;
		edges = Arrays.copyOf(tile.edges, tile.edges.length);
		terrains = Arrays.copyOf(tile.terrains, tile.terrains.length);

	}


	public SquareTile(Element eElement) {

		this.representations = new ArrayList<SquareTile>();

		this.tileID = tileNum++;
		this.coord = new Location();
		this.orientation = orientation;
		this.type = eElement.getAttribute("type").charAt(0);
		this.coord = new Location();

		edges = new edge[4];

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



		NodeList jungles = eElement.getElementsByTagName("jungle");
		NodeList trails = eElement.getElementsByTagName("trail");
		NodeList lakes = eElement.getElementsByTagName("lake");

		int numTerrains = jungles.getLength() + trails.getLength() + lakes.getLength();
		terrains = new Terrain[numTerrains];

		int i = 0;

		for (int j = 0; j < jungles.getLength(); j++) {
			Element element = (Element)jungles.item(j);
			terrains[i++] = new JungleTerrain();
		}

		for (int j = 0; j < trails.getLength(); j++) {
			Element element = (Element)trails.item(j);
			terrains[i++] = new TrailTerrain();
		}

		for (int j = 0; j < lakes.getLength(); j++) {
			Element element = (Element)lakes.item(j);
			terrains[i++] = new LakeTerrain();
		} //adding terrains


		representations.add(this);

		SquareTile tile90 = new SquareTile(this).rotateRight();
		SquareTile tile180 = new SquareTile(tile90).rotateRight();
		SquareTile tile270 = new SquareTile(tile180).rotateRight();

		representations.add(tile90);
		representations.add(tile180);
		representations.add(tile270);


	}//end constructor


	public SquareTile rotateRight() {


		this.orientation = (this.orientation+1) % edges.length;

		edge test[] = Arrays.copyOf(edges, edges.length);

		for (int i = 0; i < edges.length; i++)
			edges[(this.orientation + i) % edges.length] = test[(i + orientation - 1 + edges.length) % edges.length];

		return this;
	}//end rotateRight

	public String toString() {
		return edges[0].toString() + "\n" +
				edges[1].toString() + "\n" +
				edges[2].toString() + "\n" +
				edges[3].toString() + "\n" +
				"Mid: " + this.center + "\n\n" +
				"Terrain 0: " + terrains[0].getTerrainID();

	}//end printOut

	public boolean similarEdge(SquareTile edge) {




		return false;
	}

}//end SquareTile
