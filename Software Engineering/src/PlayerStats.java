import java.awt.Color;
import java.util.ArrayList;

/**
 * This class holds all of the data for a user.
 * @author Tyler Persons
 * @date 12.31.18
 *
 */

public class PlayerStats {
	private int totalPop = 0;
	private int armySize = 0;
	private int maxArmySize = 0;
	private int resourceCount = 0;
	private int turnStage = -1;
	private Color playerColor;
	private User user;
	public ArrayList<CardSet> sets = new ArrayList<CardSet>();
	
	/**
	 * This is the default constructor
	 * @param playerColor This the players color
	 */
	public PlayerStats(Color playerColor) {
		this.playerColor = playerColor;

	}
	
	/**
	 * This constructor give a player default population, army size, and resource counts
	 * @param pop Player's population size
	 * @param armySize Size of the player's army
	 * @param resources The amount of resources a player has.
	 */
	public PlayerStats(int pop, int armySize, int resources) {
		this.totalPop = pop;
		this.armySize = armySize;
		this.resourceCount = resources;
	}
	
	/**
	 * This method will return the amount of resources a player has.
	 * @return The amount of resource
	 */
	public int getResource() {
		return resourceCount;
	}
	
	/**
	 * This method will add resources to a player's total count.
	 * @param additive the amount of resources to add
	 * @return The amount of resource
	 */
	public int addResources(int additive) {
		resourceCount += additive;
		return resourceCount;
	}
	
	/**
	 * This method will subtract resources from a player's total count.
	 * @param sub the amount of resources to subtract
	 * @return The amount of resource
	 */
	public int removeResources(int sub) {
		resourceCount -= sub;
		return resourceCount;
	}
	
	/**
	 * This will add more people to a players population
	 * @param additive The number of people to be added
	 * @return the new population
	 */
	public int addPop(int additive) {
		totalPop += additive;
		return totalPop;
	}
	
	/**
	 * This will remove population from a player
	 * @param sub The number of people to remove
	 * @return The new population
	 */
	public int removePop(int sub) {
		totalPop -= sub;
		return totalPop;
	}
	
	/**
	 * This will return the total population of the player's civilization
	 * @return total population
	 */
	public int getPopulation() {
		return totalPop;
	}
	
	/**
	 * This will add soliders to the army
	 * @param additive The number of soliders to be added
	 * @return The new army size
	 */
	public int addSoliders(int additive) {
		armySize += additive;
		return armySize;
	}
	
	/**
	 * This will subtract soliders to the army
	 * @param sub The number of soliders to be added
	 * @return The new army size
	 */
	public int removeSoliders(int sub) {
		armySize -= sub;
		return armySize;
	}
	
	/**
	 * This will return the size of the player's army
	 * @return army size
	 */
	public int getArmySize() {
		return armySize;
	}
	
	/**
	 * Get the player's color
	 */
	public Color getColor() {
		return playerColor;
	}
	
	/**
	 * This method returns the current stage of the turn
	 * @return 1 => draw, 2 => build, 3 => fight, -1 => Not their TURN
	 */
	public int getStage() {
		return turnStage;
	}
	
	/**
	 * Sets the turn to the next phase. -1 => 1, 1 => 2, 2 => 3, 3 => -1
	 * Phase 1: Draw
	 * Phase 2: Build
	 * Phase 3: Fight
	 * Phase -1: Not your TURN
	 */
	public void nextStage() {
		turnStage++;
		if (turnStage > 3) {
			turnStage = -1;
		}else if (turnStage == 0) {
			turnStage = 1;
		}
		
	}
	
	/**
	 * This will add another card set to the arraylist
	 * @param set the new set to be added
	 */
	public void addCardSet(CardSet set) {
		this.sets.add(set);
	}
	
	/**
	 * This will set the user class for this player
	 * @param user The user class
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * This will returns this player's user class.
	 * @return
	 */
	public User getUser() {
		return this.user;
	}
}
