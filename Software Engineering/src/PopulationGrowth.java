
public class PopulationGrowth {
	
	/**
	 * This will return the cost (in spacetonium) of upgrading a planets population growth rate
	 * @param curPop The planets current population
	 * @param curRate THe planets current growth rate.
	 * @return The cost to upgrade
	 */
	public static int getGrowthPrice(int curPop, double curRate) {
		return 5;
	}
	
	/**
	 * This will return the next rate that a planet growth will go to.
	 * @param curRate The current growth rate for the planet
	 * @return the next growth rate.
	 */
	public static double getNextRate(double curRate) {
		return 5;
	}
}
