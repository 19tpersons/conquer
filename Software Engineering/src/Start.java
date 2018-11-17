import java.awt.*;
import javax.swing.*;

public class Start extends JPanel {

	public static void main(String[] args) {
		JFrame window = new JFrame("Java!");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//window.setLocation(120,70);
		window.setSize(1500,800);
		window.setResizable(false);
		Start start = new Start(1500, 800);
		window.setContentPane(start); 
		window.setVisible(true);
	}
	public int width,height;
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		
	}
	
	public Start(int width, int height) {
		GamePane pane = new GamePane(width, height);
		add(pane);
	}
	

	


}
