import javax.swing.*;
import java.awt.*;

/**
 * This is the class that holds all other classes that display components of a players turn.
 * @author Tyler Persons
 * @date 12.27.18
 *
 */
public class GamePane extends JPanel {
	private int width = U.width;
	private int height = U.height;
	private Backdrop drop;
	private HUD content;
	
	public GamePane(PlayerStats stats) {
		//Inital Setup
		setPreferredSize(new Dimension(width, height));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
		
		//This is the top half of the display
		drop = new Backdrop(width, height / 2);
		drop.setPreferredSize(new Dimension(width, height / 2));
		add(drop);
		
		//This is the bottom half of the display
		content = new HUD(width, height / 2, stats);
		add(content);
		
	}
	
	/**
	 * This will return the GamePane's hud
	 * @return the hud
	 */
	public HUD getHud() {
		return content;
	}
	
	/**
	 * This will return the GamePane's BackDrop
	 * @return the backdrop
	 */
	public Backdrop getBackDrop() {
		return drop;
	}
	
}
