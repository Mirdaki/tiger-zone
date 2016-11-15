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

    protected Map<Character, ArrayList<SquareTile>> tiles = new HashMap<Character, ArrayList<SquareTile>>();

    public Map<Character, ArrayList<SquareTile>> getTiles() {
        return tiles;
    }


    public TileStack() {
        try {
       		File file = new File("tiles.xml");
       		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
       		Document doc = dBuilder.parse(file);
//            doc.getDocumentElement().normalize();

    	   	if (doc.hasChildNodes()) {
    			//XPathFactory xPathfactory = XPathFactory.newInstance();
    			//XPath xpath = xPathfactory.newXPath();
    			//XPathExpression expr = xpath.compile("//tile[@type=\"" + type +"\"]");
    			//NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

                //find all tiles...
                NodeList nList = doc.getElementsByTagName("tile");


                //for each found tile...
                for (int i = 0; i < nList.getLength(); i++) {

                    //array list of all tiles that match the type (for multiplicity)
                    ArrayList<SquareTile> newTile = new ArrayList<SquareTile>();

                    //take ith element and find multiplicity
                    Node nNode = nList.item(i);
                    Element eElement = (Element) nNode;
                    int multiplicity = Integer.parseInt(eElement.getAttribute("count"));
                    char type = eElement.getAttribute("type").charAt(0);

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {


                        //add a tile of said type for each multiplicity into array list
                        for (int j = 0; j < multiplicity; j++) {
                            newTile.add(new SquareTile(eElement));
                        }
                        tiles.put(type,newTile);
                    }
                }
            }//if there are children nodes
        } catch (Exception e) {
            System.out.println("Error");
        }
    }//constructor
}//class
