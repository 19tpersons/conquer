import java.awt.*;
import javax.swing.*;

/**
 * This is the modal that will be used to display a SmartStar's cards.
 * @author Tyler Persons
 *
 */

public class BackDropModal extends Modal {
	private static int width = 800; 
	private static int height = 300;
	
	/**
	 * This is the default constructor.
	 * @param set The cardset that will be displayed.
	 */
	public BackDropModal(CardSet set, Color playerColor) {
		super(width, height);
		
		setBackground(new Color(0,0,0,0));
		setLayout(null);
		
		JPanel content = new JPanel();
		int x = U.width / 2 - width / 2;
		int y = U.height / 2 - height / 2;
		content.setBounds(x, y, width, height);
		content.setBackground(playerColor);
		
		set.clearSetIcon();
		set.displayCards();
		content.add(set);

		add(content);
	}
}
