import java.awt.*;
import java.io.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Card extends JPanel {
	private String type = ""; //The type of card
	private String description = "This is the description!"; //The description
	private String title = "Norman 1"; //The card title
	private Image cardImage;
	private int slot = 0; //This is the location of the card
	private boolean infoShown = false; //Is the cards descriptive side showing?
	
	//Initializes variables that are going to be used in the display of the card
	private JLabel desLabel, titleLabel, infoLabel;
	private Icon cardIcon;
	
	//This sets up the general data for the card.
	public Card(String title, String description) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//setBorder(new EmptyBorder(0, 5, 0, 5));

		this.title = title;
		this.description = description; 
		
		printFront();
		
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		Rectangle2D bg = new Rectangle2D.Double(0, 0, getWidth(), getHeight()); //This is the background of the card
		g2.setColor(Color.ORANGE);
		g2.fill(bg);		

	}
	
	/**
	 * Draws the front side of the card
	 */
	private void printFront() {
		
		if (desLabel != null && titleLabel != null && infoLabel != null) {
			remove(desLabel);
			remove(titleLabel);
			remove(infoLabel);
		}
		
		cardIcon = new Icon();
		add(cardIcon);
		
		titleLabel = new JLabel(title);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
		add(titleLabel);
		
		infoLabel = new JLabel("+");
		infoLabel.setHorizontalAlignment(JLabel.RIGHT);
		infoLabel.setFont(new Font("Arial", Font.BOLD, 18));
		infoLabel.addMouseListener( new MouseAdapter() { 
			public void mousePressed(MouseEvent evt) {
				printBack();
			} 
		});
		add(infoLabel);
		
		revalidate();
	}

	public void printBack() {
		if (cardIcon != null && titleLabel != null && infoLabel != null) { //Makes sure that we can switch back
			remove(cardIcon);
			remove(titleLabel);
			remove(infoLabel);
		}
		
		desLabel = new JLabel(description); //Description of card
		desLabel.setFont(new Font("Arial", Font.BOLD, 18));
		add(desLabel);
		
		titleLabel = new JLabel(title);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
		add(titleLabel);
		
		infoLabel = new JLabel("-");
		infoLabel.setHorizontalAlignment(JLabel.RIGHT);
		infoLabel.setFont(new Font("Arial", Font.BOLD, 18));
		infoLabel.addMouseListener( new MouseAdapter() { 
			public void mousePressed(MouseEvent evt) {
				printFront();
			} 
		});
		add(infoLabel);
		
		revalidate(); //Tells java you have changed the component structure
		repaint();
	}
	/**
	 * Gives the card a new Icon
	 * @param image The new image that is going to be the cards icon
	 */
	public void setIcon(File imageLoc) throws IOException {
		Image newImg = ImageIO.read(imageLoc);
		Image scaledImg = newImg.getScaledInstance(200, 250, Image.SCALE_AREA_AVERAGING);
		cardImage = scaledImg;
	}
	
	private class Icon extends JPanel {
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(cardImage, 0, 0, this); //Card Image
		}
	}
	
	/**
	 * Returns the type of Card
	 * @return The type of card
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the type of card
	 * @param type The new type
	 */
	public void setType(String type) {
		type = this.type;
	}
	

}
