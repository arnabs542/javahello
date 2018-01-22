package hw_practice;

public class TestStack {

	public static void main(String[] args) {
		Solution sol = new Solution();
		sol.printAll(100000); // Exception in thread "main" java.lang.StackOverflowError
	}
}

class Solution {
	public void printAll(int n) {
		if (n <= 0) {
			return;
		}
		printAll(n-1);
		System.out.println(n);
	}
}