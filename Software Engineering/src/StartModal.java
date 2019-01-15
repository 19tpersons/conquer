import java.awt.*;
import javax.swing.*;

public class StartModal extends Modal {
	private static int width = 500;
	private static int height = 350;
	private Color modalColor = Color.ORANGE;
	private JPanel content;
	
	
	public StartModal(PlayerStats stats) {
		super(width, height);
		Card planet = stats.getPrevPlanet();
		Card solar = stats.getPrevSolar().getSolar();
		this.setBackground(new Color(0,0,0,90));
		this.setLayout(null);
		
		content = new JPanel();
		content.setBounds((U.width / 2) - 250, (U.height / 2) - 125, width, height);
		content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
		content.setBackground(Color.ORANGE);
		content.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		
		//This is the left half of the modal.
		int cardInfoWidth = 200;
		JPanel cardInfo = new JPanel();
		cardInfo.setPreferredSize(new Dimension(100, height));
		cardInfo.setBackground(new Color(0,0,0,0));
		cardInfo.setLayout(new BoxLayout(cardInfo, BoxLayout.Y_AXIS));
		
		JPanel solarPanel = new JPanel(); //Describes the player's first solar system.
		solarPanel.setPreferredSize(new Dimension(200, height / 2));
		solarPanel.setBackground(modalColor);
		JLabel title = new JLabel("Solar System");
		title.setFont(new Font("Arial", Font.BOLD, 18));
		solarPanel.add(title);
		JTextArea solarInfo = new JTextArea();
		solarInfo.setEditable(false);
		solarInfo.setWrapStyleWord(true);
		solarInfo.setLineWrap(true);
		solarInfo.setFont(new Font("Arial", Font.BOLD, 16));
		solarInfo.append(solar.getTitle() + "\n" + solar.getDescription());
		solarPanel.add(solarInfo);
		cardInfo.add(solarPanel);
		
		JPanel planetPanel = new JPanel(); //Describes the player's planet.
		planetPanel.setPreferredSize(new Dimension(200, height / 2));
		planetPanel.setBackground(modalColor);
		title = new JLabel("Planet");
		title.setFont(new Font("Arial", Font.BOLD, 18));
		planetPanel.add(title);
		JTextArea planetInfo = new JTextArea();
		planetInfo.setEditable(false);
		planetInfo.setWrapStyleWord(true);
		planetInfo.setLineWrap(true);
		planetInfo.setFont(new Font("Arial", Font.BOLD, 16));
		planetInfo.append(planet.getTitle() + "\n" + planet.getDescription());
		planetPanel.add(planetInfo);
		cardInfo.add(planetPanel);
		
		content.add(cardInfo);
		
		//This is the right half of the modal.
		JPanel generalInfo = new JPanel();
		generalInfo.setPreferredSize(new Dimension(width - 200, height));
		
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
