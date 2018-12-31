import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;


/**
 * This is the abstraction of a solar system. It will hold all of the cards in a solar system 
 * 	and will also hold the card that is shown to the user to be the solar system.
 * @author Tyler Persons
 * @date 12.28.18
 *
 */
public class CardSet extends JPanel {
	private ArrayList<Card> cards = new ArrayList<Card>();
	private Card solar; //The solar system!
	private Color bgColor;
	private PlayerStats stats;
	
	//Used in junction with the cardDisplay method
	private JPanel threeCards = new JPanel(); //This is the panel where all of the set's cards will be displayed upon
	private int currCount = 0; //Used by cardDisplay() as the index to start displaying cards at
	private JPanel rightPanel, leftPanel;
	private String noCardMsg; //This will be used when there are no cards currently in the set
	
	public CardSet(Card solar, PlayerStats stats) throws IOException {
		this.bgColor = stats.getColor();
		this.solar = solar;
		this.stats = stats;
		this.solar.getIcon().addMouseListener(new PaintStartingCards());
		
		threeCards.setBackground(bgColor);
		
		//This is the panel that is on the far right of the three cards currently being displayed
		rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(50, 220));
		rightPanel.setBackground(new Color(0,0,0,0));
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		JPanel backBtn = new JPanel(); //This is the back button that will take the user back to the solar system view.
		backBtn.setPreferredSize(new Dimension(50, 40));
		backBtn.setBackground(Color.GREEN);
		backBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				stats.getUser().getPane().getHud().getCardDisplay().refreshSets();
			}
		});
		rightPanel.add(backBtn);
		rightPanel.add(Box.createRigidArea(new Dimension(50,5))); //This is used to give 5 pixels of space between the two buttons
		JPanel moveRight = new JPanel(); //This is the button that will show the next three cards in the set
		moveRight.setPreferredSize(new Dimension(50, 150));
		moveRight.setBackground(Color.RED);
		moveRight.addMouseListener(new CardMoveRight());
		rightPanel.add(moveRight);

		//This is the panel that is on the far left of the three cards currently being displayed
		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(50, 220));
		leftPanel.setBackground(new Color(0,0,0,0));
		leftPanel.add(Box.createRigidArea(new Dimension(50, 45))); //This is used to align the left button with the right button
		JPanel moveLeft = new JPanel(); //This is the button that will show the previous three cards in the set.
		moveLeft.addMouseListener(new CardMoveLeft());
		moveLeft.setPreferredSize(new Dimension(50, 150));
		moveLeft.setBackground(Color.RED);
		leftPanel.add(moveLeft);
		//This is the message that will be displayed when there are no cards in the set
		noCardMsg = "No planets have been found!";

	}
	
	/**
	 * This is the listener class for the cardset's icon. 
	 * When called, it will call the method that will display the cards in the set.
	 *
	 */
	private class PaintStartingCards extends MouseAdapter {
		public void mousePressed(MouseEvent evt) {
			if (cards.size() != 0) { //If there are cards show them
				displayCards();
			} else { //Display a message that says there are not any cards.
				stats.getUser().setModal(new Modal("No Planets", noCardMsg));
			}
		}
	}
	
	/**
	 * Moves the starting index by +3
	 */
	private class CardMoveRight extends MouseAdapter {
		public void mousePressed(MouseEvent evt) {
				currCount += 3;
				displayCards();
		}
	}
	
	/**
	 * Moves the starting index by -3
	 */
	public class CardMoveLeft extends MouseAdapter {
		public void mousePressed(MouseEvent evt) {
				currCount -= 3;
				displayCards();
		}
	}
	
	/**
	 * This method will display all of the cards in this specific set.
	 * Precondition: You will need to clear any other cards from the board before running this method.
	 */
	public void displayCards() {
		if (currCount < 0 && currCount > -3) {
			currCount = 0;
		} else if (currCount < 0) { //Current index too small? Then, lets move to the end of the card set
			currCount = cards.size() - 3;
		} else if (currCount > cards.size()) { //Current Index too big? Then, lets go to the beginning of the card set
			currCount = 0;
		}
		
		this.clearCards();
		threeCards.add(leftPanel);
		for (int i = currCount; i < (currCount + 3) && i < cards.size(); i++) { //Will only print three cards
			threeCards.add(cards.get(i));
		}
		threeCards.add(rightPanel);
		add(threeCards);

		revalidate();
		repaint();
	}
	
	
	/**
	 * This method will clear the screen of the cards in the set
	 */
	public void clearCards() {
		remove(threeCards);
		for (int i = 0; i < cards.size(); i++) {
			threeCards.remove(cards.get(i));
		}
		threeCards.remove(leftPanel);
		threeCards.remove(rightPanel);
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
		remove(solar);
		fillerCard();
	}
	
	/**
	 * This is used by the clearSetIcon method to make the solar card disappear.
	 */
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
	
	/**
	 * This method will return a reference to the solar card of this set
	 * @return the set's solar card
	 */
	public Card getSolar() {
		return this.solar;
	}
	
	/**
	 * This will return the number of cards the set currently holds
	 * @return the number of cards in the set
	 */
	public int getCardNumber() {
		return this.cards.size();
	}
	
	/**
	 * This will return all of the cards in the set
	 * @return all of the cards
	 */
	public ArrayList<Card> getCards() {
		return this.cards;
	}
}