package entities;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
	protected ArrayList<String> tigerDeck;
	protected int tileCount;
	protected Object[] randomDeck;

	//constructors
	/**
	 * TileStack() constructor, initialize the variables
	 */
	public TileDeck() {

		tigerDeck = new ArrayList<String>();
		tileCount = 0;
		randomDeck = new String[77];

		try { //attempt to parse XML file of tiles

			//file to parse
			File file = new File("resources/tiles.xml");
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			Document doc = dBuilder.parse(file);

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
							tigerDeck.add(type);
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error: TileDeck");
		}
		randomDeck = tigerDeck.toArray();
	}//constructor

	//ACCESSORS

	/**
	 * getTiles() will return the tile stack
	 * @return the tigerDeck
	 */
	public ArrayList<String> getDeck() {
		return tigerDeck;
	}

	/**
	 *  getTileCount() gets the current number of tiles
	 *  @return the tile count
	 */
	public int getTileCount() {
		return tileCount;
	}

	//MUTATORS
	
	public void shuffle() {
		Random generator;
		String randomTile;

		ArrayList<String> randomDeckReturn = new ArrayList<String>();
				
		for (int i = 0; i < randomDeck.length; i++) { 
			generator = new Random();
			randomTile = (String) randomDeck[generator.nextInt(randomDeck.length)];
			tigerDeck.remove(i);
			randomDeck = tigerDeck.toArray();
			randomDeckReturn.add(randomTile);
		}
		tigerDeck = randomDeckReturn;
	}

}//class
