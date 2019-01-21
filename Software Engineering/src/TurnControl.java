import java.util.HashMap;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/**
 * This class controls where cards will go. It also keeps track of where the cards are so that it can
 * update their respective populations everytime a fight/action occurs in the game.
 * @author DAT Software Engineering 
 */

public class TurnControl extends JPanel{
	private PlayerStats stats;
	private ArrayList<CardSet> sets;
	private Random rand = new Random();
	private CardDisplay disp;
	private CardSideNav nav;
	private User user;
	private JButton fight, draw, next;
	
	public TurnControl(PlayerStats stats, CardDisplay disp, CardSideNav nav) {
		this.stats = stats;
		sets = stats.sets;
		this.disp = disp;
		this.nav = nav;
		this.user = stats.getUser();
		stats.setTurnControl(this);
		
		// This will finish the turn and allow the other player to take their turnStage
		next = new JButton("Next Turn");
		next.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				System.out.println("End Turn");
				
				stats.nextStage();
				remove(next); //Remove the current button
				add(draw); //Continue onto the next stage
				stats.getUser().endTurn();
				stats.getUser().updateSideNav();
				revalidate();
				repaint();
			}
		});
		
		//This is the fight button. It will continue the game to the next stage.
		fight = new JButton("Fight");
		fight.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				System.out.println("Fight Phase");
				
				stats.nextStage();
				remove(fight); //Remove the current button
				add(next); //Continue onto the next stage
				stats.getUser().updateSideNav();
				revalidate();
				repaint();
			}
		});
		
		//This is the draw button. It will draw a new card from the deck.
		draw = new JButton("Draw");
		draw.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				System.out.println("Draw Phase: Drawing the card");
				
				getCard();
				
				//Updates the backdrop
				stats.getUser().getPane().getBackDrop().refreshSmartStars();

				remove(draw); //This will get rid of the button so that the next phase can begin.
				stats.nextStage();
				add(fight); //Continue onto the next stage
				stats.getUser().updateSideNav();
				revalidate();
				repaint();
				
				System.out.println("Build Phase");
			}
		});
		add(draw);
	}
	
	
	/**
	 * This will draw a new card from the stack, and add it to the right set.
	 */
	public void getCard() {
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
		
		//If the population has changed update it
		stats.getUser().calculatePop();
		this.nav.updateStats();
	}
	
	/**
	 * This method will add a card to a random set that the player owns
	 * @param card the new card to insert
	 */
	private void addToSet(Card card) {
		//Show info modal
		this.user.setModal(card.getModal());
		
		//Since this is a planet, we need to give it a button panel.
		card.setButtonPanel(stats);
		
		//Updates the players total population and resources
		stats.addResources(card.getResources());
		
		//If any of the sets do not have any cards place the planet in that solar system.
		for (int i = 0; i < sets.size(); i++) {
			if (sets.get(i).getCardNumber() == 0) {
				sets.get(i).addCard(card);
				stats.setPrevPlanet(card);
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
		 
		 //Updates the player's total population and resources
		 stats.addResources(newSet.getResources());
		 			
			CardSet tmp = new CardSet(newSet, stats);
			tmp.setBackground(stats.getColor());
			
			stats.addCardSet(tmp);
			stats.setPrevSolar(tmp);
			disp.addClearIconList(stats.sets.size() - 1); //This will add the listener to the newest solar system card
			disp.refreshSets();
			
			stats.getUser().getPane().getBackDrop().addSmartStar(tmp, stats);
	 }
	 
	 /**
	  * This method will perform a specific action
	  * @param action The action card from the deck
	  */
	 private void performAction(ActionCard action) {
		 //Show info modal
		 this.user.setModal(action.getModal());
		 
		 //Choose a system
		 HashMap<Card, CardSet> map = new HashMap<Card, CardSet>(); //This is used to map a solar card back to its respective CardSet
		 ArrayList<Card> solarCards = new ArrayList<Card>(); //Make an array of solar cars
		 for (int i = 0; i < sets.size(); i++) {
			 if (sets.get(i).getCardNumber() > 0) { //Used to ensure only solar systems with cards can be chosen
				 Card solarCard = sets.get(i).getSolar();
			 	solarCards.add(solarCard);
			 	map.put(solarCard, sets.get(i));
			 }
			 
		 } 
		 Card chosenSystemIdx = this.getSubTypeCard(solarCards, action.getSubType()); //This will act as an index to find the correct solar system
		 CardSet chosenSystem = map.get(chosenSystemIdx); //This is the system the action will effect.
		 
		 //Choose a card from the system
		 ArrayList<Card> a = chosenSystem.getCards();
		 String b = action.getSubType();
		 Card card = this.getSubTypeCard(a, b);
		 
		 //Apply the action
		 int change = action.getChange();
		 if (change > 0) {
			 card.addPop(change);
		 } else {
			 change *= -1;
			 card.removePop(change);
		 }
		 
		try {
			card.defineBack();
		} catch (IOException e) {
			//Something happend when the back of the card was getting redefined.
			e.printStackTrace();
		} 
		 
		 //Update the back of the card. 
		 stats.getUser().calculatePop();
		 this.nav.updateStats();

	}
	 
	/**
	 * This is used by the system to get a random card with each card having a weighted probability
	 * @param cards This is an array of the type Card
	 * @param sub_type This is the sub_type to compare each card to. It is used to check if the card has an added probability
	 * @return The chosen card
	 */
	private Card getSubTypeCard(ArrayList<Card> cards, String sub_type) {		
		 double[] cardProbs = new double[cards.size()]; //This will act as an account of each cardSet's probabilities
		 double endBoundary = 0;
		 for (int i = 0; i < cards.size(); i++) {
			 //The sets sub_type
			 Card card = cards.get(i);
			 String subType = card.getSubType();
			 double previousBoundary;
			 if (i == 0) { //This will get the previous cards ending boudary
				 previousBoundary = 0; 
			 } else {
				 previousBoundary = cardProbs[i - 1];
			 }
			 
			 if (subType.equals(sub_type)) {
				 cardProbs[i] = previousBoundary + 1 + card.getSubTypeRate();
			 } else {
				 cardProbs[i] = previousBoundary + 1; //Probability defaults to 1
			 }
			 
			 endBoundary = cardProbs[i];
		}
		 
		double choiceDouble = rand.nextDouble() * endBoundary;
		for (int i = 0; i < cardProbs.length; i++) {
			 if (choiceDouble <= cardProbs[i]) {
				 return cards.get(i);
			 }
		}
		return null; //This will always return a card, so this is unreachable code.
	}
}

