import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
/*
 * This is a class that parses a CSV file containing the planets, solar systems, and actions. The class
 * will make the appropriate objects for each type of card, but will not do any calculations on its own
 * for where a card will go, that job is up the TurnControl class.
 * 
 * The CSV file is in the following order:
 * 	idx, type, sub_type, title, description, population, defacto, range, image_location
 */


public class CardDB {
	private String file = "cardDB.csv";
	private ArrayList<ArrayList<String>> rows = new ArrayList<ArrayList<String>>(); //This an array list of the individual rows in the table
	
	//These three array lists are the three types of rows within the database
	private static ArrayList<Card> planets = new ArrayList<Card>();
	private static ArrayList<Card> solarSystems = new ArrayList<Card>();
	private static ArrayList<ActionCard> actions = new ArrayList<ActionCard>();
	
	public CardDB() throws FileNotFoundException {
		this.parseDB();

		this.sortCards();
	}
	
	/**
	 * This will parse the CSV file for the game.
	 */
	private void parseDB() {
		//This section reads data from the file and stores it in an arrayList
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
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
	
	public void makeAction(ArrayList<String> row) {
		String sub_type = row.get(2); //A cards given sub_type
		String title = row.get(3); //The title of the action
		String description = row.get(4); //The description of the action
		double popChange = Double.parseDouble(row.get(5)); //The positive or negative amount to change any given population by.
		double popChangeRate = Double.parseDouble((row.get(7))); //This adds randomness to the popChange number.
		File image_location = new File(row.get(8));
		
		ActionCard newCard = new ActionCard(sub_type, popChange, popChangeRate);
		newCard.makeModal(title, description, image_location);
		actions.add(newCard); //adds the news created action to an array
	}

	public void makeCard(ArrayList<String> row) {
		String title = row.get(3); //Title of the card
		String description = row.get(4); //Description of the card
		File image_location = new File(row.get(8)); //The image of the card.
		
		String type = row.get(1); //Is it card a planet or solar system
		String sub_type = row.get(2); //Cards with a sub_type are linked to certain actions cards and have a higher percent chance of those actions happening to them.
		String subTypeRate = row.get(6); //This is the additional percentage that this card will have the particular action happen to it
		
		double population = Double.parseDouble(row.get(5));
		double changeRangeRate = Double.parseDouble(row.get(7)); //This number is used to give the population a sense of randomness for every specific game.		

		
		//This will try to make a new card and if it can't will just return the method
		Card newCard;
		try {
			
			newCard = new Card(title, description, image_location , type);
			if (!sub_type.equals("null") && !subTypeRate.equals("null")) { //If this card has a specific sub_type then define it.
				newCard.setSubType(sub_type, Double.parseDouble(subTypeRate));
			}
			newCard.setPop(population, changeRangeRate);

		} catch (IOException e) {
			return;
		}
		
		
		//This section finishes up the method by adding the new Card to its arrayList
		if (type.equals("planet")) {
			planets.add(newCard); //adds the news created planet to an array
		}else { //Must be a solar system
			solarSystems.add(newCard);
		}
	}
	
	public static ActionCard getAction() {
		ActionCard temp = actions.get(0);
		actions.remove(0);
		return temp;
	}
	
	public static Card getPlanetCard() {
		Card temp = planets.get(0);
		planets.remove(0);
		return temp;
	}

	public static Card getSolarCard() {
		Card temp = solarSystems.get(0);
		solarSystems.remove(0);
		return temp;
	}
}
