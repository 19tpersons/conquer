import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This is the abstraction of all of the actions any user can take. It holds their individual statistics and game interface.
 * @author DAT Software Engineering
 *
 */
public class User extends JPanel {
	private int width, height;
	private PlayerStats stats;
	private GamePane pane;
	private Modal modal;
	private JLayeredPane layered = new JLayeredPane();
	private RootGameControl root;

	public User(int width, int height, Color playerColor, RootGameControl root) {
		this.width = width;
		this.height = height;
		this.root = root;
		
		layered.setPreferredSize(new Dimension(width, height));
		layered.setLayout(null);
		layered.setBackground(Color.blue);
		
		this.stats = new PlayerStats(playerColor);
		stats.setUser(this);
		this.pane = new GamePane(stats);
		pane.setBounds(0,0, width, height);
		
		add(layered);
		
	
	}
	
	

	/**
	 * If there is a modal that the game wants to display they just use this.
	 * @param modal
	 */
	public void setModal(Modal modal) {
		this.modal = modal;
		modal.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				Point p = new Point(evt.getLocationOnScreen());
				Modal modal = (Modal) evt.getComponent();
				SwingUtilities.convertPointFromScreen(p, modal.getContent());
				if (!modal.getContent().contains(p)) {
					layered.remove(modal);
					revalidate();
					repaint();
					getPane().getHud().getCardDisplay().refreshSets();
				}			}
		});
		modal.setBounds(0,0, width, height);
		layered.add(modal, 0);
		repaint();
	}
	
	/**
	 * This clears the modal from the screen.
	 */
	public void hideModal() {
		layered.remove(this.modal);
		revalidate();
		repaint();
	}
	
	/**
	 * This will show the user's game pane.
	 */
	public void showPane() {
		layered.add(pane, 1);
		revalidate();
		repaint();
	}
	
	
	
	/**
	 * This will return this user's game panel
	 * @return the game panel
	 */
	public GamePane getPane() {
		return this.pane;
	}
	
	/**
	 * This will start a player's turn.
	 */
	public void startTurn() {
		//Several things may have happened between the last time this player had a turn, so we need to update some things.
		this.getPane().getBackDrop().refreshSmartStars(); //The map may have changed
		this.growPop(); //Each turn the population for each planet will grow.
		this.calculatePop(); //There may have been a war
		this.calculateTroops();
		stats.nextStage(); //Right now the game thinks it's not the player's turn.
		this.updateSideNav(); //The stats need to be updated to reflect these changes.
	}
	
	/**
	 * This will finish the player's turn.
	 */
	public void endTurn() {
		root.nextTurn();
	}
	
	/**
	 * This will calculate the player's total population.
	 * @return the players population.
	 */
	public void calculatePop() {
		stats.setPopulation(this.getPane().getHud().getCardDisplay().calculatePop());
	}
	
	/**
	 * This will calculate the player's total troop population.
	 * @return the player's total troop population.
	 */
	public void calculateTroops() {
		stats.setArmySize(this.getPane().getHud().getCardDisplay().calculateTroops());
	}
	
	/**
	 * This will return a player's stats class
	 * @return the stats
	 */
	public PlayerStats getStats() {
		return this.stats;
	}
	
	/**
	 * This will update the CardSideNav
	 */
	public void updateSideNav() {
		this.getPane().getHud().getSideNav().updateStats();
	}
	
	/**
	 * This will return a random card from the player's deck
	 * @return a random card.
	 */
	public Card getRandomCard() {
		return this.getPane().getHud().getCardDisplay().getRandomCard();
	}
	
	/**
	 * Every turn the population for each planet will grow.
	 */
	public void growPop() {
		this.getPane().getHud().getCardDisplay().growPop();
	}
}
