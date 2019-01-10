import java.util.Random;

/**
* This class will preform a fight between two players.
* @author DAT Software Engineering
*/


public class Battle {
    private int defense, offense; //These are the numbers the class is going to used to calculate who will win the battle
    
    /**
     * This is used to calculate who will win a battle.
     * @param defensePop The original population of the defending system.
     * @param defenseBonus If the defense has a bonus, this is it.
     * @param offensePop The original population attacking the systemm.
     */
    public Battle(int defensePop, double defenseBonus, int offensePop) {
        this.defense = getDefensiveBonus(defensePop, defenseBonus); //Adds the bonus to the defensive army size
        this.offense = getOffensiveBonus(offensePop, defense); //Adds the bonus to the offensive army size.
        
        this.fight(defense, offense); //This will fight.
    
        
    }

    /**
     * This will return the defensive bonus this player will get.
     * @return new army size with bonus
     */
    private int getDefensiveBonus(int pop, double bonus) {
        return pop + (int) (pop * bonus);
    }
    
    /**
     * This will return the offensive bonus this player will get.
     * @return new army size with bonus
     */
    private int getOffensiveBonus(int offense, int defense) {
        double bonusRate = 0.05; //This will be multiplied by the bonus at the end to determine the new army size.
        double popRatio = offense / defense;
        
        //This will evaluate the popRatio to see how much of a bonus the offensive player will get
        int bonus = 0;
        if (popRatio < 1) {
            bonus = -2;
        } else if (popRatio == 1) {
            bonus = 0;
        } else if (popRatio <= 1.1) {
            bonus = 1;
        } else if (popRatio <= 1.25) {
            bonus = 2;
        } else if (popRatio <= 1.5) {
            bonus = 4;
        } else if (popRatio <= 2) {
            bonus = 6;
        } else {
            bonus = 10;
        }
        
        int popBonus = offense * (int) (bonus * bonusRate); //This is the number of additional population the army will get.
        return offense + popBonus; //This is the new army size.
    }
    
    private void fight(int defense, int offense) {
        int regimentSize = 10; //A regiment is 10 million troops
        
        int defenseRegiments = defense / 10;
        int offenseRegiments = offense / 10;
        
        Random rand = new Random();
        while (defenseRegiments > 0 && offenseRegiments > 0) {
            int winner = rand.nextInt(2);
            if (winner == 0) { //If winner is equal to 1, the offensive opponent won this round
                defenseRegiments -= 1;
            } else { //Otherwise, the deffensive player won.
                offenseRegiments -= 1;
            }
        }
        
       int defenseRegimentsLost = (defense / 10) - defenseRegiments;
       int offenseRegimentsLost = (offense / 10) - offenseRegiments;
       
       //This will update the army sizes
       this.offense -= regimentSize * offenseRegimentsLost;
       this.defense -= regimentSize * defenseRegimentsLost;
       
       //Since double's are used something needs to fix any rounding issues
       if (this.defense <= regimentSize) {
    	   this.defense = 0;
       }
       
    }
    
    /**
     * This will return the defense army size after the fight
     * @return the new defense army size
     */
    public int getDefenseSurvivors() {
        return defense;
    }
    
    /**
     * This will return the offense army size after the fight
     * @return the new offense army size.
     */
    public int getOffenseSurvivors() {
        return offense;
    }
}