import java.net.URL;

/**
 * This is a utility class. It holds data that is essential for the program to function.
 * @author DAT Software Engineering
 *
 */
public class U {
	//These statistics are basic configurations for the game.
	public static final int width = 1500;
	public static final int height = 800;
	public static final int cardWidth = 200;
	public static final int cardHeight = 250;
	public static final int cardSetLimit = 3;
	public static final int playerLimit = 2;
	
	//These statistics are used in the balancing of the game.
	public static final int planetMinPop = 50; //This is the minimum amount of population a planet needs.
	public static final int soldierContributionCost = 20; //It cost x resources per 10 million added troops.
	public static final int popUnitAmt = 10; //This is the unit of measurement population for the game is in
	public static final double startPopChangeRate = 0.05;
	
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
	
	/**
	 * If this program is to be compiled as a jar it needs to be loaded in a special way.
	 * @param name
	 * @return
	 */
	public static URL getFile(String name) {
		return U.class.getResource(name);
	}

	

}
