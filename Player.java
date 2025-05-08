import java.util.*;

/**
 * The Player class is a superclass of both the Dealer and User class. The Player
 * class contains methods that interact with the Player's hand, representing actions
 * in the Blackjack game.
 */

public class Player {

    private ArrayList<String> hand = new ArrayList<String>(); 
    private Random rand = new Random(); 
    private Deck referenceDeck = new Deck(); 
    private Boolean determiner = true; //indicator of whether Player has another turn left, initially true
    private Boolean busted = false; 
    private Integer turnCount = 0;


    /**
     * Adds/deals two cards to Dealer's hand.
     * @param deck a Deck for a round of Blackjack
     */
    public void deal(Deck deck) {
        for (int i = 0; i < 2; i++) {
            String randomCard = hit();
            addHand(randomCard);
            deck.addCardCount(randomCard);
        }
    }

    /**
     * When a Player decides to hit, checks to determine whether a card (String) 
     * has already appeared four times in a round of Blackjack. If card has already 
     * appeared four times, generates a new card to add to Player hand. Checks if 
     * Player has busted.
     * @param deck given Deck for a round of Blackjack
     */
    public void hitAction(Deck deck) {
        String currentCard = hit(); 
        deck.addCardCount(currentCard); // record instance of card
        while (deck.checkCardCount(currentCard) == false) { // while currentCard has appeared more than 4 times
            currentCard = hit(); // deals a new card instead
            deck.addCardCount(currentCard); // record instance of card
        }
        addHand(currentCard);
        if (calculateHand(hand) > 21) { // if Player has busted
            busted = true;
        }
    }



    /**
     * Resets Player instance variables in order to play a new round of Blackjack.
     */
    public void reset() {
        hand.clear();
        busted = false;
        determiner = true;
        turnCount = 0;
    }

    
    /**
     * Adds given card to Player's hand.
     * @param card (String) being added to Player hand 
     */
    public void addHand(String card) {
        hand.add(card); 
    }
    
    /**
     * Uses convertCard to convert each card in a Player's hand
     * from numeric format to card format. Returns a new ArrayList<String> 
     * with card values only.
     * @return showHand (ArrayList<String>) to be displayed to user
     */
    public ArrayList<String> displayHand() { 
        ArrayList<String> showHand = new ArrayList<>();
        for (String card : hand) {
            showHand.add(convertCard(card));
        }
        return showHand;
    }

    /**
     * Returns the current state of the determiner.
     * @return Boolean determiner of Player
     */
    public Boolean getDeterminer() {
        return determiner;
    }

    /**
     * Sets determiner to Boolean depending on given input. Used to indicate
     * when Player decides to stand and does not have another turn left. 
     * @param myBoolean a given Boolean
     */
    public void setDeterminer(Boolean myBoolean) {
        determiner = myBoolean;
    }

    /**
     * Returns the Boolean indicating whether Player has busted (hand value is 
     * greater than 21).
     * @return Boolean indicator of state of busted
     */
    public Boolean getBusted() {
        return busted;
    }

    /**
     * Returns the current turnCount for Player. Increments turnCount 
     * each time it is called. 
     * @return number of turns Player has had (Integer)
     */
    public Integer getTurnCount() {
        turnCount++;
        return turnCount;
    }

    /**
     * Empty method for calling the inherited method in each subclass when being called
     * in gamePlay method in Blackjack class. 
     */
    public void turn(Deck deck) {}


    
    /**
     * Sets the determiner false when player stands. 
     */
    public void stand() {
        System.out.println();
        setDeterminer(false);
    }

    


    /**
     * Converts card from numeric value to card value. Specifically used
     * for face cards (J, Q, K, A). If given card is not a face card,
     * returns inputted String. 
     * @param card given card (String) that needs to be converted
     * @return converted card (String)
     */
    public String convertCard(String card) {
        if (card.equals("11")) {
            return "J";
        } else if (card.equals("12")) {
            return "Q";
        } else if (card.equals("13")) {
            return "K";
        } else if (card.equals("1")) {
            return "A";
        } else { // if card is not a face card, simply return the card
            return card;
        }
    }

    /**
     * Returns the total value of Player hand. 
     * @param hand given Player hand
     * @return total value of Player hand (Integer)
     */
    public Integer calculateHand(ArrayList<String> hand) {
        Integer sum = 0; 
        Integer countAce = 0; 

        for (String card : hand) {
            sum = sum + referenceDeck.getValue(card); 
            if (card.equals("1")) { 
                countAce = countAce + 1; 
            }
        }

        while (sum > 21 && countAce > 0) {
            sum = sum - 10; // If the sum is greater than 21 and hand has Ace, it now considers Ace 1 instead of 11. 
            countAce = countAce - 1; // Reduce the count of Ace so that it does not decrease the sum unnecessarily. 
        }
        return sum; 
    }
 
    /**
     * Selects a random card to add to Player hand when Player chooses to hit. 
     * @return String value of drawn card
     */
    public String hit() {
        int randomCard = rand.nextInt(1, 14); // random # from 1 to 13
        return String.valueOf(randomCard); // just return the card (add in addHand method)
    }

    /**
     * Returns current Player hand.
     * @return Player hand (ArrayList<String>)
     */
    public ArrayList<String> getHand() {
        return hand; 
    }
}
