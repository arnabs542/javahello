package hw;

/**
 * 
 * String 
 * Determine If One String Is Another's Substring 
 * 
 * Determine if a small
 * string is a substring of another large string.
 * 
 * Return the index of the first occurrence of the small string in the large
 * string.
 * 
 * Return -1 if the small string is not a substring of the large string.
 * 
 * Assumptions
 * 
 * Both large and small are not null 
 * If small is empty string, return 0 
 * 
 * Examples
 * 
 * "ab" is a substring of "bcabc", return 2 
 * "bcd" is not a substring of "bcabc", return -1 
 * "" is substring of "abc", return 0
 * 
 * @author
 *
 *
 *  Notice:
 *  1. There is no assumptions about the charset used in the String,
 *  so that we can not assume we are using 26blower case characters.
 *
 *  2. This is the most correct version of RabinKarp in computer programming,
 *  we need to handle 1). we could use arbitrary charset, 2). the overflow case,
 *  by taking the module operation on a very large number.
 *
 *  3. You probably do not need to write this kind of solution to handle above
 *  two cases, if you are in an interview. But it is still necessary to understand
 *  the reason behind it.
 *
 *
 *
 */

//
// 1) There is no assumption about the charset used in the String,

public class DetermineIfOneStringIsAnothersSubstring {

    // Method 1: Naive solution.
    public int strstrI(String large, String small) {
        if (large.length() < small.length()) {
            return -1;
        }
        if (small.length() == 0) {
            return 0;
        }
        for (int i = 0; i <= large.length() - small.length(); i++) {
            if (equals(large, i, small)) {
                return i;
            }
        }
        return -1;
    }

    public boolean equals(String large, int start, String small) {
        for (int i = 0; i < small.length(); i++) {
            if (large.charAt(i + start) != small.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    // Method 2: RabinKarp
    public int strstrII(String large, String small) {
        if (large.length() < small.length()) {
            return -1;
        }
        if (small.length() == 0) {
            return 0;
        }
        for (int i = 0; i <= large.length() - small.length(); i++) {
            if (equals(large, i, small)) {
                return i;
            }
        }
        // We need a large prime as module end.
        int largePrime = 101;
        // we also need a small prime to claculate the hash value
        // (since the charset would be very large, e.g. 1,112,064 for using UTF,
        // we can not use that number).
        int prime = 31;
        int seed = 1;
        // hash value is calculated using the smallPrime and taken the module
        // operation on largePrime.
        // hash = (s0 * smallP^k + s1*smallP^(k-1) + ... + sk*smallP^0) % largeP
        int targetHash = small.charAt(0) % largePrime;
        for (int i = 1; i < small.length(); i++) {
            seed = moduleHash(seed, 0, prime, largePrime);
            targetHash = moduleHash(targetHash, small.charAt(i), prime, largePrime);
        }
        int hash = 0;
        for (int i = 0; i < small.length(); i++) {
            hash = moduleHash(hash, large.charAt(i), prime, largePrime);
        }
        if (hash == targetHash && equals(large, 0, small)) {
            return 0;
        }
        for (int i = 1; i <= large.length() - small.length(); i++) {
            // we need to make sure the module number is non-negative.
            hash = nonNegative(hash - seed * large.charAt(i - 1) % largePrime, largePrime);
            hash = moduleHash(hash, large.charAt(i + small.length() - 1), prime, largePrime);
            // Notice: if the has matches, it does not mean we really find a substring match.
            // Because there is collision, we need to check if the substring really matches the small string.
            // On average, this will not increase the time complexity, the probability
            // of collision is O(1) so we still have O(N + M).
            if (hash == targetHash && equals(large, i, small)) {
                return i;
            }
        }
        return -1;
    }

    public int moduleHash(int hash, int addition, int prime, int largePrime) {
        return (hash * prime % largePrime + addition) % largePrime;
    }

    public int nonNegative(int hash, int largePrime) {
        if (hash < 0) {
            hash += largePrime;
        }
        return hash;
    }

}
