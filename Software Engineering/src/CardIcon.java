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
	private JPanel buttonPanel = new JPanel();
	private Image history;
	private int width, height;
	private JButton fightBtn = new JButton("Fight");
	
	public CardIcon(File imageLoc, int width, int height, boolean planetType) throws IOException {
		//This section sets up the card
		this.setPreferredSize(new Dimension(width, height)); //Sets size of icon
		this.height = height; //height of icon
		this.width = width; //width of icon
		this.setIcon(imageLoc); //Sets the cards icon
		history = icon; //Used for the hover function
		
		//buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setBackground(new Color(255,255,255, 50)); //TODO: Make the buttons!
		buttonPanel.setPreferredSize(new Dimension(width, height));
		buttonPanel.add(fightBtn);
		
		if (planetType) {
			this.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent evt) {
						if(!isComponentInPanel(fightBtn)) {
							icon = null;
							add(buttonPanel);
							revalidate();
							repaint();
						}
				}
				
				public void mouseExited(MouseEvent evt) {
					Point p = new Point(evt.getLocationOnScreen());
					SwingUtilities.convertPointFromScreen(p, evt.getComponent());
					if (evt.getComponent().contains(p)) {
						return;
					} else {
						icon = history;
						remove(buttonPanel);
						repaint();
						revalidate();
					}
				}
			});
		}

	}
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		//The background
		g2.setColor(Color.ORANGE);
		g2.fillRect(0, 0, width, height);
		
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
	
	private boolean isComponentInPanel(Component component) {
		return java.util.Arrays.asList(this.getComponents()).contains(component);
	}
}
