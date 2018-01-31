package hw;
/**
 * DP
Fibonacci Number
Get the Kth number in the Fibonacci Sequence. (K is 0-indexed, the 0th Fibonacci number is 0 and the 1st Fibonacci
 number is 1).

Examples

0th fibonacci number is 0
1st fibonacci number is 1
2nd fibonacci number is 1
3rd fibonacci number is 2
6th fibonacci number is 8
Corner Cases

What if K < 0? in this case, we should always return 0.
Is it possible the result fibonacci number is overflowed? We can assume it will not be overflowed when we solve
 this problem on this online judge, but we should also know that it is possible to get an overflowed number,
 and sometimes we will need to use something like BigInteger.

 * @author 
 *
 */
public class FibonacciNumber {
	  public long fibonacci(int K) {
		    if (K < 0) {
		      return 0;
		    }
		    if (K <= 1) {
		      return K;
		    }
		    long a = 0, b = 1;
		    int idx = 2;
		    while (idx <= K) {
		      long tmp = a + b;
		      a = b;
		      b = tmp;
		      idx++;
		    }
		    return b;  // not a!!!!
		  }
	  
	public long fibonacci1(int K) {
        // write your solution here
        if (K <= 0)
          return 0;
        if (K == 1)
          return 1;
        long dp1 = 0, dp2 = 1;  
        int idx = 2;
        while(true) {
          long curr = dp1 + dp2;
          if (idx == K)
            return curr;
          dp1 = dp2;  
          dp2 = curr;
          idx += 1;
        }  
	}         
}
