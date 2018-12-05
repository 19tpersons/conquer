import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class CardIcon extends JPanel{
	private Image icon;
	private int width, height;
	
	public CardIcon() {}
	
	public CardIcon(File imageLoc, int width, int height, boolean planetType) throws IOException {
		this.setPreferredSize(new Dimension(width, height)); //Sets size of icon
		
		this.height = height; //height of icon
		this.width = width; //width of icon
		this.setIcon(imageLoc); //Sets the cards icon
		
	

		if (planetType) {
			this.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent evt) {
					setBackground(Color.BLACK); //TODO: Make the buttons!
				}
			});
		}
		
	}
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(icon, 0, 0, this); //Card Image
	}
	
	/**
	 * Gives the card a new Icon
	 * @param image The new image that is going to be the cards icon
	 */
	public void setIcon(File imageLoc) throws IOException {
		Image newImg = ImageIO.read(imageLoc);
		Image scaledImg = newImg.getScaledInstance(this.width, this.height, Image.SCALE_AREA_AVERAGING);
		icon = scaledImg;

	}
}
