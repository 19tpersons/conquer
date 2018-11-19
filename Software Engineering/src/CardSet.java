import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class CardSet extends JPanel {
	private ArrayList<Card> cards = new ArrayList<Card>();
	private Card solar; //The solar system!
	
	public CardSet(Card solar) throws IOException {
		this.solar = solar;

	}
	

	
	/**
	 * This method will display all of the cards in this specific set
	 */
	public void displayCards() {
		for (int i = 0; i < cards.size(); i++) {
			add(cards.get(i));
		}
		revalidate();
	}
	
	/**
	 * This will display the "solar system" card of the set
	 */
	public void displaySet() {
		this.clearCards(); //Will clear the cards that are in this solar system if they are already displayed
		add(solar);
		revalidate();
	}
	
	/**
	 * This method will clear the screen of the cards in the set
	 */
	public void clearCards() {
		for (int i = 0; i < cards.size(); i++) {
			remove(cards.get(i));
		}
		revalidate();
	}
	/**
	 * This method will add a new card to this specific set
	 */
	public void addCard(Card newCard) {
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
