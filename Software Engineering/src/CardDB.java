import java.awt.*;
import java.io.*;
import java.util.ArrayList;

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
	private ArrayList<Card> planets = new ArrayList<Card>();
	private ArrayList<Card> solarSystems = new ArrayList<Card>();
	private ArrayList<ActionCard> actions = new ArrayList<ActionCard>();
	
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
					String lastChar = cell.substring(cell.length() - 1); //Last character in cell
					String secondChar = cell.substring(cell.length() - 2); //Second to last character in cell
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
		String sub_type = row.get(2);
		String title = row.get(3);
		String description = row.get(4);
		int popChange = Integer.parseInt(row.get(5));
		int defacto = Integer.parseInt(row.get(6));
		String image_location = row.get(8);
		
		ActionCard newCard = new ActionCard(sub_type, popChange, defacto);
		newCard.makeModal(title, description, image_location);
		actions.add(newCard); //adds the news created action to an array
	}

	public void makeCard(ArrayList<String> row) {
		String title = row.get(3);
		String description = row.get(4);
		int popChange = Integer.parseInt(row.get(5));
		int range = Integer.parseInt(row.get(7));
		String image_location = row.get(8);
		
		Card newCard = new Card(sub_type, popChange, defacto);
		actions.add(newCard); //adds the news created action to an array
	}
	
	//public ActionCard getAction() {}
	
	//public Card getPlanetCard() {}

	//public Card getSolarCard() {}
}
