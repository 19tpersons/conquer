import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class InfoModal extends Modal {
	private Image icon;
	
	public InfoModal(String title, String description, File image_location) {
		this.setBackground(new Color(0,0,0, 90));
		this.setLayout(null);
		
		JPanel content = new JPanel();
		content.setBounds((U.width / 2) - 250, (U.height / 2) - 125, 500, 250);
		content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
		content.setBackground(Color.ORANGE);
		
		//This is the picture for the modal
		Image newImg = null;
		try {
			newImg = ImageIO.read(image_location);
		} catch (IOException e) {
			return;
		}
		Image scaledImg = newImg.getScaledInstance(U.cardWidth, 250, Image.SCALE_AREA_AVERAGING);
		icon = scaledImg;
		JPanel iconPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.drawImage(icon, 0, 0, this); //Card Image
			}
		};
		iconPanel.setPreferredSize(new Dimension(U.cardWidth, 250));
		iconPanel.setBackground(new Color(0,0,0,0));
		content.add(iconPanel);
		
		//These are the words for the modal
		JPanel words = new JPanel();
		words.setBackground(new Color(0,0,0,0));
		
		JLabel header = new JLabel("You got a Card!");
		words.add(header);
		
		JLabel topTitle = new JLabel(title);
		words.add(topTitle);
		
		JLabel desc = new JLabel(description);
		words.add(desc);
		content.add(words);
		
		
		add(content);
	}
	
}
