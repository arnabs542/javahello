package hw;

public interface Random1000UsingRandom5 {

	public int random1000_self() {
	    // Write your solution here.
	    // you can use RandomFive.random5() for generating
	    // 0 - 4 with equal probability.
	    // basic idea, 5^4 = 6** < 1000, 5^5 = 3125 > 1000
	    while (true) {
	    	int val = 0;
	      for (int i = 0; i < 5; i++) {
	      	val = val * 5 + RandomFive.random5();
	      }
	      if (val < 3000) {
	      	return val % 1000;
	      }
	    }
	    // return 0; -- unreachable statement
	}

	public int random1000() {
		while (true) {
			// The following 4 lines of code is usually used to compute 
			// a0*x^0 + a1*x^1 + a2*x^2 + a3*x^3 + ... + ak*x^k
			int num = 0;
			for (int i = 0; i < 5; i++) {
				num = num * 5 + RandomFive.random5();
			}
			// choose 3000 instead of 1000 to reduce the # of expected random5() calls.
			if (num < 3000) {
				return num % 1000;
			}
		}
	}
}
