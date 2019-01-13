import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class is used to initiate fighting between two players. It doesn't decide the outcome, but it does handle the results.
 * @author DAT Software Engineering
 *
 */

public class FightModal extends Modal {
	private int width = 400;
	private int height = 350;
	private int offenseDead, defenseDead, offenseSurvivors, defenseSurvivors;
	private JPanel content;
	private Color modalColor = new Color(230, 0, 0);
	
	public FightModal(PlayerStats enemyStats, PlayerStats stats, Card card) {
		//Sets up jpanel
		super(400, 350);
		setBackground(new Color(0,0,0,0));
		setLayout(null);
		
		content = new JPanel();
		int x = U.width / 2 - width / 2; //Center the modal horz.
		int y = U.height / 2 - height / 2; //Center the modal vert.
		content.setBounds(x, y, width, height);
		content.setBackground(modalColor);
		content.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
	
		this.showFightSetup(enemyStats, stats, card);
		
		add(content);
	}
	
	public void showFightSetup(PlayerStats enemyStats, PlayerStats stats, Card card) {
		
			//This is the title of the label
			JLabel title = new JLabel();
			title.setText("Commit troops to fight!");
			title.setFont(new Font("Arial", Font.BOLD, 18));
			content.add(title);
		
		
			//Initialize label
			JLabel troopsToCommit = new JLabel();
			troopsToCommit.setFont(new Font("Arial", Font.BOLD, 18));
			troopsToCommit.setAlignmentX(Component.CENTER_ALIGNMENT);
		
			JSlider slider = new JSlider(0, enemyStats.getArmySize(), 0); //From 0 to the population minus the minimum population that cannot be in the army.
			slider.setPreferredSize(new Dimension(width - 30, 90));
			slider.setMajorTickSpacing(100);
			slider.setMinorTickSpacing(50);
			slider.setPaintLabels(true);
			slider.setPaintTicks(true);
			slider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent event) {
					int change = slider.getValue();
					troopsToCommit.setText("Troops: " + change + "\n");
				}
			});
			content.add(slider);
		
			//This is the amount of troops the enemy has.
			JLabel enemyTroops = new JLabel("Enemy Troops: " + card.getTroopContribution());
			enemyTroops.setFont(new Font("Arial", Font.BOLD, 18));
			enemyTroops.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
			JButton fight = new JButton("FIGHT!");
			fight.setFont(new Font("Arial", Font.BOLD, 18));
			fight.setAlignmentX(Component.CENTER_ALIGNMENT);
			fight.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent evt) {
					int enemyTroops = slider.getValue();
					Battle battle = new Battle(card.getTroopContribution(), card.getDefensiveBonus(), enemyTroops);
					
					//Subtract dead troops from the card's troop contributions
					defenseSurvivors = battle.getDefenseSurvivors();
					defenseDead = card.getTroopContribution() - defenseSurvivors;
					card.subTroops(defenseDead);
				
					//This is the number of dead enemy troops
					offenseSurvivors = battle.getOffenseSurvivors();
					offenseDead = enemyTroops - offenseSurvivors;
					enemyStats.subTroops(offenseDead);
				
					//If the enemy wins we give them an award.
					if (defenseSurvivors == 0) {
						int cardResources = stats.getResource();
						int removedResources = (int) (cardResources * 0.05);
						card.setResources(cardResources - removedResources);
						enemyStats.addResources(removedResources);
					}
				
					//Update stats
					stats.getUser().updateSideNav();
					enemyStats.getUser().updateSideNav();
				
					hideFightSetup();
				
					//Shows the results
					showResults();
				}
			});
		
		
			//This will holder everything under the slider
			JPanel holder = new JPanel();
			holder.setLayout(new BoxLayout(holder, BoxLayout.Y_AXIS));
			holder.setPreferredSize(new Dimension(width - 30, 200));
			holder.setBackground(modalColor.brighter());
			holder.add(enemyTroops);
			holder.add(troopsToCommit);
			holder.add(Box.createRigidArea(new Dimension(width, 15)));
			holder.add(fight);
			content.add(holder);
	}
	
	/**
	 * This will remove the fight setup content from the panel
	 */
	public void hideFightSetup() {
		content.removeAll();
		revalidate();
		repaint();
	}
	
	
	public void showResults() {
		repaint();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("Results");
		title.setFont(new Font("Arial", Font.BOLD, 18));
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		content.add(title);
		
		JLabel result = new JLabel();
		result.setFont(new Font("Arial", Font.BOLD, 58));
		result.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JTextArea stats = new JTextArea();
		stats.setFont(new Font("Arial", Font.BOLD, 26));
		stats.setEditable(false);
		stats.setWrapStyleWord(true);
		stats.setLineWrap(true);
		stats.setBackground(modalColor);
		stats.setMargin(new Insets(0,20,0,0));
		
		if (defenseSurvivors == 0) { //Offense won
			content.setBackground(new Color(0, 204, 0)); //Change the backdrop to green
			
			result.setText("You Won!");
			
			stats.append("\n\nTroops Alive: " + offenseSurvivors);
			
			stats.append("\nEnemy Dead: " + defenseDead);
			
			stats.append("\nResources Gained: ");
			
			stats.append("\nPopulation Gained: ");
			
			stats.setBackground(new Color(0, 204, 0));
		} else {
			result.setText("You Lost!");
			
			stats.append("\n\nTroops Alive: " + offenseSurvivors);
		
			stats.append("\nEnemy Dead: " + defenseDead);

		}
		content.add(result);
		content.add(stats);
		
		//content.setBackground(new Color());
	}
	
	/**
	 * This will return the modal's content panel.
	 * @return content panel.
	 */
	public JPanel getContent() {
		return this.content;
	}
}
