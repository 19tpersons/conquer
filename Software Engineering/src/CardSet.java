import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;


/**
 * This is the abstraction of a solar system. It will hold all of the cards in a solar system 
 * 	and will also hold the card that is shown to the user to be the solar system.
 * @author DAT Software Engineering
 *
 */
public class CardSet extends JPanel {
	private ArrayList<Card> cards = new ArrayList<Card>();
	private Card solar; //The solar system!
	private Color bgColor;
	private PlayerStats stats;
	
	//Used in junction with the cardDisplay method
	private JPanel currCardDisp = new JPanel(); //This is the panel where all of the set's cards will be displayed upon
	private int currCount = 0; //Used by cardDisplay() as the index to start displaying cards at
	private JPanel rightPanel, leftPanel;
	private String noCardMsg = "No planets have been found!"; //This will be used when there are no cards currently in the set
	
	public CardSet(Card solar, PlayerStats stats) {
		this.bgColor = stats.getColor();
		this.solar = solar;
		this.stats = stats;
		this.solar.getIcon().addMouseListener(new PaintStartingCards());
		
		currCardDisp.setBackground(bgColor);
		
		this.initializeCardViewer();
	}
	
	/**
	 * This method initializes two panes that will be places on either side of the card display. These
	 * buttons are used to control what cards are currently being displayed
	 */
	private void initializeCardViewer() {
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
		//moveRight.add(new JLabel(new ImageIcon(U.getFile("right_arrow.png"))));
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
		currCardDisp.add(leftPanel);
		for (int i = currCount; i < (currCount + 3) && i < cards.size(); i++) { //Will only print three cards
			currCardDisp.add(cards.get(i));
		}
		currCardDisp.add(rightPanel);
		add(currCardDisp);

		revalidate();
		repaint();
	}
	
	
	/**
	 * This method will clear the screen of the cards in the set
	 */
	public void clearCards() {
		remove(currCardDisp);
		for (int i = 0; i < cards.size(); i++) {
			currCardDisp.remove(cards.get(i));
		}
		currCardDisp.remove(leftPanel);
		currCardDisp.remove(rightPanel);
		revalidate();
	}
	
	/**
	 * This method will add a new card to this specific set
	 */
	public void addCard(Card newCard) {
		cards.add(newCard);
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
			/**
			 * 
			 */
			private static final long serialVersionUID = -6513140442002396919L;

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
	
	/**
	 * This will calculate the total population of people in this solar system.
	 * @return the total population of the solar system
	 */
	public int calculatePop() {
		int total = 0;

		if (solar.getPop() > 0) {
			total += solar.getPop();
		}
		
		for (int i = 0; i < cards.size(); i++) {
			total += cards.get(i).getPop();
		}
		return total;
	}
	
	/**
	 * This will calculate the total number of troops that are from this solar system
	 * @return the total population of troops in the solar system.
	 */
	public int calculateTroops() {
		int total = 0;
		
		for (int i = 0; i < cards.size(); i++) {
			total += cards.get(i).getTroopContribution();
		}
		return total;
	}
	
	/**
	 * This method will return a random method from this set.
	 * @return A random card.
	 */
	public Card getRandomCard() {
		Random rand = new Random();
		int cardIdx = rand.nextInt(cards.size());
		return cards.get(cardIdx);
	}
	
	/**
	 * This will loop through each card and add population to the card
	 */
	public void growCardPops() {
		for (int i = 0; i < cards.size(); i++) {
			Card temp = cards.get(i);
			int growth = (int) (temp.getPopChangeRate() * temp.getPop()); //Calculates the growth for this turn
			temp.addPop(growth); //Adds that growth
			
			try {
				temp.defineBack();
			} catch (IOException e) {
				//Something happened when the back of the card was being redefined?
				e.printStackTrace();
			} //Update the back of the card
		}
	}
}