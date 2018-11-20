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
	 * @param nickname The card to remove
	 */
	public void removeCard(String nickname) {
		int index = this.findCard(nickname); //Finds the index of the card with the given nickname
		cards.remove(index);
	}
	
	/**
	 * This is a getter method that will return any card in the set given its idx
	 * @param idx The cards "nickname"
	 * @return The card that was asked for
	 */
	public Card getCard(int idx) {
		return cards.get(idx);
	}
	
	/**
	 * This method will replace any given card in that has a specified nickname
	 * @param card The replacement card
	 * @param nickname The nickname of the card to be replaced
	 */
	public void changeCard(Card card, String nickname) {
		int index = this.findCard(nickname);
		cards.set(index, card);
	}
	
	/**
	 * This is a utility method that will return the index of a card that has a given nickname.
	 * @param nickname The card's nickname
	 * @return The index of the card with a specific nickname or -1 if no card found
	 */
	private int findCard(String nickname) {
		for (int i = 0; i < cards.size(); i++) {
			Card card = cards.get(i);
			if (nickname.equals(card.getName())) {
				return i;
			}
		}
		return -1;
	}
}
