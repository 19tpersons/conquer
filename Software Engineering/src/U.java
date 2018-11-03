
public class U {
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
