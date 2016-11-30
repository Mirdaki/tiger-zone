package entities;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/*
 * This class basically mimics the TileStack class, except now in the form of a randomly
 * accessible deck. The only way to get a tile is via randomTile(), which will give a random
 * tile and adjust the deck as needed. This is mostly for testing purposes.
 */
public class TileDeck {
	//class members
	protected Map<Integer, TigerTile> tigerDeck;
	protected int tileCount;
	protected Object[] randomDeck;
	protected ArrayList<TigerTile> givenDeck = new ArrayList<TigerTile>();

	//constructors
	/**
	 * TileStack() constructor, initialize the variables
	 */
	public TileDeck() {

		tigerDeck = new HashMap<Integer, TigerTile>();
		tileCount = 0;
		randomDeck = new TigerTile[77];
		System.out.println("Path is " + System.getProperty("user.dir"));

		try { //attempt to parse XML file of tiles


			//file to parse
			File file = new File("../resources/tiles.xml");
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			System.out.println("here");
			Document doc = dBuilder.parse(file);
			System.out.println("here2");

			//if there was stuff inside of the XML file
			if (doc.hasChildNodes()) {
				//find all tiles
				NodeList nList = doc.getElementsByTagName("tile");

				//for each found tile...
				for (int i = 0; i < nList.getLength(); i++) {

					//create an array list of all tiles that match the type (for multiplicity)
					TigerTile newTile;

					//take ith element and find multiplicity
					Node nNode = nList.item(i);
					Element eElement = (Element) nNode;
					String type = eElement.getAttribute("type");
					int multiplicity = Integer.parseInt(eElement.getAttribute("count"));

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						//add a tile of said type for each multiplicity into array list
						for (int j = 0; j < multiplicity; j++, tileCount++) {

							TigerTile newTiler = new TigerTile(eElement);
							givenDeck.add(newTiler);
							tigerDeck.put(tileCount, newTiler);
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error: TileDeck");
		}
		randomDeck = tigerDeck.values().toArray();

	}//constructor

	//ACCESSORS

	/**
	 * getTiles() will return the tile stack
	 * @return the tigerDeck
	 */
	public Map<Integer, TigerTile> getTiles() {
		return tigerDeck;
	}

	/**
	 *  getTileCount() gets the current number of tiles
	 *  @return the tile count
	 */
	public int getTileCount() {
		return tileCount;
	}

	public TigerTile getRandom() {
		Random generator = new Random();
		TigerTile randomTile = (TigerTile) randomDeck[generator.nextInt(randomDeck.length)];

		tigerDeck.remove(randomTile.getTileID());
		randomDeck = tigerDeck.values().toArray();

		return randomTile;
	}

	public Object[] getRandomDeck() {
		return randomDeck;
	}

	//MUTATORS

	public void start(String type) {
		for(Map.Entry<Integer, TigerTile> entry : tigerDeck.entrySet()) {
			TigerTile temp = entry.getValue();
			if (temp.getType().equals(type)){
				tigerDeck.remove(temp.getTileID());
				randomDeck = tigerDeck.values().toArray();
				break;
			}
		}
	}

	public void setActual(ArrayList<TigerTile> given) {
		this.givenDeck = given;
	}

	public String getNext(int next) {
		return givenDeck.get(next).getType();
	}

	public ArrayList<TigerTile> getGiven() {
		return givenDeck;
	}

}//class
