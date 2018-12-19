import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CardSideNav extends JPanel {
	private PlayerStats stats;
	private JPanel disp = new JPanel(); //This is the display for the information
	
	public CardSideNav(int width, int height, PlayerStats stats) {
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
		JLabel stage = new JLabel("Phase " + Integer.toString(stats.getStage()));
		stage.setFont(new Font("Arial", Font.BOLD, 22));
		stage.setBorder(new EmptyBorder(20,25,0,0));//top,left,bottom,right
		disp.add(stage);
		
		//The current player's population
		JLabel popCount = new JLabel("Population: " + Integer.toString(stats.getPopulation()));
		popCount.setFont(new Font("Arial", Font.BOLD, 22));
		popCount.setBorder(new EmptyBorder(5,25,0,0));//top,left,bottom,right
		disp.add(popCount);
		
		//The current player's army size
		JLabel armySize = new JLabel("Army Size: " + Integer.toString(stats.getPopulation()));
		armySize.setFont(new Font("Arial", Font.BOLD, 22));
		armySize.setBorder(new EmptyBorder(5,25,0,0));//top,left,bottom,right
		disp.add(armySize);
		
		//The current player's resource count
		JLabel resourceCount = new JLabel("Resource Count: " + Integer.toString(stats.getResource()));
		resourceCount.setFont(new Font("Arial", Font.BOLD, 22));
		resourceCount.setBorder(new EmptyBorder(5,25,0,0));//top,left,bottom,right
		disp.add(resourceCount);
		
		TurnControl tControl = new TurnControl(stats);
		disp.add(tControl);
		
		//Configures the disp jpanel
		disp.setLayout(new BoxLayout(disp, BoxLayout.Y_AXIS));
		disp.setBackground(new Color(255, 230, 255));
		disp.setPreferredSize(new Dimension(width, 350));
		disp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		add(disp);
	}
	
	
}
