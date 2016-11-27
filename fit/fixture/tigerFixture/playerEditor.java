package tigerFixture;

import entities.Player;
import java.beans.PropertyEditorSupport;
import java.beans.PropertyEditor;

public class playerEditor extends PropertyEditorSupport implements PropertyEditor {
	public void setAsText(String text) throws IllegalArgumentException {
		String [] holder = text.split("_");
		setValue(new Player(Integer.parseInt(holder[0])));
	}
	public String getAsText() {
		Player player = (Player) getValue();
		//arg 1 for id, 2 for numTiger, 3 for score.
		//_ separates values in the string
		return String.format("%1$d_%2$d_%3$d", player.getID(), player.getTiger(), player.getScore());
	}
}