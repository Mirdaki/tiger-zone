import java.util.HashMap;
import java.util.Map;

/*
 * This is the BoardObject that will implement the board itself.
 * Considerations:
 * 		Should it be a static 2D array? We know the max number of tiles is 72,
 * 		so a 72x72 matrix should suffice to maintain any given tile?
 */
public class BoardObject {


	protected Map<String, SquareTile> tiles = new HashMap<String, SquareTile>();
	protected Map<Location, SquareTile> board = new HashMap<Location, SquareTile>();
	Player players[] = new Player[2];
	TigerObject Tiger;

	public BoardObject()
	{
		for (char i = 'A', j = 0; i <= 'X'; i++, j+=4) {

			SquareTile tile0 = new SquareTile(j, new Location(),i);
			SquareTile tile90 = new SquareTile(j, new Location(),i).rotateRight();
			SquareTile tile180 = new SquareTile(j, new Location(),i).rotateRight().rotateRight();
			SquareTile tile270 = new SquareTile(j, new Location(),i).rotateRight().rotateRight().rotateRight();

			tiles.put(i + "0", tile0);
			tiles.put(i + "90", tile90);
			tiles.put(i + "180", tile180);
			tiles.put(i + "270", tile270);
		}

		players[0] = new Player(50);
		players[1] = new Player(51);
	} //end constructor

	public Map<String, SquareTile> getMap()
	{
		return tiles;
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
