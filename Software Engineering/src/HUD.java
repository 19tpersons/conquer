import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.io.IOException;

public class HUD extends JPanel {
	
	
	public HUD(int width, int height) {
		//Inital Setup
		setPreferredSize(new Dimension(width, height));

		try {
			CardDisplay disp = new CardDisplay(width, height);
			disp.setPreferredSize(new Dimension(width, height));
			disp.setBackground(new Color(0,0,0,0));
			add(disp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		int height = getHeight();
		int width = getWidth();
		Rectangle2D rect = new Rectangle2D.Double(0, 0, width, height);
		g2.setColor(new Color(0, 0, 204));
		g2.fill(rect);
	}
	
}
