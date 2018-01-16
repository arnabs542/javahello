package utils;

public class SwapUtil {

	private void swap(char[] chars, int a, int b) {
		char tmp = chars[a];
		chars[a] = chars[b];
		chars[b] = tmp;
	}
	
}
