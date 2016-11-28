package tigerFixture;

import entities.Player;
//import fitnesse.slim.Converter;

public class playerFixture {
	private Player tester = new Player(50);
	private int numTiger, score, id;
	boolean isFirst, isAI;

	/*private class playerFixtureConverter implements Converter {
 		public String toString(Player o);
  		Player fromString(String arg);
	}
	
	public playerFixture() {
		fitnesse.slim.converters.ConverterRegistry.addConverter(Player.class, new playerConverter());
	}*/

	public void setNumTiger(int numTiger) {
		tester.setTiger(numTiger);
		this.numTiger = tester.getTiger();
	}

	public void setScore(int score) {
		tester.setScore(score);
		this.score = tester.getScore();
	}

	public void setID(int id) {
		this.id = tester.getID();
	}

	public int getTigerTest() {
		return numTiger;
	}

	public int getScoreTest() {
		return score;
	}

	public int getIDTest() {
		return id;
	}

	public void setFirst(boolean isFirst) {
		tester.setFirst(isFirst);
		this.isFirst = tester.isFirst();
	}

	public void setAI(boolean isAI) {
		tester.setAI(isAI);
		this.isAI = tester.isAI();
	}	

	public boolean isFirst() {
		return isFirst;
	}

	public boolean isAI() {
		return isAI;
	}
}