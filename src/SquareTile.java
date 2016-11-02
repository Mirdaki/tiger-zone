import java.util.Arrays;

/*
 * This SquareTile object extends off of the TileObject for when we may have to change tile types
*/
public class SquareTile extends TileObject {
	
	private static final char FIELD = 'F';		//0
	private static final char ROAD = 'R';  		//1
	private static final char CITY = 'C';  		//2
	private static final char MONASTERY = 'M';	//3
	private static final char CROSSING = 'X';	//4
	
	protected char[] data;
	protected char type, center;
	
	public SquareTile()
	{
		
	}
	
	public SquareTile(int tileID, int value, int x, int y, char type)
	{
		super(tileID, value, x, y);
		this.numEdges = 4;
		this.numVertices = 4;
		this.orientation = 0;
		this.type = type;
		
		data = new char[this.numEdges + this.numVertices];
		
		switch (type){
			case 'A':
				data[0] = CITY;
				data[1] = CITY;
				data[2] = FIELD;
				data[3] = FIELD;
				data[4] = FIELD;
				data[5] = CITY;
				data[6] = CITY;
				data[7] = CITY;
				center = FIELD;
				break;
			case 'B':
				break;
			case 'C':
				break;
			case 'D':
				break;
			case 'E':
				break;
			case 'F':
				break;
			case 'G':
				break;
			case 'H':
				break;
			case 'I':
				break;
			case 'J':
				break;
			case 'K':
				break;
			case 'L':
				break;
			case 'M':
				break;
			case 'N':
				break;
			case 'O':
				break;
			case 'P':
				break;
			case 'Q':
				break;
			case 'R':
				break;
			case 'S':
				break;
			case 'T':
				break;
			case 'U':
				break;
			case 'V':
				break;
			case 'W':
				break;
			case 'X':
				break;
			default: 
				break;
		}//end switch case
	}//end constructor
	
	public void rotateRight() {
		this.orientation = (this.orientation+2) % data.length;		
		char test[] = Arrays.copyOf(data, data.length);
		
		for (int i = 0; i < data.length; i++)
			data[(this.orientation + i) % data.length] = test[(i + orientation - 2 + data.length) % data.length];
	}//end rotateRight
	
	public void rotateLeft() {
		this.rotateRight();
		this.rotateRight();
		this.rotateRight();
	}//end rotateLeft
	
	public void printOut() {	
		System.out.println(
				data[7] + "\t" + data[0] + "\t" + data[1] + "\n" +
				data[6] + "\t" + center + "\t" + data[2] + "\n" +				
				data[5] + "\t" + data[4] + "\t" + data[3] + "\n"			
				);
	}//end printOut
	
}//end SquareTile
