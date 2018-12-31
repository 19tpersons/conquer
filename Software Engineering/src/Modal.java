import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

/**
 * This class is the parent class of all modals in the game.
 * @author Tyler Persons
 * @date 12.27.18
 *
 */

public class Modal extends JPanel {
	private int modalWidth = 300;
	private int modalHeight = 225;
	
	public Modal() {
		
	}
	
	/**
	 * This can be used to display a simple message to the user
	 * @param title the title of the modal
	 * @param message The message to be displayed
	 */
	public Modal(String title, String message) {
		setLayout(null);
		setBackground(new Color(0,0,0,90));
		
		JPanel content = new JPanel();
		int x = U.width / 2 - modalWidth / 2;
		int y = U.height / 2 - modalHeight / 2;
		content.setBounds(x, y, modalWidth, modalHeight);
		content.setBackground(Color.ORANGE);
		
		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		content.add(titleLabel);
		
		JTextArea text = new JTextArea(message);
		text.setPreferredSize(new Dimension(modalWidth, modalHeight));
		text.setEditable(false);
		text.setFont(new Font("Arial", Font.BOLD, 22));
		text.setBackground(Color.ORANGE);
		text.setMargin(new Insets(10,10,10,10));
		text.setWrapStyleWord(true);
		text.setLineWrap(true);
		content.add(text);
		
		add(content);
	}
	

}