import java.awt.Color;

public class U {
	public static final int width = 1500;
	public static final int height = 800;
	public static final int cardSetLimit = 3;
	public static final int cardWidth = 200;
	public static final int cardHeight = 250;
	
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
     * Make a color brighten. From stackoverflow
     *
     * @param color Color to make brighten.
     * @param fraction Darkness fraction.
     * @return Lighter color.
     */
    public static Color brighten(Color color, double fraction) {

        int red = (int) Math.round(Math.min(255, color.getRed() + 255 * fraction));
        int green = (int) Math.round(Math.min(255, color.getGreen() + 255 * fraction));
        int blue = color.getBlue();
        //int blue = (int) Math.round(Math.min(255, color.getBlue() + 255 * fraction));

        int alpha = color.getAlpha();

        return new Color(red, green, blue, alpha);

    }
}
