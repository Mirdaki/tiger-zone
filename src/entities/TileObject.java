import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * This is the TileObject that will handle a basic tile of the TigerZone game implementation.
 * 
 * According to the official rules, there are 72 total tiles in a game of Carcassonne. 
 * There are 26 total types of tiles. See following for their ID and multiplicity: 
 * 		A - 2x		B - 4x		C - 1x		D - 4x		E - 5x		F - 2x 		
 * 		G - 1x 		H - 3x		I - 2x 		J - 3x		K - 3x 		L - 3x 
 * 		M - 2x 		N - 2x 		O - 2x		P - 3x 		Q - 1x		R - 3x
 * 		S - 2x 		T - 1x 		U - 8x 		V - 9x		W - 4x 		X - 1x
 *
 * A typical tile will need to describe:
 * 		1.) an ID to identify type
 * 		2.) its coordinate location on the board
 * 		3.) its card value (1 or 2)
 * 		4.) its orientation (which direction the "top" is facing")
 * 		5.) its edges
 * 			a.) the number of
 * 			b.) list of?
 * 		6.) the number of vertices
 * 			a.) the number of
 * 			b.) list of?
 * 		7.) its farm land 
 * 		8.) its roads
 * 		9.) its monasteries
 * 		10.) its center (castle, road, farm land, monastery, village)
 * 		11.) its meeples 
 * 			a.) the number of
 * 			b.) the placeable locations
 * 			c.) list of?
 * 			
 */


//NEED TO CREATE AN ABSTRACT TILEOBJECT THAT WE CAN TAKE FROM

public abstract class TileObject {
	
	protected static final char FIELD = 'F';		//0
	protected static final char ROAD = 'R';  		//1
	protected static final char CITY = 'C';  		//2
	protected static final char MONASTERY = 'M';	//3
	protected static final char CROSSING = 'X';	//4
	
	protected int tileID, 
				value,
				numEdges,
				numVertices,
				orientation;

	protected char type;
	
	protected Location coord;
	protected Player owner;
	protected MeepleObject meeple;
	
		
	//constructors
	public TileObject() { 
		
	}
	
	public TileObject(int tileID, int value, Location coord) {
		this.tileID = tileID;
		this.value = value;
		this.coord = coord;
	}
	
	//accessor methods
	public int getTileID() {
		return tileID;
	}

	public void setTileID(int tileID) {
		this.tileID = tileID;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) { 
		this.value = value;
	}
	
	public Location getX() { 
		return coord;
	}
	
	public void setX(Location coord) { 
		this.coord = coord;
	}

	public int getNumEdges() {
		return numEdges;
	}
	
	public void setNumEdges(int numEdges) {
		this.numEdges = numEdges;
	}

	public int getNumVertices() { 
		return numVertices;
	}

	public void setNumVertices(int numVertices) { 
		this.numVertices = numVertices;
	}
	
	public int getOrientation() { 
		return orientation;
	}
		
	public Player getOwner() {
		return owner;
	}
	
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	
	
	//normal methods
	public String toString() {
		return "ID: " + this.tileID + "\nCard value: " + this.value + "\n(x,y) coordinate: " + this.coord.toString() + "\nEdge count: " 
				+ this.numEdges + "\nVertex count: " + this.numVertices + "\nOrientation: " + this.orientation;
	}//end toSring
	
	
} //end TileObject
	
	
	