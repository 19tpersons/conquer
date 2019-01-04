import java.awt.*;
import javax.swing.*;

/**
 * This is the root of the entire game. It controls the interactions between the two users, the music, logging, etc.
 * @author Tyler Persons
 *
 */
public class RootGameControl extends JPanel {
	private int width = U.width; //This is the window's width.
	private int height = U.height; //This is the window's height.
	private static User[] users;
	private static int userCount = 0; //Keeps track of the user's turn.
	
	
	/**
	 * This is the default constructor for the class. It expects the number of players in the game, and an array with the right amount of colors.
	 * @param numPlayers The number of players in the game.
	 * @param playerColors An array of colors that will be used for each user.
	 */
	public RootGameControl(int numPlayers, Color[] playerColors) {
		this.users = new User[numPlayers]; //This will create an array of Users'
		
		for (int i = 0; i < numPlayers; i++) {
			this.users[i] = new User(width, height, playerColors[i], this); //Creates a new User.
			this.users[i].showPane();
		}
		
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
		remove(users[userCount]);
		userCount++;
		User user = users[userCount];
		user.startTurn();
		add(user);
		revalidate();
		repaint();
	}
}
