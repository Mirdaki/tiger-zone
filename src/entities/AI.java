package entities;

import java.util.*;

public class AI {
	private static int movenumber = 0;
	BoardObject currentBoard;
	//int choiceX, choiceY, orientation;
	//TileStack currentStack;
	TigerTile currentTile;
	private static String ourMove = null;
	ArrayList<Region> descendingRegions = new ArrayList<Region>();
	protected int moveCount;

	//AI takes in the current state of the board
	//decides how to proceed with next move
	public AI(BoardObject currentBoard) {
		this.currentBoard = currentBoard;
	}

	public int adjust(int value) { 
		if(value == 0) return 1;
		else if (value == 1) return 2;
		else if (value == 2) return 3;
		else if (value == 3) return 6;
		else if (value == 4) return 9;
		else if (value == 5) return 8;
		else if (value == 6) return 7;
		else if (value == 7) return 4;
		else return value;			
	}

	//Creates list of available regions for tiger/crocodile placement
	//Reorders list of regions in descending order of score potential
	//First entry in the list should have highest priority for tiger/crocodile placement
	public ArrayList<Region> orderedListOfRegions() {
		//currentBoard.allRegions;
		ArrayList<Region> descendingRegions = new ArrayList<Region>();
		//Traverses list of all incomplete regions
		for(Map.Entry<Integer, Region> entry : currentBoard.incompleteRegions.entrySet()) {
			Region tempRegion = entry.getValue();
			//int pX = tempRegion.getPotential

			//Transfers entries from Map to ArrayList
			descendingRegions.add(tempRegion);
		}
		//Collections.sort(descendingRegions, Collections.reverseOrder());
		int n = descendingRegions.size();
		Region temp;

		//Bubble sort to reorder list in descending order
		for(int i=0; i < n; i++){
			for(int j=1; j < (n-i); j++){
				if(descendingRegions.get(j-1).getPotential() < descendingRegions.get(j).getPotential()){
					//swap the elements!
					temp = descendingRegions.get(j-1);
					descendingRegions.add((j-1), descendingRegions.get(j));
					descendingRegions.add(j,temp);
				}
			}
		}
		return descendingRegions;
	}

	//Checks how many tigers are in a given region
	//Returns array where the first entry is the amount of tigers our team has
	//second entry is the amount of tigers opposing team has
	public int[] checkOurTigers(Region tempRegion) {
		int ourTigers = 0;
		if(tempRegion.hasTigers()) {
			//Traverses list of all tigers in the region and counts how many belong to Player
			for(int i = 0; i < tempRegion.getNumOfTigers(); i++) {
				if(tempRegion.theTigers.get(i).getTigerOwner() == currentBoard.getPlayer(0)) {
					ourTigers++;
				}
			}
		}
		//Determines number of opponent's tigers in region given Player's tigers and the amount of tigers
		//in the region
		int theirTigers = tempRegion.getNumOfTigers() - ourTigers;
		int theArray[] = {ourTigers, theirTigers};
		return theArray;

	}

	//Returns String to be given to TigerZoneClient
	//Checks if a tile can be placed
	//If tile cannot be placed determines if a tiger will be picked up from/added to a region or to pass
	//If tile can be placed looks at available spots determines location, orientation and possible tiger/crocodile placement
	public String getMove(TigerTile currentTile) {
		movenumber++;
		boolean placeprinted = false;

		Player[] players = currentBoard.getPlayers();

		//Current tile to be played is unplaceable
		if(!currentBoard.canPlace(currentTile)) {
			ourMove = "TILE " + currentTile.getType() + " ";
			//If we not have any tigers find one to pick up
			if(players[0].getNumOfTigers() == 0) {
				// pop out tiger we own from least valued incomplete region.
				ArrayList<Region> dlist = orderedListOfRegions();
				for(int i = dlist.size() - 1; i >= 0; i--) {	// starting from least valued region
					int tigers[] = checkOurTigers(dlist.get(i));
					if(tigers[0] > 0) {		// if we have tigers in it.
						//retrieve Tiger
						for(int j = 0; j < dlist.get(i).getNumOfTigers(); j++) {
							if(dlist.get(i).theTigers.get(j).getTigerOwner() == players[0]) {
								Location removefrom = dlist.get(i).theTigers.get(j).getLocation();
								ourMove += "UNPLACEABLE RETRIEVE TIGER AT " + removefrom.getX() + " " + removefrom.getY();
								break;
							}
						}
						break;
					}
				}
			}
			//If active player has more than one tiger find a region to add another tiger
			else if(players[0].getNumOfTigers() > 1) {

				ArrayList<Region> desclist = orderedListOfRegions();
				for(int i = 0; i < desclist.size(); i++) {
					int tigers[] = checkOurTigers(desclist.get(i));
					if(tigers[1] - tigers[0] == 1) {
						//place Tiger
						for(int j = 0; j < desclist.get(i).getNumOfTigers(); j++) {
							if(desclist.get(i).theTigers.get(j).getTigerOwner() == players[0]) {
								Location addhere = desclist.get(i).theTigers.get(j).getLocation();
								ourMove += "UNPLACEABLE ADD ANOTHER TIGER TO" + " " + addhere.getX() + " " + addhere.getY();
								break;
							}
						}
						break;
					} else {
						ourMove += "UNPLACEABLE PASS";
						break;
					}
				}

			}
			//If not decision can be made about adding or picking up a tiger do nothing
			else {
				ourMove += "UNPLACEABLE PASS";
			}
		}
		//If current tile to be played is placable
		else {
			ourMove = "PLACE " + currentTile.getType() + " ";
			//BoardObject currentBoard = new BoardObject(currentBoard);
			//ArrayList<Region> tempArray = orderedListOfRegions();	// O(n^2) so if timeout deal with this.

			//tempPS is an array list of available locations and orientations the current tile can be played in
			ArrayList<TilePair> tempPS = currentBoard.getPossibleSpots();
			int index = -1;
			// Step 0 - override random if tile is a den tile.
			if(currentTile.getCenter()=='X') {
				HashMap<Location, Integer> uniqueLocation = new HashMap<Location, Integer>();
				for(int i = 0; i < tempPS.size(); i++) {
					if(!uniqueLocation.containsKey(tempPS.get(i))) {
						uniqueLocation.put(tempPS.get(i).getLocation(), 0);
					}
					else
					{
						int curVal = uniqueLocation.get(tempPS.get(i).getLocation());
						uniqueLocation.put(tempPS.get(i).getLocation(), curVal+1);
					}
				}
				int maxMoore = -1;
				Location bestloc = null;;
				for (HashMap.Entry<Location, Integer> entry : uniqueLocation.entrySet()) {
					Location location = entry.getKey();
					if(currentBoard.getMoore(location).size() > maxMoore)
					{
						maxMoore = currentBoard.getMoore(location).size();
						bestloc = location;
					}
				}
				for (int i = 0; i < tempPS.size(); i++)
				{
					if(tempPS.get(i).getLocation()==bestloc)
					{
						index = i;
					}
				}

			}
			// Step 1 - pick random spot out of eligible ones.
			else {
				Random randomGenerator = new Random();
				index = randomGenerator.nextInt(tempPS.size());
			}
			TilePair tempSpot = tempPS.get(index);
			Location tileLoc = tempSpot.getLocation();
			int orientation = tempSpot.getOrientation();

			int row = tileLoc.getY();
			int col = tileLoc.getX();

			int adjustedY = BoardObject.startY + (BoardObject.COLSIZE/2 - row);
			int adjustedX = BoardObject.startX + (col - BoardObject.ROWSIZE/2);

			ourMove += "AT " + adjustedX + " " + adjustedY + " " + orientation;

			currentTile.setOrientation(orientation / 90);
			currentBoard.place(currentTile, new Location(adjustedX, adjustedY));
			currentBoard.confirm();
			TigerTile temp = currentBoard.getRecentTile();
			Terrain[] terrains = temp.getTerrains();
			Map<Integer, Region> allRegions = currentBoard.getAll();

			ArrayList<Integer> potentials = new ArrayList<Integer>();
			ArrayList<Integer> potentialRegionID = new ArrayList<Integer>();
			ArrayList<Boolean> prevstatus = new ArrayList<Boolean>();

			for (int i = 0; i < terrains.length; i++) {
				int regionID = terrains[i].getRegionID();
				Region region = allRegions.get(regionID);
				if (!region.hasTigers() && !region.hasCrocodiles()) {
					potentials.add(region.getPotential());
					potentialRegionID.add(region.getRegionID());
					prevstatus.add(region.isCompleted());
				}
			}

			int maxPotential = -1;
			int maxIndex = 0;

			for (int i = 0; i < potentials.size(); i++) {
				if(potentials.get(i) > maxPotential) {
					maxIndex = i;
					maxPotential = potentials.get(i);
				}
			}
			if(maxPotential != -1) {
				if(currentBoard.getActivePlayer().getNumOfTigers() == 0) {
					if(currentBoard.getActivePlayer().getNumOfCrocs() == 0) {
						ourMove += " NONE";
						placeprinted = true;
					}
					else {
						Region theregion = allRegions.get(potentialRegionID.get(maxIndex));
						if(theregion.getType()=='L') {
							int tigers[] = checkOurTigers(theregion);
							if(tigers[1] != 0 && tigers[0] == 0) {
								// place croc
								ourMove += " CROCODILE";
								placeprinted = true;
							}
						}
					}
				}
				else {
					Region region = allRegions.get(potentialRegionID.get(maxIndex));
					if((prevstatus.get(maxIndex)==false && region.isCompleted==true) || currentTile.getSpecial()=='X' || movenumber%4==0) {

						if (currentTile.getSpecial()=='X') {
							ourMove += " TIGER " + 5;
							placeprinted = true;
						}
						// TODO: Uncomment this to place tiger on regions other than den
//						else {
//
//							//determine the minimum index that the region occurs at, and return that...
//							int minIndex = 10;		 
//							for (int i = 0; i < terrains.length; i++) { 
//								if(terrains[i].getRegionID() == region.getRegionID()) {
//									int adjustedIterator = adjust(i);
//									if (terrains[i].getZoneMin() < minIndex) { 
//										minIndex = terrains[i].getZoneMin();
//									}
//								}
//							}
//
//							if (players[0].hasTigers() && !region.hasTigers()) { 
//
//								
//								if (region.getType() == 'T' && currentTile.getCenter() == 'T' && (minIndex == 6 || minIndex == 8)) { 
//									System.out.println(currentTile.getCenter());
//									minIndex = 5;
//								}
//								currentBoard.placeTiger(minIndex);								
//								ourMove += " TIGER " + minIndex;
//								placeprinted = true;
//							} 
//							else {
//								ourMove += " NONE";
//								placeprinted = true;
//							}
//						}

					}
					else {
						ourMove += " NONE";
						placeprinted = true;

					}
				}
			}

			if(!placeprinted) ourMove += " NONE";

		}

		return ourMove;
	} // end of get move

} // end of class
