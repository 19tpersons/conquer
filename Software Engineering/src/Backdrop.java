import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D; 


public class Backdrop extends JPanel {
	private int width, height;
	
	public Backdrop(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		Rectangle2D backdrop = new Rectangle2D.Double(0,0, width, height);
		g2.fill(backdrop);
	}
	
	
}
