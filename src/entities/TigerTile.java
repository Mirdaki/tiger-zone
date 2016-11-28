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
 * TigerTile is an object of the board that represents a tile.
 * There are 27 tile types and correspond to the tiles described
 * in the TigerZone rules.
 * TigerTile is inherited from the TileObject class. See that file
 * for object breakdown.
 */
public class TigerTile extends TileObject {

	//tiger tile attributes
	protected Player owner;
	protected String type;
	protected char center;
	protected ArrayList<TigerObject> tigers;
	protected CrocodileObject croc;
	protected char special;
	protected Terrain[] terrains;

	//constructors
	public TigerTile() { 

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
		tigers = new ArrayList<TigerObject>();

		//obtain all of the regions associated with a tie and put into a list of terrains
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

			if (regionType.equalsIgnoreCase("jungle")) { terrains[i] = new JungleTerrain(edgeConnections); }
			else if (regionType.equalsIgnoreCase("trail")) { 
				char trailType = element.getAttribute("type").charAt(0);

				//if the trail type is connecting, pass false (not an end); else, pass true
				if (trailType == 'C') {	terrains[i] = new TrailTerrain(edgeConnections, false, prey); }
				else { terrains[i] = new TrailTerrain(edgeConnections, true, prey); }
			}
			else if (regionType.equalsIgnoreCase("lake")) { 
				char lakeType = element.getAttribute("type").charAt(0);
				
				//if the lake type is connecting, pass false (not an end); else, pass true
				if (lakeType == 'C') { terrains[i] = new LakeTerrain(edgeConnections, false, prey); }
				else { terrains[i] = new LakeTerrain(edgeConnections, true, prey); }
			}
			
			//set the terrain's tile ID to current tile and increase counter to next spot
			terrains[i++].setTileID(tileID);
		}


		Terrain[] edgeTerrains = new Terrain[8];

		for (Terrain terrain : terrains) {
			ArrayList<Integer> tileConnections = terrain.getTileConnections();
			for (int j = 0; j < tileConnections.size(); j++) { edgeTerrains[tileConnections.get(j)] = terrain; }
		}

		edges = new TileEdges(edgeTerrains,terrains);

		//add all of the lake associations to any adjacent jungles
		//this is done by checking to our left and right, and if we are
		//adjacent to any lakes, then we add the lakes to the SET associated
		//with the jungle. It is based on regionID
		for (int j = 0; j < edgeTerrains.length; j++) { 
			if(edgeTerrains[j] instanceof JungleTerrain) { 

				//get the lakes 
				ArrayList<Integer> adjacents = ((JungleTerrain) edgeTerrains[j]).getLakes();

				//look to our left
				Terrain left = edgeTerrains[Math.floorMod((j-1),edgeTerrains.length)];
				int leftRegionID = left.getRegionID();

				//if a lake and isn't already in the list, add
				if (left instanceof LakeTerrain && !adjacents.contains(leftRegionID)) { 
					((JungleTerrain) edgeTerrains[j]).addLake(leftRegionID);
				}

				//look to our right
				Terrain right = edgeTerrains[Math.floorMod((j+1),edgeTerrains.length)];
				int rightRegionID = right.getRegionID();

				//if a lake and isn't already in the list, add
				if (right instanceof LakeTerrain && !adjacents.contains(rightRegionID)) { 
					((JungleTerrain) edgeTerrains[j]).addLake(rightRegionID);
				}

			}
		}

	}//end constructor

	//individual tile construction (independent of TileStack factory)
	public TigerTile(String type, int orientation) {
		try { //attempt to parse XML file of tiles

			//file to parse
			File file = new File("resources/tiles.xml");
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse(file);

			//if there was stuff inside of the XML file
			if (doc.hasChildNodes()) {
				//find tile type
				XPathFactory xPathfactory = XPathFactory.newInstance();
				XPath xpath = xPathfactory.newXPath();
				XPathExpression expr = xpath.compile("//tile[@type=\"" + type +"\"]");
				NodeList nList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
				

				//get first found element
				Node nNode = nList.item(0);
				Element eElement = (Element) nNode;

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					//initialize variables
					tileID = tileNum++; //ensures uniqueID to a tile
					this.orientation = orientation;
					coord = new Location();
					this.type = type;
					special = type.charAt(4);
					center = eElement.getElementsByTagName("center").item(0).getTextContent().charAt(0);
					tigers = new ArrayList<TigerObject>();

					//obtain all of the regions associated with a tie and put into a list of terrains
					NodeList regions = eElement.getElementsByTagName("region");
					terrains = new Terrain[regions.getLength()];

					//if there are prey animals, add them to the terrain
					Animal prey = null;
					if (special != '-') { prey = new Animal(special); }

					//i is a counter variable for the next terrain
					int i = 0; 

					//for every region found for the specified tile, create them
					for (int j = 0; j < regions.getLength(); j++) { 

						Element element = (Element)regions.item(j);
						ArrayList<Integer> edgeConnections = new ArrayList<Integer>();

						//the XML text associated with a given region gives the points of connection on a tile (anywhere from 0 to 7)
						String regionType = element.getAttribute("rtype");
						String edgeConnectionsText = element.getTextContent();
						StringTokenizer tokens = new StringTokenizer(edgeConnectionsText);

						//every terrain has a list of locations that it is connected at on a tile
						while(tokens.hasMoreTokens()) {
							int temp = Integer.parseInt(tokens.nextToken());
							edgeConnections.add(Math.floorMod(temp + 2 * orientation, 8));
						}

						if (regionType.equalsIgnoreCase("jungle")) { terrains[i] = new JungleTerrain(edgeConnections); } 
						else if (regionType.equalsIgnoreCase("trail")) { 
							char trailType = eElement.getAttribute("type").charAt(0);

							//if the trail type is connecting, pass false (not an end); else, pass true
							if (trailType == 'C') { terrains[i] = new TrailTerrain(edgeConnections, false, prey); }
							else { terrains[i] = new TrailTerrain(edgeConnections, true, prey); }
						}
						else if (regionType.equalsIgnoreCase("lake")) { 
							char lakeType = eElement.getAttribute("type").charAt(0);

							//if the lake type is connecting, pass false (not an end); else, pass true
							if (lakeType == 'C') { terrains[i] = new LakeTerrain(edgeConnections, false, prey); }
							else { terrains[i] = new LakeTerrain(edgeConnections, true, prey); }
						}

						//set the terrain's tile ID to current tile and increase counter to next spot
						terrains[i++].setTileID(tileID);
					}

					//for every terrain, place into the edge terrain list at their connecting points (max of 8)
					Terrain[] edgeTerrains = new Terrain[8];
					for (Terrain terrain : terrains) {
						ArrayList<Integer> tileConnections = terrain.getTileConnections();
						for (int j = 0; j < tileConnections.size(); j++) { edgeTerrains[tileConnections.get(j)] = terrain; }
					}

					//associate the tile edges with the unique terrains and the terrain list
					edges = new TileEdges(edgeTerrains,terrains);


					//add all of the lake associations to any adjacent jungles
					//this is done by checking to our left and right, and if we are
					//adjacent to any lakes, then we add the lakes to the SET associated
					//with the jungle. It is based on regionID

					//for every jungle terrain
					for (int j = 0; j < edgeTerrains.length; j++) { 
						if(edgeTerrains[j] instanceof JungleTerrain) { 

							//get the lakes 
							ArrayList<Integer> adjacents = ((JungleTerrain) edgeTerrains[j]).getLakes();

							//look to our left
							Terrain left = edgeTerrains[Math.floorMod((j-1),edgeTerrains.length)];
							int leftRegionID = left.getRegionID();

							//if a lake and isn't already in the list, add
							if (left instanceof LakeTerrain && !adjacents.contains(leftRegionID)) { 
								((JungleTerrain) edgeTerrains[j]).addLake(leftRegionID);
							}

							//look to our right
							Terrain right = edgeTerrains[Math.floorMod((j+1),edgeTerrains.length)];
							int rightRegionID = right.getRegionID();

							//if a lake and isn't already in the list, add
							if (right instanceof LakeTerrain && !adjacents.contains(rightRegionID)) { 
								((JungleTerrain) edgeTerrains[j]).addLake(rightRegionID);
							}
						}
					}
				}
			} 
		} catch (Exception e) { //if there was an error with generating the independent tile
			System.out.println("Error");
		}
	}

	// ACCESSORSS 
	public Terrain getEdge(int index) {
		return edges.getTerrain(index);
	}

	public char getEdgeType(int index) {
		return edges.getTerrain(index).getType();
	}

	public char getSpecial() {
		return special;
	}

	public String getType() {
		return type;
	}

	/**
	 * getCenter() gets the current tile's type
	 * @return the current tile's type
	 */
	public char getCenter() {
		return center;
	}

	/**
	 * getOwner() gets the current tile's owner
	 * @return the current tile's owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * getTiger() gets the tile's current tiger
	 * @return the tile's current tiger
	 */
	public ArrayList<TigerObject> getTigers() {
		return tigers;
	}

	/**
	 * getTiger() gets the tile's current tiger
	 * @return the tile's current tiger
	 */
	public TigerObject getTiger() {
		return tigers.get(0);
	}

	public void removeTiger() { 
		tigers.remove(0);
	}
	
	
	public CrocodileObject getCroc() { 
		return croc;
	}

	/**
	 * getTerrains() gets the terrains associated with the tile
	 * @return the terrains
	 */
	public Terrain[] getTerrains() {
		return terrains;
	}

	//MUTATORS 
	public void setSpecial(char special) {
		this.special = special;
	}

	/**
	 * setType() set's the current tile's type
	 * @param type the new tile type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * setCenter() sets the current tile's type
	 * @param center sets the current tile's type
	 */
	public void setCenter(char center) {
		this.center = center;
	}

	/**
	 * setOwner() sets the current tile's owner
	 * @param owner is the new owner
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public void setTigers(ArrayList<TigerObject> tigers) { 
		this.tigers = tigers;
	}
	
	public void setCroc(CrocodileObject croc) { 
		this.croc = croc;
	}

	public void addTiger(TigerObject tiger) { 
		tigers.add(tiger);
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
