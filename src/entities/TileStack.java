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
import java.util.HashMap;
import java.util.Map;

public class TileStack {

    //class members
    protected Map<Character, ArrayList<SquareTile>> tileStack;
    protected int tileCount;

    /**
	 * TileStack() constructor, initialize the variables
	 */
    public TileStack() {

        tileStack = new HashMap<Character, ArrayList<SquareTile>>();
        tileCount = 0;

        try { //attempt to parse XML file of tiles

            //file to parse
       		File file = new File("entities/tiles.xml");
       		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

       		Document doc = dBuilder.parse(file);

            //if there was stuff inside of the XML file
    	   	if (doc.hasChildNodes()) {

                //find all tiles
                NodeList nList = doc.getElementsByTagName("tile");

                //for each found tile...
                for (int i = 0; i < nList.getLength(); i++) {

                    //create an array list of all tiles that match the type (for multiplicity)
                    ArrayList<SquareTile> newTile = new ArrayList<SquareTile>();

                    //take ith element and find multiplicity
                    Node nNode = nList.item(i);
                    Element eElement = (Element) nNode;
                    char type = eElement.getAttribute("type").charAt(0);
                    int multiplicity = Integer.parseInt(eElement.getAttribute("count"));


                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                        //add a tile of said type for each multiplicity into array list
                        for (int j = 0; j < multiplicity; j++, tileCount++)
                            newTile.add(new SquareTile(eElement));

                        //place tile types and their multiplicities into the tileStack

                        tileStack.put(type,newTile);



                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }//constructor

    /**
	 * getTiles() will return the tile stack
     * @return the tileStack
	 */
    public Map<Character, ArrayList<SquareTile>> getTiles() {
        return tileStack;
    }

    /**
	 * setTiles() will set the current tile stack to a new tile stack
     * @param tileStack will set the tileStack to a new tileStack
	 */
    public void setTiles(Map<Character, ArrayList<SquareTile>> tileStack) {
        this.tileStack = tileStack;
    }

    /**
     *  getTile() gets a tile from the tileStack based on input
     *  @param type the type of tile you are looking for
     *  @param orientation the orientation of the tile being looked for
     *  @return the specified tile
     */
    public SquareTile getTile(char type, int orientation) {

        //get the arrayList of matching tiles
        ArrayList<SquareTile> tileMatches = tileStack.get(type);

        //if no matches, return null
        if (tileMatches.isEmpty()) return null;

        //pull first match, reset its orientation
        SquareTile tile = tileMatches.get(0);
        tile.setOrientation(orientation);

        return tile;
    }

    /**
     *  getTileCount() gets the current number of tiles
     *  @return the tile count
     */
    public int getTileCount() {
        return tileCount;
    }

    /**
     *  setTileCount() sets the tileCount to be a new amount (dont do this lol)
     *  @param tileCount the new tile count
     */
    public void setTileCount(int tileCount) {
        this.tileCount = tileCount;
    }

}//class
