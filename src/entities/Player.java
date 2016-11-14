import java.util.ArrayList;

public class Player {

	protected int numTiger, score, id;
	protected ArrayList<TigerObject> Tigers;
	boolean isFirst, isAI;

	public Player(int id) {
		this.id = id;
		numTiger = 7;
		Tigers = new ArrayList<TigerObject>();
		score = 0;
	}

	public int getTiger() {
		return numTiger;
	}

	public void setTiger(int numTiger) {
		this.numTiger = numTiger;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	public void addScore(int score) {
		this.score += score;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	/**
	 * Returns array list of Tigers the player owns.
	 * @return ArrayList<TigerObject>
	 */
	public ArrayList<TigerObject> getTigers()
	{
		return Tigers;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public boolean isAI() {
		return isAI;
	}

	public void setAI(boolean isAI) {
		this.isAI = isAI;
	}

	public String toString() {
		String isFirst = "This is the second player.";
		String isAI = "This is not an AI player.";

		if (this.isFirst) isFirst = "This is the first player.";
		if (this.isAI) isAI = "This is an AI player.";

		return "Player ID: " + this.getID() + "\nScore: " + this.getScore() + "\nTiger: " + this.getTiger() + "\n" + isFirst + "\n" + isAI;
	}

}
