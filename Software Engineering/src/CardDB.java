import java.awt.*;
import java.io.*;

import javax.swing.*;

public class CardDB {
	private String file = "cards.csv";
	
	public CardDB() throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		br.readLine();
	}
}
