import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.*;

/**
 * This is the current way to start a game.
 * @author Tyler Persons
 *
 */
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
		
		Color[] colors = {new Color(0, 0, 204), Color.RED};
		RootGameControl root = new RootGameControl(2, colors);
		root.startGame();
		add(root);
		
		/*
		User one = new User(width, height, new Color(0, 0, 204));
		one.showPane();
		add(one);
		*/
		
	}
	

	


}
