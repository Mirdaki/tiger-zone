
public class Player {

	protected int numMeeple, score, id;
	boolean isFirst, isAI; 
	
	public Player(int id) { 
		this.id = id;
		numMeeple = 8;
		score = 0;
	}
	
	public int getMeeple() {
		return numMeeple;
	}
	
	public void setMeeple(int numMeeple) { 
		this.numMeeple = numMeeple;
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
		
		return "Player ID: " + this.getID() + "\nScore: " + this.getScore() + "\nMeeple: " + this.getMeeple() + "\n" + isFirst + "\n" + isAI;
	}
	
}
