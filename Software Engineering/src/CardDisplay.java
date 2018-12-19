import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class CardDisplay extends JPanel {
	//private static CardSet[] sets;
	private static ArrayList<CardSet> sets;
	private Color playerColor;
	
	public CardDisplay(int width, int height, PlayerStats stats) throws IOException {
		this.playerColor = stats.getColor();
		//this.sets = stats.getCardSets();
		this.sets = stats.sets;

		
		String description = "<html>This is just a test. But it is an amazing test:) I have done something awesome!!</html>";
		
		File icon = new File("images/norman.jpg");
		Card card = new Card("Norman 2", description, icon, "solar");
		card.setPreferredSize(new Dimension(200,320));
		card.addMouseListener(new clearSetIconListener());

		CardSet temp = new CardSet(card, this.playerColor);
		temp.setPreferredSize(new Dimension(width, height));
		temp.setBackground(playerColor);
		stats.addCardSet(temp);

		//this.sets = stats.getCardSets();
		//add(sets[0]);
		temp.displaySetIcon();
		
		TurnControl.draw.addMouseListener(new addSetsListener());
	}
	
	//This class is looking to see if any of the CardSet icons have been clicked. If so, it will clear the sets' icon
	private class clearSetIconListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			clearSetIcons();
		}
	}
	
	private class addSetsListener extends MouseAdapter {
		public void mousePressed(MouseEvent evt) {
			//clearSetIcons();
			for (int i = 0; i < sets.size(); i++) {
				add(sets.get(i));
			}
			revalidate();
			repaint();
		}
	}
	
	/**
	 * This will display the Solar System cards for each set
	 */
	public void displaySets() {
		for (int i = 0; i < sets.size(); i++) { //This loops through each of the sets and prints them.
			add(sets.get(i));
			sets.get(i).displaySetIcon();
		}
		
	}
	/**
	 * This will add a card to any given set
	 * @param setNum The location in the array of the cardset
	 * @param card The new card
	 */
	public void addCard(int setNum, Card card) {
		//sets[setNum].addCard(card);
		sets.get(setNum).addCard(card);
	}
	
	/**
	 * This method will add a new set to the CardDisplay
	 * @param sets The new sets from a PlayerStats object
	 */
	public static void addSet(CardSet[] sets) {
		//CardDisplay.sets = sets;
	}
	
	/**
	 * This will clear each set's icon from the board.
	 */
	public void clearSetIcons() {
		for (int i = 0; i < sets.size(); i++) {
			sets.get(i).clearSetIcon();
		}
		//sets[0].clearSetIcon();
	}
}
