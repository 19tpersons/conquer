import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.AbstractBorder;


public class Button extends JPanel {
	public Button(String buttonText) {
		JLabel text = new JLabel(buttonText);
		text.setFont(new Font("Arial", Font.BOLD, 18));
		text.setForeground(Color.WHITE);

		add(text);
	}
}
