import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Write a description of class StartForGame here.
 *
 * @author (Alex Gergen)
 * @version (11-18-18)
 */
public class StartForGame extends JPanel implements ActionListener
{
    /**
     * A main routine allows this class to be run as an application.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("The Master Plan");
        StartForGame content = new StartForGame();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //window.setLocation(250,200);
        
        //window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //window.setUndecorated(true);
        
        window.pack();
        window.setResizable(true);
        window.setVisible(true);
    }
    //Color selectedColor = Color.GRAY;
    
    public StartForGame()  {
       //setBackground(Color.BLUE);
       setLayout(new BorderLayout());
       JLabel background=new JLabel(new ImageIcon("test3.jpg"));
       add(background);
       setPreferredSize(new Dimension(1500, 800));
       //background.setLayout(new GridLayout(10,1,3,3));
       background.setLayout(new FlowLayout(FlowLayout.CENTER));
              
       JPanel buttonBar;
       JButton button;
       
       
       
       buttonBar = new JPanel();
       //buttonBar.setBackground(Color.GRAY);
       background.add(buttonBar);
       
       
       button = new JButton("New Game");
       //button.setAlignmentX(Component.CENTER_ALIGNMENT);
       //button.setAlignmentY(Component.CENTER_ALIGNMENT);
       //button.setPreferredSize(new Dimension(20, 5)); 
       button.addActionListener(this);
       buttonBar.add(button);
       
       button = new JButton("Load Game");
       //button.setAlignmentX(Component.CENTER_ALIGNMENT);
       button.addActionListener(this);
       buttonBar.add(button);
       
       button = new JButton("Settings");
       //button.setAlignmentX(Component.CENTER_ALIGNMENT);
       button.addActionListener(this);
       buttonBar.add(button);
       
       button = new JButton("Credits");
       //button.setAlignmentX(Component.CENTER_ALIGNMENT);
       button.addActionListener(this);
       buttonBar.add(button);
       
       button = new JButton("Quit");
       //button.setAlignmentX(Component.CENTER_ALIGNMENT);
       button.addActionListener(this);
       buttonBar.add(button);
       
       setSize(1099,399);
       setSize(1500,800);
       
       //buttonBar.setLocation(100,30);
       
       //setBorder(BorderFactory.createLineBorder(Color.GRAY,3));
    }
    
    /**
    * Respond to a button click by showing a dialog and setting the 
    * message label to describe the user's response.
    */
   public void actionPerformed(ActionEvent evt) {

      String command = evt.getActionCommand();
      if (command.equals("New Game")) {
         //message.setText("Displaying message dialog.");
         JOptionPane.showMessageDialog(this,
             "This is the button that will start the game.");
         //message.setText("You closed the message dialog.");
      }
      else if (command.equals("Load Game")) {
         //message.setText("Displaying message dialog.");
         JOptionPane.showMessageDialog(this,
             "This is the button that will load the game.");
         //message.setText("You closed the message dialog.");
      }
      else if (command.equals("Settings")) {
         //message.setText("Displaying message dialog.");
         JOptionPane.showMessageDialog(this,
             "This is the button that will show the settings.");
         //message.setText("You closed the message dialog.");
      }
      else if (command.equals("Credits")) {
         //message.setText("Displaying message dialog.");
         JOptionPane.showMessageDialog(this,
             "The Master Plan\nCreated by:\nTyler Persons\nAlex Gergen\nDakota Edens");
         //message.setText("You closed the message dialog.");
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
          //System.exit(0);
        
        
      }
    }
}
