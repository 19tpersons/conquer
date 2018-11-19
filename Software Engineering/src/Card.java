import java.awt.*;
import java.io.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Card extends JPanel {
	//General Stats about card
	private String title = ""; //The card title
	private String type = ""; //The type of card
	private String description = ""; //The description
	private int population = 0; //Population of card
	private String nickname = ""; //This is a way to identify the card
	
	private Image cardImage;
	private boolean infoShown = false; //Is the cards descriptive side showing?
	//Initializes variables that are going to be used in the display of the card
	private JLabel desLabel, titleLabel, infoLabel, popLabel;
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
		
		if (desLabel != null && titleLabel != null && infoLabel != null && popLabel != null) {
			remove(desLabel);
			remove(titleLabel);
			remove(infoLabel);
			remove(popLabel);
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
		
		popLabel = new JLabel(Integer.toString(population));
		popLabel.setFont(new Font("Arial", Font.BOLD, 16));
		add(popLabel);
		
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
	
	//Defacto getter and setter methods
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
	
	/**
	 * Sets the description of card
	 * @param description The description of the card
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * This returns the description of the card
	 * @return description of card
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Sets the title of the card
	 * @param title The new title of the card
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * This returns the title of the card
	 * @return title of card
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * This sets the cards population to something new
	 * @param newPop The new population of the card
	 */
	public void setPop(int newPop) {
		this.population = newPop;
	}
	/**
	 * Returns the population of the card
	 * @return The cards current population
	 */
	public int getPop() {
		return this.population;
	}
	
	/**
	 * Changes the nickname of the card
	 * @param name The new nickname
	 */
	public void setNickname(String name) {
		this.nickname = name;
	}
	
	/**
	 * This returns the nickname of the card
	 * @return the card nickname
	 */
	public String getName() {
		return this.nickname;
	}
}
