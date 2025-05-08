import java.util.HashMap;
import java.util.*;

/**
 * Deck class is used to assign numerical values to card values using a HashMap. Deck
 * class also uses a HashMap to keep track of the number of each card that has been played 
 * in Blackjack game since a card can only appear four times (one time for each suit). 
 */

public class Deck {

       private HashMap <String, Integer> deck = new HashMap<String, Integer>(); 
       private HashMap<String, Integer> cardCount = new HashMap<String, Integer>();

       /**
        * Deck constructor initially adds keys and values to deck and cardCount HashMaps. 
        All cardCount values are initially set to 0. 
        */
       public Deck() {
        deck.put("2", 2); 
        deck.put("3", 3); 
        deck.put("4", 4); 
        deck.put("5", 5); 
        deck.put("6", 6); 
        deck.put("7", 7); 
        deck.put("8", 8); 
        deck.put("9", 9); 
        deck.put("10", 10); 
        deck.put("11", 10); // J, Q, K are considered 10 in Blackjack. 
        deck.put("12", 10); 
        deck.put("13", 10); 
        deck.put("1", 11); // as a default, Ace is considered 11. 

        cardCount.put("2", 0); 
        cardCount.put("3", 0); 
        cardCount.put("4", 0); 
        cardCount.put("5", 0); 
        cardCount.put("6", 0); 
        cardCount.put("7", 0); 
        cardCount.put("8", 0); 
        cardCount.put("9", 0); 
        cardCount.put("10", 0); 
        cardCount.put("11", 0);  
        cardCount.put("12", 0); 
        cardCount.put("13", 0); 
        cardCount.put("1", 0); 
       }

       /**
        * Returns value of a given card. 
        * @param card given card (String)
        * @return value of given card (Integer)
        */
        public Integer getValue(String card) {
            return deck.get(card);
        }

        /**
         * Alters cardCount (HashMap) to record an instance of given card 
         * @param card given card (String)
         */
        public void addCardCount(String card) {
            cardCount.put(card, cardCount.get(card) + 1);
        }

        /**
         * Checks whether a card has appeared 4 times in Blackjack game.
         * @param card given card
         * @return false if card has appeared more than 4 times or true otherwise. 
         */
        public Boolean checkCardCount(String card) {
            if (cardCount.get(card) > 4) {
                return false;
            } else {
                return true;
            }
        }   
}