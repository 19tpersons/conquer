import javax.swing.*;
import java.awt.*;

public class GamePane extends JPanel {
	public GamePane(int width, int height) {
		//Inital Setup
		setPreferredSize(new Dimension(width, height));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		Backdrop drop = new Backdrop(width, height / 2);
		drop.setPreferredSize(new Dimension(width, height / 2));
		add(drop);
		
		HUD content = new HUD(width, height / 2);
		add(content);
		
	}
}
