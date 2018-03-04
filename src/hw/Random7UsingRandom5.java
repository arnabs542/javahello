package hw;

public class Random7UsingRandom5 {
	public int random7_self() {
		while (true) {
			int val = RandomFive.random5() * 5 + RandomFive.random5();
			if (val < 21) {
				return val % 7;
			}

		}
		// return 0; -- unreachable statement
	}

	// On lc.com, you can directly call RandomFive.random5() to
	// get a random number in the range of 0 - 4.
	public int random7() {
		while (true) {
			// to generate a uniformly distributed 0-24 number.
			int random = 5 * RandomFive.random5() + RandomFive.random5();
			// we only care about the first 21 numbers and should
			// ignore and try again for the numbers >= 21.
			if (random < 21) {
				return random % 7;
			}
		}
	}
}
