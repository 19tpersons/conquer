import java.awt.Color;

/**
 * This is a utility class. It holds data that is essential for the program to function.
 * @author DAT Software Engineering
 *
 */
public class U {
	public static final int width = 1500;
	public static final int height = 800;
	public static final int cardSetLimit = 3;
	public static final int playerLimit = 2;
	public static final int cardWidth = 200;
	public static final int cardHeight = 250;
	public static final int planetMinPop = 50; //This is the minimum amount of population a planet needs.
	
	/**
	 * This method will calculate the dynamic size for any element when given the window's width/height and the percent
	 * @param percent The percent width/height of the element (should be a whole number)
	 * @param size The window width/height
	 * @return The size that is x% the size of the window
	 */
	public static int calcSize(double percent, int size) {
		percent = percent / 100;
		return (int) (size * percent);
	}
	
	/**
	 * This method will calculate where an element should go given a percent and the width/height of the window
	 * @param percent The whole number percent 
	 * @param size The size of the window
	 * @return The location
	 */
	public static int calcPos(double percent, int size) {
		percent = percent / 100;
		return size - (int) (size * percent);		
		
	}
	

}
