package hw.ood.blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * game status s1 --> rules + actions --> s2 --> check end --> continue....
 * 
 * 1. understand the rules
 * 2. capture game status
 * 3. model game procedure
 * 
 * ******************************************************
 * 
 *  For BlackJack,
 *  简化：所有玩家dealer模式，score < 16 will continue to add card
 *  a. equals to after init. only one round ??
 *  b. every player in this round will continue to add card and check end
 *
 * 
 * 
 * TODO: for split situations, introduce 'Player' class, 
 * handle one playe with multiple hands logic
 *
 *
 *
 */
public class BlackJackGameAutomator {
	private Deck deck;
	private BlackJackHand[] hands;
	private static final int HIT_UNTIL = 16;
	
	public BlackJackGameAutomator(int numPlayers) {
		hands = new BlackJackHand[numPlayers];
		for (int i = 0; i < numPlayers; i++) {
			hands[i] = new BlackJackHand();
		}
	}
	
	void initializeDeck() {
		deck = new Deck();
		deck.shuffle();
	}
	
	// if successfully init, return true, otherwise false
	boolean dealInitial() {
		for (BlackJackHand hand : hands) {
			Card[] cards = deck.dealHand(2);
			if (cards == null) {
				return false;
			}
			hand.addCards(cards);
		}
		return true;
	}
	
	// get the indexes of hands that are winning
	List<Integer> getBlackJacks() {
		List<Integer> winners = new ArrayList<>();
		for (int i = 0; i < hands.length; i++) {
			if (hands[i].isBlackJack()) {
				winners.add(i);
			}
		}
		return winners;
	}
	
	// hand play
	boolean playHand(BlackJackHand hand) {
		while (hand.score() < HIT_UNTIL) {
			Card card = deck.dealCard();
			if (card == null) {
				return false;
			}
			hand.addCards(new Card[] {card});
		}
		return true;
	}
	
	boolean playAllHands() {
		for (BlackJackHand hand : hands) {
			if (!playHand(hand)) {
				return false;
			}
		}
		return true;
	}
	
	List<Integer> getWinners() {
		List<Integer> winners = new ArrayList<>();
		int winningScore = 0;
		for (int i = 0; i < hands.length; i++) {
			BlackJackHand hand = hands[i];
			if (!hand.busted()) {
				if (hand.score() > winningScore) {
					winningScore = hand.score();
					winners.clear();
					winners.add(i);
				} else if (hand.score() == winningScore) {
					winners.add(i);
				}
			}
		}
		return winners;
	}
	
	void printHandsAndScore() {
		for (int i = 0; i < hands.length; i++) {
			System.out.print("Hand " + i + "(" + hands[i].score() + "): ");
			// hands[i].print();
			System.out.println();
		}
	}
	
	public void simulate() {
		initializeDeck();
		
		boolean success = dealInitial();
		if (!success) {
			System.out.println("Error. Out of cards");
		} else {
			System.out.println("-- Initial --");
			printHandsAndScore();
			List<Integer> blackjacks = getBlackJacks();
			
			// for there is winners after init
			if (blackjacks.size() > 0) {
				System.out.println("Blackjack at ");
				for (int i: blackjacks) {
					System.out.print(i + " ");
				}
				System.out.println("done.");
			} else {
				// if no blackjack yet, continue to play
				success = playAllHands();
				printHandsAndScore();
				List<Integer> winners = getWinners();
				if (winners.size() > 0) {
					System.out.print("Winners: ");
					for (int i : winners) {
						System.out.print(i + " ");
					}
					System.out.println();
				} else {
					System.out.println("Draw. All players have busted.");
				}
			}
		}
	}
	
	// A simple test case.
	public static void main(String[] args) {
		BlackJackGameAutomator automator = new BlackJackGameAutomator(5);
		automator.simulate();
	}
}
