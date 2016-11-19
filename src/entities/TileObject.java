package entities;

public abstract class TileObject {

	//attributes
	protected static int tileNum; //global int to unique tileID

	protected int tileID;
	protected int orientation; //orientation mappings: {i=0:0, 1:90, 2:180, 3:270}
	protected String type;
	protected char center;

	protected Location coord;
	protected Player owner;
	protected TigerObject tiger;

	protected edge[] edges;
	protected Terrain terrains[];

	//default constructor for inheritance
	public TileObject() {
	}

	/**
	 *	getTileNum() will get the total number of tiles that have
	 *	been created using this class.
	 *	@return the total number of tiles created
	 */
	public int getTileNum() {
		return tileNum;
	}

	/**
	 *	setTileNum() sets the tile number count to a new amount
	 *	@param tileNum the new tile amount
	 */
	public void setTileNum(int tileNum) {
		this.tileNum = tileNum;
	}

	/**
	 *	getTileID() gets the current tile's ID
	 *	@return the current tile's ID
	 */
	public int getTileID() {
		return tileID;
	}

	/**
	 *	setTileID() sets the current tile's ID to a new ID
	 *	@param tileID the new tile ID
	 */
	public void setTileID(int tileID) {
		this.tileID = tileID;
	}

	/**
	 * getOrientation() will get the current tile's orientation
	 * @return the current tile's orientation
	 */
	public int getOrientation() {
		return orientation;
	}

	/**
	 * setOrientation() will set the current tile's orientation
	 * @param orientation the new orientation
	 */
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	/**
	 * getType() will get the current tile's type
	 * See Tile Type.png for more information
	 * @return the current tile's type
	 */
	public String getType() {
		return type;
	}

	/**
	 * setType() set's the current tile's type
	 * @param type the new tile type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * getCenter() gets the current tile's type
	 * @return the current tile's type
	 */
	public char getCenter() {
		return center;
	}

	/**
     * setCenter() sets the current tile's type
	 * @param center sets the current tile's type
	 */
	public void setCenter(char center) {
		this.center = center;
	}

	/**
	 * getCoord() gets the current tile's coordinate
	 * @return the current tile's coordinate
	 */
	public Location getCoord() {
		return coord;
	}

	/**
	 * setCoord() sets the current tile's coordinate
	 * @param coord sets the current tile's coordinate to the new coordinate
	 */
	public void setCoord(Location coord) {
		this.coord = coord;
	}

	/**
	 * getOwner() gets the current tile's owner
	 * @return the current tile's owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * setOwner() sets the current tile's owner
	 * @param owner is the new owner
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * getTiger() gets the tile's current tiger
	 * @return the tile's current tiger
	 */
	public TigerObject getTiger() {
		return tiger;
	}

	/**
	 * setTiger() sets the tile's object to the specified tiger
	 * @param tiger the new tiger to set
	 */
	public void setTiger(TigerObject tiger) {
		this.tiger = tiger;
	}

	/**
	 * getEdges() gets the current tile's edges and maps them according
	 * to the orientation set on the tile
	 * @return the edges
	 */
	public edge[] getEdges() {
		edge[] test = {edges[Math.floorMod((0 - orientation + 4),4)],edges[Math.floorMod((0 - orientation + 4),4)],edges[Math.floorMod((0 - orientation + 4),4)],edges[Math.floorMod((0 - orientation + 4),4)]};
		return test;
	}

	/**
	 * setEdges() sets the tile's current edges to a new set of edges
	 * @param edges the new edges to be set on the current tile
	 */
	public void setEdges(edge[] edges) {
		this.edges = edges;
	}

	/**
	 * getTerrains() gets the terrains associated with the tile
	 * @return the terrains
	 */
	public Terrain[] getTerrains() {
		return terrains;
	}

	/**
	 * setTerrains() sets the current tile's terrains to a new set of terrains
	 * @param terrains sets the terrains to the specified set of terrains
	 */
	public void setTerrains(Terrain[] terrains) {
		this.terrains = terrains;
	}

	/**
	 * toString() formats the tile information to be printable
	 * @return the String of formatted information
 	 */
	public String toString() {
		return "ID: " + this.tileID +
		"\n(x,y) coordinate: " + this.coord.toString() +
		"\nOrientation: " + this.orientation +
		"\nType: " + this.type +
		"\nCenter: " + this.center +
		"\nOwner: " + this.owner;
	}//end toSring

} //end TileObject
