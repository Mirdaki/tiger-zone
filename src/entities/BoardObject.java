import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

/*
 * This is the BoardObject that will implement the board itself.
 * Considerations:
 * 		Should it be a static 2D array? We know the max number of tiles is 72,
 * 		so a 72x72 matrix should suffice to maintain any given tile?
 */
public class BoardObject {

	/*
		initialize dictionary of possible tiles based on type
		initialize board based on tiles and location
		initialize players
	*/

	protected Map<Character, ArrayList<SquareTile>> tileStack = new HashMap<Character, ArrayList<SquareTile>>();
	protected Map<Location, SquareTile> board = new HashMap<Location, SquareTile>();

	Player players[] = new Player[2];
	TigerObject Tiger;

	public BoardObject() {

		TileStack initialize = new TileStack();
		tileStack = initialize.getTiles();

		players[0] = new Player(50);
		players[1] = new Player(51);
	} //end constructor

	public Map<Character, ArrayList<SquareTile>> getMap()
	{
		return tileStack;
	}

	public SquareTile getTile(char type, int orientation)
    {
        //get all tiles of type
		ArrayList<SquareTile> wtf = tileStack.get(type);

		//get the first tile that matches 'G' and all its orientations
        if (wtf.size() == 0) return null;

    	ArrayList<SquareTile> tile = wtf.get(0).getReps();
        if (tile.size() == 0) return null;

        SquareTile value = tile.get(orientation);

        return value;
    }

	public Player getPlayer(int index) {
		return players[index];
	}

	public void setPlayer(int index, Player player) {
		players[index].setID(player.getID());
		players[index].setFirst(player.isFirst());
		players[index].setAI(player.isAI());
		players[index].setTiger(player.getTiger());
		players[index].setScore(player.getScore());
	}

}
