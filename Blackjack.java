import java.util.Scanner;
import java.util.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Blackjack class provides implementation needed to Blackjack game and handles 
 * the main functionality of the simulation.
 * @author Sotaro Wakabayashi
 * @author Daniel Kim
 */

public class Blackjack {
    private Queue<Player> queue = new LinkedList<>();
    private Integer countPlay = 0; // counts number of rounds that user has played
    private Scanner scanner = new Scanner(System.in);   
    private Integer betValue;     
    private User myUser = new User(); 
    private Dealer myDealer = new Dealer(); 


    /**
     * Adds two Player objects to queue to represent the order in which
     * turns should occur. At the beginning of the game, User will go first,
     * and then Dealer's turn will occur. Deals two cards to User and Dealer.
     * @param deck given Deck for a round of Blackjack.
     */
    public void firstTurn(Deck deck) {
        queue.add(myUser); 
        queue.add(myDealer); 
        myUser.deal(deck); // deal two cards to User    
        myDealer.deal(deck); // deal two cards to Dealer
    }

    /**
     * Handles functionality for a single round of Blackjack. While loop will end when both User and
     * Dealer indicate that they would like to stand. Uses queue.poll() to identify whose
     * turn it is and remove them from the queue. Uses queue.add() to add Player back 
     * to the queue. Calls Player method turn() to simulate a turn for each player in Blackjack.
     * @param deck given Deck for a round of Blackjack.
     */
    public void gamePlay(Deck deck) {
        while (myUser.getDeterminer() == true || myDealer.getDeterminer() == true) { // while either player has another turn left
            Player currentPlayer = queue.poll(); // whose turn it is, User or Dealer
            queue.add(currentPlayer); // add the player object back to the queue so that it keeps track of the turn
            currentPlayer.turn(deck); // the actual operation (hit or stand) in each subclass, depending on which class object queue has in the head. 
        }
    }

    /**
     * Prints the result of a round of Blackjack, given User and Player
     * hands.
     * @return result (String) of a round of Blackjack
     */
    public void displayResult() { 
        System.out.println("Your hand is: " + myUser.displayHand());
        System.out.println("Dealer's hand is: " + myDealer.displayHand());
        System.out.println(); 
        Integer result = determineWinner();
        if (result == 1) {
            System.out.println("Both you and the dealer busted. It's a tie.");
        } else if (result == 2) {
            System.out.println("You busted, and the dealer won.");
        } else if (result == 3) {
            System.out.println("The dealer busted, and you won!");
        } else if (result == 4) {
            System.out.println("You lost.");
        } else if (result == 5) {
            System.out.println("You won!");
        } else if (result == 6) {
            System.out.println("It's a tie.");
        }
    }

    /**
     * Uses Player method calculateHand to determine the value of User and 
     * Dealer hands. Returns a String representing the outcome of a round
     * of Blackjack. Helper method for displayResult and changeMoney(User 
     * method).
     * @return String indicating result for User
     */
    public Integer determineWinner() {
        Integer userSum = myUser.calculateHand(myUser.getHand()); 
        Integer dealerSum = myDealer.calculateHand(myDealer.getHand()); 

        if (myDealer.getBusted() && myUser.getBusted()) {
            return 1; // both Players busted; tie
        } else if (dealerSum <= 21 && myUser.getBusted()) {
            return 2; // User busted, Dealer wins
        } else if (myDealer.getBusted() && userSum <= 21) {
            return 3; // Dealer busted, User wins
        } else if (dealerSum > userSum) {
            return 4; // Dealer wins
        } else if (userSum > dealerSum) {
            return 5; // User wins
        } else {
            return 6; // tie
        }
    }
    
    /**
     * Initiates each round of Blackjack game. Takes in amount of money User
     * is betting and records value.
     * Calls the firstTurn method to set up a round of Blackjack. 
     */
    public void startRound(Deck deck) {
        System.out.println("You currently have $" + myUser.getMoney() + ". You can bet from $50.");
        System.out.println("Enter how much you want to bet: ");
        betValue = scanner.nextInt(); 
        System.out.println("You bet $" + myUser.bet(betValue));
        System.out.println();

        firstTurn(deck); 
    }

    /**
     * Indicates the end of a round of Blackjack. Clears User and Dealer hand. 
     * Clears turn queue and increments countPlay (which checks how many rounds
     * have been played) by 1. Changes amount of money User has depending on result
     * of Blackjack round.
     */
    public void endRound() {
        displayResult();
        myUser.changeMoney(betValue, determineWinner()); // change User money depending on result
        myUser.reset();
        myDealer.reset();
        queue.clear();
        countPlay++;
        System.out.println();
    }

    /**
     * Prints output at the end of a Blackjack game depending on User money amount. 
     */
    public void endGame() {
        if (myUser.getMoney() < 50) {
            System.out.println("You cannot bet anymore haha! Loser!"); // run out of money
        } else {
            System.out.println("The game is done! You have $" + myUser.getMoney() + ".");
        }
    }

    /**
     * Main method contains implementation for Blackjack gameplay. User can play a 
     * maximum of 5 rounds of Blackjack or play until they have less than $50. 
     * @param args array of String arguments from terminal. 
     */
    public static void main(String[] args) {
        Blackjack myBlackjack = new Blackjack(); 
        System.out.println("Welcome to Blackjack! You can play 5 times at maximum.");

        while (myBlackjack.myUser.getMoney() >= 50 && myBlackjack.countPlay < 5) {
            Deck myDeck = new Deck(); // at the start of each new round, create and use a new Deck
            myBlackjack.startRound(myDeck);
            myBlackjack.gamePlay(myDeck);
            // myBlackjack.gamePlay(myDeck); // this method ends when both players decide to stand
            myBlackjack.endRound();
        } 
        myBlackjack.endGame();
    }
}
