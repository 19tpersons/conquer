import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

/**
 * This class is used to display the card sets a player has.
 * @author Tyler Persons
 * @date 12.27.18
 *
 */
public class CardDisplay extends JPanel {
	private ArrayList<CardSet> sets; //The card sets
	private PlayerStats stats;
	private Color playerColor;
	
	public CardDisplay(int width, int height, PlayerStats stats) throws IOException {
		this.playerColor = stats.getColor();
		this.sets = stats.sets;
		this.stats = stats;

		
	}
	
	/**
	 * This is used by the TurnControl class to give each solar system card the ability to display their contents
	 * without any other solar systems staying on the bottom Hud
	 * @param index The location of the card to add the listener.
	 */
	public void addClearIconList(int index) {
		sets.get(index).getSolar().getIcon().addMouseListener(new MouseAdapter() { //This gets the solar system card that each set holds and then, adds a mouse listner to it.
			public void mousePressed(MouseEvent evt) {
				if (sets.get(index).getCardNumber() != 0) { //If there are no cards in the set, do not clear the solar system icons.
					clearSetIcons();
					revalidate();
				}
			}
		});
	}
	
	/**
	 * This will display the Solar System cards for each set
	 */
	public void displaySets() {
		for (int i = 0; i < sets.size(); i++) { //This loops through each of the sets and prints them.
			sets.get(i).displaySetIcon();
			add(sets.get(i));
		}
	}

	/**
	 * This will add a card to any given set
	 * @param setNum The location in the array of the cardset
	 * @param card The new card
	 */
	public void addCard(int setNum, Card card) {
		sets.get(setNum).addCard(card);
	}
	
	/**
	 * This method will add a new set to the CardDisplay
	 */
	public void refreshSets() {
		//If there are any sets currently being displayed clear them
		clearSetIcons();
		
		//Now display the sets with any new additions
		displaySets();

		revalidate();
		repaint();
	}
	
	/**
	 * This will clear each set's icon from the board.
	 */
	public void clearSetIcons() {
		for (int i = 0; i < sets.size(); i++) {
			sets.get(i).clearSetIcon();
		}
	}
	
	
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		int height = getHeight();
		int width = getWidth();
		Rectangle2D rect = new Rectangle2D.Double(0, 0, width, height);
		g2.setColor(playerColor);
		g2.fill(rect);
	}
}
