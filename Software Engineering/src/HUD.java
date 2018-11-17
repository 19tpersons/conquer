import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
import java.io.IOException;

public class HUD extends JPanel {
	public HUD(int width, int height) {
		//Inital Setup
		setPreferredSize(new Dimension(width, height / 2));
		
		CardSet cardSet = null;
		try {
			cardSet = new CardSet();
			cardSet.setPreferredSize(new Dimension(width, height));
		} catch (IOException e) {
			e.printStackTrace();
		}
		add(cardSet);
		

	}
	
	
}
