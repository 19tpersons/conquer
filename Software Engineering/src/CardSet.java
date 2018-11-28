import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.awt.event.*;

public class CardSet extends JPanel {
	private ArrayList<Card> cards = new ArrayList<Card>();
	private Card solar; //The solar system!
	private JPanel threeCards = new JPanel(); //This is the panel where all of the set's cards will be displayed upon
	private Color bgColor;
	
	
	public CardSet(Card solar, Color bgColor) throws IOException {
		this.bgColor = bgColor;
		this.solar = solar;
		this.solar.addMouseListener(new paintCardsListener());
	}
	
	//This is the listener class for the cardset's icon. When clicked, it will call the method that will display the cards in the set.
	private class paintCardsListener extends MouseAdapter {
		public void mousePressed(MouseEvent evt) {
			displayCards(0);
		}
	}
	
	/**
	 * This method will display all of the cards in this specific set.
	 * Precondition: You will need to clear any other cards from the board before running this method.
	 */
	public void displayCards(int startIndex) {
		this.clearCards();
		add(threeCards);
		for (int i = startIndex; i < (startIndex + 3) && i < cards.size(); i++) { //Will only print three cards
			threeCards.add(cards.get(i));
		}
		revalidate();
	}
	

	
	/**
	 * This method will clear the screen of the cards in the set
	 */
	public void clearCards() {
		for (int i = 0; i < cards.size(); i++) {
			threeCards.remove(cards.get(i));
		}
		remove(threeCards);
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

	/**
	 * This will display the "solar system" card of the set
	 */
	public void displaySetIcon() {
		this.clearCards(); //Will clear the cards that are in this solar system if they are already displayed
		add(solar);
	}
	
	/**
	 * This will remove the set's icon from the board.
	 * @param bgColor the color the filler space rectangle is going to be.
	 */
	public void clearSetIcon() {
		solar.setVisible(false);
		remove(solar);
		fillerCard();
	}
	
	public void fillerCard() {
		JPanel filler = new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(bgColor);
				
				Rectangle2D rect = new Rectangle2D.Double(0,0, 200, 320);
				g2.fill(rect);
				
			}
		};
		filler.setPreferredSize(new Dimension(200, 320));
		add(filler);
		remove(filler);
	}
}