import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * This is the abstraction of all of the actions any user can take. It holds their individual statistics and game interface.
 * @author Tyler Persons
 *
 */
public class User extends JPanel {
	private int width, height;
	private PlayerStats stats;
	private GamePane pane;
	private Modal modal;
	private JLayeredPane layered = new JLayeredPane();

	public User(int width, int height, Color playerColor) {
		this.width = width;
		this.height = height;
		
		layered.setPreferredSize(new Dimension(width, height));
		layered.setLayout(null);
		layered.setBackground(Color.blue);
		
		this.stats = new PlayerStats(playerColor);
		stats.setUser(this);
		this.pane = new GamePane(stats);
		pane.setBounds(0,0, width, height);
		
		add(layered);
	}
	
	

	/**
	 * If there is a modal that the game wants to display they just use this.
	 * @param modal
	 */
	public void setModal(Modal modal) {
		this.modal = modal;
		modal.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent evt) {
				layered.remove(modal);
				revalidate();
				repaint();
			}
		});
		modal.setBounds(0,0, width, height);
		layered.add(modal, 0, 0);
		modal.requestFocus();
		repaint();
	}
	
	/**
	 * This clears the modal from the screen.
	 */
	public void hideModal() {
		layered.remove(this.modal);
		revalidate();
		repaint();
	}
	
	/**
	 * This will show the user's game pane.
	 */
	public void showPane() {
		layered.add(pane, 0);
		revalidate();
		repaint();
	}
	
	/**
	 * This will return this user's game panel
	 * @return the game panel
	 */
	public GamePane getPane() {
		return this.pane;
	}

}
