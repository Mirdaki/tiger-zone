import java.io.*;
import java.util.Map;
import java.util.Scanner;
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

public class TigerZone {


	public TigerZone() {

	}

	private static void printNote(NodeList nodeList) {

	    for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);

			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				// get node name and value
				System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
				System.out.println("Node Value =" + tempNode.getTextContent());

				if (tempNode.hasAttributes()) {
					// get attributes names and values
					NamedNodeMap nodeMap = tempNode.getAttributes();

					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node node = nodeMap.item(i);
						System.out.println("attr name : " + node.getNodeName());
						System.out.println("attr value : " + node.getNodeValue());
					}
				}

				if (tempNode.hasChildNodes()) {
					// loop again if has child nodes
					printNote(tempNode.getChildNodes());
				}

				System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
			} //end if statement
	    } //end for loop
	} //end method
	public static void main(String[] args) {







		BoardObject test = new BoardObject();



		Map<String, SquareTile> testing = test.getMap();

		System.out.println(testing.get("P0").toString() + "\n");
		System.out.println(testing.get("P180").toString() + "\n");

		/*
		Player player1 = test.getPlayer(0),
				player2 = test.getPlayer(1);


		player1.setFirst(false);
		player1.setFirst(true);

		player2.setAI(true);

		player1.setMeeple(player1.getMeeple()-5);

		player2.addScore(50);
		player2.addScore(150);

		System.out.println(player1.toString());
		System.out.println(player2.toString());

		test.setPlayer(1, player1);

		System.out.println(player1.toString());
		System.out.println(player2.toString());



		*/



//		Player player1 = test.getPlayer(0);
//		Player player2 = test.getPlayer(1);
//
//		player1.setFirst(false);
//		player1.setFirst(true);
//
//		player2.setAI(true);
//
//		player1.setMeeple(player1.getMeeple()-5);
//
//		player2.addScore(50);
//		player2.addScore(150);
//
//		System.out.println(player1.toString());
//		System.out.println(player2.toString());

		//System.out.println(testing.get("P0").toString() + "\n");
		//System.out.println(testing.get("P90").toString() + "\n");
		//System.out.println(testing.get("P180").toString() + "\n");
		//System.out.println(testing.get("P270").toString() + "\n");

		//SquareTile tester = testing.get("A0");
		//System.out.println(tester.toString());

		//edge edges[] = tester.getEdges();

		//for (int i = 0; i < edges.length; i++)
		//{
		//	System.out.println(edges[i].toString());
		//}

	/*	SquareTile test = new SquareTile(1,new Location(),'A');


		System.out.println(test.toString() + "\n");

		test.rotateRight();
		test.rotateRight();
		test.rotateRight();

		System.out.println(test.toString() + "\n");

		test.rotateLeft();
		System.out.println(test.toString() + "\n");
*/

		/*SquareTile test;

		test = new SquareTile(1,2,3,4,'A');

		//System.out.println(test.toString() + "\n");

		test.printOut();

		System.out.println();

		test.rotateRight();
//		test.rotateLeft();

		test.printOut();*/

		/*HexTile testHex;
		testHex = new HexTile(5,1,7,8);

		System.out.println("\n"+testHex.toString());

		Scanner input = new Scanner(System.in);
		String test = "";

		do
		{
			test = input.nextLine();
			System.out.println(test);
		} while (test.compareTo("quit") != 0);*/


	} //end of main

} //end of class
