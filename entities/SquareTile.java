import java.awt.event.ActionEvent;
import java.util.Arrays;

/*
 * This SquareTile object extends off of the TileObject for when we may have to change tile types
*/
public class SquareTile extends TileObject {
	
	protected edge[] edges;
	protected char type, center;

	
	
	public SquareTile(int tileID, Location coord, char type) {
		
		this.tileID = tileID;
		this.coord = coord;
		this.numEdges = 4;
		this.numVertices = 4;
		this.orientation = 0;
		this.type = type;
		
		edges = new edge[this.numEdges];
		
		switch (type){
			case 'A':
				edges[0] = new edge(FIELD,FIELD,FIELD);
				edges[1] = new edge(FIELD,FIELD,FIELD);
				edges[2] = new edge(FIELD,ROAD,FIELD);
				edges[3] = new edge(FIELD,FIELD,FIELD);
				center = MONASTERY;
				this.value = 1;
				break;
			case 'B':
				edges[0] = new edge(FIELD,FIELD,FIELD);
				edges[1] = new edge(FIELD,FIELD,FIELD);
				edges[2] = new edge(FIELD,FIELD,FIELD);
				edges[3] = new edge(FIELD,FIELD,FIELD);
				center = MONASTERY;
				this.value = 1;
				break;
			case 'C':
				edges[0] = new edge(CITY,CITY,CITY);
				edges[1] = new edge(CITY,CITY,CITY);
				edges[2] = new edge(CITY,CITY,CITY);
				edges[3] = new edge(CITY,CITY,CITY);
				center = CITY;
				this.value = 2;
				break;
			case 'D':
				edges[0] = new edge(FIELD,ROAD,FIELD);
				edges[1] = new edge(CITY,CITY,CITY);
				edges[2] = new edge(FIELD,ROAD,FIELD);
				edges[3] = new edge(FIELD,FIELD,FIELD);
				center = ROAD;
				this.value = 1;

				break;
			case 'E':
				edges[0] = new edge(CITY,CITY,CITY);
				edges[1] = new edge(FIELD,FIELD,FIELD);
				edges[2] = new edge(FIELD,FIELD,FIELD);
				edges[3] = new edge(FIELD,FIELD,FIELD);
				center = FIELD;
				this.value = 1;
				break;
			case 'F':
				edges[0] = new edge(FIELD,FIELD,FIELD);
				edges[1] = new edge(CITY,CITY,CITY);
				edges[2] = new edge(FIELD,FIELD,FIELD);
				edges[3] = new edge(CITY,CITY,CITY);
				center = CITY;
				this.value = 2;
				break;
			case 'G':
				edges[0] = new edge(CITY,CITY,CITY);
				edges[1] = new edge(FIELD,FIELD,FIELD);
				edges[2] = new edge(CITY,CITY,CITY);
				edges[3] = new edge(FIELD,FIELD,FIELD);
				center = CITY;
				this.value = 1;				
				break;
			case 'H':
				edges[0] = new edge(FIELD,FIELD,FIELD);
				edges[1] = new edge(CITY,CITY,CITY);
				edges[2] = new edge(FIELD,FIELD,FIELD);
				edges[3] = new edge(CITY,CITY,CITY);
				center = FIELD;
				this.value = 1;
				break;
			case 'I':
				edges[0] = new edge(FIELD,FIELD,FIELD);
				edges[1] = new edge(CITY,CITY,CITY);
				edges[2] = new edge(CITY,CITY,CITY);
				edges[3] = new edge(FIELD,FIELD,FIELD);
				center = FIELD;
				this.value = 1;
				break;
			case 'J':
				edges[0] = new edge(CITY,CITY,CITY);
				edges[1] = new edge(FIELD,ROAD,FIELD);
				edges[2] = new edge(FIELD,ROAD,FIELD);
				edges[3] = new edge(FIELD,FIELD,FIELD);
				center = FIELD;
				this.value = 1;
				break;
			case 'K':
				edges[0] = new edge(FIELD,ROAD,FIELD);
				edges[1] = new edge(CITY,CITY,CITY);
				edges[2] = new edge(FIELD,FIELD,FIELD);
				edges[3] = new edge(FIELD,ROAD,FIELD);
				center = FIELD;
				this.value = 1;
				break;
			case 'L':
				edges[0] = new edge(FIELD,ROAD,FIELD);
				edges[1] = new edge(CITY,CITY,CITY);
				edges[2] = new edge(FIELD,ROAD,FIELD);
				edges[3] = new edge(FIELD,ROAD,FIELD);
				center = CROSSING;
				this.value = 1;
				break;
			case 'M':
				edges[0] = new edge(CITY,CITY,CITY);
				edges[1] = new edge(FIELD,FIELD,FIELD);
				edges[2] = new edge(FIELD,FIELD,FIELD);
				edges[3] = new edge(CITY,CITY,CITY);
				center = FIELD;
				this.value = 2;
				break;
			case 'N':
				edges[0] = new edge(CITY,CITY,CITY);
				edges[1] = new edge(FIELD,FIELD,FIELD);
				edges[2] = new edge(FIELD,FIELD,FIELD);
				edges[3] = new edge(CITY,CITY,CITY);
				center = FIELD;
				this.value = 1;
				break;
			case 'O':
				edges[0] = new edge(CITY,CITY,CITY);
				edges[1] = new edge(FIELD,ROAD,FIELD);
				edges[2] = new edge(FIELD,ROAD,FIELD);
				edges[3] = new edge(CITY,CITY,CITY);
				center = FIELD;
				this.value = 2;
				break;
			case 'P':
				edges[0] = new edge(CITY,CITY,CITY);
				edges[1] = new edge(FIELD,ROAD,FIELD);
				edges[2] = new edge(FIELD,ROAD,FIELD);
				edges[3] = new edge(CITY,CITY,CITY);
				center = FIELD;
				this.value = 1;

				break;
			case 'Q':
				edges[0] = new edge(CITY,CITY,CITY);
				edges[1] = new edge(CITY,CITY,CITY);
				edges[2] = new edge(FIELD,FIELD,FIELD);
				edges[3] = new edge(CITY,CITY,CITY);
				center = CITY;
				this.value = 2;
				break;
			case 'R':
				edges[0] = new edge(CITY,CITY,CITY);
				edges[1] = new edge(CITY,CITY,CITY);
				edges[2] = new edge(FIELD,FIELD,FIELD);
				edges[3] = new edge(CITY,CITY,CITY);
				center = CITY;
				this.value = 1;				
				break;
			case 'S':
				edges[0] = new edge(CITY,CITY,CITY);
				edges[1] = new edge(CITY,CITY,CITY);
				edges[2] = new edge(FIELD,ROAD,FIELD);
				edges[3] = new edge(CITY,CITY,CITY);
				center = CITY;
				this.value = 2;				
				break;
			case 'T':
				edges[0] = new edge(CITY,CITY,CITY);
				edges[1] = new edge(CITY,CITY,CITY);
				edges[2] = new edge(FIELD,ROAD,FIELD);
				edges[3] = new edge(CITY,CITY,CITY);
				center = CITY;
				this.value = 1;				
				break;
			case 'U':
				edges[0] = new edge(FIELD,ROAD,FIELD);
				edges[1] = new edge(FIELD,FIELD,FIELD);
				edges[2] = new edge(FIELD,ROAD,FIELD);
				edges[3] = new edge(FIELD,FIELD,FIELD);
				center = ROAD;
				this.value = 1;				
				break;
			case 'V':
				edges[0] = new edge(FIELD,FIELD,FIELD);
				edges[1] = new edge(FIELD,FIELD,FIELD);
				edges[2] = new edge(FIELD,ROAD,FIELD);
				edges[3] = new edge(FIELD,ROAD,FIELD);
				center = FIELD;
				this.value = 1;								
				break;
			case 'W':
				edges[0] = new edge(FIELD,FIELD,FIELD);
				edges[1] = new edge(FIELD,ROAD,FIELD);
				edges[2] = new edge(FIELD,ROAD,FIELD);
				edges[3] = new edge(FIELD,ROAD,FIELD);
				center = CROSSING;
				this.value = 1;
				break;
			case 'X':
				edges[0] = new edge(FIELD,ROAD,FIELD);
				edges[1] = new edge(FIELD,ROAD,FIELD);
				edges[2] = new edge(FIELD,ROAD,FIELD);
				edges[3] = new edge(FIELD,ROAD,FIELD);
				center = CROSSING;
				this.value = 1;
				break;
			default: 
				break;
		}//end switch case
	}//end constructor
	
	
	
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
				"Mid: " + this.center;
	}//end printOut
	
}//end SquareTile
