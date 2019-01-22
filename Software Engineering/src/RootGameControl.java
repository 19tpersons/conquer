import java.awt.*;
import javax.swing.*;

/**
 * This is the root of the entire game. It controls the interactions between the two users, the music, logging, etc.
 * @author DAT Software Engineering
 *
 */
public class RootGameControl extends JPanel {
	private int width = U.width; //This is the window's width.
	private int height = U.height; //This is the window's height.
	private static User[] users;
	private static int userCount = 0; //Keeps track of the user's turn.
	private int numPlayers; //This is the number of players in the game.
	
	/**
	 * This is the default constructor for the class. It expects the number of players in the game, and an array with the right amount of colors.
	 * @param numPlayers The number of players in the game.
	 * @param playerColors An array of colors that will be used for each user.
	 */
	public RootGameControl(int numPlayers, Color[] playerColors) {
    	//This moves the JPanel up fix pixels to remove any white space
		FlowLayout flow = new FlowLayout();
		flow.setVgap(-5);
		setLayout(flow);
		
		this.numPlayers = numPlayers;
		this.users = new User[numPlayers]; //This will create an array of Users'
		
		System.out.println("Creating the users");
		for (int i = 0; i < numPlayers; i++) {
			User user = new User(width, height, playerColors[i], this);
			this.users[i] =  user;//Creates a new User.
			user.showPane();
			
			//This will move the game ahead two turns so that each player already has a planet and solar system
			user.getStats().getTurnControl().getCard(); //Give the player an initial solar system
			user.hideModal();
			user.getStats().getTurnControl().getCard(); //Give the player an initial planet
			user.hideModal();
			
			user.setModal(new StartModal(user.getStats()));
		}
		
	}
	
	/**
	 * This is a static method that will return the User that is currently being played
	 * @return the current user
	 */
	public static User getCurUser() {
		return users[userCount];
	}
	
	/**
	 * This will start the game.
	 */
	public void startGame() {
		User user = users[userCount];
		user.startTurn();
		add(user);

	}
	
	/**
	 * This will move the game onto the next player's turn.
	 */
	public void nextTurn() {
		//If the player has 30,000 million (30 billion), then they have won!
		if (users[userCount].getStats().getPopulation() >= 30000) {
			System.out.println("Player " + userCount + " wins!");
			
			System.exit(0);
		}
		System.out.println("\nStarting the next turn. Player " + userCount);
		
		
		remove(users[userCount]); //Removes the current user from the display
		
		//Changes the player's turn
		if (userCount == (this.numPlayers - 1)) {
			userCount = 0;
		} else {
			userCount++;
		}
		User user = users[userCount];
		user.startTurn();
		add(user); //Adds the player to the panel.
		
		revalidate();
		repaint();
	}
}
