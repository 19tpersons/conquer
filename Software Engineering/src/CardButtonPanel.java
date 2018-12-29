import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.*;

/**
 * This is the series of buttons that appear when a user hovers over the icon of a planet card.
 * @author Tyler Persons
 * @date 12.28.18
 */
public class CardButtonPanel extends JPanel {
	private JPanel buttonPanel = new JPanel();

	public CardButtonPanel(int width, int height, PlayerStats stats, Card card) {
		this.setBackground(new Color(0,0,0,0));
		
		buttonPanel.setBackground(new Color(255,255,255,50)); //TODO: Make the buttons!
		buttonPanel.setPreferredSize(new Dimension(width, height));
		
		JButton soldiersBtn = new JButton("Add Soldiers");
		soldiersBtn.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				stats.getUser().setModal(new AddSoldiersModal(stats, card));
			}
		});
		buttonPanel.add(soldiersBtn);
		
		add(buttonPanel);
	}
}
