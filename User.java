import java.util.*;
import java.util.Scanner;
/*
 * The User class is a subclass of Player. User class contains methods pertaining
 * to the betting aspect of the game, which is unique to the User. 
 */

public class User extends Player {
    
    private Integer money = 1000; // user starts with $1000
    private Scanner scanner = new Scanner(System.in);
    
    /**
     * Constructor for User, inherits methods/instance variables of Player class. 
     */
    public User() {
        super();
    }

    /**
      * Adds/deals two cards to User's hand and prints hand. Records instances of dealt cards.
      * @param deck given Deck object
     */
    public void deal(Deck deck) {
        super.deal(deck);
        System.out.println("Your hand is: " + displayHand());
    } 

    /**
     * Simulates a turn for User. Uses Scanner object to take in user input. 
     * Depending on user input, performs hitAction or stand method. 
     * @param deck given Deck for a round of Blackjack
     */
    @Override
    public void turn(Deck deck) {
        if (getDeterminer()) {
            System.out.println("YOUR Turn #" + getTurnCount() + ": ");
            System.out.println("hit or stand?"); 
            String nextOperation = scanner.next(); 
            if (nextOperation.equals("hit")) { // user hits
                hitAction(deck);
            } else if (nextOperation.equals("stand")) { // user stands
                stand();
            }   
        }
    }
    

    /**
     * Performs hitAction for User. Prints User hand. If User has busted,
     * sets determiner and prints accordingly.
     * @param deck given Deck object
     */
    public void hitAction(Deck deck) {
        super.hitAction(deck);
        System.out.print("You chose to hit. ");
        System.out.println("Your hand is: " + displayHand());
        if (getBusted().equals(true)) { // User busted
            System.out.println("You busted!");
            setDeterminer(false);
        }
        System.out.println();
    }

    /**
     * When User decides to stand, prints corresponding message and sets determiner to false.
     */
    public void stand() {
        System.out.println("You chose to stand.");
        super.stand();
    }

    /**
     * Initial action of game; subtracts User's bet from total amount of money.
     * @param betValue the amount of money that User wants to bet
     * @return Integer value of User bet.
     */
    public Integer bet(Integer betValue) {
        money = money - betValue; 
        return betValue; 
    }

    /**
     * Returns amount of money that User currently has. 
     * @return value of money user currently has
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * Changes the amount of money User has depending on value of bet and result
     * of game. Returns the remaining amount of money that User has. Works with 
     * helper method determineWinner (Blackjack method).
     * @param betValue amount of money that User wants to bet
     * @param result result of a single round of Blackjack game (Integer)
     * @return amount of money remaining after round of Blackjack has been played
     */
    public Integer changeMoney(Integer betValue, Integer result) {
        if (result == 3 || result == 5) {
            money = money + (betValue * 2);
            return money; 
        } else if (result == 1 || result == 6) {
            money = money + betValue;
            return money; 
        } else {
            return money; 
        }
    }  
}
