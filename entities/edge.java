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
	
	public void setType(char type) {
		points.set(0, type);
	}
	
	
	public String toString() {
		return "Top: " + points.get(0) + " Mid: " + points.get(1) + " Bot: " + points.get(2);
	}
}
