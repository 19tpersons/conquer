import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.io.IOException;

/**
 * This is the bottom half of the screen. It is what the player interacts with the most as they play the game.
 * @author DAT Software Engineering
 *
 */

public class HUD extends JPanel {
	//private Color playerColor = new Color(0, 0, 204);
	//private Color playerColor = new Color(255, 151, 53);
	private Color playerColor;
	private CardDisplay disp = null;
	private CardSideNav sideNav;
	
	public HUD(int width, int height, PlayerStats stats) {
		//Inital Setup
		setLayout(new BorderLayout(0,0));
		setPreferredSize(new Dimension(width, height));
		this.playerColor = stats.getColor();
		
		//Make the card display. It is the class that holds the cardSets (i.e. the solar systems)
		disp = new CardDisplay(width - 350, height, stats);
		disp.setPreferredSize(new Dimension(width - 275, height));
		disp.setBackground(new Color(0,0,0,0));
		add(BorderLayout.CENTER, disp);

		
		sideNav = new CardSideNav(275, height, stats, disp);
		add(BorderLayout.LINE_END, sideNav);

	}
	

	
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		int height = getHeight();
		int width = getWidth();
		Rectangle2D rect = new Rectangle2D.Double(0, 0, width, height);
		g2.setColor(playerColor);
		g2.fill(rect);
	}
	
	/**
	 * This will return the HUD's CardSideNav
	 * @return the side nav
	 */
	public CardSideNav getSideNav() {
		return this.sideNav;
	}
	
	/**
	 * This will return the HUD's CardDisplay
	 * @return the card display
	 */
	public CardDisplay getCardDisplay() {
		return this.disp;
	}
}
