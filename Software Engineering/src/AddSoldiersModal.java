import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This will allow a player to add troops to their army.
 * @author Tyler Persons
 * @date 12.31.18
 *
 */

public class AddSoldiersModal extends Modal {
	private int modalWidth = 300;
	private int modalHeight = 200;
	
	public AddSoldiersModal(PlayerStats stats, Card card) {
		setBackground(new Color(0,0,0,0));
		setLayout(null);
		
		JPanel content = new JPanel();
		int x = U.width / 2 - modalWidth / 2;
		int y = U.height / 2 - modalWidth / 2;
		content.setBounds(x, y, modalWidth, modalHeight);
		content.setBackground(Color.ORANGE);
		
		JLabel title = new JLabel();
		title.setText("Add men to army!");
		content.add(title);
		
		JSlider slider = new JSlider(0, card.getPop(), 0);
		slider.setMajorTickSpacing(1);
		slider.setPreferredSize(new Dimension(modalWidth - 30, 50));
		slider.setPaintTicks(true);
		content.add(slider);
		
		JButton submit = new JButton("Submit");
		submit.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				int change = slider.getValue();
				card.removePop(change);
				try {
					card.defineBack();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stats.removePop(change);
				stats.addSoliders(change);
				stats.getUser().getPane().getHud().getSideNav().updateStats();
				stats.getUser().hideModal();
			}
		});
		content.add(submit);
		
		add(content);
	}
}
