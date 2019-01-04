import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random; 

/**
 * This is used to display the star systems each player owns.
 * @author Tyler Persons
 *
 */
public class Backdrop extends JPanel {
	private int width, height;
	private static ArrayList<SmartStar> smartStars = new ArrayList<SmartStar>();
	
	public Backdrop(int width, int height) {
		this.width = width;
		this.height = height;
		setBackground(new Color(51,51,51));
		setLayout(null);
		
		
		//This will make "dumb" stars- stars that are there just for looks.
		Random rand = new Random();
		for (int i = 0; i < 180; i++) {
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			
			printStar(x, y);
		}

	}

	/**
	 * This will make a square star that will be placed at (x, y) on the backdrop
	 * @param x the x-coord
	 * @param y the y-coord
	 */
	private void printStar(int x, int y) {
		JPanel dumbStar = new JPanel() {
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
								
				g2.setColor(Color.yellow);
				g2.fillRect(0, 0, 5, 5);
			}
		};
		
		dumbStar.setBounds(x, y, 5, 5); //Since, the backdrop has a layout of null we need to define where to put it
		add(dumbStar);
		
		
	}

	/**
	 * This will add a new smart card to the backdrop. The smart card will give the user a visual view of their solar systems.
	 * @param set the solar system.
	 * @param playerColor the player's color.
	 */
	public void addSmartStar(CardSet set, PlayerStats stats) {
		Random rand = new Random();
		int x = rand.nextInt(width - 100);
		if (x < 100)
			x += 100;
		int y = rand.nextInt(height - 50);
		if (y < 50) //Try to keep the stars away from the edges.
			y += 50;
		
		SmartStar star = new SmartStar(set, stats);
		star.setBounds(x, y, 15, 15);
		
		this.smartStars.add(star); //So it can be accessed later.
	}
	
	/**
	 * This will refresh the panel, so that any new solar systems are added to the backdrop.
	 */
	public void refreshSmartStars() {
		//If there are any currently on display, then remove them.
		for (int i = 0; i < smartStars.size(); i++) {
			remove(smartStars.get(i));
		}
		revalidate();
		repaint();
		
		//Add all of the stars to the panel with any additions
		for (int i = 0; i < smartStars.size(); i++) {
			add(smartStars.get(i));
		}
	}
	
}
