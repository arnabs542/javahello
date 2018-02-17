package hw.ood.blackjack;

public class Card {
	private final int faceValue; // 1 for A, 11 for J, 12 for Q, 13 for K. or we can use enum here.
	private final Suit suit;
	
	public Card(int c, Suit s) {
		faceValue = c;
		suit = s;
	}
	
	public int value() {
		return faceValue;
	}
	
	public Suit suit() {
		return suit;
	}
}
