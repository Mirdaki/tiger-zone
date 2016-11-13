/* List of implementations necessary for Scoring system that vary from current plan on Entity UML:

Terrain: String type, public String getType(), removeMeeple() {should remove all meeples and return to respective players?}, getMeeples() {should return ArrayList instead of array}
City: int numPennants
Farmland: public ArrayList getNeighbouringCities() */

import java.util.*;
public class ScoringSystem {
	public static void main(String[] args) {}
	Player PlayerOne = new Player();
	Player PlayerTwo = new Player();
	void MidGameScoring(Tile justplaced)
	{
		int possiblePoints; // declare here for scope purposes.
		ArrayList<Terrain> TileTerrainList = justplaced.getTerrain();
		// gets ArrayList of all Terrains that are part of this tile.
		for(int index = 0; index < TileTerrainList.size(); index++)
		{
			Terrain temp = TileTerrainList.get(index);
			// Note: need Terrain class to have String type and getter to make this easier
			String temptype = temp.getType();
			// Step 2: detect what type of terrain we are dealing with
			switch(temptype)
			{
				case "farmland":
				// Special case scored only at end of game, so do nothing. 
					break;
				case "road":
					// step 3: see if it is complete
					if(temp.endOfRoad) // complete road
					{
						// step 4: find score according to rules
						possiblePoints = temp.getNumofTiles();
						// score points equal to number of road segments.
						// step 5: detect number of meeples & their owner(s) to award score
						awardScore(temp,possiblePoints);
					}
					else {}	// do nothing, its not complete yet.
					break;
				case "city":
					// step 3: see if it is complete
					if(temp.isCompleted()) // complete city
					{
						// step 4: find score according to rules
						possiblePoints = (temp.numSurroundTiles + temp.numPennants) * 2;
						// 2 points per city tile & for each pennant in city.
						// step 5: detect number of meeples & their owner(s) to award score
						awardScore(temp,possiblePoints);
					}
					else {}	// do nothing, its not complete yet.
					break;
				case "monastery":
					// step 3: see if it is complete
					if(temp.numSurroundTiles==8) //complete cloister surrounded by land tiles in all 8 positions.
					{
						// step 4: find score according to rules
						possiblePoints = 9;
						// complete monastery gets 9 points. 
						// step 5: Monastery has only 1 monk maximum, if present award points to that player.
						if (temp.meeples.size()>0)
						temp.meeples.get(0).player.addScore(possiblePoints);
					}
					else {} // do nothing, its not complete yet.
					break;
				default:
					System.out.println("Error case");
					break;
			}
			// step 6: remove meeples from this terrain
						temp.removeMeeple();
						// Note: This method should remove all meeples (can't think of any case you'd need to remove individual ones.
		}
	}
	
	// Scores incomplete terrains according to meeples present there
	// and scores farmers, adds to players scores, then calls declareWinnerA();
	void ScoringEndGame()
	{ 
		int possiblePoints; // declare here for scope purposes.
		ArrayList<Terrain> TerrainList = new ArrayList<Terrain>();
		// this belongs to board is fully populated by this point, so later put in = Board.TerrainList;
		// Step 1: run through list of all terrains on board 
		for(int index = 0; index < TerrainList.size(); index++)
		{
			Terrain temp = TerrainList.get(index);
			// Note: need Terrain class to have String type and getter to make this easier
			String temptype = temp.getType();
			// Step 2: detect what type of terrain we are dealing with
			switch(temptype)
			{
				case "road":
					// step 3: see if it is incomplete
					if(!temp.endOfRoad) //incomplete road
					{
						// step 4: find score according to rules
						possiblePoints = temp.getNumofTiles();
						// score points equal to number of road segments.
						// step 5: detect number of meeples & their owner(s) to award score
						awardScore(temp,possiblePoints);
					}
					else {}	// do nothing, it was complete and previously scored.
					break;
				case "city":
					// step 3: see if it is incomplete
					if(!temp.isCompleted()) //incomplete city
					{
						// step 4: find score according to rules
						possiblePoints = temp.numSurroundTiles + temp.numPennants;
						// 1 point per city tile & for each pennant in city.
						// step 5: detect number of meeples & their owner(s) to award score
						awardScore(temp,possiblePoints);
					}
					else {}	// do nothing, it was complete and previously scored.
					break;
				case "monastery":
					// step 3: see if it is incomplete
					if(temp.numSurroundTiles!=8) //incomplete cloister not surrounded by land tiles in all 8 positions.
					{
						// step 4: find score according to rules
						possiblePoints = 1 + temp.numSurroundTiles;
						// incomplete monastery gets 1 point for cloister and 1 per surrounding tile. 
						// step 5: Monastery has only 1 monk maximum, if present award points to that player.
						if (temp.meeples.size()>0)
						temp.meeples.get(0).player.addScore(possiblePoints);
					}
					else {} // do nothing, it was complete and previously scored.
					break;
				case "farmland":
					// Special case as we know this is the only stage we score farms, don't check for 'complete'ness
					ArrayList<Terrain> listofcities = temp.getNeighbouringCities();
					possiblePoints = 0;
					for(int i = 0; i < listofcities.size(); i++)
					{
						if(listofcities.get(i).isCompleted())
							possiblePoints += 3;
					}
					awardScore(temp,possiblePoints);
					//do this
					break;
				default:
					System.out.println("Error case");
					break;
			}
			// step 6: remove meeples from this terrain
			temp.removeMeeple();
			// Note: This method should remove all meeples (can't think of any case you'd need to remove individual ones.
		}
		// now that all the scores for farms and incompleted features have been awarded
		// we must declare the result of the game.
		int result = declareWinnerA();
		switch(result)
		{
			case 1: System.out.println("Player 1 wins!");
				break;
			case 2: System.out.println("Player 2 wins!");
				break;
			case 3: System.out.println("Both players win!");
				break;	
		}
	}
	public void awardScore(Terrain temp, int possiblePoints){
	ArrayList<Meeple> tempMeeples = temp.getMeeples(); // returns an arraylist of meeples (called meeple in Terrain)
	// UML says this is array but its easier if its an ArrayList (size function).
	switch(tempMeeples.size())
	{
	case 0: // do nothing, there are no meeples on it.
		break;
	case 1:	tempMeeples.get(0).player.addScore(possiblePoints);
	// for the sole meeple, add to its player's score as previously calculated
	// Not doing temp.setPoints(possiblePoints) b/c it's not valuable to do so.
		break;
	default:
	/// more than 1 meeple
		int p1mcount = 0;
		int p2mcount = 0;
		for(int i = 0; i < tempMeeples.size(); i++)
			{
				if(tempMeeples.get(i).player==PlayerOne)
					p1mcount++;
				else
					p2mcount++;
			}
		if(p1mcount==p2mcount)
		{
			PlayerOne.addScore(possiblePoints);
			PlayerTwo.addScore(possiblePoints);
		}
		else if(p1mcount>p2mcount)
			PlayerOne.addScore(possiblePoints);
		else
			PlayerTwo.addScore(possiblePoints);
		break;
	}
	}
	private int declareWinnerA() // only called by ScoringEndGame
	{
		int A = PlayerOne.getScore();
		int B = PlayerTwo.getScore();
		if (A > B) { return 1; } //player 1 wins
		else if (A < B) { return 2; } //player 2 wins
		else if (A == B){ return 3; } //draw
		return -1; //error condition (impossible to reach unless values can't be loaded)
	}
	
	public int declareWinnerB(int condition, int playerID)
	{ //fail case.  Either time ran out or an illegal move was made.
		//condition is why it was called.  1 for time out, 2 for illegal move
		if (condition == 1)
		{
			//error message for time out
			if (playerID == 1) 
			{ 
				System.out.println("Player 1 has exceeded time allowed.\n");
				return 2;
			}
			if (playerID == 2) 
			{ 
				System.out.println("Player 2 has exceeded time allowed.\n");
				return 1;
			}
			
		}
		if (condition == 2)
		{
			//error message for illegal move
			if (playerID == 1) 
			{ 
				System.out.println("Player 1 has made an illegal move.\n");
				return 2;
			}
			if (playerID == 2) 
			{ 
				System.out.println("Player 2 has made an illegal move.\n");
				return 1;
			}
		}
		return -1; // error condition
	}
}