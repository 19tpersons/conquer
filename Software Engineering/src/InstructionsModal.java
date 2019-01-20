import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class InstructionsModal extends Modal {
	private static int width = 900;
	private static int height = 700;
	private JPanel content;
	
	public InstructionsModal() {
		super(width, height);
		
		this.setBackground(new Color(0,0,0, 90));
		this.setLayout(null);
		
		content = new JPanel();
		content.setBounds((U.width / 2) - (width / 2), (U.height / 2) - (height / 2), width, height);
		content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
		content.setBackground(Color.ORANGE);
		content.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		
		add(content);
		
		try {
			throw new Exception("ERROR: HERE");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This will return the modal's content panel.
	 * @return content panel.
	 */
	public JPanel getContent() {
		return this.content;
	}
}
