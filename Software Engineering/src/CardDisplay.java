import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class CardDisplay extends JPanel {
	private CardSet[] sets = new CardSet[2];
	private Color playerColor;
	
	public CardDisplay(int width, int height, Color playerColor) throws IOException {
		this.playerColor = playerColor;
		
		String description = "<html>This is just a test. But it is an amazing test:) I have done something awesome!!</html>";
		
		Card card = new Card("Norman 2", description);
		File icon = new File("images/test.jpg");
		card.setIcon(icon);
		card.setPreferredSize(new Dimension(200,320));
		card.addMouseListener(new clearSetIconListener());

		sets[0] = new CardSet(card, this.playerColor);
		sets[0].setPreferredSize(new Dimension(width, height));
		sets[0].setBackground(playerColor);
		
		card = new Card("Norman 3", description);
		card.setIcon(icon);
		card.setPreferredSize(new Dimension(200,320));
		sets[0].addCard(card);
		add(sets[0]);
		sets[0].displaySetIcon();
		
		card = new Card("Norma 4", description);
		card.setIcon(icon);
		card.setPreferredSize(new Dimension(200,320));
		sets[0].addCard(card);
		add(sets[0]);
		sets[0].displaySetIcon();
		
		card = new Card("Norma 5", description);
		card.setIcon(icon);
		card.setPreferredSize(new Dimension(200,320));
		sets[0].addCard(card);
		add(sets[0]);
		sets[0].displaySetIcon();
		
		card = new Card("Norma 6", description);
		card.setIcon(icon);
		card.setPreferredSize(new Dimension(200,320));
		sets[0].addCard(card);
		add(sets[0]);
		sets[0].displaySetIcon();
		
		card = new Card("Norma 7", description);
		card.setIcon(icon);
		card.setPreferredSize(new Dimension(200,320));
		sets[0].addCard(card);
		add(sets[0]);
		sets[0].displaySetIcon();
		
		card = new Card("Norma 8", description);
		card.setIcon(icon);
		card.setPreferredSize(new Dimension(200,320));
		sets[0].addCard(card);
		add(sets[0]);
		sets[0].displaySetIcon();
		
		card = new Card("Norma 9", description);
		card.setIcon(icon);
		card.setPreferredSize(new Dimension(200,320));
		sets[0].addCard(card);
		add(sets[0]);
		sets[0].displaySetIcon();
	}
	
	//This class is looking to see if any of the CardSet icons have been clicked. If so, it will clear the sets' icon
	private class clearSetIconListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			clearSetIcons();
		}
	}
	
	/**
	 * This will display the Solar System cards for each set
	 */
	public void displaySets() {
		for (int i = 0; i < sets.length; i++) { //This loops through each of the sets and prints them.
			add(sets[i]);
			sets[i].displaySetIcon();
		}
		
	}
	/**
	 * This will add a card to any given set
	 * @param setNum The location in the array of the cardset
	 * @param card The new card
	 */
	public void addCard(int setNum, Card card) {
		sets[setNum].addCard(card);
	}
	
	/**
	 * This will clear each set's icon from the board.
	 */
	public void clearSetIcons() {
		/*for (int i = 0; i < sets.length; i++) {
			sets[i].clearSetIcon();
		}*/
		sets[0].clearSetIcon();
	}
}
