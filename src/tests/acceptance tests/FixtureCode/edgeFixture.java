package tigerFixture;
import entities.edge;

public class edgeFixture {

	//why is there a list points and an array list points?

	private char testType;
	char testTop, testMid, testBot;
	edge testEdge = new edge(testTop, testMid, testBot);

	public char getType() {
		return testType;
	}

	public char getTop() {
		return testTop;
	}

	public char getMid() {
		return testMid;
	}

	public char getBot() {
		return testBot;
	}

	public void setTopTest(char top) {
		testEdge.setTop(top);
		this.testTop = testEdge.getTop();
	}

	public void setMidTest(char mid) {
		testEdge.setMid(mid);
		this.testMid = testEdge.getMid();
	}

	public void setBotTest(char bot) {
		testEdge.setBot(bot);
		this.testBot = testEdge.getBot();
	}

	public void setTypeTest(char type) {
		testEdge.setType(type);
		this.testType = testEdge.getType();
	}
}