import java.util.ArrayList;
import java.util.List;

public class edge {
	List<Character> points = new ArrayList<Character>();

	protected char type; 
	
	public edge(char top, char mid, char bot) {
		points.add(top);
		points.add(mid);
		points.add(bot);
		this.type = mid;		
	}
	
	public char getType() {
		return type;
	}
	
	public char getTop() { 
		return points.get(0);
	}
	
	public char getMid() { 
		return points.get(1);
	}
	
	public char getBot() { 
		return points.get(2);
	}
	
	public void setTop(char top) {
		points.set(0, top);
	}
	
	public void setMid(char mid) {
		points.set(1, mid);
	}
	
	public void setBot(char bot) {
		points.set(2, bot);
	}
	
	public void setType(char type) {
		points.set(1, type);
	}
	
	public boolean equals(edge edge) {

		//if equals
		if (this.getTop() == edge.getTop() && this.getMid() == edge.getMid() && this.getBot() == edge.getBot()) { 
			return true;
		}	
		
		//if not equal
		return false;
	}
	
	public String toString() {
		return "Top: " + points.get(0) + " Mid: " + points.get(1) + " Bot: " + points.get(2);
	}
}
