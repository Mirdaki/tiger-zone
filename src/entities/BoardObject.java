package entities;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;

/*
 * This is the BoardObject that will implement the board itself.
 * Considerations:
 * 		Should it be a static 2D array? We know the max number of tiles is 72,
 * 		so a 72x72 matrix should suffice to maintain any given tile?
 */
public class BoardObject {

	protected static final int ROWSIZE = 11, COLSIZE = 11;
	protected TileStack tileStack;
	protected Map<String, ArrayList<SquareTile>> tiles = new HashMap<String, ArrayList<SquareTile>>();
	protected SquareTile[][] board;
	protected boolean state; //right now this serves as just a if we started or not
	protected Player[] players;
	protected TigerObject tiger;

	protected ArrayList<Location> availableSpots;
	protected ArrayList<Region> completedRegions;
	protected Map<Integer, Region> incompleteRegions;

	/**
	 * BoardObject() constructor, initialize the variables
	 */
	public BoardObject() {

		availableSpots = new ArrayList<Location>();
		incompleteRegions = new HashMap<Integer, Region>();
		completedRegions = new ArrayList<Region>();
		availableSpots.add(new Location(0,0));
		board = new SquareTile[ROWSIZE][COLSIZE];
		tileStack = new TileStack();
		tiles = tileStack.getTiles();
		state = false;

	} //end constructor
	/**
	 *	getAS() returns the array list of available spots
	 *	@return the ArrayList of available spots
	 */
	public ArrayList<Location> getAS() {
		return availableSpots;
	}

	/**
	 *	setAS() sets the array list of available spots to new spots
	 *	@param availableSpots an array list of new available spots to set
	 */
	public void setAS(ArrayList<Location> availableSpots) {
		this.availableSpots = availableSpots;
	}

	public Map<Integer, Region> getIncomplete() {
		return incompleteRegions;
	}

	public void setIncomplete(Map<Integer, Region> incompleteRegions) {
		this.incompleteRegions = incompleteRegions;
	}

	public ArrayList<Region> getComplete() {
		return completedRegions;
	}

	public void setComplete(ArrayList<Region> completedRegions) {
		this.completedRegions = completedRegions;
	}

	/**
	 *  valid() serves as our placement validity checker. It currently
	 *	checks to see if the location being placed is within bounds
	 *	of the board and if the location is in the available list of
	 *	spots.
		 *
	 * 	TO DO: check if the adjacent tiles at that location can accept the tile
	 *  @param tile the tile to be potentially placed
	 *	@param coord The Location on the board for the tile to be placed.
	 *	@return true if valid placement, false if not
	 */
	public boolean valid(SquareTile tile, Location coord) {

		//check to see if there is any available tiles of the input type
		String type = tile.getType();
        ArrayList<SquareTile> tileMatches = tileStack.getList(type);
		if(tileMatches.isEmpty()) return false;

		//get queried placement
		int row = coord.getRow();
		int col = coord.getCol();

		//if out of bounds of the board, or location filled return false automatically
		if ((row<0 || row>ROWSIZE-1) || (col<0 || col>COLSIZE-1)) return false;
		if(board[row][col] != null) return false;

		//find if the requested spot is in the list of accumulated available spots
		boolean found = false;
		int index = 0;
		for (int i = 0; i < availableSpots.size(); i++) {
			if (availableSpots.get(i).equals(coord)) {
				index = i;
				found = true;
				break;
			}
		}

		//if wasn't found in the list, return false
		if (!found) return false;

		//get adjacent tiles
		SquareTile up = null, right = null, down = null, left = null;
		if(row > 0) up = board[row - 1][col];
		if(col < COLSIZE-1) right = board[row][col + 1];
		if(row < ROWSIZE-1) down = board[row + 1][col];
		if(col > 0) left = board[row][col - 1];

		//if tile edges dont match up with adjacent touching edges, return false
		if(up != null && !up.getEdge(2).equals(tile.getEdge(0))) return false;
		if(right != null && !right.getEdge(3).equals(tile.getEdge(1))) return false;
		if(down != null && !down.getEdge(0).equals(tile.getEdge(2))) return false;
		if(left != null && !left.getEdge(1).equals(tile.getEdge(3))) return false;

		//else remove location from available spots list, return true
		availableSpots.remove(index);
		return true;
	}

	/**
	 *  place() will be what handles physically placing a specified tile at
	 *	the specified location. It calls on valid() to first determine if
	 *  the queried location is available for the tile. It also adds in the
	 *	the adjacent tiles to the available spots list if not already in there.
 	 *
	 * 	TO DO: optimize, account for boundary conditions
	 *
	 *	@param tile The tile to be placed
	 *  @param coord The coordinate location to be placed at
	 *	@return true if placed, false if not
	 */

	public boolean isSurrounded(Location coord) {

		int row = coord.getRow();
		int col = coord.getCol();

		SquareTile tile = board[row][col];
		if (tile == null) return false;

		char special = tile.getSpecial();

		SquareTile up = null, right = null, down = null, left = null;
		SquareTile nw = null, ne = null, se = null, sw = null;

		if(row > 0) up = board[row - 1][col];
		if(col < COLSIZE-1) right = board[row][col + 1];
		if(row < ROWSIZE-1) down = board[row + 1][col];
		if(col > 0) left = board[row][col - 1];

		if(up == null || right == null || down == null || left == null) return false;

		if(special == 'X') {
			if (row > 0 && col > 0) nw = board[row-1][col-1];
			if (row > 0 && col < COLSIZE-1) ne = board[row-1][col+1];
			if (row < ROWSIZE-1 && col < COLSIZE-1) se = board[row+1][col+1];
			if (row < ROWSIZE-1 && col > 0) sw = board[row+1][col-1];
			if(nw == null || ne == null || se == null || sw == null) return false;
		}
		return true;
	}

	public boolean place(SquareTile tile, Location coord) {

		//proceed if valid placement/game is starting
		if (valid(tile,coord)) {

			//get coordinates
			int row = coord.getRow();
			int col = coord.getCol();
			int adjustedRow = row - ROWSIZE/2;
			int adjustedCol = col - COLSIZE/2;
			String type = tile.getType();

			//get adjacent tiles, if any
			SquareTile up = null, right = null, down = null, left = null;
			if(row > 0) up = board[row - 1][col];
			if(col < COLSIZE-1) right = board[row][col + 1];
			if(row < ROWSIZE-1) down = board[row + 1][col];
			if(col > 0) left = board[row][col - 1];

			//initialize potential locations to be added to available spots list
			Location addUp = null, addRight = null, addLeft = null, addDown = null;

			if(row > 0) addUp = new Location(adjustedRow - 1, adjustedCol);
			if(col < COLSIZE-1) addRight = new Location(adjustedRow, adjustedCol + 1);
			if(row < ROWSIZE-1) addDown = new Location(adjustedRow + 1, adjustedCol);
			if(col > 0) addLeft = new Location(adjustedRow, adjustedCol - 1);

			//remove potential duplicate values (is there a better way to do this?)
			for (int i = 0; i < availableSpots.size(); i++) {
				if ((addUp != null && availableSpots.get(i).equals(addUp)) ||
				(addLeft != null && availableSpots.get(i).equals(addLeft)) ||
				(addRight != null && availableSpots.get(i).equals(addRight)) ||
				(addDown != null && availableSpots.get(i).equals(addDown)) ||
				availableSpots.get(i).equals(coord))
					availableSpots.remove(i);
			}

			//if adjacent tiles were empty, add them to available spots to plac
			if (up == null && addUp != null) availableSpots.add(addUp);
			if (right == null && addRight != null) availableSpots.add(addRight);
			if (down == null && addDown != null) availableSpots.add(addDown);
			if (left == null && addLeft != null) availableSpots.add(addLeft);

			Terrain[] terrains = tile.getTerrains();
			boolean connectedUp = (up != null) ? true : false,
					connectedRight = (right != null) ? true : false,
					connectedDown = (down != null) ? true : false,
					connectedLeft = (left != null) ? true : false;


			for (Terrain terrain : terrains) {
				if (terrain instanceof DenTerrain) incompleteRegions.put(terrain.getTerrainID(),new DenRegion(terrain));
				else if (terrain instanceof LakeTerrain) incompleteRegions.put(terrain.getTerrainID(),new LakeRegion(terrain));
				else if (terrain instanceof TrailTerrain) incompleteRegions.put(terrain.getTerrainID(),new TrailRegion(terrain));
				else if (terrain instanceof JungleTerrain) incompleteRegions.put(terrain.getTerrainID(),new JungleRegion(terrain));

			}

			if(connectedLeft) {
				edge edge = left.getEdge(1);
				edge edge2 = tile.getEdge(3);

				Terrain top = edge.getTop(), mid = edge.getMid(), bot = edge.getBot();
				Terrain top2 = edge2.getTop(), mid2 = edge2.getMid(), bot2 = edge2.getBot();

				if (top.getType() == top2.getType()) {

					// Set<Integer> keys = incompleteRegions.keySet();
					//         for(Integer key: keys){
					//             System.out.println(key);
					//         }

					/*
					NOTE: NEED TO FUCKING FIX THIS. NEED TO SOMEHOW CHANGE EVERY GOD DAMN FUCKING EDGE POINT
					THAT CONNECTS WITH A GIVEN TERRAIN.
					IDEA: PLACE ALL EDGE POINTS INTO A SINGLE ARRAY from [0,11], ACCESS THEM THAT WAY. OTHERWISE
					THIS SHIT IS GOING TO GET TOO CRAZY.
					*/
					ArrayList<Integer> connections = top2.getTileConnections();

					Region merge = incompleteRegions.get(top2.getTerrainID());
					Region merger = incompleteRegions.get(top.getTerrainID());

				// 	if (merger == null)
				// 	{
				// 		edge[] edges = tile.getEdges();
				// 	for (Integer integer : connections) {
				// 		if (integer < 3) {
				// 			ArrayList<Terrain> points = edges[0].getPoints();
				// 			points(integer,)
				// 		}
				// 		System.out.print(" " +integer);
 			// 		}
				// }

					 	if (merger != null) merger.mergeRegion(merge);
				}

				if (mid.getType() == mid2.getType()) {
					Region merge = incompleteRegions.get(mid2.getTerrainID());
					Region merger = incompleteRegions.get(mid.getTerrainID());
					if (merger != null) merger.mergeRegion(merge);
				}

				if (bot.getType() == bot2.getType()) {
					Region merge = incompleteRegions.get(bot2.getTerrainID());
					Region merger = incompleteRegions.get(bot.getTerrainID());
					if (merger != null) merger.mergeRegion(merge);
				}

				incompleteRegions.remove(top2.getTerrainID());

			}

			//set the tile's coordinate to it's new spot, place it
			tile.setCoord(coord);
			board[row][col] = tile;
			tileStack.removeTile(type);

			return true;
		}
		return false;
	}
	/**
	* 	start() begins the game by placing the starting tile directly
	*	in the center of the game board.
	*/
	public void start() {
		SquareTile startingTile = tileStack.getTile("TLTJ-",0);
		place(startingTile, new Location(0,0));
	}

	/**
	*	getTile() calls on the TileStack's getTile() method to
	*	obtain the specified tile type and its orientation.
	*	Once obtained, it should be removed from the running list
	* 	of available tiles in the TileStack.
	*
	*	TO DO: remove tile from running list of available tiles
	*
	*	@param type The corresponding tile type (A-Z, a), see Tile Types.png
	*	@param orientation The desired orientation (0=0, 1=90, 2=180, 3=270)
	*	@return the corresponding SquareTile
	*/
	public SquareTile getTile(String type, int orientation) {;
		return tileStack.getTile(type,orientation);
	}

	/**
	*	getPlayers() is self explanatory
	*	@return a Player[] of players currently in the game
	*/
	public Player[] getPlayers() {
		return players;
	}

	/**
	*	setPlayers() is self explanatory - set the current Player[]
	*	to a new Player[]
	*/
	public void setPlayers(Player[] players) {
		this.players = players;
	}

	/**
	*	getPlayer() is self explanatory
	*	@param index the index of the player you want (0=player1, 1=player2)
	*	@return a Player object corresponding to the player
	*/
	public Player getPlayer(int index) {
		return players[index];
	}

	/**
	*	setPlayer() is self explanatory. Need to set existing player object's data
	*	to input object's data directly.
	*	@param index the index of the player you want to set (0=player1, 1=player2)
	*	@param player the new Player object to set
	*/
	public void setPlayer(int index, Player player) {
		players[index].setID(player.getID());
		players[index].setFirst(player.isFirst());
		players[index].setAI(player.isAI());
		players[index].setTiger(player.getTiger());
		players[index].setScore(player.getScore());
	}

	/**
	*	getBoard() is self explanatory
	*	@return the current game board
	*/
	public SquareTile[][] getBoard() {
		return board;
	}

	/**
	*	setBoard() is self explanatory. It sets the current board to a new board.
	*/
	public void setBoard(SquareTile[][] board) {
		this.board = board;
	}

	/**
	 *	Print out the game board in a naive format. Shows locations as their
	 *	coordinates or as the tile that is currently placed.
     */
	public void print() {
		for (int row = 0; row < ROWSIZE; row++) {
			for (int col = 0; col < COLSIZE; col++) {
				if(board[row][col] == null) System.out.print("(" + (row - ROWSIZE/2) + "," + (col - COLSIZE/2) + ")\t");
				else System.out.print(board[row][col].getType() + "\t");
			}
			System.out.println("\n");
		}
	}

	/**
	 *	Print out the current running list of available spots in naive format.
	 *  If empty list, print none
     */
	public void printSpots() {
		System.out.println("Available spots (" + availableSpots.size() + "):");
		if (availableSpots.isEmpty()) System.out.println("None");
		else
			for (int i = 0; i < availableSpots.size(); i++) {
				System.out.print(availableSpots.get(i).toString() + " ");
			}//end for
	}//end printSpots
}
