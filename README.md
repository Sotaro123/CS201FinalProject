# CS201FinalProject

## Overview
This folder contains the java files Deck, Player, User, Dealer, and Blackjack, which combine to create a simulation of 
the card game Blackjack. The simulation allows the user to: 
* Play a maximum of 5 rounds of the Blackjack card game (or until the user has less than $50)
* Simulate the betting aspect of casino-style Blackjack. 

## Usage
After compiling all files, the usage for this program is as follows:

$ java Blackjack
Welcome to Blackjack! You can play 5 times at maximum.
You currently have $1000. You can bet from $50.
Enter how much you want to bet: 
$ 500
You bet $500

Your hand is: [Q, K]
Dealer's hand is (Dealer does not show you their first card): [6]

Turn #1: 
hit or stand?
$ stand
You chose to stand.

Dealer chose to hit.
Dealer's hand is (Dealer does not show you their first card): [6, 2]

Turn #2: 
Dealer chose to stand.

Your hand is: [Q, K]
Dealer's hand is: [7, 6, 2]

You won!

You currently have $1500. You can bet from $50.

## Rubric Items
- Inheritance: The Dealer and User classes inherit from the Player class.
- HashMap: The Deck class implements two HashMaps, which are declared and instantiated in lines 11-12. The HashMaps
are used to record information about card values and the number of times each card has appeared. 
- Queue: The Blackjack class implements a Queue (declared and instantiated in line 16) to keep track of whose
turn it is in a round of Blackjack. 

## What does your project do that is interesting and substantive?
Our project is a simulation of the popular card game, Blackjack. The simulation closely mimicks several aspects of 
casino-style Blackjack, including the dealer hiding their first card and the betting action that occurs at the beginning
of each round. The program also ensures that a card never appears more than 4 times, which is identical to a real 
52-card deck. Overall, users can use this program to experience a realistic version of Blackjack as preparation to 
play for real money. 

## Why is inheritance useful for your previously specified superclass and subclasses?
The Dealer and User classes inherit from the Player class. This is efficient because the Dealer and User are technically
both Players (in the sense that they both play the game and want to win). Therefore, the two subclasses share many methods,
such as deal, hit, and displayHand. Implementing inheritance allows us to have these shared methods in both subclasses without 
needing to redefine them. 

## For each of the two additional previously-specified class concepts that you used, why is that concept the best to use in your project?
1. HashMap: The Deck class uses two separate HashMaps. The first HashMap (deck) keeps track of the value of each card. In Blackjack, 
the J, Q, and K cards are each worth 10. The best way to record this information was to have an Integer value that corresponds to each
card in the deck, and using a HashMap allows us to do so. The second HashMap in the Deck class (cardCount) is used to keep track of the
number of times a card appears in a round of Blackjack, specifically to make sure that a single card doesn't appear more than 4 times. 
The cardCount HashMap is utilized in the same manner as a tally chart, where we add a tally (increase the count by 1) next to a card 
each time it appears. 

2. Queue: The Blackjack class implements a Queue to help determine whose turn it is during a round of Blackjack. Although there are only
two players in our simulation, turns function in a rotating manner in Blackjack. This means that the Player who has been waiting the 
longest for their turn should be the next to play. The perfectly aligns with the First In First Out principle that Queues are based on. 
This property of Queues allows the program to easily identify whether the User or Dealer is next up to play and act accordingly.
