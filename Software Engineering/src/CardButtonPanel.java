import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.*;

/**
 * This is the series of buttons that appear when a user hovers over the icon of a planet card.
 * @author Tyler Persons
 * @date 12.28.18
 */
public class CardButtonPanel extends JPanel {
	private JPanel generalPanel = new JPanel(), fightPhasePanel = new JPanel();
	private JButton soldiersBtn, fightBtn;

	public CardButtonPanel(int width, int height, PlayerStats stats, Card card) {
		this.setBackground(new Color(0,0,0,0));
		
		//This sets up the panel
		generalPanel.setBackground(new Color(255,255,255,50));
		generalPanel.setPreferredSize(new Dimension(width, height));
		
		
		//This will allow users' to add soldiers to their armies
		soldiersBtn = new JButton("Add Soldiers");
		soldiersBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				Modal modal = new AddSoldiersModal(stats, card);
				stats.getUser().setModal(modal);
			}
		});
		generalPanel.add(soldiersBtn);
		
		
		//This will allow users' to modify a planet
		
		
		
		
		//This sets up the panel
		fightPhasePanel.setBackground(new Color(255,255,255,50));
		fightPhasePanel.setPreferredSize(new Dimension(width, height));
		
		//This will allow users' to fight each other.
		fightBtn = new JButton("Fight");
		fightBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				//FightModal modal = new FightModal(stats, card);
				//stats.getUser().setModal(modal);
			}
		});
		fightPhasePanel.add(fightBtn);
	

	}
	
	/**
	 * If the turn is currently in the fight phase, then we need to show those specific buttons
	 */
	public void showFightPhase() {
		remove(generalPanel);
		add(fightPhasePanel);
		revalidate();
		repaint();
	} 
	
	/**
	 * This will show the buttons that the user can click at any point during their turn.
	 */
	public void showGeneral() {
		remove(fightPhasePanel);
		add(generalPanel);
		revalidate();
		repaint();
	}
}
