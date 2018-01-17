package hw;

/**
 * String Right Shift By N Characters Right shift a given string by n
 * characters.
 * 
 * Assumptions
 * 
 * The given string is not null. n >= 0.
 * 
 * @author
 *
 */
public class RightShiftByNCharacter {
	  // Assumptions: input is not null, n >= 0
	  public String rightShift(String input, int n) {
	    if (input.length() <= 1) {
	      return input;
	    }
	    n %= input.length();
	    if (n == 0) {
	      return input;
	    }
	    char[] chars = input.toCharArray();
	    // reverse the whole string -->
	    // (right most n chars become left most n chars, left part similar, 
	    // only intra-part order is reversed.)
	    // reverse the shifted part on [0: n)
	    // reverse the the right part on [n:]
	    reverse(chars, 0, chars.length - 1);
	    reverse(chars, 0, n - 1);
	    reverse(chars, n, chars.length - 1);
	    return new String(chars); // !!! don't forget
	  }
	  
	  private void reverse(char[] chars, int left, int right) {
			for (int i = left, j = right; i < j; i++, j--) {
				char tmp = chars[i];
				chars[i] = chars[j];
				chars[j] = tmp;
			}
		}
}
