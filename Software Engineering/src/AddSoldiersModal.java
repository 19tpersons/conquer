import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This will allow a player to add troops to their army.
 * @author Tyler Persons
 * @date 12.31.18
 *
 */

public class AddSoldiersModal extends Modal {
	private int modalWidth = 300;
	private int modalHeight = 200;
	
	public AddSoldiersModal(PlayerStats stats, Card card) {
		setBackground(new Color(0,0,0,0));
		setLayout(null);
		
		JPanel content = new JPanel();
		int x = U.width / 2 - modalWidth / 2;
		int y = U.height / 2 - modalWidth / 2;
		content.setBounds(x, y, modalWidth, modalHeight);
		content.setBackground(Color.ORANGE);
		
		JLabel title = new JLabel();
		title.setText("Add men to army!");
		content.add(title);
		
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
		
		JSlider slider = new JSlider(0, card.getPop() - U.planetMinPop, 0); //From 0 to the population minus the minimum population that cannot be in the army.
		slider.setMajorTickSpacing(10);
		slider.setPreferredSize(new Dimension(modalWidth - 30, 60));
		slider.setPaintTicks(true);
		content.add(slider);
		
		JLabel currentContribution = new JLabel("Current Troop Contributions: \n" + card.getTroopContribution());
		currentContribution.setFont(new Font("Arial", Font.BOLD, 18));
		content.add(currentContribution);
		
		JButton submit = new JButton("Submit");
		submit.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				int change = slider.getValue();
				
				card.addTroops(change); //The amount of troops this planet is currently contributing to the army is now increased.	
				stats.addSoliders(change); //Add the soldiers to the army.
				
				stats.getUser().getPane().getHud().getSideNav().updateStats(); //Update the statistics panel.
				stats.getUser().hideModal(); //Once processed remove the modal from the screen.
			}
		});
		content.add(submit);
		
		add(content);
	}
}
