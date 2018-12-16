import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;
/*
 * This is a class that parses a CSV file containing the planets, solar systems, and actions. The class
 * will make the appropriate objects for each type of card, but will not do any calculations on its own
 * for where a card will go, that job is up the TurnControl class.
 */


public class CardDB {
	private String file = "cardDB.csv";
	private ArrayList<String> rows = new ArrayList<String>(); //This an array list of the individual rows in the table
	
	
	public CardDB() throws FileNotFoundException {
		this.parseDB();

		this.createCards();
	}
	
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
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//public ActionCard getAction() {}
	
	//public Card getPlanetCard() {}

	//public Card getSolarCard() {}
}
