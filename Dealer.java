import java.util.*;

/**
 * The Dealer class is a subclass of Player. Dealer class contains methods of displaying
 * hand and "hit" action that are unique to Dealer. 
 */

public class Dealer extends Player {

    /**
     * Contstructor for Dealer, inherits methods/instance variables of Player class. 
     */
    public Dealer() {
        super();
    }

    /**
     * Adds/deals two cards to Dealer's hand. Prints Dealer hand (while hiding first card).
     * Records instances of dealt cards. 
     * @param deck given Deck object
     */
    public void deal(Deck deck) {
        super.deal(deck);
        System.out.println("Dealer's hand is (Dealer does not show you their first card): " + displayDealerHand());
                                                                                            //displayDealerHand() is unique to Dealer class
        System.out.println();
    } 

    /**
     * Simulates a turn for AI Dealer. If Dealer deicdes to hit, calls Player hitAction method.
     * If Dealer decides to stand, calls stand method. 
     * @param deck a given Deck for a round of Blackjack
     */
    @Override
    public void turn(Deck deck) {
        if (getDeterminer()) {
            System.out.println("DEALER Turn #" + getTurnCount() + ": ");
            if (dealerDecision()) { // dealer hits
                hitAction(deck);              
            } else { // dealer stands
                stand();
            }
        }
    }

    /**
     * Performs hitAction for Dealer and prints Dealer hand (while hiding first card). 
     * @param deck given Deck object
     */
    public void hitAction(Deck deck) {
        super.hitAction(deck);
        System.out.println("Dealer chose to hit.");
        System.out.println("Dealer's hand is (Dealer does not show you their first card): " + displayDealerHand()); 
        System.out.println();                                                             //dealer.displayDealerHand() only belongs to Dealer class
    }

    /**
     * When Dealer decides to stand, prints corresponding message and sets determiner to false.
     */
    public void stand() {
        System.out.println("Dealer chose to stand.");
        super.stand();
    }
    

    /**
     * Returns dealer's hand without showing the first card.
     * @return Dealer's hand (ArrayList<String>) without first card
     */
    public ArrayList<String> displayDealerHand() {
        ArrayList<String> showDealerHand = new ArrayList<>(displayHand()); // creates a copy of Dealer hand
        showDealerHand.remove(0);
        return showDealerHand;
    }

    /**
     * AI implementation for Dealer. Dealer will hit if hand value is below 15
     * and stand if hand value is greater than 15. 
     * @return true if value of hand is less than 15, false otherwise. 
     */
    public Boolean dealerDecision() {
        if (calculateHand(getHand()) < 15) {
            return true;
        } else {
            return false;
        }
    }
}
