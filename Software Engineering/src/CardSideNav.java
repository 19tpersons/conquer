import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * This is the panel at the far right of the GamePane. It displays the user's statistics
 * @author DAT Software Engineering
 * @date 12.27.18
 *
 */
public class CardSideNav extends JPanel {
	private PlayerStats stats;
	private JPanel disp = new JPanel(); //This is the display for the information
	
	//These are the statistics
	private JLabel stage, popCount, armySize, resourceCount;
	/**
	 * This is the default constructor for the CardSideNav class. It displays a player's statistics and allows them to continue on with their turn
	 * @param width The is the width of the panel
	 * @param height this is the height of the panel
	 * @param stats this is a reference to a player's stats
	 * @param cardDisp this is a reference to the player's card display. It is needed for the turncontrol
	 */
	public CardSideNav(int width, int height, PlayerStats stats, CardDisplay cardDisp) {
		//Sets up the class
		FlowLayout tmp = new FlowLayout(); //This is used to fix the horz. and vert. margins
		tmp.setHgap(0);
		tmp.setVgap(0);
		this.setLayout(tmp);
		this.setBackground(new Color(0,0,0,0)); //Background clear
		this.setPreferredSize(new Dimension(width, height));
		this.setBorder(BorderFactory.createEmptyBorder(height - 350, 0, 0, 0));
		this.stats = stats;

		//The current stage of the player's turn
		stage = new JLabel("Turn Phase: " + stats.getStage(true));
		stage.setFont(new Font("Arial", Font.BOLD, 22));
		stage.setBorder(new EmptyBorder(20,0,0,0));//top,left,bottom,right
		disp.add(stage);
		
		//The current player's population
		popCount = new JLabel("Population: " + Integer.toString(stats.getPopulation()) + "M");
		popCount.setFont(new Font("Arial", Font.BOLD, 22));
		popCount.setBorder(new EmptyBorder(5,0,0,0));//top,left,bottom,right
		disp.add(popCount);
		
		//The current player's army size
		armySize = new JLabel("Army Size: " + Integer.toString(stats.getPopulation()));
		armySize.setFont(new Font("Arial", Font.BOLD, 22));
		armySize.setBorder(new EmptyBorder(5,0,0,0));//top,left,bottom,right
		disp.add(armySize);
		
		//The current player's resource count
		resourceCount = new JLabel("Resource Count: " + Integer.toString(stats.getResource()));
		resourceCount.setFont(new Font("Arial", Font.BOLD, 22));
		resourceCount.setBorder(new EmptyBorder(5,0,0,0));//top,left,bottom,right
		disp.add(resourceCount);
		
		TurnControl tControl = new TurnControl(stats, cardDisp, this);
		tControl.setBackground(new Color(0,0,0,0));
		disp.add(tControl);
	
		
		//Configures the disp jpanel
		disp.setLayout(new BoxLayout(disp, BoxLayout.Y_AXIS));
		disp.setBackground(new Color(255, 230, 255));
		disp.setPreferredSize(new Dimension(width, 350));
		disp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		add(disp);
	}
	
	public void updateStats() {
		stage.setText("Turn Phase: " + stats.getStage(true)); 
		popCount.setText("Population: " + stats.getPopulation() + "M"); 
		armySize.setText("Army Size: " + stats.getArmySize()); 
		resourceCount.setText("Resources: " + stats.getResource());
	}
	
}
