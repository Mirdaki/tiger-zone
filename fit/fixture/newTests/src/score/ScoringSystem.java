/* Based on Scoring Rules stated in Tiger Zone V2.2 */
/* only method not presently in Region is getNumberOfCrocodiles	*/
import java.util.*;

import entities.Region;
public class ScoringSystem {
	public static void main(String[] args) {}
	// Board must pass completed region into this method.
	void MidGameScoring(Region justcompleted)
	{
		int possiblePoints; 			// declare here for scope purposes.
		int extra = 0;
		String temptype = justcompleted.getType();
			// Step 2: detect what type of region we are dealing with
			switch(temptype)
			{
				case "Jungle":
				// Special case scored only at end of game, so do nothing. Shouldn't even trigger.
					break;
				case "Trail":
					// step 3: see if it is complete
					if(justcompleted.isCompleted())
					{
						// step 4: find score according to rules
						possiblePoints = justcompleted.getNumofTiles();
						extra = justcompleted.getNumberOfAnimals() - justcompleted.getNumberOfCrocodiles();
						if(extra < 0)
							extra = 0;
						possiblePoints += extra;
						// step 5: detect number of tigers & their owner(s) to award score
						awardScore(justcompleted,possiblePoints);
					}
					else {}	// do nothing, it was sent by mistake.
					break;
				case "Lake":
					// step 3: see if it is complete
					if(justcompleted.isCompleted())
					{
						// step 4: find score according to rules
						possiblePoints = justcompleted.getNumofTiles() * 2;
						extra = justcompleted.getUniqueAnimals()  - justcompleted.getNumberOfCrocodiles();
						if(extra < 0)
							extra = 0;
						possiblePoints *= (1+extra);
						// step 5: detect number of tigers & their owner(s) to award score
						awardScore(justcompleted,possiblePoints);
					}
					else {}	// do nothing, it was sent by mistake.
					break;
				case "Den":
					// step 3: see if it is complete
					if(justcompleted.isCompleted())
					{
						// step 4: find score according to rules
						possiblePoints = 9;
						// step 5: Den has only 1 tiger maximum, and if present then award points to that player.
						if (justcompleted.getTigers().size()>0)
						justcompleted.getTigers().get(0).getTigerOwner().addScore(possiblePoints);
					}
					else {} // do nothing, it was sent by mistake.
					break;
				default:
					// System.out.println("Error case");
					break;
			}
			// step 6: remove tigers from this region
						justcompleted.removeAllTigers();
	}
	
	// Scores incomplete regions and all jungles according at end of game adds to players scores, then calls declareWinnerA()
	// Board must pass ArrayList<Region> RegionList into this method.
	void ScoringEndGame(ArrayList<Region> scorethese)
	{ 
		int possiblePoints; // declare here for scope purposes.
		int extra;
		// Step 1: run through list of all region left to be scored on board 
		for(int index = 0; index < scorethese.size(); index++)
		{
			Region temp = scorethese.get(index);
			String temptype = temp.getType();
			// Step 2: detect what type of terrain we are dealing with
			switch(temptype)
			{
				case "Jungle":
					// Special case as we know this is the only stage we score jungles, don't check for 'complete'ness
						// step 4: find score according to rules
						possiblePoints = (3 * temp.getNumberOfNeighboringLakes()) + (5 * temp.getNumberOfNeighboringDens());
						// step 5: detect number of tigers & their owner(s) to award score
						awardScore(temp,possiblePoints);
					break;
				case "Trail":
					// step 3: see if it is complete
					if(!temp.isCompleted())
					{
						// step 4: find score according to rules
						possiblePoints = temp.getNumofTiles();
						extra = temp.getNumberOfAnimals() - temp.getNumberOfCrocodiles();
						if(extra < 0)
							extra = 0;
						possiblePoints += extra;
						// step 5: detect number of tigers & their owner(s) to award score
						awardScore(temp,possiblePoints);
					}
					else {}	// do nothing, it was sent by mistake.
					break;
				case "Lake":
					// step 3: see if it is incomplete
					if(!temp.isCompleted()) 
					{
						/// step 4: find score according to rules
						possiblePoints = temp.getNumofTiles();
						extra = temp.getUniqueAnimals() - temp.getNumberOfCrocodiles();
						if(extra < 0)
							extra = 0;
						possiblePoints *= (1+extra);
						// step 5: detect number of tigers & their owner(s) to award score
						awardScore(temp,possiblePoints);
					}
					else {}	// do nothing, it was complete and previously scored.
					break;
				case "Den":
					// step 3: see if it is incomplete
					if(!temp.isCompleted())//incomplete cloister not surrounded by land tiles in all 8 positions.
					{
						// step 4: find score according to rules
						possiblePoints = 1 + temp.getNumberOfNeighboringTiles();
						// step 5: Den has only 1 tiger maximum, and if present then award points to that player.
						if (temp.getTigers().size()>0)
						temp.getTigers().get(0).getTigerOwner().addScore(possiblePoints);
					}
					else {} // do nothing, it was complete and previously scored.
					break;
				default:
					System.out.println("Error case");
					break;
			}
			// step 6: remove tigers from this region
			temp.removeAllTigers();
		}
		// now that all the scores  have been awarded, we must declare the result of the game.
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
	
	// helper method
	public void awardScore(Region justcompleted, int possiblePoints){
		ArrayList<TigerObject> tigers = justcompleted.getTigers(); // returns an arraylist of tigers
	switch(tigers.size())
	{
	case 0: // do nothing, there are no tigers on it.
		break;
	case 1:	tigers.get(0).getTigerOwner().addScore(possiblePoints);
	// for the sole tiger, add to its player's score as previously calculated
		break;
	default:
	/// more than 1 tiger via clever placement of tiles
		int p1mcount = 0;
		int p2mcount = 0;
		for(int i = 0; i < tigers.size(); i++)
			{
				if(tigers.get(i).getTigerOwner().isFirst())
					p1mcount++;
				else
					p2mcount++;
			}
		if(p1mcount==p2mcount)
		{
			BoardObject.getPlayer(0).addScore(possiblePoints);
			BoardObject.getPlayer(1).addScore(possiblePoints);
		}
		else if(p1mcount>p2mcount)
			BoardObject.getPlayer(0).addScore(possiblePoints);
		else
			BoardObject.getPlayer(1).addScore(possiblePoints);
		break;
	}
	}
	private int declareWinnerA() // only called by ScoringEndGame
	{
		int A = BoardObject.getPlayer(0).getScore();
		int B = BoardObject.getPlayer(1).getScore();
		if (A > B) { return 1; } //player 1 wins
		else if (A < B) { return 2; } //player 2 wins
		else if (A == B){ return 3; } //draw
		return -1; //error condition (impossible to reach unless values can't be loaded)
	}
	
	public int declareWinnerB(int condition, int playerID)
	{ 	//condition is why it was called.  1 for time out, 2 for illegal move, 3 for bad formatting
		if (condition == 1)
		{
			/*Player clock doesn’t seem to exist. 
			 *If board doesn’t receive tile placement in 1 second after prev. tile placed, call this.*/
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
		else if (condition == 2)
		{
			// If board detects illegal tile/tiger placement, call this.
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
		else if (condition == 3)
		{
			// Network interactor would call this.
			//error message for bad format
			if (playerID == 1) 
			{ 
				System.out.println("Player 1 gave wrongly formatted instruction.\n");
				return 2;
			}
			if (playerID == 2) 
			{ 
				System.out.println("Player 2 gave wrongly formatted instruction.\n");
				return 1;
			}
			
		}
		return -1; // error condition
	}
}