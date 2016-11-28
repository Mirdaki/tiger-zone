package entities;
/*
 * An abstract tile object class. It will contain all of the abstract features of a tile
 * such as the ID, number, orientation, coordinate, and edges. TigerTile will be based
 * off of this. It is assumed square, but could be altered for other polygons (maybe).
 */
public abstract class TileObject {

	//attributes
	protected static int tileNum; //global int to unique tileID
	protected int tileID;
	protected int orientation; //orientation mappings: {i=0:0, 1:90, 2:180, 3:270}
	protected Location coord;
	protected TileEdges edges;

	//default constructor for inheritance
	public TileObject() {
	}

	//ACCESSORSS 
	
	/**
	 *	getTileNum() will get the total number of tiles that have
	 *	been created using this class.
	 *	@return the total number of tiles created
	 */
	public int getTileNum() {
		return tileNum;
	}

	/**
	 *	getTileID() gets the current tile's ID
	 *	@return the current tile's ID
	 */
	public int getTileID() {
		return tileID;
	}

	/**
	 * getOrientation() will get the current tile's orientation
	 * @return the current tile's orientation
	 */
	public int getOrientation() {
		return orientation;
	}

	/**
	 * getCoord() gets the current tile's coordinate
	 * @return the current tile's coordinate
	 */
	public Location getCoord() {
		return coord;
	}

	/**
	 * getEdges() gets the current tile's edges and maps them according
	 * to the orientation set on the tile
	 * @return the edges
	 */
	public TileEdges getEdges() {
		return edges;
	}

	//MUTATORS 
	
	/**
	 *	setTileNum() sets the tile number count to a new amount
	 *	@param tileNum the new tile amount
	 */
	public void setTileNum(int tileNum) {
		TileObject.tileNum = tileNum;
	}

	/**
	 *	setTileID() sets the current tile's ID to a new ID
	 *	@param tileID the new tile ID
	 */
	public void setTileID(int tileID) {
		this.tileID = tileID;
	}

	/**
	 * setOrientation() will set the current tile's orientation
	 * @param orientation the new orientation
	 */
	public void setOrientation(int orientation) {
		this.orientation = orientation;
		edges.setOrientation(orientation);
	}

	/**
	 * setCoord() sets the current tile's coordinate
	 * @param coord sets the current tile's coordinate to the new coordinate
	 */
	public void setCoord(Location coord) {
		this.coord = coord;
	}

	/**
	 * setEdges() sets the tile's current edges to a new set of edges
	 * @param edges the new edges to be set on the current tile
	 */
	public void setEdges(TileEdges edges) {
		this.edges = edges;
	}

	/**
	 * toString() formats the tile information to be printable
	 * @return the String of formatted information
	 */
	public String toString() {
		return "ID: " + this.tileID +
				"\n(x,y) coordinate: " + this.coord.toString() +
				"\nOrientation: " + this.orientation;
	}//end toSring

} //end TileObject
