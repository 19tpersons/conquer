import javax.swing.*;
import java.awt.*;

public class GamePane extends JPanel {
	private int width = U.width;
	private int height = U.height;
	
	public GamePane(PlayerStats stats) {
		//Inital Setup
		setPreferredSize(new Dimension(width, height));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
		
		Backdrop drop = new Backdrop(width, height / 2);
		drop.setPreferredSize(new Dimension(width, height / 2));
		add(drop);
		
		HUD content = new HUD(width, height / 2, stats);
		add(content);
		
	}
}
