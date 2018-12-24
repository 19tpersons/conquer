import java.awt.*;
import java.util.HashMap;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
/*
 * This class controls where cards will go. It also keeps track of where the cards are so that it can
 * update their respective populations everytime a fight/action occurs in the game.
 */

public class TurnControl extends JPanel{
	private PlayerStats stats;
	private ArrayList<CardSet> sets;
	private Random rand = new Random();
	private CardDisplay disp;
	private CardSideNav nav;
	private User user;
	
	public TurnControl(PlayerStats stats, CardDisplay disp, CardSideNav nav) {
		this.stats = stats;
		sets = stats.sets;
		this.disp = disp;
		this.nav = nav;
		this.user = stats.getUser();
		
		JButton draw = new JButton("Draw");
		draw.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				getCard();
				//remove(draw); //This will get rid of the button so that the next phase can begin.
				revalidate();
				repaint();
				
			}
		});
		add(draw);
	}
	
	
	/**
	 * This will draw a new card from the stack, and add it to the right set.
	 */
	private void getCard() {
		int cardTypeInt; //This will be used to figure out what type of card to draw for the user. 0 => planet, 1 => action, 2 => solar
		if (sets.size() < U.cardSetLimit) {
			cardTypeInt = 3;
		} else {
			cardTypeInt = 2;
		}
		
		int type;
		if (sets.size() == 0) { //If the user doesn't have any solar systems, then give them one.
			type = 2;
		} else if (sets.get(0).getCardNumber() == 0) { //If the user doesn't have any planets, give them one.
			type = 0;
		} else {
			type = rand.nextInt(cardTypeInt);
		}

		switch(type) {
			case 0:
				this.addToSet(CardDB.getPlanetCard()); //if zero get a planet card.
				break;
			case 1:
				this.performAction(CardDB.getAction());
				break;
			case 2:
				this.addSet(CardDB.getSolarCard());
				break;
		}
	}
	
	/**
	 * This method will add a card to a random set that the player owns
	 * @param card the new card to insert
	 */
	private void addToSet(Card card) {
		//Show info modal
		this.user.setModal(card.getModal());
		
		//Updates the players total population
		stats.addPop(card.getPop());
		this.nav.updateStats();
		
		//If any of the sets do not have any cards place the planet in that solar system.
		for (int i = 0; i < sets.size(); i++) {
			if (sets.get(i).getCardNumber() == 0) {
				sets.get(i).addCard(card);
				return;
			}
		}
		
		//If all of the solar systems have at least one planet then go ahead and pick a random one to put the new card in.
		int setSelect = rand.nextInt(sets.size());
		sets.get(setSelect).addCard(card);
	}
	
	/**
	 * This will give the player a new solar system.
	 * @param newSet The solar system card
	 */
	 private void addSet(Card newSet) {
		 //Show info modal
		 this.user.setModal(newSet.getModal());
		 
		 //Updates the player's total population
		 stats.addPop(newSet.getPop());
		 this.nav.updateStats();
		 
		try {
			CardSet tmp = new CardSet(newSet, stats.getColor());
			tmp.setBackground(stats.getColor());
			
			stats.addCardSet(tmp);
			disp.addClearIconList(stats.sets.size() - 1); //This will add the listener to the newest solar system card
			disp.refreshSets();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		 this.sets = stats.sets;
	 }
	 
	 /**
	  * This method will perform a specific action
	  * @param action The action card from the deck
	  */
	 private void performAction(ActionCard action) {
		 //Show info modal
		 this.user.setModal(action.getModal());
		 
		 //Choose a system
		 //This section is used to catalog the probabilities of the action happening to any given CardSet
		 ArrayList<Card> cardSetTmp = new ArrayList<Card>(); //This is sent into the getSubTypeCard method
		 HashMap<Card, CardSet> map = new HashMap<Card, CardSet>(); //This is used to map the returned value
		 for (int i = 0; i < sets.size(); i++) {
			 if (sets.get(i).getCardNumber() != 0) {
				 Card solar = sets.get(i).getSolar();
				 cardSetTmp.add(solar);
				 map.put(solar, sets.get(i));
			 }
		 }
		 
		 if (cardSetTmp.size() == 0) {
			 //TODO:
		 }
		 
		 Card index = this.getSubTypeCard(cardSetTmp, action.getSubType());
		 CardSet cardSet = map.get(index);
		 
		 
		 //Choose a card from the system
		 Card card = this.getSubTypeCard(cardSet.getCards(), action.getSubType());
		 
		 //Apply the action
		 int change = action.getChange();
		 if (change > 0) {
			 card.addPop(change);
			 stats.addPop(change);
		 } else {
			 change = change * -1;
			 card.removePop(change);
			 stats.removePop(change);
		 }
		 this.nav.updateStats();

	}
	 
	/**
	 * This is used by the system to get a random card with each card having a weighted probability
	 * @param cards This is an arraylist of the type Card
	 * @param sub_type This is the sub_type to compare each card to. It is used to check if the card has an added probability
	 * @return The chosen card
	 */
	private Card getSubTypeCard(ArrayList<Card> cards, String sub_type) {
		 double[] cardProbs = new double[cards.size()]; //This will act as an account of each cardSet's probabilities
		 double sum = 0;
		 for (int i = 0; i < cards.size(); i++) {
			 //The sets sub_type
			 Card card = cards.get(i);
			 String subType = card.getSubType();
			 if (subType.equals(sub_type)) {
				 cardProbs[i] = 1 + card.getSubTypeRate();
			 } else {
				 cardProbs[i] = 1; //Probability defaults to 1
			 }
			 
			 sum += cardProbs[i];
		}
		 
		double choiceDouble = rand.nextDouble() * sum;
		for (int i = 0; i < cardProbs.length; i++) {
			 if (i < choiceDouble && cardProbs[i] >= choiceDouble) {
				 return cards.get(i);
			 }
		}
		return null; //This will always return a card, so this is unreachable code.
	}
}

