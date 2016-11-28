package entities;


import java.util.ArrayList;

public class isPlayerOrAI {

	private BoardObject testGame = new BoardObject();
	private Player[] thePlayers = new Player[2];
	private Player human = new Player(50);
	private Player computer = new Player(51);
	private int humanIndex, computerIndex;

	//fitnesse requires a non complex type be passed into void functions
	//passing a 0 into setArrayOfPlayers causes the board object to get the array of players
	//other values result in nothing happening
	public void setArrayOfPlayers(int doOrDont) {
		if(doOrDont == 0) {
			this.testGame.setPlayers(this.thePlayers);
		}
		else {

		}
	}

	//set isAI to true here
	public void setAI(boolean isAI) {
		this.computer.setAI(isAI);
	}

	//set isAI to false here
	public void setHuman(boolean isAI) {
		this.human.setAI(isAI);
	}

	public void setThePlayer(int index) {
		this.testGame.setPlayer(index, this.human);
		this.humanIndex = index;
	}

	public void setTheComputer(int index) {
		this.testGame.setPlayer(index, this.computer);
		this.computerIndex = index;
	}

	public boolean isAI() {
		if(this.testGame.players[computerIndex].isAI() == true) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isPlayer() {
		if(this.testGame.players[humanIndex].isAI() == false) {
			return true;
		}
		else {
			return false;
		}
	}
}