import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is a class that parses a CSV file containing the planets, solar systems, and actions. The class
 * will make the appropriate objects for each type of card, but will not do any calculations on its own
 * for where a card will go, that job is up the TurnControl class.
 * 
 * The CSV file is in the following order:
 * 	idx, type, sub_type, title, description, population, defacto, range, image_location
 * 
 * @author DAT Software Engineering
 */


public class CardDB {
	private String file = "cardDB.csv";
	private Random rand = new Random(); //Used in the program to help randomize the deck.
	
	private ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>(); //This an array list of the individual rows in the table
	
	//These three arraylists are the three types of rows within the database
	private static ArrayList<Card> planets = new ArrayList<Card>();
	private static ArrayList<Card> solarSystems = new ArrayList<Card>();
	private static ArrayList<ActionCard> actions = new ArrayList<ActionCard>();
	
	//These three arraylists will be used to provide the game with card repeats only if one of the above three arraylists run out.
	private static int pCount = 0, sCount = 0, aCount = 0; //Used to keep track of which cards have already been replayed.
	private static ArrayList<Card> usedPlanets = new ArrayList<Card>();
	private static ArrayList<Card> usedSolarSystems = new ArrayList<Card>();
	private static ArrayList<ActionCard> usedActions = new ArrayList<ActionCard>();
	
	public CardDB() throws FileNotFoundException {
		this.parseDB();

		this.sortCards();
	}
	
	/**
	 * This will parse the CSV file for the game.
	 */
	private void parseDB() {
		//This section reads data from the file and stores it in an arrayList
		try (BufferedReader br = new BufferedReader(new InputStreamReader(CardDB.class.getResourceAsStream(file)))){
			String topLine = br.readLine();
			
			while (br.ready()) {
				ArrayList<String> row = new ArrayList<String>();
				
				String tmpCellHolder = ""; //This is used to hold data if a cell contains a comma and that comma isn't the end of the cell.
				String line = br.readLine();
				String[] cells = line.split(","); //Splits the read line into an array divided from the commas
				for (int i = 0; i < cells.length; i++) {
					String cell = cells[i];
					String lastChar, secondChar;
					if (cell.length() > 2) {
						lastChar = cell.substring(cell.length() - 1); //Last character in cell
						secondChar = cell.substring(cell.length() - 2); //Second to last character in cell
					}else {
						lastChar = "null";
						secondChar = "null";
					}
					if (lastChar.equals("\\") && !secondChar.equals("\\")) { //Checks to see if the , was escaped or if the \ was escaped!
						tmpCellHolder += "," + cell;
					} else {
						if (!tmpCellHolder.equals("")) {
							row.add(tmpCellHolder);
							tmpCellHolder = "";
						} else {
							row.add(cell);
						}
					}
				}
				rows.add(row);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This will figure out where the rows go!
	 */
	public void sortCards() {
		for(ArrayList<String> row: rows) {
			String type = row.get(1);
			
			if (type.equals("action")) {
				this.makeAction(row);
			} else {
				this.makeCard(row);
			}
		}
	}
	
	/**
	 * This will will make an action card from the information in the database
	 * @param row A single row in the database that holds the information for a single card
	 */
	public void makeAction(ArrayList<String> row) {
		String sub_type = row.get(2); //A cards given sub_type
		String title = row.get(3); //The title of the action
		String description = row.get(4); //The description of the action
		double popChange = Double.parseDouble(row.get(5)); //The positive or negative amount to change any given population by.
		double popChangeRate = Double.parseDouble((row.get(7))); //This adds randomness to the popChange number.
		String image_location = row.get(8);
		
		ActionCard newCard = new ActionCard(sub_type, popChange, popChangeRate);
		newCard.makeModal(title, description, image_location);
		
		//We want to randomize the order
		if (actions.size() > 0) {
			int position = rand.nextInt(actions.size());
			actions.add(position, newCard);
		} else {
			actions.add(newCard); //adds the news created action to an array
		}
	}

	/**
	 * This will will make a generic card from the information in the database. The card will either be a planet or a solar system.
	 * @param row A single row in the database that holds the information for a single card.
	 */
	public void makeCard(ArrayList<String> row) {
		String title = row.get(3); //Title of the card
		String description = row.get(4); //Description of the card
		String image_location = row.get(8); //The image of the card.
		
		String type = row.get(1); //Is it card a planet or solar system
		String sub_type = row.get(2); //Cards with a sub_type are linked to certain actions cards and have a higher percent chance of those actions happening to them.
		String subTypeRate = row.get(6); //This is the additional percentage that this card will have the particular action happen to it
		
		double population = Double.parseDouble(row.get(5));
		double changeRangeRate = Double.parseDouble(row.get(7)); //This number is used to give the population a sense of randomness for every specific game.		

		int resourceCount = Integer.parseInt(row.get(9));
		
		//This will try to make a new card and if it can't will just return the method
		Card newCard;
		try {
			
			newCard = new Card(title, description, image_location, type);
			if (!sub_type.equals("null") && !subTypeRate.equals("null")) { //If this card has a specific sub_type then define it.
				newCard.setSubType(sub_type, Double.parseDouble(subTypeRate));
			}
			newCard.setPop(population, changeRangeRate); //Set the population
			newCard.setResources(resourceCount);
			newCard.setPopChangeRate(U.startPopChangeRate);
			
			newCard.defineBack(); //Since, the population changed, we need to update the back of the card.
			
		} catch (IOException e) {
			return;
		}
		newCard.setPreferredSize(new Dimension(200,320));
		
		//This section finishes up the method by adding the new Card to its arrayList
		if (type.equals("planet")) {
			if (planets.size() > 0) { //Puts the card in a random position.
				int position = rand.nextInt(planets.size());
				planets.add(position, newCard);
			} else {
				planets.add(newCard); //adds the news created planet to an array
			}
		}else { //Must be a solar system
			if (solarSystems.size() > 0) { //Puts the card in a random position.
				int position = rand.nextInt(solarSystems.size());
				solarSystems.add(position, newCard);
			} else {
				solarSystems.add(newCard);
			}
		}
	}
	
	/**
	 * This will allow the system to get a new action card
	 * @return the new action card
	 */
	public static ActionCard getAction() {
		if (actions.size() == 0) { //If there are no un-drawn action cards, re-draw some.
			ActionCard temp = usedActions.get(aCount).clone();
			aCount++;
			if (aCount >= usedActions.size()) {
				aCount = 0;
			}
			return temp;
		} else {
			ActionCard temp = actions.get(0);
			usedActions.add(temp);
			actions.remove(0);
			return temp; 
		}
	}
	
	/**
	 * This will return a new planet card
	 * @return a planet card
	 */
	public static Card getPlanetCard() {
		if (planets.size() == 0) { //If all of the planet cards have already been played 
			Card temp = usedPlanets.get(pCount).clone(); //get the used card

			pCount++;
			if (pCount >= usedPlanets.size()) {
				pCount = 0;
			}
			return temp;
		} else { //If there are un-drawn cards, use those.
			Card temp = planets.get(0);
			usedPlanets.add(temp);
			planets.remove(0);
			return temp;
		}
	}

	/**
	 * This allows you to gets a Solar System.
	 * @return the new solar system
	 */
	public static Card getSolarCard() {
		if (solarSystems.size() == 0) { //If all of the solar systems cards have already been played
			Card temp = usedSolarSystems.get(sCount).clone(); //Played cards
			
			sCount++; //Add one to count
			if (sCount >= usedSolarSystems.size()) {
				sCount = 0;
			}
			return temp;
		} else {
			Card temp = solarSystems.get(0); //Get new card
			usedSolarSystems.add(temp); 
			solarSystems.remove(0);
			return temp;
		}
	}
}
