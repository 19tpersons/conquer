import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class CardDisplay extends JPanel {
	private CardSet[] sets = new CardSet[2];
	
	public CardDisplay(int width, int height) throws IOException {
		String description = "<html>This is just a test. But it is an amazing test:) I have done something awesome!!</html>";
		
		Card card = new Card("Norman 2", description);
		File icon = new File("images/test.jpg");
		card.setIcon(icon);
		card.setPreferredSize(new Dimension(200,320));
		sets[0] = new CardSet(card);
		sets[0].setPreferredSize(new Dimension(width, height));
		sets[0].setBackground(new Color(0,0,0,0));
		add(sets[0]);
		sets[0].displaySet();
	}
	
	/**
	 * This will display the Solar System cards for each set
	 */
	public void displaySets() {
		for (int i = 0; i < sets.length; i++) { //This loops through each of the sets and prints them.
			add(sets[i]);
			sets[i].displaySet();
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
}
