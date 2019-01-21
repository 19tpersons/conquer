import java.awt.Color;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 * This class is the modal that displays the game's instructions on the starting screen.
 * 
 * @author DAT Software Engineering
 *
 */

public class InstructionsModal extends Modal {
	private static int width = 900;
	private static int height = 700;
	private JPanel content;
	private JTextPane pane;
	
	public InstructionsModal() {
		super(width, height);
		
		this.setBackground(new Color(0,0,0, 90));
		this.setLayout(null);
		
		content = new JPanel();
		content.setBounds((U.width / 2) - (width / 2), (U.height / 2) - (height / 2), width, height);
		content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
		content.setBackground(Color.ORANGE);
		content.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		
		//The instructions
		pane = new JTextPane();
		pane.setEditable(false);
		
		//Title heading
		SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setBold(attrs, true);
        StyleConstants.setFontSize(attrs, 28);
        StyleConstants.setAlignment(attrs, StyleConstants.ALIGN_CENTER);
        pane.setCharacterAttributes(attrs, true);
        pane.setMargin(new Insets(10,10,10,10));
        pane.setText("Instructions\n\n");
        
        addHeading("Interface Overview");
        String overview = "\tThe interface is comprised of two halves, the galaxy screen, upper halve, and the card screen, the lower halve. The card screen is where you will see all of you cards. The first layer of cards will be you solar systems (i.e. card sets). If you click on a card, it will bring you to the planets in that solar system. Clicking on the + by the name will bring up the information on that card. To deselect a planet click on the GREEN BUTTON, and to deselect a system click anywhere but the card.  On the bottom right corner of the draw screen you will see the current information of you civilization.\r\n" + 
        				  "\tThe solar sytem screen is where you will see other players planets. If you click on the opposing colors tiny square, you will be able to see the planets in there solar systems. Note: if there are currently no planets in the field you will be GIVEN A ERROR.\r\n";
        addText(overview);
        
        addText("\n*The game has a total of three phases.");
        
        addHeading("Draw Phase");
        String text = "In this phase you will get a random card. This card can be three different things. \r\n" + 
        		"1) A Solar system, these allow you to hold planets. \r\n" + 
        		"2) A planet, this is where your population and resources are. \r\n" + 
        		"3) A random event, these will cause changes in population such as a meteor strike or a plague. All new planets and systems will be visible in the card screen.\r\n";
        addText(text);
        
        addHeading("Build Phase");
        text = "\tIn this phase you will train unit to go into battle. NOTE: you can only do this in the build phase! To do this simply click on a solar system to access the planet you want to assign troops from. Then at the top of the planet click the button add soldiers. Then adjust the slider for however many troops you would like to train. NOTE: training units costs resources so train wisely.";
        addText(text);
        
        addHeading("Fight Phase");
        text = "\tIn this phase you will be able to put your military to the test and fight the other player! NOTE: You can only fight other planets in the fight phase. To fight an enemy planet you first have to click on the system you want to attack in the galaxy screen. Then on the planet you want to attack click on the Fight button at the top of the card. Then adjust the slider picking how many troops you want to send in to the fight. After you either win or lose information will pop up telling you the results of the battle. NOTE: If a planet doesn’t have a fight button on it that means that they have no army.";
        addText(text);
        
        JScrollPane scroll = new JScrollPane(pane); //Adds a scroll bar
        content.add(scroll);
		add(content);
		
	}
	
	/**
	 * This minimizes the amount of codes reuse by allowing header- with the exact same stylistic parameters-
	 * to be added multiple times.
	 * @param heading The new heading to be added to the document.
	 */
	private void addHeading(String heading) {
		SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setBold(attrs, true);
        StyleConstants.setFontSize(attrs, 22);
        
        Document doc = pane.getStyledDocument();  
        try {
			doc.insertString(doc.getLength(), heading + "\n", attrs);
		} catch (BadLocationException e) {
			//Something happened to the doc?
			e.printStackTrace();
		}  
	}
	
	/**
	 * This minimizes the amount of codes reuse by allowing text to be added- with the exact same stylistic parameters-
	 * multiple times.
	 * @param text The new text to be added to the document.
	 */
	public void addText(String text) {
		SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setFontSize(attrs, 18);
        
        Document doc = pane.getStyledDocument();  
        try {
			doc.insertString(doc.getLength(), text + "\n", attrs);
		} catch (BadLocationException e) {
			//Something happened to the doc?
			e.printStackTrace();
		}
	}
	
	/**
	 * This will return the modal's content panel.
	 * @return content panel.
	 */
	public JPanel getContent() {
		return this.content;
	}
}
