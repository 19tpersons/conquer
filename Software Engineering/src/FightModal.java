import java.awt.*;
import javax.swing.*;

public class FightModal extends Modal {
	private int width = 400;
	private int height = 350;
	private JPanel content;
	
	public FightModal(PlayerStats stats, Card card) {
		//Sets up jpanel
		super(400, 350);
		setBackground(new Color(0,0,0,0));
		setLayout(null);
		
		content = new JPanel();
		int x = U.width / 2 - width / 2; //Center the modal horz.
		int y = U.height / 2 - height / 2; //Center the modal vert.
		content.setBounds(x, y, width, height);
		content.setBackground(new Color(230, 0, 0));
		
		
	}
	
	/**
	 * This will return the modal's content panel.
	 * @return content panel.
	 */
	public JPanel getContent() {
		return this.content;
	}
}
