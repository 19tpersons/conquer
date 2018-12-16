import java.awt.*;
import javax.swing.*;

public class ActionCard extends JPanel {
	private String sub_type;
	private int popChange; //This is the positive or negative number of population the action will cause
	private int actionPercentChange; //This is the percent change of amount of affect any action will have.
	private InfoModal modal;
	
	public ActionCard(String sub_type, int popChange, int actionPercentChange) {
		this.sub_type = sub_type;
		this.popChange = popChange;
		this.actionPercentChange = actionPercentChange;
	}
	
	public void makeModal(String title, String description, String image_location) {
		this.modal = new InfoModal(title, description, image_location);
	}
}
