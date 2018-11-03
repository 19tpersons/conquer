import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;
import java.io.*;

public class CardSet extends JPanel {
	public CardSet() throws IOException {
		String type = "Planet";
		String description = "<html>This is just a test. But it is an amazing test:) I have done something awesome!!</html>";
		
		Card card = new Card("Norman 2", description);
		File icon = new File("images/test.jpg");
		card.setIcon(icon);
		card.setPreferredSize(new Dimension(200,320));
		add(card);
		
		card = new Card("Norman 3", description);
		icon = new File("images/test.jpg");
		card.setIcon(icon);
		card.setPreferredSize(new Dimension(200,320));
		add(card);
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		int height = getHeight();
		int width = getWidth();
		Rectangle2D rect = new Rectangle2D.Double(0, 0, getWidth(), U.calcSize(45, getHeight()));
		g2.setColor(new Color(0, 0, 204));
		g2.fill(rect);
	}
}
