package hw.ood.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
	protected final List<Card> cards = new ArrayList<>();
	
	public int score() { // or we can define an abstract method here, i.e. no implementation for score()
		int score = 0;
		for (Card card : cards) {
			score += card.value();
		}
		return score;
	}
	
	public void addCards(Card[] c) {
		Collections.addAll(cards,  c);
	}
	
	public int size() {
		return cards.size();
	}
}
