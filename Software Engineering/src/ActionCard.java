import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class ActionCard extends JPanel {
	private String sub_type;
	private int popChange; //This is the positive or negative number of population the action will cause
	private InfoModal modal;
	
	/**
	 * 
	 * @param sub_type
	 * @param popChange This is the positive or negative number of population the action will cause
	 * @param actionPercentChange This is the percent change of amount of affect any action will have.
	 */
	public ActionCard(String sub_type, int popChange, int actionPercentChange) {
		this.sub_type = sub_type;
		this.popChange = popChange;
		
		Random rand = new Random();
		int randChange =  rand.nextInt(popChange * actionPercentChange); //This is a number that adds randomness to the amount of change an action can cause to any given population.
		int operator = rand.nextInt(2);
		if (operator == 0) { //zero means add the randChange to the popChange
			this.popChange += randChange;
		}else { //one means subtract the randChage to the popChange
			this.popChange -= randChange;
		}
		
	}
	
	/**
	 * This will return a positive or negative number that will be added to the population of a specific planet.
	 * @return the population change for this specific action.
	 */
	public int getChange() {
		return this.popChange;
	}
	
	/**
	 * This will make the modal object.
	 * @param title The title of the action
	 * @param description The description of the action
	 * @param image_location The image for the action
	 */
	public void makeModal(String title, String description, String image_location) {
		this.modal = new InfoModal(title, description, image_location);
	}
	
	/**
	 * This will show the modal.
	 */
	public void showModal() {
		add(modal);
	}
	
	/**
	 * This will hide the modal.
	 */
	public void hideModal() {
		remove(modal);
	}
}