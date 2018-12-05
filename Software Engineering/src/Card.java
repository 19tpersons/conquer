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
	
	//Initializes variables that are going to be used in the display of the card
	private JLabel desLabel, titleLabel, infoLabel, popLabel;
	private CardIcon icon = new CardIcon();
	
	//This sets up the general data for the card.
	public Card(String title, String description, File imageLoc, boolean planetType) throws IOException {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//setBorder(new EmptyBorder(0, 5, 0, 5));

		this.title = title;
		this.description = description;
		
		//This creates the icon
		if (planetType) {
			icon = new CardIcon(imageLoc, 200, 250, true);
		}else {
			icon = new CardIcon(imageLoc, 200, 250, false);
		}
		//The cards title
		titleLabel = new JLabel(title);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
		//Description of the card
		desLabel = new JLabel(description);
		desLabel.setFont(new Font("Arial", Font.BOLD, 18));
		//The population label
		popLabel = new JLabel(Integer.toString(population));
		popLabel.setFont(new Font("Arial", Font.BOLD, 16));
		
		
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
		
		add(icon);
		repaint(); //Repaints the icon
		
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
		remove(icon); //Remove icon from card
		remove(titleLabel); //Remove title
		remove(infoLabel); //Remove "+" from card
		
		add(desLabel);	//Description
		add(titleLabel);
		add(popLabel); //Current Population of Card
		
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
		icon = new CardIcon(imageLoc, 200, 250, true);
	}
	
	
	/**
	 * This will return the card's icon Image object
	 * @return The cards icon
	 */
	public CardIcon getIcon() {
		return this.icon;
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
