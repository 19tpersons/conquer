import java.awt.*;
import java.io.*;
import java.util.Random;
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
	private String sub_type = "";
	private double subTypeRate = 0.0;
	
	//Initializes variables that are going to be used in the display of the card
	private CardIcon icon;
	private JPanel topInfo = new JPanel(); //This is the top of the card
	private JPanel bottomInfo = new JPanel();//This is the bottom of the card
	
	//This sets up the general data for the card.
	public Card(String title, String description, File imageLoc, String type) throws IOException {
		//Sets up card
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.title = title;
		this.description = description;
		
		//This creates the icon
		if (type.equals("planet")) {
			icon = new CardIcon(imageLoc, 200, 250, true);
		} else {
			icon = new CardIcon(imageLoc, 200, 250, false);
		}
			
		//This section defines the top of the card
		JLabel titleLabel = new JLabel(title); //The cards title
		titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
		topInfo.add(titleLabel);
		JLabel infoLabel = new JLabel("+"); //This is the button that will show the back of the card
		infoLabel.setHorizontalAlignment(JLabel.RIGHT);
		infoLabel.setFont(new Font("Arial", Font.BOLD, 18));
		infoLabel.addMouseListener( new MouseAdapter() { 
			public void mousePressed(MouseEvent evt) {
				printBack();
			} 
		});
		topInfo.add(infoLabel);		
		topInfo.setPreferredSize(new Dimension(200, 320 - 250));
		topInfo.setBackground(new Color(0,0,0,0)); //Allows the card's color to show!
		
		//This section defines the contents for the back of the card
		JLabel desLabel = new JLabel(description);	//Description of the card
		desLabel.setFont(new Font("Arial", Font.BOLD, 18));
		bottomInfo.add(desLabel);
		JLabel popLabel = new JLabel(Integer.toString(population)); //The population label
		popLabel.setFont(new Font("Arial", Font.BOLD, 16));
		bottomInfo.add(popLabel);
		infoLabel = new JLabel("-"); //This button will show the front of the card
		infoLabel.setHorizontalAlignment(JLabel.RIGHT);
		infoLabel.setFont(new Font("Arial", Font.BOLD, 18));
		infoLabel.addMouseListener( new MouseAdapter() { 
			public void mousePressed(MouseEvent evt) {
				printFront();
			} 
		});
		bottomInfo.add(infoLabel);
		bottomInfo.setPreferredSize(new Dimension(200, 320 - 250));		
		bottomInfo.setBackground(new Color(0,0,0,0)); //Allows the card's color to show!
		
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
		if (bottomInfo != null) {
			remove(bottomInfo);
		}
		
		add(icon);
		add(topInfo);
		
		repaint(); //Repaints the icon
		revalidate();
	}

	public void printBack() {
		remove(topInfo);
		remove(icon);
		add(bottomInfo);
			
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
	 * This method returns this cards sub_type
	 * @return the cards sub_type
	 */
	public String getSubType() {
		return sub_type;
	}
	
	/**
	 * This class will set a cards sub_type. If a card has a sub_type it will be that much more likely 
	 * of being chosen for a given action of the same sub_type.
	 * @param sub_type The cards sub_type.
	 * @param subTypeRate This is the additional chance of a given action occuring to this.
	 */
	public void setSubType(String sub_type, double subTypeRate) {
		this.sub_type = sub_type;
		this.subTypeRate = subTypeRate;
	}
	
	public double getSubTypeRate() {
		return this.subTypeRate;
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
	 * This sets the card's population to something new
	 * @param newPop the new population of the card
	 */
	public void setPop(int newPop) {
		this.population = newPop;
	}
	
	/**
	 * This sets the cards population to something new
	 * @param newPop The new population of the card
	 * @param changeRangeRate This is a percentage that will be multiplied by the given population to allow for some randomness in populations per game.
	 */
	public void setPop(double newPop, double changeRangeRate) {
		this.population = (int) newPop;
		//This section is used to calculate the population change.
		Random rand = new Random();
		int popChange = rand.nextInt((int) (this.population * changeRangeRate)); //This is the number that will be either added or subtracted from the default population of the planet/solar system.
		int operator = rand.nextInt(2); //zero is plus, one is negative
		if (operator == 0) {
			this.population += popChange;
		} else {
			this.population -= popChange;
		}
	}
	/**
	 * Returns the population of the card
	 * @return The cards current population
	 */
	public int getPop() {
		return this.population;
	}
	
	/**
	 * This method will add more population to the card and return the sum.
	 * @param additive The pop. to add
	 */
	public void addPop(int additive) {
		this.population += additive;
	}
	
	/**
	 * This method will subtract more population to the card and return the difference.
	 * @param amt The pop. to subtract
	 */
	public void removePop(int amt) {
		this.population -= amt;
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
