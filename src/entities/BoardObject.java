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

	public static final int ROWSIZE = 11, COLSIZE = 11;
	protected TileStack tileStack;
	protected SquareTile[][] board;
	protected boolean state; //east now this serves as just a if we started or not
	protected Player[] players;
	protected TigerObject tiger;
	protected Map<String, ArrayList<SquareTile>> tiles;
	protected Map<Integer, Region> incompleteRegions;
	protected Map<Integer, Integer> minSpots; 
	
	protected ArrayList<Location> availableSpots;
	protected ArrayList<Region> completedRegions;

	protected String whyInvalid;
	protected Location recentPlacement;
	protected boolean tigerPlaced; 
	protected boolean pending;
	/**
	 * BoardObject() constructor, initialize the variables
	 */
	public BoardObject() {

		availableSpots = new ArrayList<Location>();
		incompleteRegions = new HashMap<Integer, Region>();
		minSpots = new HashMap<Integer, Integer>();
		
		completedRegions = new ArrayList<Region>();
		availableSpots.add(new Location(0,0));
		board = new SquareTile[ROWSIZE][COLSIZE];
		tileStack = new TileStack();
		tiles = tileStack.getTiles();
		pending = false;
		whyInvalid = "";

	} //end constructor

	public String getReason() { 
		String reason = whyInvalid;
		setReason("");
		return reason;
	}

	public void setReason(String whyInvalid) { 
		this.whyInvalid = whyInvalid;
	}
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

		if (pending) {
			setReason("Pending move still!"); 
			return false;
		}

		//check to see if there is any available tiles of the input type
		if (tile == null) return false;

		String type = tile.getType();
		ArrayList<SquareTile> tileMatches = tileStack.getList(type);
		if(tileMatches == null) { 
			setReason("No tile matches found. Did you use all of them?");
			return false;
		}

		//get queried placement
		int row = coord.getY();
		int col = coord.getX();

		//if out of bounds of the board, or location filled return false automatically
		if ((row<0 || row>ROWSIZE-1) || (col<0 || col>COLSIZE-1)) {
			setReason("Out of board range. Resize the board?");
			return false;
		}

		if(board[row][col] != null) {
			setReason("Spot is filled. Try another location.");
			return false;
		}

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
		if (!found) { 
			setReason("Requested location isn't in the available spots list");
			return false;
		}

		//get adjacent tiles
		SquareTile north = null, east = null, south = null, west = null;
		if(row > 0) north = board[row - 1][col];
		if(col < COLSIZE-1) east = board[row][col + 1];
		if(row < ROWSIZE-1) south = board[row + 1][col];
		if(col > 0) west = board[row][col - 1];

		//if tile edges dont match north with adjacent touching edges, return false
		setReason("Can't place for given orientation.");
		if(north != null && north.getEdgeType(TileEdges.SOUTH) != tile.getEdgeType(TileEdges.NORTH))  return false;
		if(east != null && east.getEdgeType(TileEdges.WEST) != tile.getEdgeType(TileEdges.EAST)) return false;
		if(south != null && south.getEdgeType(TileEdges.NORTH) != tile.getEdgeType(TileEdges.SOUTH)) return false;
		if(west != null && west.getEdgeType(TileEdges.EAST) != tile.getEdgeType(TileEdges.WEST)) return false;
		setReason("");

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

		int row = coord.getY();
		int col = coord.getX();

		SquareTile tile = board[row][col];
		if (tile == null) return false;

		char special = tile.getSpecial();

		SquareTile north = null, east = null, south = null, west = null;
		SquareTile nw = null, ne = null, se = null, sw = null;

		if(row > 0) north = board[row - 1][col];
		if(col < COLSIZE-1) east = board[row][col + 1];
		if(row < ROWSIZE-1) south = board[row + 1][col];
		if(col > 0) west = board[row][col - 1];

		if(north == null || east == null || south == null || west == null) return false;

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

			minSpots.clear();
			
			//get coordinates
			int row = coord.getY();
			int col = coord.getX();
			int adjustedY = COLSIZE/2 - row;
			int adjustedX = col - ROWSIZE/2;
			String type = tile.getType();

			//get adjacent tiles, if any
			SquareTile north = null, east = null, south = null, west = null;
			if(row > 0) north = board[row - 1][col];
			if(col < COLSIZE-1) east = board[row][col + 1];
			if(row < ROWSIZE-1) south = board[row + 1][col];
			if(col > 0) west = board[row][col - 1];

			//initialize potential locations to be added to available spots list
			Location addnorth = null, addeast = null, addwest = null, addsouth = null;

			addnorth = new Location(adjustedX, adjustedY + 1);
			addeast = new Location(adjustedX + 1, adjustedY);
			addsouth = new Location(adjustedX, adjustedY - 1);
			addwest = new Location(adjustedX - 1, adjustedY);

			//remove potential dnorthlicate values (is there a better way to do this?)
			for (int i = 0; i < availableSpots.size(); i++) {
				if ((addnorth != null && availableSpots.get(i).equals(addnorth)) ||
						(addwest != null && availableSpots.get(i).equals(addwest)) ||
						(addeast != null && availableSpots.get(i).equals(addeast)) ||
						(addsouth != null && availableSpots.get(i).equals(addsouth)) ||
						availableSpots.get(i).equals(coord))
					availableSpots.remove(i);
			}

			//if adjacent tiles were empty, add them to available spots to plac
			if (north == null && addnorth != null) availableSpots.add(addnorth);
			if (east == null && addeast != null) availableSpots.add(addeast);
			if (south == null && addsouth != null) availableSpots.add(addsouth);
			if (west == null && addwest != null) availableSpots.add(addwest);

			Terrain[] terrains = tile.getTerrains();
			boolean connectednorth = (north != null) ? true : false,
					connectedeast = (east != null) ? true : false,
							connectedsouth = (south != null) ? true : false,
									connectedwest = (west != null) ? true : false;

			for (Terrain terrain : terrains) {
				//				if (terrain instanceof DenTerrain) incompleteRegions.put(terrain.getRegionID(),new DenRegion(terrain));
				if (terrain instanceof LakeTerrain) incompleteRegions.put(terrain.getRegionID(),new LakeRegion(terrain));
				else if (terrain instanceof TrailTerrain) incompleteRegions.put(terrain.getRegionID(),new TrailRegion(terrain));
				else if (terrain instanceof JungleTerrain) incompleteRegions.put(terrain.getRegionID(),new JungleRegion(terrain));
			}

			if (connectedwest) { 
				mergeTileRegions(west,tile,TileEdges.WEST);
			}
			if (connectedeast) { 
				mergeTileRegions(east,tile,TileEdges.EAST);
			}
			if (connectednorth) { 
				mergeTileRegions(north,tile,TileEdges.NORTH);
			}
			if (connectedsouth) { 
				mergeTileRegions(south,tile,TileEdges.SOUTH);
			}

			//if den
			if(tile.getCenter() == 'X') { 
				DenRegion newDen = new DenRegion(coord);
				incompleteRegions.put(newDen.getRegionID(), newDen);
			}

			//set the tile's coordinate to it's new spot, place it
			tile.setCoord(coord);
			board[row][col] = tile;
			recentPlacement = coord;
			tileStack.removeTile(type);

			updateDens();
			moveCompleted();
			
			return true;
		}
		//		setReason("Wasn't able to place.");
		return false;
	}
	public void updateDens() {
		for(Map.Entry<Integer, Region> entry : incompleteRegions.entrySet()) {
			Region region = entry.getValue();
			if(region instanceof DenRegion) {
				ArrayList<Location> newMoore = getMoore(((DenRegion) region).getLocation());
				((DenRegion) region).setMoore(newMoore);
			}
		}		
	}

	public void moveCompleted() { 
		ArrayList<Integer> toRemove = new ArrayList<Integer>();
		for(Map.Entry<Integer, Region> entry : incompleteRegions.entrySet()) {
			Region region = entry.getValue();
			if(region.isCompleted()) {
				toRemove.add(region.getRegionID());
				completedRegions.add(region);
			}
		}

		for (Integer key : toRemove) { 
			incompleteRegions.remove(key);
		}
	}

	public Region getIncompleteRegion(int key) { 
		return incompleteRegions.get(key);
	}

	public ArrayList<Location> getMoore(Location location) { 
		int row = location.getY();
		int col = location.getX();
		ArrayList<Location> mooreHood = new ArrayList<Location>();
		SquareTile north = null, east = null, south = null, west = null;
		SquareTile nw = null, ne = null, se = null, sw = null;

		if(row > 0) north = board[row - 1][col];
		if(col < COLSIZE-1) east = board[row][col + 1];
		if(row < ROWSIZE-1) south = board[row + 1][col];
		if(col > 0) west = board[row][col - 1];

		if (row > 0 && col > 0) nw = board[row-1][col-1];
		if (row > 0 && col < COLSIZE-1) ne = board[row-1][col+1];
		if (row < ROWSIZE-1 && col < COLSIZE-1) se = board[row+1][col+1];
		if (row < ROWSIZE-1 && col > 0) sw = board[row+1][col-1];

		mooreHood.add(location);
		if(north != null) mooreHood.add(new Location(row-1,col));
		if(east != null) mooreHood.add(new Location(row,col+1));
		if(south != null) mooreHood.add(new Location(row+1,col));
		if(west != null) mooreHood.add(new Location(row,col-1));
		if(nw != null) mooreHood.add(new Location(row-1,col-1));
		if(ne != null) mooreHood.add(new Location(row-1,col+1));
		if(se != null) mooreHood.add(new Location(row+1,col+1));
		if(sw != null) mooreHood.add(new Location(row+1,col-1));

		return mooreHood;
	}

	public void mergeTileRegions(SquareTile a, SquareTile b, int edge) {
		//tile A will serve as the parent, tile B will be the child

		TileEdges aEdges = a.getEdges();
		TileEdges bEdges = b.getEdges();
		Terrain aTop = null, aMid = null, aBot = null;
		Terrain bTop = null, bMid = null, bBot = null;

		switch (edge) {
		case TileEdges.NORTH:
			aTop = aEdges.getTerrain(TileEdges.SOUTHWEST);
			aMid = aEdges.getTerrain(TileEdges.SOUTH);
			aBot = aEdges.getTerrain(TileEdges.SOUTHEAST);
			bTop = bEdges.getTerrain(TileEdges.NORTHWEST);
			bMid = bEdges.getTerrain(TileEdges.NORTH);
			bBot = bEdges.getTerrain(TileEdges.NORTHEAST);
			break;
		case TileEdges.EAST:
			aTop = aEdges.getTerrain(TileEdges.NORTHWEST);
			aMid = aEdges.getTerrain(TileEdges.WEST);
			aBot = aEdges.getTerrain(TileEdges.SOUTHWEST);
			bTop = bEdges.getTerrain(TileEdges.NORTHEAST);
			bMid = bEdges.getTerrain(TileEdges.EAST);
			bBot = bEdges.getTerrain(TileEdges.SOUTHEAST);
			break;
		case TileEdges.SOUTH:
			aTop = aEdges.getTerrain(TileEdges.NORTHWEST);
			aMid = aEdges.getTerrain(TileEdges.NORTH);
			aBot = aEdges.getTerrain(TileEdges.NORTHEAST);
			bTop = bEdges.getTerrain(TileEdges.SOUTHWEST);
			bMid = bEdges.getTerrain(TileEdges.SOUTH);
			bBot = bEdges.getTerrain(TileEdges.SOUTHEAST);
			break;
		case TileEdges.WEST:
			aTop = aEdges.getTerrain(TileEdges.NORTHEAST);
			aMid = aEdges.getTerrain(TileEdges.EAST);
			aBot = aEdges.getTerrain(TileEdges.SOUTHEAST);
			bTop = bEdges.getTerrain(TileEdges.NORTHWEST);
			bMid = bEdges.getTerrain(TileEdges.WEST);
			bBot = bEdges.getTerrain(TileEdges.SOUTHWEST);
			break;
		default: break;
		}

		//1 + 8 + 9

		Region aRegion = incompleteRegions.get(aMid.getRegionID());
		Region bRegion = incompleteRegions.get(bMid.getRegionID());
		ArrayList<Integer> tileConnections = bMid.getTileConnections();
		int oldRegionID = bRegion.getRegionID();

		if(aRegion.getRegionID() != bRegion.getRegionID()) { 

			for (Integer entry : tileConnections) bEdges.setEdge(entry, aRegion.getRegionID());

			aRegion.addTerrain(bRegion.getTerrains(),aRegion.getRegionID());
			
			updateMin(aRegion.getRegionID(),aRegion.getMin());
			
			incompleteRegions.remove(oldRegionID);
		}		

		//if we are connecting a trail, then we have to check the top and bottom to connect jungles as well
		if (bMid.getType() == 'T') { 

			//top
			aRegion = incompleteRegions.get(aTop.getRegionID());
			bRegion = incompleteRegions.get(bTop.getRegionID());

			if(aRegion.getRegionID() != bRegion.getRegionID()) { 
				oldRegionID = bRegion.getRegionID();

				tileConnections = bTop.getTileConnections();
				for (Integer entry : tileConnections) bEdges.setEdge(entry, aRegion.getRegionID());

				aRegion.addTerrain(bRegion.getTerrains(),aRegion.getRegionID());
				updateMin(aRegion.getRegionID(),aRegion.getMin());
				incompleteRegions.remove(oldRegionID);
			} 
			//bottom
			aRegion = incompleteRegions.get(aBot.getRegionID());
			bRegion = incompleteRegions.get(bBot.getRegionID());

			if(aRegion.getRegionID() != bRegion.getRegionID()) { 
				oldRegionID = bRegion.getRegionID();

				tileConnections = bBot.getTileConnections();
				for (Integer entry : tileConnections) bEdges.setEdge(entry, aRegion.getRegionID());

				aRegion.addTerrain(bRegion.getTerrains(),aRegion.getRegionID());
				updateMin(aRegion.getRegionID(),aRegion.getMin());
				incompleteRegions.remove(oldRegionID);
			}
		}
	}
	
	public void updateMin(int regionID, int value) { 
		
		if (minSpots.containsKey(regionID)) { 
			if (value < minSpots.get(regionID)) { 
				minSpots.remove(regionID);
				minSpots.put(regionID, value);
			}
		} else minSpots.put(regionID,  value);
		
	}

	public int adjustIndex(int index, boolean reverse) { 

		if (!reverse) { 
			if(index == 0) return 1;
			else if (index == 1) return 2;
			else if (index == 2) return 3;
			else if (index == 3) return 6;
			else if (index == 4) return 9;
			else if (index == 5) return 8;
			else if (index == 6) return 7;
			else if (index == 7) return 4;
			else return index;	
		}
		else {
			if(index == 1) return 0;
			else if (index == 2) return 1;
			else if (index == 3) return 2;
			else if (index == 4) return 7;
			else if (index == 6) return 3;
			else if (index == 7) return 6;
			else if (index == 8) return 5;
			else if (index == 9) return 4;
			else return index;	
		}
	}

	public boolean placeTiger(int index) {
		if (index > 9 || index < 1) {
			setReason("Index out of bounds");
			return false;
		}

		Location recent = recentPlacement;
		SquareTile last = getTile(recent);
		if (last == null) { 
			setReason("Error: no placed tile?");
			return false;
		}
		
		if (tigerPlaced) { 
			setReason("Already placed a Tiger!");
			return false;
		}
		
		int terrainPoint = adjustIndex(index, true);
		Terrain terrain = last.getEdge(terrainPoint);
		int regionID = terrain.getRegionID();
		
		Region region = incompleteRegions.get(regionID);
		if (region == null) { 
			for (Region reg : completedRegions) { 
				if (reg.getRegionID() == regionID) region = reg;
			}			
		}
		
		if(region == null) { 
			setReason("ERROR: Region wasn't found.");
			return false;
		}
		
		if (index == 5 && last.getCenter() != 'D') {
			setReason("This isn't a den!");
			return false;
		}
		int min = index;
		if (!minSpots.containsKey(regionID)) { 
			min = region.getMin();
		}
		if(min != index) { 
			setReason("Specified index was not the minimum");
			return false;
		}
		
		if(region.hasTigers()) { 
			setReason("Region at index already has a Tiger!");
			return false;
		}
		else region.addTiger();
		tigerPlaced = true;
		return true;		
	}

	/**
	 * 	start() begins the game by placing the starting tile directly
	 *	in the center of the game board.
	 */
	public void start() {
		//		SquareTile startingTile = tileStack.getTile("TLTJ-",0);
		SquareTile startingTile = tileStack.getTile("TLTJ-",0);

		place(startingTile, new Location(0,0));
	}
	
	public void confirm() { 
		pending = false;
		tigerPlaced = false;
	}
		
	public void setPending() { 
		pending = true;
	}
	
	public boolean getPending() { 
		return pending;
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
	public SquareTile getTile(String type, int orientation) {
		SquareTile result = tileStack.getTile(type, orientation);
		if (result == null) { 
			setReason("Couldn't find specified tile. Try another.");
		}		
		return result;
	}

	public SquareTile getTile(Location location) {
		return board[location.getY()][location.getX()];
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
				if(board[row][col] == null) System.out.print("(" + (col - COLSIZE/2) + "," + (ROWSIZE/2 - row) + ")\t");
				else System.out.print(board[row][col].getType() + "\t");
				
//				if(board[row][col] == null) System.out.print("\t");
//				else System.out.print(board[row][col].getType() + "\t");
			
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
