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
	
	public HUD(int width, int height, Color playerColor) {
		//Inital Setup
		setPreferredSize(new Dimension(width, height));
		this.playerColor = playerColor;

		try {
			CardDisplay disp = new CardDisplay(width, height, playerColor);
			//disp.addMouseListener(new hudRepaintListener());
			disp.setPreferredSize(new Dimension(width, height));
			disp.setBackground(new Color(0,0,0,0));
			add(disp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//SideNav sideNav = new SideNav();
	}
	
	//This will repaint the HUD every time CardDisp class is clicked. It is used to allow for the removing of components from the CardDisp class.
	private class hudRepaintListener extends MouseAdapter {
		public void mousePressed(MouseEvent evt) {
			repaint();
		}
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
