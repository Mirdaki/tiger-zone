package entities;

import java.util.*;

public class artificialIntelligence {
	BoardObject currentBoard;
	//int choiceX, choiceY, orientation;
	//TileStack currentStack;
	TigerTile currentTile;
	private static String ourMove = null;
	ArrayList<Region> descendingRegions = new ArrayList<Region>();
	protected int moveCount; 

	public artificialIntelligence(BoardObject currentBoard, TigerTile currentTile) {
		this.currentTile = currentTile;
		this.currentBoard = currentBoard;
		moveCount = 0;
	}

	public ArrayList<Region> orderedListOfRegions(BoardObject aCurrentBoard) {
		//currentBoard.allRegions;
		ArrayList<Region> descendingRegions = new ArrayList<Region>();
		for(Map.Entry<Integer, Region> entry : aCurrentBoard.allRegions.entrySet()) {
			Region tempRegion = entry.getValue();
			//int pX = tempRegion.getPotential
			descendingRegions.add(tempRegion);
		}
		//Collections.sort(descendingRegions, Collections.reverseOrder());
		int n = descendingRegions.size();
       	Region temp;
               
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

	public int[] checkOurTigers(Region tempRegion) {
		int ourTigers = 0;
		if(tempRegion.hasTigers()) {
			for(int i = 0; i < tempRegion.getNumOfTigers(); i++) {
				if(tempRegion.theTigers.get(i).owner == currentBoard.getPlayer(0)) {
					ourTigers++;
				}
			}
		}
		int theirTigers = tempRegion.getNumOfTigers() - ourTigers;
		int theArray[] = {ourTigers, theirTigers};
		return theArray;

	}

	public boolean isAdjacent(Location ps, Location r)
	{
		if((ps.getX() == r.getX() && (Math.abs(ps.getY()- r.getY()) == 1)) || (ps.getY()==r.getY() && (Math.abs(ps.getX()-r.getX())==1))) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	public String getMove() {
		Region tigerPlacer = null;
		if(!currentBoard.canPlace(currentTile)) {
		//iff we have 0 tigers left
		//pass
		//add another tiger
			ourMove = "TILE " + currentTile.getType() + " ";

//			if(currentBoard.getPlayer(0).getNumOfTigers() == 0) { 
//				//pop out random tiger
//				ArrayList<Region> otherTempArray = orderedListOfRegions(currentBoard);
//				for(int i = otherTempArray.size() - 1; i >= 0; i--) {
//					int tigers[] = checkOurTigers(otherTempArray.get(i));
//					if(tigers[0] > 0) {
//						//place Tiger
//						for(int j = 0; j < otherTempArray.get(i).getNumOfTigers(); j++) {
//							if(otherTempArray.get(i).theTigers.get(j).owner == currentBoard.getPlayer(0)) {
//								Location thisisitTwo = otherTempArray.get(i).theTigers.get(j).getLocation();
//								ourMove += "UNPLACEABLE RETRIEVE TIGER AT " + thisisitTwo.getX() + " " + thisisitTwo.getY();
//								break;
//							}
//						}
//						break;
//					}
//				}
//			}
//			else if(currentBoard.getPlayer(0).getNumOfTigers() > 1) {
//				ArrayList<Region> tempArray = orderedListOfRegions(currentBoard);
//				for(int i = 0; i < tempArray.size(); i++) {
//
//					int tigers[] = checkOurTigers(tempArray.get(i));
//					if(tigers[1] - tigers[0] == 1) {
//	
//						//place Tiger
//						for(int j = 0; j < tempArray.get(i).getNumOfTigers(); j++) {
//
//							if(tempArray.get(i).theTigers.get(j).owner == currentBoard.getPlayer(0)) {
//
//								Location thisisit = tempArray.get(i).theTigers.get(j).getLocation();
//								ourMove += "UNPLACEABLE ADD ANOTHER TIGER TO" + " " + thisisit.getX() + " " + thisisit.getY();
//								break;
//							}
//						}
//						break;
//					}
//				}
//			}
//			else {
				ourMove += "UNPLACEABLE PASS";
//			}
		}
		else {
			
						

			ourMove = "PLACE " + currentTile.getType() + " ";	
			BoardObject tempBoard = new BoardObject(currentBoard);
			ArrayList<Region> tempArray = orderedListOfRegions(tempBoard);
			ArrayList<TilePair> tempPS = currentBoard.getPossibleSpots();
//			Map<Location, Integer> uniqueLocation = new HashMap<Location, Integer>();

//			for(int i = 0; i < tempPS.size(); i++) {
//				if(!uniqueLocation.containsKey(tempPS.get(i))) {
//					uniqueLocation.put(tempPS.get(i).getLocation(), tempPS.get(i).getOrientation());
//				}
//				else 
//				{
//					int curVal = uniqueLocation.get(tempPS.get(i).getLocation());
//					uniqueLocation.put(tempPS.get(i).getLocation(), curVal+1);
//				}
//			}
			// Step 1 - pick tile.
			
			Random randomGenerator = new Random();
			int index = randomGenerator.nextInt(tempPS.size());
			
			TilePair tempSpot = currentBoard.getPossibleSpots().get(index);
			Location tileLoc = tempSpot.getLocation();
			int orientation = tempSpot.getOrientation();
			
			int row = tileLoc.getY();
			int col = tileLoc.getX();
			
			int adjustedY = BoardObject.startY + (BoardObject.COLSIZE/2 - row);
			int adjustedX = BoardObject.startX + (col - BoardObject.ROWSIZE/2);
			
			ourMove += "AT " + adjustedX + " " + adjustedY + " " + orientation; 
			
			currentTile.setOrientation(orientation / 90);
			currentBoard.place(currentTile, new Location(adjustedX, adjustedY));
			TigerTile temp = currentBoard.getRecentTile();
			Terrain[] terrains = temp.getTerrains();
			Map<Integer, Region> allRegions = tempBoard.getAll();
			
			ArrayList<Integer> potentials = new ArrayList<Integer>();
			ArrayList<Integer> potentialRegionID = new ArrayList<Integer>();
			
			for (int i = 0; i < terrains.length; i++) { 
				int regionID = terrains[i].getRegionID();
				Region region = allRegions.get(regionID);
				if (!region.hasTigers() && !region.hasCrocodiles()) { 
					potentials.add(region.getPotential());
					potentialRegionID.add(region.getRegionID());
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
				
				Region region = allRegions.get(potentialRegionID.get(maxIndex));
				ourMove += " TIGER " + region.getRecentMin();
			}
						
//			for(int i = 0; i < tempArray.size(); i++) {
//				Region temp = tempArray.get(i);
//				Set<Integer> ourTileList = temp.getTileList();
//				Iterator<Integer> it = ourTileList.iterator();
//				int or = 0;
//     			while(it.hasNext()) {
//
//     				TigerTile tiletoplay = tempBoard.tiles.get(it.next());
//
//     				Location tileLoc = tiletoplay.getCoord();
//     				for(Map.Entry<Location, Integer> entry : uniqueLocation.entrySet()) {
//     					if(isAdjacent(entry.getKey(), tileLoc)) {
//     						tempBoard.place(currentTile, tileLoc);
//     						for(int y = 0; y < tempPS.size(); y++) {
//
//     							if(tempPS.get(i).getLocation() == tileLoc) {
//     								tigerPlacer = temp;
//     								or = tempPS.get(i).getOrientation();
//     								/*int or2;
//     								switch(or)
//     								{
//     									case 0: 
//     										or2 = 0;
//     										break;
//     									case 90:
//     										or2 = 1;
//     										break;
//     									case 180:
//     										or2 = 2;
//     										break;
//     									case 270:
//     										or2 = 3;
//     										break;
//     								}*/
//     								//currentTile.setOrientation(or2);
//     							}
//     						}
//     						
//     						ourMove.concat("AT " + tileLoc.getX() + " " + tileLoc.getY() + " " + or);
//
//     						break;
//     					}
//     				}
//     				break;
//     			}
//     			break;
//			}
//
//			//check tiles in regions
//			//if any playable spot is adjacent to tile in region place tile
//
//			if(tempBoard.getPlayer(0).getNumOfTigers() == 0) 
//				{
//					if(tempBoard.getPlayer(0).getNumOfCrocs() == 0)
//					{
//						ourMove.concat(" NONE");
//					}
//					else
//					{
//						for(int i = 0; i < tempArray.size(); i++) {
//							int tigers[] = checkOurTigers(tempArray.get(i));
//							if(tigers[1] != 0 && tigers[0] == 0) {
//								// place croc
//								ourMove.concat(" CROCODILE");
//									
//							break;
//							}
//						}
//					}
//				}
//			else
//				{
//					// first legal?
//					ourMove.concat(" TIGER " + tigerPlacer.getRecentMin()); //decide region
//				}
		}
		return ourMove;
	}

}