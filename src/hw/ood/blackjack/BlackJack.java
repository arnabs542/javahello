package hw.ood.blackjack;

// Is it necessary to extend Card? Not really necessary...
// different scoring should be put to BlackJackHand
public class BlackJack extends Card{
	public BlackJack(int c, Suit s) {
		super(c, s);
	}
}
