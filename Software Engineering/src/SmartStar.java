import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * This class is what the user clicks on if they would like to see the other player's planets or if they would like to fight them.
 * @author Tyler Persons
 *
 */

public class SmartStar extends JPanel{
	private Color color = new Color(255, 102, 0);
	
	public SmartStar(CardSet set, PlayerStats stats) {
		this.color = stats.getColor();
		this.setBorder(BorderFactory.createLineBorder(new Color(255, 102, 0), 2));
		
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				if (set.getCardNumber() == 0) {
					return;
				} else {
					stats.getUser().setModal(new BackDropModal(set, stats.getColor().brighter()));
				}
			}
		});
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(color);
		g2.fillRect(2, 2, 11, 11);
	}

}
