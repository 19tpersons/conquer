import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class CardSet extends JPanel {
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	public CardSet() throws IOException {
		String type = "Planet";
		String description = "<html>This is just a test. But it is an amazing test:) I have done something awesome!!</html>";
		
		Card card = new Card("Norman 2", description);
		File icon = new File("images/test.jpg");
		card.setIcon(icon);
		card.setPreferredSize(new Dimension(200,320));
		add(card);
		
		card = new Card("Norman 3", description);
		icon = new File("images/test.jpg");
		card.setIcon(icon);
		card.setPreferredSize(new Dimension(200,320));
		add(card);
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		int height = getHeight();
		int width = getWidth();
		Rectangle2D rect = new Rectangle2D.Double(0, 0, width, height);
		g2.setColor(new Color(0, 0, 204));
		g2.fill(rect);
	}
	
	/**
	 * This method will display all of the cards in this specific set
	 */
	public void displayCards() {
		
		
	}
	
	/**
	 * This method will add a new card to this specific set
	 */
	public void addCard(Card newCard) {
		/*
		 * 	card = new Card("Norman 3", description);
			icon = new File("images/test.jpg");
			card.setIcon(icon);
			card.setPreferredSize(new Dimension(200,320));
			add(card);
		 */
		cards.add(newCard);
		
	}
	/**
	 * This method will remove a card from this specific set
	 * @param idx The card to remove
	 */
	public void removeCard(int idx) {
		
	}
	
	/**
	 * This is a getter class that will return any card in the set given its idx
	 * @param idx The cards "nickname"
	 * @return The card that was asked for
	 */
	public Card getCard(int idx) {
		return cards.get(idx);
	}
	
	public void changeCard(Card card) {
		
		
	}
}
