import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;

import javax.swing.*;
/*
 * This class controls where cards will go. It also keeps track of where the cards are so that it can
 * update their respective populations everytime a fight/action occurs in the game.
 */

public class TurnControl extends JPanel{
	private PlayerStats stats;
	private CardSet[] sets;
	private Random rand = new Random();
	public static JButton draw = new JButton("Draw");

	public TurnControl(PlayerStats stats) {
		this.stats = stats;
		sets = stats.getCardSets();
		
		draw.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				getCard();
			}
		});
		add(draw);
	}
	
	
	/**
	 * This will draw a new card from the stack, and add it to the right set.
	 */
	private void getCard() {
		int cardTypeInt; //This will be used to figure out what type of card to draw for the user. 0 => planet, 1 => action, 2 => solar
		if (sets.length < U.cardSetLimit) {
			cardTypeInt = 3;
		} else {
			cardTypeInt = 2;
		}
		
		int type;
		if (sets.length == 0) { //If the user doesn't have any solar systems, then give them one.
			type = 2;
		} else {
			type = rand.nextInt(cardTypeInt);
		}
		switch(type) {
			case 0:
				this.addToSet(CardDB.getPlanetCard()); //if zero get a planet card.
				break;
			case 1:
				//this.performAction(CardDB.getAction());
				break;
			case 2:
				this.addSet(CardDB.getSolarCard());
		}
		sets[0].addCard(CardDB.getPlanetCard());
	}
	
	/**
	 * This method will add a card to a random set that the player owns
	 * @param card the new card to insert
	 */
	private void addToSet(Card card) {
		//If any of the sets do not have any cards place the planet in that solar system.
		for (int i = 0; i < sets.length; i++) {
			if (sets[i].getSetSize() == 0) {
				sets[i].addCard(card);
				return;
			}
		}
		
		//If all of the solar systems have at least one planet then go ahead and pick a random one to put the new card in.
		int setSelect = rand.nextInt(sets.length);
		sets[setSelect].addCard(card);
	}
	
	 private void addSet(Card newSet) {
		 try {
			stats.addCardSet(new CardSet(newSet, stats.getColor()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 this.sets = stats.getCardSets();
	 }
}
