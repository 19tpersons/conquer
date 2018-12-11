import javax.swing.*;
import java.awt.*;

public class User extends JPanel {
	private PlayerStats stats;
	private GamePane pane;

	public User(Color playerColor) {
		this.stats = new PlayerStats(playerColor);
		this.pane = new GamePane(stats);
	}
	
	/**
	 * This is the first stage of a players turn. The player gets a new card that will 
	 *  affect his/her population size, or known planets/solar systems.
	 */
	public void drawStage() {
		//Show draw button
		
		//Once clicked, Draw card!
		
		//How does it affect him? show (description and location) or show (description) and delete/add (population).
		
		//End
	}
	
	public void buildStage() {}
	
	public void fightStage() {}
	
	
	public void showPane() {
		add(pane);
		revalidate();
		repaint();
	}

}
