import java.awt.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * This class is used to display information about a new card that has been drawn.
 * @author DAT Software Engineering
 */
public class CardInfoModal extends Modal {
	private Image icon;
	private static int height = 250;
	private static int width = 500;
	private JPanel content;
	
	public CardInfoModal(String title, String description, int population, int resources, String image_location) {
		super(width, height);
		
		this.setBackground(new Color(0,0,0, 90));
		this.setLayout(null);
		
		content = new JPanel();
		content.setBounds((U.width / 2) - 250, (U.height / 2) - 125, width, height);
		content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
		content.setBackground(Color.ORANGE);
		content.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		
		//This is the picture for the modal
		Image newImg = null;
		try {
			newImg = ImageIO.read(U.getFile(image_location));
		} catch (IOException e) {
			return;
		}
		Image scaledImg = newImg.getScaledInstance(U.cardWidth, height, Image.SCALE_AREA_AVERAGING);
		icon = scaledImg;
		JPanel iconPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.drawImage(icon, 0, 0, this); //Card Image
			}
		};
		iconPanel.setPreferredSize(new Dimension(U.cardWidth, height));
		iconPanel.setBackground(new Color(0,0,0,0));
		content.add(iconPanel);
		
		//These are the words for the modal
		JPanel words = new JPanel();
		words.setLayout(new BoxLayout(words, BoxLayout.Y_AXIS));
		words.setBackground(Color.ORANGE);
		
		JLabel header = new JLabel("You got a Card!");
		header.setFont(new Font("Arial", Font.BOLD, 20));
		header.setBorder(new EmptyBorder(10, 0, 0, 5));//top,left,bottom,right
		words.add(header);
		
		
		JTextArea desc = new JTextArea(title + ":\n\n");
		desc.append(description + "\n\n");
		desc.append("Native Populution: " + population + "M\n\n");
		desc.append("Resouces: " + resources);
		desc.setEditable(false);
		desc.setLineWrap(true);
		desc.setWrapStyleWord(true);
		desc.setFont(new Font("Arial", Font.BOLD, 18));
		desc.setBackground(Color.ORANGE);
		desc.setMargin(new Insets(0,10,0,0));
		words.add(desc);
		content.add(words);
		
		
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
