import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class CardDisplay extends JPanel {
	//private static CardSet[] sets;
	private ArrayList<CardSet> sets;
	private PlayerStats stats;
	private Color playerColor;
	
	public CardDisplay(int width, int height, PlayerStats stats) throws IOException {
		this.playerColor = stats.getColor();
		this.sets = stats.sets;
		this.stats = stats;
	
		
		String description = "<html>This is just a test. But it is an amazing test:) I have done something awesome!!</html>";
		
		/*File icon = new File("images/norman.jpg");
		Card card = new Card("Norman 2", description, icon, "solar");
		card.setPreferredSize(new Dimension(200,320));
		card.addMouseListener(new clearSetIconListener());

		CardSet temp = new CardSet(card, this.playerColor);
		temp.setPreferredSize(new Dimension(width, height));
		temp.setBackground(playerColor);
		stats.addCardSet(temp);

		temp.displaySetIcon();*/
		
	}
	
	//This class is looking to see if any of the CardSet icons have been clicked. If so, it will clear the sets' icon
	private class clearSetIconListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			clearSetIcons();			
			revalidate();
		}
	}
	
	/**
	 * This is used by the TurnControl class to give each solar system card the ability to display their contents
	 * without any other solar systems staying on the bottom Hud
	 * @param index The location of the card to add the listener.
	 */
	public void addClearIconList(int index) {
		sets.get(index).addMouseListener(new clearSetIconListener());
		sets.get(index).getSolar().getIcon().addMouseListener(new clearSetIconListener());
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
