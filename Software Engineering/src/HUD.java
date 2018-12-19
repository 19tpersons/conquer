import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.awt.geom.*;
import java.io.IOException;

public class HUD extends JPanel {
	//private Color playerColor = new Color(0, 0, 204);
	//private Color playerColor = new Color(255, 151, 53);
	private Color playerColor;
	
	public HUD(int width, int height, PlayerStats stats) {
		//Inital Setup
		setLayout(new BorderLayout(0,0));
		setPreferredSize(new Dimension(width, height));
		this.playerColor = stats.getColor();
		
		try {
			CardDisplay disp = new CardDisplay(width - 275, height, stats);
			disp.setPreferredSize(new Dimension(width - 275, height));
			disp.setBackground(new Color(0,0,0,0));
			add(BorderLayout.CENTER, disp);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		CardSideNav sideNav = new CardSideNav(275, height, stats);
		add(BorderLayout.LINE_END, sideNav);

	}
	

	
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		int height = getHeight();
		int width = getWidth();
		Rectangle2D rect = new Rectangle2D.Double(0, 0, width, height);
		g2.setColor(playerColor);
		g2.fill(rect);
	}
	
	
}
