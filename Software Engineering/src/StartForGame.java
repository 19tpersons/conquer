
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
/**
 * This is the start of our game. It is the first thing the user will see.
 *
 * @author DAT Software Engineering
 */
public class StartForGame extends JPanel implements ActionListener
{
	private int volume = 50;

	/**
     * A main routine allows this class to be run as an application.
     */
    public static void main(String[] args) {   
    	
        JFrame window = new JFrame("Conquer!");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(U.width, U.height);
		window.setResizable(false);
        
		StartForGame start = null;
		try {
			start = new StartForGame();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		window.setContentPane(start);
		window.pack();
        window.setVisible(true);
        
        //Change the standard output so that all errors and game analytics will be printed to a file.
        try {
        	//Clear the gameAnalytics file so that we can put new statistics in for this game.
        	File gameAnalytics = new File("gameAnalytics.log");
        	PrintWriter writer = new PrintWriter(gameAnalytics);
        	writer.print("");
        	writer.close();
        	
			System.setOut(new PrintStream(gameAnalytics));
			
			System.setErr(new PrintStream(new File("error.log")));
		} catch (FileNotFoundException e) {
			//The premissions for the game are probably off?
			e.printStackTrace();
		}
        
        BackgroundMusic music = new BackgroundMusic(); //The games music
    }
    
    public StartForGame() throws InterruptedException {
    	//This moves the JPanel up fix pixels to remove any white space
		FlowLayout flow = new FlowLayout();
		flow.setVgap(-5);
		flow.setHgap(0);
		setLayout(flow);
		       
   		//This is the background image
		JLayeredPane layered = new JLayeredPane();
		layered.setPreferredSize(new Dimension(U.width, U.height));
		layered.setLayout(new FlowLayout());
		
      	JLabel content = new JLabel(new ImageIcon(U.getFile("Background picture #9.png")));
      	content.setPreferredSize(new Dimension(U.width, U.height));
      	//content.setLayout(new FlowLayout(FlowLayout.CENTER));
      	content.setLayout(new GridBagLayout());
      	
      	//This holds all of the buttons on the start up screen.
      	JPanel buttonBar = new JPanel();
      	buttonBar.setBackground(Color.BLACK);
       	
       //This will start a new game.
       Button button = new Button("New Game");
       button.addMouseListener(new MouseAdapter() {
    	   public void mousePressed(MouseEvent evt) {
    		   System.out.println("Starting the game");
    		   
    			//This sets up the cards that will be used for the rest of the game.
    			CardDB cardDB = new CardDB();
    			
    			//These are the colors that each player will have
    			Color[] colors = {new Color(0, 0, 204), new Color(0, 153, 51)};
    			
    			//This will start the game.
    			RootGameControl root = new RootGameControl(2, colors);
    			root.setPreferredSize(new Dimension(U.width, U.height));
    			root.startGame();
    			
    			//Display the game.
    			layered.remove(content);
    			layered.add(root, 0, 0);
    			repaint();
    			revalidate();
    	   }
       });
       buttonBar.add(button);
       
       //This will show the instructions for the game
       button = new Button("Instructions");
       button.addMouseListener(new MouseAdapter() { //This will add the Instructions to the screen
    	   public void mousePressed(MouseEvent evt) {
    		   InstructionsModal modal = new InstructionsModal(); //This holds the game's instructions
    		   modal.setPreferredSize(new Dimension(U.width, U.height));
    		   modal.addMouseListener(new MouseAdapter() { //If someone clicks on the gray area the modal will close
    			   public void mousePressed(MouseEvent evt) {
    				   Point p = new Point(evt.getLocationOnScreen());
    				   Modal modal = (Modal) evt.getComponent();
    				   SwingUtilities.convertPointFromScreen(p, modal.getContent());
    				   
    				   if (!modal.getContent().contains(p)) {
	    					  layered.remove(modal);
	    					  layered.revalidate();
	    					  layered.repaint();
    				   }
    			   }
    		   });
    		   
    		   layered.add(modal, 0);
    		   repaint();
    		   revalidate();
    	   }
       });
       buttonBar.add(button);
       
       //This will credit the producers
       button = new Button("Credits");
       button.addActionListener(this);
       buttonBar.add(button);
       
       //This will exit the game.
       button = new Button("Quit");
       button.addActionListener(this);
       buttonBar.add(button);

       content.add(buttonBar);

       //This is the panel that holds the publisher animation
       JPanel publisher = new JPanel();
       publisher.setPreferredSize(new Dimension(U.width, U.height));
       publisher.setLayout(new BoxLayout(publisher, BoxLayout.Y_AXIS));
       JTextPane publisherPane = new JTextPane();
       
       //This is the game publisher styling
       StyledDocument doc = publisherPane.getStyledDocument();
       SimpleAttributeSet center = new SimpleAttributeSet();
       StyleConstants.setFontFamily(center, "Courier"); //Font
       StyleConstants.setFontSize(center, 175); //Size
       StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER); //Center Alignment
       doc.setParagraphAttributes(0, doc.getLength(), center, false);
       //This sets the text
       publisherPane.setEditable(false); 
       publisherPane.setBackground(Color.BLACK);
       publisherPane.setForeground(Color.BLACK);
       publisherPane.setText("Cognitive Thought Media Presents"); //Publisher
       publisher.add(publisherPane);
       
       Timer publisherFadeIn = new Timer(20, new ActionListener() {
    	   Color fg = new Color(255, 255, 255, 0);
    	   public void actionPerformed(ActionEvent arg0) {
    		   if (fg.getAlpha() < 255) {
    			   fg = new Color(fg.getRed(), fg.getGreen(), fg.getBlue(), fg.getAlpha() + 5); //Fade in white
    			   publisherPane.setForeground(fg);
    		   } 
    	   }
    	  
       });
       publisherFadeIn.start();
       
       layered.add(publisher, 0);
       //After 3 seconds we will get rid of the publisher name
       Timer timer = new Timer(4000, new ActionListener() {
    	   public void actionPerformed(ActionEvent arg0) {
    		   layered.remove(publisher);
    		   layered.revalidate();
    		   layered.repaint();	
    		   publisherFadeIn.stop();
    	   }
       });
       timer.setRepeats(false);
       timer.start();
       
       layered.add(content, 2);
       add(layered);       
    }
    
    /**
    * Respond to a button click by showing a dialog and setting the 
    * message label to describe the user's response.
    */
   public void actionPerformed(ActionEvent evt) {

      String command = evt.getActionCommand();
      if (command.equals("Credits")) {
         JOptionPane.showMessageDialog(this,
             "The Master Plan\nCreated by:\nDAT Software Engineering\nDakota Edens\nAlex Gergen\nTyler Persons");
      }
      else if (command.equals("Quit")) {
          int response = JOptionPane.showConfirmDialog(this,
             "Do you really want to quit?.\n" );
         switch(response) {
            case JOptionPane.YES_OPTION: 
              System.exit(0);
               break;
            case JOptionPane.NO_OPTION: 
               
               break;
            case JOptionPane.CANCEL_OPTION: 
               
               break;
            case JOptionPane.CLOSED_OPTION: 
               break;
               
            default: 
                //default action or exception
                break;
         }
        
        
      }
    }
}

