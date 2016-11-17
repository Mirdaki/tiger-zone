package entities;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/*
 * This is the BoardObject that will implement the board itself.
 * Considerations:
 * 		Should it be a static 2D array? We know the max number of tiles is 72,
 * 		so a 72x72 matrix should suffice to maintain any given tile?
 */
public class BoardObject {

	protected static final int ROWSIZE = 11, COLSIZE = 11;
	protected TileStack tileStack;
	protected Map<Character, ArrayList<SquareTile>> tiles = new HashMap<Character, ArrayList<SquareTile>>();
	protected SquareTile[][] board;
	protected boolean state; //right now this serves as just a if we started or not
	protected ArrayList<Location> availableSpots;
	protected Player[] players;
	protected TigerObject tiger;

	/**
	 * BoardObject() constructor, initialize the variables
	 */
	public BoardObject() {

		availableSpots = new ArrayList<Location>();
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

	// public Map<Character, ArrayList<SquareTile>> getMap() {
	// 	return tiles;
	// }

	/**
	 *  valid() serves as our placement validity checker. It currently
	 *	checks to see if the location being placed is within bounds
	 *	of the board and if the location is in the available list of
	 *	spots.
		 *
	 * 	TO DO: check if the adjacent tiles at that location can accept the tile
	 *
	 *	@param coord The Location on the board for the tile to be placed.
	 *	@return true if valid placement, false if not
	 */
	public boolean valid(Location coord) {

		//get queried placement
		int x = coord.getX();
		int y = coord.getY();

		//if out of bounds of the board, return false automatically
		if ((x<0 || x>COLSIZE-1) || (y<0 || y>ROWSIZE-1)) return false;

		//find if the requested spot is in the list of available spots, return true
		for (int i = 0; i < availableSpots.size(); i++) {
			if (availableSpots.get(i).equals(coord)) {
				availableSpots.remove(i);
				return true;
			}
		}

		//if not found, return false
		return false;
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
	public boolean place(SquareTile tile, Location coord) {

		//proceed if valid placement/game is starting
		if (valid(coord) || !state) {

			//get coordinates
			int x = coord.getX();
			int y = coord.getY();

			//get adjacent tiles (fix this for boundary conditions)
			SquareTile up = board[x - 1][y];
			SquareTile right = board[x][y + 1];
			SquareTile down = board[x + 1][y];
			SquareTile left = board[x][y - 1];

			//initialize potential locations to be added to available spots list
			Location addUp = new Location(x - 1, y);
			Location addRight = new Location(x, y + 1);
			Location addDown = new Location(x + 1, y);
			Location addLeft = new Location(x, y - 1);

			//remove potential duplicate values (is there a better way to do this?)
			for (int i = 0; i < availableSpots.size(); i++) {
				if (availableSpots.get(i).equals(addUp) || availableSpots.get(i).equals(addLeft) || availableSpots.get(i).equals(addRight) || availableSpots.get(i).equals(addDown) || availableSpots.get(i).equals(coord))
					availableSpots.remove(i);
			}

			//if adjacent tiles were empty, add them to available spots to place
			if (up == null) availableSpots.add(addUp);
			if (right == null) availableSpots.add(addRight);
			if (down == null) availableSpots.add(addDown);
			if (left == null) availableSpots.add(addLeft);

			//if the game is starting, set this true
			if(!state) state = true;

			//set the tile's coordinate to it's new spot, place it
			tile.setCoord(coord);
			board[x][y] = tile;

			return true;
		}
		return false;
	}
	/**
	* 	start() begins the game by placing the starting tile directly
	*	in the center of the game board.
	*/
	public void start() {
		SquareTile startingTile = getTile('L',0);
		place(startingTile, new Location(ROWSIZE/2,COLSIZE/2));
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
	public SquareTile getTile(char type, int orientation) {
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
				if(board[row][col] == null) System.out.print("(" + row + "," + col + ")\t");
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
		System.out.println("Available spots:" );
		if (availableSpots.isEmpty()) System.out.println("None");
		else
			for (int i = 0; i < availableSpots.size(); i++) {
				System.out.print(availableSpots.get(i).toString() + " ");
			}//end for
	}//end printSpots
}
