package entities;

import java.util.*;

public class artificialIntelligence {
	BoardObject currentBoard;
	//int choiceX, choiceY, orientation;
	//TileStack currentStack;
	TigerTile currentTile;
	private static String ourMove = null;
	ArrayList<Region> descendingRegions = new ArrayList<Region>();

	public artificialIntelligence(BoardObject currentBoard, TigerTile currentTile) {
		this.currentTile = currentTile;
		this.currentBoard = currentBoard;
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
		Region tigerPlacer;
		if(!currentBoard.canPlace(currentTile)) {
		//iff we have 0 tigers left
		//pass
		//add another tiger
			ourMove = "TILE " + currentTile.getType() + " ";

			if(currentBoard.getPlayer(0).getNumOfTigers() == 0) { 
				//pop out random tiger
				ArrayList<Region> otherTempArray = orderedListOfRegions(currentBoard);
				for(int i = otherTempArray.size() - 1; i >= 0; i--)
				{
					int tigers[] = checkOurTigers(otherTempArray.get(i));
					if(tigers[0] > 0) {
						//place Tiger
						for(int j = 0; j < otherTempArray.get(i).getNumOfTigers(); j++) {
							if(otherTempArray.get(i).theTigers.get(j).owner == currentBoard.getPlayer(0)) {
								Location thisisitTwo = otherTempArray.get(i).theTigers.get(j).getLocation();
								ourMove.concat("UNPLACEABLE RETRIEVE TIGER AT" + " " + thisisitTwo.getX() + " " + thisisitTwo.getY());
							}
						}
					}
				}
				
				//ourMove.concat("UNPLACEABLE RETRIEVE TIGER AT" + " " + thisisit.getX() + " " + thisisit.getY());
			}
			else if(currentBoard.getPlayer(0).getNumOfTigers() > 1) {
				ArrayList<Region> tempArray = orderedListOfRegions(currentBoard);
				for(int i = 0; i < tempArray.size(); i++) {

					int tigers[] = checkOurTigers(tempArray.get(i));
					if(tigers[1] - tigers[0] == 1) {
						//place Tiger
						for(int j = 0; j < tempArray.get(i).getNumOfTigers(); j++) {
							if(tempArray.get(i).theTigers.get(j).owner == currentBoard.getPlayer(0)) {
								Location thisisit = tempArray.get(i).theTigers.get(j).getLocation();
								ourMove.concat("UNPLACEABLE ADD ANOTHER TIGER TO" + " " + thisisit.getX() + " " + thisisit.getY());
							}
						}
						break;
					}
				}
			}
			else {
				ourMove.concat("UNPLACEABLE PASS");
			}

		}
		else {
			ourMove = "PLACE " + currentTile.getType() + " ";
			BoardObject tempBoard = new BoardObject(currentBoard);
			ArrayList<Region> tempArray = orderedListOfRegions(tempBoard);
			ArrayList<TilePair> tempPS = currentBoard.getPossibleSpots();
			HashMap<Location, Integer> uniqueLocation = new HashMap<Location, Integer>();

			for(int i = 0; i < tempPS.size(); i++) {
				if(!uniqueLocation.contains(tempPS.get(i))) {
					uniqueLocation.put(tempPS.get(i).getLocation(), 0);
				}
				else 
				{
					int curVal = uniqueLocation.get(tempPS.get(i).getLocation());
					uniqueLocation.put(tempPS.get(i).getLocation(), curVal+1);
				}
			}
			// Step 1 - pick tile.
			for(int i = 0; i < tempArray.size(); i++) {
				Region temp = tempArray.get(i);
				Set<Integer> ourTileList = temp.getTileList();
				Iterator<Integer> it = ourTileList.iterator();
				int or;
     			while(it.hasNext())
     			{

     				TigerTile tiletoplay = tempBoard.tileDeck.get(it.next());
     				Location tileLoc = tiletoplay.getCoord();
     				for(Map.Entry<Location, Integer> entry : uniqueLocation.entrySet()) {
     					if(isAdjacent(entry.getKey(), tileLoc))
     					{
     						tempBoard.place(currentTile, tileLoc);
     						for(int y = 0; y < tempPS.size(); y++)
     						{
     							if(tempPS.get(i).getLocation() == tileLoc)
     							{
     								tigerPlacer = temp;
     								or = tempPS.get(i).getOrientation();
     								/*int or2;
     								switch(or)
     								{
     									case 0: 
     										or2 = 0;
     										break;
     									case 90:
     										or2 = 1;
     										break;
     									case 180:
     										or2 = 2;
     										break;
     									case 270:
     										or2 = 3;
     										break;
     								}*/
     								//currentTile.setOrientation(or2);
     							}
     						}
     						
     						ourMove.concat("AT " + tileLoc.getX() + " " + tileLoc.getY() + " " + or);

     						break;
     					}
     				}
     				break;
     			}
     			break;
			}

			//check tiles in regions
			//if any playable spot is adjacent to tile in region place tile

			if(tempBoard.getPlayer(0).getNumOfTigers() == 0) 
				{
					if(tempBoard.getPlayer(0).getNumOfCrocs() == 0)
					{
						ourMove.concat(" NONE");
					}
					else
					{
						for(int i = 0; i < tempArray.size(); i++) {
							int tigers[] = checkOurTigers(tempArray.get(i));
							if(tigers[1] != 0 && tigers[0] == 0) {
								// place croc
								ourMove.concat(" CROCODILE");
									
							break;
							}
						}
					}
				}
			else
				{
					// first legal?
					ourMove.concat(" TIGER " + tigerPlacer.getRecentMin()); //decide region
				}
		}
		return ourMove;
	}

}