import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This will allow a player to add troops to their army.
 * @author DAT Software Engineering
 *
 */

public class AddSoldiersModal extends Modal {
	private static int width = 300;
	private static int height = 200;
	private JPanel content;
	
	public AddSoldiersModal(PlayerStats stats, Card card) {
		//This sets up the modal
		super(width, height);
		setBackground(new Color(0,0,0,0));
		setLayout(null);
		
		
		//This is the panel that holds all of the Modal's content.
		content = new JPanel();
		int x = U.width / 2 - width / 2; //Center the modal horz.
		int y = U.height / 2 - height / 2; //Center the modal vert.
		content.setBounds(x, y, width, height);
		content.setBackground(Color.ORANGE);

		//This is the title of the label
		JLabel title = new JLabel();
		title.setText("Add men to army!");
		content.add(title);
		
		//Initializes the labels and buttons that will be needed below
		JLabel costLabel = new JLabel("Cost: "), troopsToContribute = new JLabel("Troops: ");
		JButton submit;

		
		//Sets up the slider for the troop contributions
		JSlider slider = new JSlider(0, card.getPop() - U.planetMinPop, 0); //From 0 to the population minus the minimum population that cannot be in the army.
		slider.setMajorTickSpacing(100);
		slider.setMinorTickSpacing(50);
		slider.setPreferredSize(new Dimension(width - 30, 60));
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(new ChangeListener() {
		      public void stateChanged(ChangeEvent event) {
		    	  int change = slider.getValue();
		    	  int cost = (change / 10) * U.soldierContributionCost; //The cost of adding troops to the army
		    	  
		    	  costLabel.setText("Cost: " + cost + "\n");
		    	  troopsToContribute.setText("Troops: " + change + "\n");
		      }
		});
		content.add(slider);
		
		//This panel contains the bottom half of the content.
		JPanel bottomHalfPanel = new JPanel();
		bottomHalfPanel.setLayout(new BoxLayout(bottomHalfPanel, BoxLayout.Y_AXIS));
		bottomHalfPanel.setBackground(Color.ORANGE);
		
		//This is the label that will show the current cost of adding troops to the army.
		costLabel.setFont(new Font("Arial", Font.BOLD, 18));
		costLabel.setPreferredSize(new Dimension(this.width, 20));
		costLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		bottomHalfPanel.add(costLabel);
		
		//This displays the number of troops that will be contributed to the army
		troopsToContribute.setFont(new Font("Arial", Font.BOLD, 18));
		troopsToContribute.setAlignmentX(Component.CENTER_ALIGNMENT);
		bottomHalfPanel.add(troopsToContribute);
		
		//Adding the cost label and the troopsToContribute label
		content.add(bottomHalfPanel);
		
		//This will submit the value selected on the slider
		submit = new JButton("Submit");
		submit.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				int change = slider.getValue();
				
				//Adds soldiers
				card.addTroops(change); //The amount of troops this planet is currently contributing to the army is now increased.	
				stats.getUser().calculateTroops(); //Add the soldiers to the army.
				
				//Subtracts the cost 
		    	int cost = (change / 10) * U.soldierContributionCost; //The cost of adding troops to the army
		    	stats.removeResources(cost); //Removes resources from player
				
				stats.getUser().getPane().getHud().getSideNav().updateStats(); //Update the statistics panel.
			}
		});
		content.add(submit);
		
		//These are quick checks to make sure the user can add troops in the first place.
		if (stats.getArmySize() > stats.getMaxArmySize()) { //A player has a maximum allowed army size.
			stats.getUser().hideModal();
			String issue = "Your army is either bigger than the maximum allowed size for your population.";
			stats.getUser().setModal(new Modal("Recruitment Issue", issue));
		} else if ((card.getPop() - card.getTroopContribution()) < U.planetMinPop) { //A planet must have a certain amount of population not in the army.
			stats.getUser().hideModal();
			String issue = "You cannot add more people from this planet to the army.";
			stats.getUser().setModal(new Modal("Recruitment Issue", issue));
		} else {
			add(content);
		}
	}
	
	/**
	 * This will return the modal's content panel.
	 * @return content panel.
	 */
	public JPanel getContent() {
		return this.content;
	}
}
