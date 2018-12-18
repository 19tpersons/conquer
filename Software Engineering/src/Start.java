import java.awt.*;
import java.io.FileNotFoundException;

import javax.swing.*;

public class Start extends JPanel {

	public static void main(String[] args) {
		JFrame window = new JFrame("Conquer!");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(U.width, U.height);
		window.setResizable(false);
		
		//This starts the game
		Start start = new Start(U.width, U.height);
		window.setContentPane(start); 
		window.setVisible(true);
	}
	
	public int width,height;
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		
	}
	
	public Start(int width, int height) {
		//This is used by the rest of the program
		try {
			CardDB cardDB = new CardDB();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FlowLayout flow = new FlowLayout();
		flow.setVgap(-5);
		setLayout(flow);
		
		User one = new User(new Color(0, 0, 204));
		one.showPane();
		add(one);
	}
	

	


}
