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
public class TigerTile extends TileObject {

	protected Player owner;
	protected String type;
	protected char center;
	protected TigerObject tiger;
	protected CrocodileObject croc;
	protected char special;
	protected Terrain[] terrains;


	public TigerTile() { 

	}

	public TigerTile(String type, int orientation) {
		try { //attempt to parse XML file of tiles

			//file to parse
			File file = new File("src/entities/tiles.xml");
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse(file);

			//if there was stuff inside of the XML file
			if (doc.hasChildNodes()) {
				//find tile type
				XPathFactory xPathfactory = XPathFactory.newInstance();
				XPath xpath = xPathfactory.newXPath();
				XPathExpression expr = xpath.compile("//tile[@type=\"" + type +"\"]");
				NodeList nList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

				Node nNode = nList.item(0);
				Element eElement = (Element) nNode;

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					tileID = tileNum++; //ensures uniqueID to a tile
					this.orientation = orientation;
					coord = new Location();
					this.type = type;
					special = type.charAt(4);
					center = eElement.getElementsByTagName("center").item(0).getTextContent().charAt(0);

					NodeList regions = eElement.getElementsByTagName("region");
					terrains = new Terrain[regions.getLength()];

					Animal prey = null;
					if (special != '-') 
						prey = new Animal(special);

					int i = 0; 
					for (int j = 0; j < regions.getLength(); j++) { 

						Element element = (Element)regions.item(j);
						ArrayList<Integer> edgeConnections = new ArrayList<Integer>();

						String regionType = element.getAttribute("rtype");
						String edgeConnectionsText = element.getTextContent();
						StringTokenizer tokens = new StringTokenizer(edgeConnectionsText);

						while(tokens.hasMoreTokens()) {
							int temp = Integer.parseInt(tokens.nextToken());
							edgeConnections.add(Math.floorMod(temp + 2 * orientation, 8));
						}

						if (regionType.equalsIgnoreCase("jungle")) 
							terrains[i] = new JungleTerrain(edgeConnections);
						else if (regionType.equalsIgnoreCase("trail")) { 
							char trailType = eElement.getAttribute("type").charAt(0);
							if (trailType == 'C') 
								terrains[i] = new TrailTerrain(edgeConnections, false, prey);
							else 
								terrains[i] = new TrailTerrain(edgeConnections, true, prey);
						}
						else if (regionType.equalsIgnoreCase("lake")) { 
							char lakeType = eElement.getAttribute("type").charAt(0);
							if (lakeType == 'C') 
								terrains[i] = new LakeTerrain(edgeConnections, false, prey);
							else 
								terrains[i] = new LakeTerrain(edgeConnections, true, prey);
						}
						
						terrains[i++].setTileID(tileID);
					}

					Terrain[] edgeTerrains = new Terrain[8];
					for (Terrain terrain : terrains) {
						ArrayList<Integer> tileConnections = terrain.getTileConnections();
						for (int j = 0; j < tileConnections.size(); j++) 
							edgeTerrains[tileConnections.get(j)] = terrain;
					}
					edges = new TileEdges(edgeTerrains,terrains);

					//terrains has all of my terrains...
					for (int j = 0; j < edgeTerrains.length; j++) { 
						if(edgeTerrains[j] instanceof JungleTerrain) { 

							ArrayList<Integer> adjacents = ((JungleTerrain) edgeTerrains[j]).getLakes();

							//look to our left
							Terrain left = edgeTerrains[Math.floorMod((j-1),edgeTerrains.length)];
							int leftRegionID = left.getRegionID();

							if (left instanceof LakeTerrain && !adjacents.contains(leftRegionID)) { 
								((JungleTerrain) edgeTerrains[j]).addLake(leftRegionID);
							}

							//look to our right
							Terrain right = edgeTerrains[Math.floorMod((j+1),edgeTerrains.length)];
							int rightRegionID = right.getRegionID();
							if (right instanceof LakeTerrain && !adjacents.contains(rightRegionID)) { 
								((JungleTerrain) edgeTerrains[j]).addLake(rightRegionID);
							}

						}
					}

				}
			}
		} catch (Exception e) {
			System.out.println("Error");
		}
	}

	/**
	 * Creates a square tile object based on XML element.
	 * Each square tile has an associated list of representations.
	 * @param eElement An XML element that contains all relevant information
	 * of a given tile extracted from an XML document.
	 * @return SquareTile
	 */
	public TigerTile(Element eElement) {

		//initialize member variables
		tileID = tileNum++; //ensures uniqueID to a tile
		orientation = 0;
		coord = new Location();
		type = eElement.getAttribute("type");
		special = type.charAt(4);
		center = eElement.getElementsByTagName("center").item(0).getTextContent().charAt(0);

		NodeList regions = eElement.getElementsByTagName("region");
		terrains = new Terrain[regions.getLength()];

		Animal prey = null;
		if (special != '-') 
			prey = new Animal(special);

		int i = 0; 
		for (int j = 0; j < regions.getLength(); j++) { 

			Element element = (Element)regions.item(j);
			ArrayList<Integer> edgeConnections = new ArrayList<Integer>();

			String regionType = element.getAttribute("rtype");
			String edgeConnectionsText = element.getTextContent();
			StringTokenizer tokens = new StringTokenizer(edgeConnectionsText);

			while(tokens.hasMoreTokens()) {
				int temp = Integer.parseInt(tokens.nextToken());
				edgeConnections.add(Math.floorMod(temp + 2 * orientation, 8));
			}

			if (regionType.equalsIgnoreCase("jungle")) 
				terrains[i++] = new JungleTerrain(edgeConnections);
			else if (regionType.equalsIgnoreCase("trail")) { 
				
				char trailType = element.getAttribute("type").charAt(0);

				if (trailType == 'C') 
					terrains[i++] = new TrailTerrain(edgeConnections, false, prey);
				else 
					terrains[i++] = new TrailTerrain(edgeConnections, true, prey);
			}
			else if (regionType.equalsIgnoreCase("lake")) { 
				char lakeType = element.getAttribute("type").charAt(0);
				if (lakeType == 'C')
					terrains[i++] = new LakeTerrain(edgeConnections, false, prey);
				else 
					terrains[i++] = new LakeTerrain(edgeConnections, true, prey);
			}
		}


		Terrain[] edgeTerrains = new Terrain[8];

		for (Terrain terrain : terrains) {
			ArrayList<Integer> tileConnections = terrain.getTileConnections();
			for (int j = 0; j < tileConnections.size(); j++) 
				edgeTerrains[tileConnections.get(j)] = terrain;
		}
		edges = new TileEdges(edgeTerrains,terrains);



		//terrains has all of my terrains...
		for (int j = 0; j < edgeTerrains.length; j++) { 
			if(edgeTerrains[j] instanceof JungleTerrain) { 

				ArrayList<Integer> adjacents = ((JungleTerrain) edgeTerrains[j]).getLakes();

				//look to our left
				Terrain left = edgeTerrains[Math.floorMod((j-1),edgeTerrains.length)];
				int leftRegionID = left.getRegionID();

				if (left instanceof LakeTerrain && !adjacents.contains(leftRegionID)) { 
					((JungleTerrain) edgeTerrains[j]).addLake(leftRegionID);
				}

				//look to our right
				Terrain right = edgeTerrains[Math.floorMod((j+1),edgeTerrains.length)];
				int rightRegionID = right.getRegionID();
				if (right instanceof LakeTerrain && !adjacents.contains(rightRegionID)) { 
					((JungleTerrain) edgeTerrains[j]).addLake(rightRegionID);
				}
			}
		}
		
	}//end constructor


	public Terrain getEdge(int index) {
		return edges.getTerrain(index);
	}

	public char getEdgeType(int index) {
		return edges.getTerrain(index).getType();
	}
	public char getSpecial() {
		return special;
	}

	public void setSpecial(char special) {
		this.special = special;
	}
	public String getType() {
		return type;
	}

	/**
	 * setType() set's the current tile's type
	 * @param type the new tile type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * getCenter() gets the current tile's type
	 * @return the current tile's type
	 */
	public char getCenter() {
		return center;
	}

	/**
	 * setCenter() sets the current tile's type
	 * @param center sets the current tile's type
	 */
	public void setCenter(char center) {
		this.center = center;
	}
	/**
	 * getOwner() gets the current tile's owner
	 * @return the current tile's owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * setOwner() sets the current tile's owner
	 * @param owner is the new owner
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * getTiger() gets the tile's current tiger
	 * @return the tile's current tiger
	 */
	public TigerObject getTiger() {
		return tiger;
	}

	/**
	 * setTiger() sets the tile's object to the specified tiger
	 * @param tiger the new tiger to set
	 */
	public void setTiger(TigerObject tiger) {
		this.tiger = tiger;
	}


	public CrocodileObject getCroc() { 
		return croc;
	}

	public void setCroc(CrocodileObject croc) { 
		this.croc = croc;
	}
	/**
	 * getTerrains() gets the terrains associated with the tile
	 * @return the terrains
	 */
	public Terrain[] getTerrains() {
		return terrains;
	}

	/**
	 * setTerrains() sets the current tile's terrains to a new set of terrains
	 * @param terrains sets the terrains to the specified set of terrains
	 */
	public void setTerrains(Terrain[] terrains) {
		this.terrains = terrains;
	}

	@Override
	public String toString() {
		return "ID: " + this.tileID +
				"\n(x,y) coordinate: " + this.coord.toString() +
				"\nOrientation: " + this.orientation +
				"\nType: " + this.type +
				"\nCenter: " + this.center +
				"\nOwner: " + this.owner +
				"\nSpecialty: " + this.special +
				"\nTerrain(s): " + this.terrains.length +
				"\n\nEdges:\n" + this.edges;
	}//end printOut
}//end SquareTile
