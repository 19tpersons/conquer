
public class PopulationGrowth {
	
	/**
	 * This will return the cost (in spacetonium) of upgrading a planets population growth rate
	 * @param curPop The planets current population
	 * @param curRate THe planets current growth rate.
	 * @return The cost to upgrade
	 */
	public static int getGrowthPrice(int curPop) {
		int multiple = 50;
		int factor = 0; //This is the level of price a player will have to pay
		
		if (curPop <= 500) {
			factor = 1;
		} else if (curPop <= 1000) {
			factor = 2;
		} else if (curPop <= 1500) {
			factor = 3;
		} else if (curPop <= 2000) {
			factor = 4;
		} else if (curPop <= 2500) {
			factor = 5;
		} else if (curPop <= 4000) {
			factor = 6;
		} else {
			factor = 9;
		}
		return multiple * factor;
	}
	
	/**
	 * This will return the next rate that a planet growth will go to.
	 * @param curRate The current growth rate for the planet
	 * @return the next growth rate.
	 */
	public static double getNextRate(double curRate) {
		double rate = 0;
		
		if (curRate <= 0.05) {
			rate = 0.1;
		} else if (curRate <= 0.1) {
			rate = 0.15;
		} else if (curRate <= 0.15) {
			rate = 0.2;
		} else if (curRate <= 0.2) {
			rate = 0.25;
		} else if (curRate <= 0.25) {
			rate = 0.3;
		} else {
			rate = curRate * 1.25;
		}
		return rate;
	}
}
