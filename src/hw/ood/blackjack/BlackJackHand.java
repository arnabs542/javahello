package hw.ood.blackjack;

import java.util.ArrayList;
import java.util.List;

//BlackJack score rules:
//1 ~ 10 scores its face value
//J, Q, K score 10
//A scores either 1 or 11
public class BlackJackHand  extends Hand{
	@Override
	public int score() {
		List<Integer> scores = possibleScores();
		int maxUnder = Integer.MIN_VALUE; // max score <= 21
		int minOver = Integer.MAX_VALUE; // min score > 21
		for (int score: scores) {
			if (score > 21 && score < minOver) {
				minOver = score;
			} else if (score <= 21 && score > maxUnder) {
				maxUnder = score;
			}
		}
		// --> if there is possible score that under 21, take it; otherwise, maxUnder is Integer.MIN_VALUE
		// --> have to be the minOver value. The result is to find the most benifical value for self.
		// --> either maxUnder or minOver (will be compared with other hands)
		return maxUnder == Integer.MIN_VALUE ? minOver : maxUnder;
	}
	
	// --> because cards like Ace will have multiple possible values,
	// find all possible scores for current hand.
	private List<Integer> possibleScores() {
		List<Integer> scores = new ArrayList<>();
		for (Card card : cards) {
			updateScores(card, scores);
		}
		return scores;
	}
	
	// for a new card, update the possible scores
	// if the new card have multiple possible score
	// will extend the scores length to record all possible
	// total scores.
	private void updateScores(Card card, List<Integer> scores) {
		final int[] toAdd = getScores(card);
		if (scores.isEmpty()) {
			for (int score : toAdd) {
				scores.add(score);
			}
		} else {
			final int length = scores.size();
			// for every existing scores,
			for (int i = 0; i < length; i++) {
				int oldScore = scores.get(i);
				// update scores[i] with the first score of new card
				scores.set(i, oldScore + toAdd[0]);
				// for other score of new card, add new total score 
				// with scores[i] as old score and toAdd[j] as incrementing score
				for (int j = 1; j < toAdd.length; j++) {
					scores.add(oldScore + toAdd[j]);
				}
			}
		}
	}
	
	// get possible scoring value for a card
	private int[] getScores(Card card) {
		if (card.value() > 1) {
			return new int[] {Math.min(card.value(), 10)};
		} else {
			// Ace
			return new int[] {1, 11};
		}
	}
	
	public boolean busted() {
		return score() > 21;
	}
	
	// check if the current hand is blackjack
	public boolean isBlackJack() {
		if (cards.size() != 2) {
			return false;
		}
		Card first = cards.get(0);
		Card second = cards.get(1);
		return (isAce(first) && isFaceCard(second) || (isAce(second) && isFaceCard(first)));
	}
	
	private static boolean isAce(Card c) {
		return c.value() == 1;
	}
	
	private static boolean isFaceCard(Card c) {
		return c.value() >= 11 && c.value() <= 13;
	}
	
}
