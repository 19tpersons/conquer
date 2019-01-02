import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random; 

/**
 * This is used to display the star systems each player owns.
 * @author Tyler Persons
 *
 */
public class Backdrop extends JPanel {
	private int width, height;
	
	public Backdrop(int width, int height) {
		this.width = width;
		this.height = height;
		setBackground(new Color(51,51,51));
		setLayout(null);
		
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
	
	
}
