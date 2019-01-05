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
 * @author DAT Software Engineering
 * @date 12.27.18
 *
 */

public class Modal extends JPanel {
	private int modalWidth = 300;
	private int modalHeight = 225;
	private JPanel content;
	
	public Modal() {
		
	}
	
	public Modal(int modalWidth, int modalHeight) {
		this.modalWidth = modalWidth;
		this.modalHeight = modalHeight;
	}
	
	/**
	 * This can be used to display a simple message to the user
	 * @param title the title of the modal
	 * @param message The message to be displayed
	 */
	public Modal(String title, String message) {
		setLayout(null);
		setBackground(new Color(0,0,0,10));
		setOpaque(true);
		
		this.content = new JPanel();
		int x = U.width / 2 - modalWidth / 2;
		int y = U.height / 2 - modalHeight / 2;
		content.setBounds(x, y, modalWidth, modalHeight);
		content.setBackground(Color.ORANGE);
		
		JLabel titleLabel = new JLabel(title);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		content.add(titleLabel);
		content.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		
		JTextArea text = new JTextArea(message);
		text.setPreferredSize(new Dimension(modalWidth - 10, modalHeight)); //Subtracted by 10 so that the border can be seen.
		text.setEditable(false);
		text.setFont(new Font("Arial", Font.BOLD, 22));
		text.setBackground(Color.ORANGE);
		text.setMargin(new Insets(10,10,10,10));
		text.setWrapStyleWord(true);
		text.setLineWrap(true);

		JScrollPane scroll = new JScrollPane(text);
		content.add(scroll);
		
		content.add(Box.createRigidArea(new Dimension(modalWidth - 10, 10)));
		
		add(content);
		
	}
	
	/**
	 * This will return the modal's width.
	 * @return the modal's width
	 */
	public int getModalWidth() {
		return this.modalWidth;
	}
	
	/**
	 * This will return the modal's height.
	 * @return the modal's height.
	 */
	public int getModalHeight() {
		return this.modalHeight;
	}
	
	/**
	 * This will return the modal's content panel.
	 * @return content panel.
	 */
	public JPanel getContent() {
		return this.content;
	}
	
}
