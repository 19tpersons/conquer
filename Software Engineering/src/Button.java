import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;


public class Button extends JButton {
	
	public Button(String buttonText) {
		FlowLayout layout = new FlowLayout();
		layout.setVgap(2);
		layout.setHgap(2);
		this.setLayout(layout); //sets the vertical and horizontal gaps.
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		setBackground(Color.WHITE);
		setOpaque(true);
		
		setText(buttonText);
		setFont(new Font("Arial", Font.BOLD, 20));
		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				setBackground(new Color(217, 217, 217));
			}
			
			public void mouseExited(MouseEvent evt) {
				setBackground(Color.WHITE);
			}
			
		});
	}
	
}
