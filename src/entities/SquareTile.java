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

/*
 * This SquareTile object extends off of the TileObject for when we may have to change tile types
*/
public class SquareTile extends TileObject {

	protected edge[] edges;
	protected char type, center;
	Location coord;
	protected Terrain terrains[];



	public SquareTile(int tileID, Location coord, char type) {

		this.tileID = tileID;
		this.coord = coord;
		this.numEdges = 4;
		this.numVertices = 4;
		this.orientation = 0;
		this.type = type;
		this.coord = coord;

		edges = new edge[this.numEdges];

		try {
	   		File file = new File("tiles.xml");
	   		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	   		Document doc = dBuilder.parse(file);

		   	if (doc.hasChildNodes()) {

				XPathFactory xPathfactory = XPathFactory.newInstance();
				XPath xpath = xPathfactory.newXPath();
				XPathExpression expr = xpath.compile("//tile[@type=\"" + type +"\"]");
				NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

				Node nNode = nl.item(0);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

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
							if (nNode.getNodeType() == Node.ELEMENT_NODE) {
								terrains[i++] = new JungleTerrain(Integer.parseInt(element.getAttribute("id")));
							}
						}


						for (int j = 0; j < trails.getLength(); j++) {
							Element element = (Element)trails.item(j);
							if (nNode.getNodeType() == Node.ELEMENT_NODE) {
								terrains[i++] = new TrailTerrain(Integer.parseInt(element.getAttribute("id")));
							}
						}

						for (int j = 0; j < lakes.getLength(); j++) {
							Element element = (Element)lakes.item(j);
							if (nNode.getNodeType() == Node.ELEMENT_NODE) {
								terrains[i++] = new LakeTerrain(Integer.parseInt(element.getAttribute("id")));
							}
						}


				}
		   	}

	       } catch (Exception e) {
	   			//System.out.println(e.getMessage());
	       }



	}//end constructor

	public edge[] getEdges() {
		return edges;
	}

	public SquareTile rotateRight() {


		this.orientation = (this.orientation+1) % edges.length;

		edge test[] = Arrays.copyOf(edges, edges.length);

		for (int i = 0; i < edges.length; i++)
			edges[(this.orientation + i) % edges.length] = test[(i + orientation - 1 + edges.length) % edges.length];

		return this;
		/*
		char test[] = Arrays.copyOf(data, data.length);

		for (int i = 0; i < data.length; i++)
			data[(this.orientation + i) % data.length] = test[(i + orientation - 2 + data.length) % data.length]; */
	}//end rotateRight

	public void rotateLeft() {
		this.orientation = (this.orientation - 1 + edges.length) % edges.length;

		edge test[] = Arrays.copyOf(edges, edges.length);

		for (int i = 0; i < edges.length; i++)
			edges[(this.orientation + i - 1 + edges.length) % edges.length] = test[(i + orientation) % edges.length];
	}//end rotateLeft

	public String toString() {
		return edges[0].toString() + "\n" +
				edges[1].toString() + "\n" +
				edges[2].toString() + "\n" +
				edges[3].toString() + "\n" +
				"Mid: " + this.center + "\n\n" +
				"Terrain 3: " + terrains[3].getTerrainID();

	}//end printOut

	public boolean similarEdge(SquareTile edge) {




		return false;
	}

}//end SquareTile
