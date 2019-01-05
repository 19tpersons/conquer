import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

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
		
		
		//These are quick checks to make sure the user can add troops in the first place.
		if (stats.getArmySize() > stats.getMaxArmySize()) { //A player has a maximum allowed army size.
			stats.getUser().hideModal();
			String issue = "Your army is either bigger than the maximum allowed size for your population.";
			stats.getUser().setModal(new Modal("Recruitment Issue", issue));
			return;
		} else if ((card.getPop() - card.getTroopContribution()) < U.planetMinPop) { //A planet must have a certain amount of population not in the army.
			String issue = "You cannot add more people from this planet to the army.";
			stats.getUser().setModal(new Modal("Recruitment Issue", issue));
			return;
		}
		
		content = new JPanel();
		int x = U.width / 2 - width / 2; //Center the modal horz.
		int y = U.height / 2 - height / 2; //Center the modal vert.
		content.setBounds(x, y, width, height);
		content.setBackground(Color.ORANGE);
		
		//This is the title of the label
		JLabel title = new JLabel();
		title.setText("Add men to army!");
		content.add(title);
		
		
		//This is the label that will show the current cost of adding troops to the army.
		JLabel costLabel = new JLabel("Cost will be 0");
		
		//Sets up the slider for the troop contributions
		JSlider slider = new JSlider(0, card.getPop() - U.planetMinPop, 0); //From 0 to the population minus the minimum population that cannot be in the army.
		slider.setMajorTickSpacing(10);
		slider.setPreferredSize(new Dimension(width - 30, 60));
		slider.setPaintTicks(true);
		slider.addChangeListener(new ChangeListener() {
		      public void stateChanged(ChangeEvent event) {
		    	  int change = slider.getValue();
		    	  int cost = (change / 10) * U.soldierContributionCost; //The cost of adding troops to the army
		    	  
		    	  costLabel.setText("Cost will be " + cost);
		      }
		});
		content.add(slider);
		
		//This displays the current contribution of troops for this planet
		JLabel currentContribution = new JLabel("Current Troop Contributions: \n" + card.getTroopContribution());
		currentContribution.setFont(new Font("Arial", Font.BOLD, 18));
		content.add(currentContribution);
		
		//Adding the cost label
		content.add(costLabel);
		
		//This will submit the value selected on the slider
		JButton submit = new JButton("Submit");
		submit.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				int change = slider.getValue();
				
				//Adds soldiers
				card.addTroops(change); //The amount of troops this planet is currently contributing to the army is now increased.	
				stats.addSoliders(change); //Add the soldiers to the army.
				
				//Subtracts the cost 
		    	int cost = (change / 10) * U.soldierContributionCost; //The cost of adding troops to the army
		    	stats.removeResources(cost); //Removes resources from player
				
				stats.getUser().getPane().getHud().getSideNav().updateStats(); //Update the statistics panel.
				stats.getUser().hideModal(); //Once processed remove the modal from the screen.
			}
		});
		content.add(submit);
		
		add(content);
	}
	
	/**
	 * This will return the modal's content panel.
	 * @return content panel.
	 */
	public JPanel getContent() {
		return this.content;
	}
}
