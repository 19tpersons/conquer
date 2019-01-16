import java.awt.*;
import javax.swing.*;

public class StartModal extends Modal {
	private static int width = 500;
	private static int height = 375;
	private Color modalColor = Color.ORANGE;
	private JPanel content;
	
	
	public StartModal(PlayerStats stats) {
		super(width, height);
		Card planet = stats.getPrevPlanet();
		Card solar = stats.getPrevSolar().getSolar();
		this.setBackground(new Color(0,0,0,90));
		this.setLayout(null);
		
		content = new JPanel();
		content.setBounds((U.width / 2) - (width / 2), (U.height / 2) - (height / 2), width, height);
		content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
		content.setBackground(Color.ORANGE);
		content.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		
		//This is the left half of the modal.
		int cardInfoWidth = 225;
		JPanel cardInfo = new JPanel();
		cardInfo.setPreferredSize(new Dimension(100, height));
		cardInfo.setBackground(new Color(0,0,0,0));
		cardInfo.setLayout(new BoxLayout(cardInfo, BoxLayout.Y_AXIS));
		
		JPanel solarPanel = new JPanel(); //Describes the player's first solar system.
		solarPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
		solarPanel.setPreferredSize(new Dimension(cardInfoWidth, height / 2));
		solarPanel.setBackground(new Color(255,173,51));
		JLabel title = new JLabel("Solar System");
		title.setFont(new Font("Arial", Font.BOLD, 18));
		solarPanel.add(title);
		JTextArea solarInfo = new JTextArea(6, 10);
		solarInfo.setEditable(false);
		solarInfo.setWrapStyleWord(true);
		solarInfo.setLineWrap(true);
		solarInfo.setFont(new Font("Arial", Font.BOLD, 16));
		solarInfo.append(solar.getTitle() + ":\n" + solar.getDescription());
		solarPanel.add(solarInfo);
		cardInfo.add(solarPanel);
		
		JPanel planetPanel = new JPanel(); //Describes the player's planet.
		planetPanel.setPreferredSize(new Dimension(cardInfoWidth, height / 2));
		planetPanel.setBackground(new Color(255,173,51));
		title = new JLabel("Planet");
		title.setFont(new Font("Arial", Font.BOLD, 18));
		planetPanel.add(title);
		JTextArea planetInfo = new JTextArea(6,10);
		planetInfo.setEditable(false);
		planetInfo.setWrapStyleWord(true);
		planetInfo.setLineWrap(true);
		planetInfo.setFont(new Font("Arial", Font.BOLD, 16));
		planetInfo.append(planet.getTitle() + ":\n" + planet.getDescription());
		planetPanel.add(planetInfo);
		cardInfo.add(planetPanel);
		
		content.add(cardInfo);
		
		//This is the right half of the modal.
		JPanel generalInfo = new JPanel();
		generalInfo.setPreferredSize(new Dimension(width - cardInfoWidth, height));
		generalInfo.setBackground(new Color(255,153,0));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(width - cardInfoWidth, 50));
		title = new JLabel("Conquer: The Card Game");
		title.setFont(new Font("Arial", Font.BOLD, 22));
		titlePanel.setBackground(new Color(255,153,0));
		titlePanel.add(title);
		JTextArea welcome = new JTextArea(60, 20);
		welcome.setEditable(false);
		welcome.setWrapStyleWord(true);
		welcome.setLineWrap(true);
		welcome.setMargin(new Insets(10,10,10,10));
		welcome.setFont(new Font("Arial", Font.BOLD, 18));
		welcome.append("Welcome!\n\n"
				+ "Your people have spent the previous 100 years preparing for this day."
				+ " The time has come for your race to explore and colonize the stars."
				+ " All the races of the universe want to be the most powerful and growth is the easiest way to accomplish this"
				+ " so don't fail! Spread and grow for the glory of POWER!");
		generalInfo.add(titlePanel);
		generalInfo.add(welcome);
		content.add(generalInfo);
		
		add(content);
	}
	
	/**
	 * This will return the modal's content panel.
	 * @return content panel.
	 */
	public JPanel getContent() {
		return this.content;
	}
}
