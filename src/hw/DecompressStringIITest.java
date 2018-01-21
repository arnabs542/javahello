package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DecompressStringIITest {

	@Test
	void testDecompress_case1() {
		DecompressStringII sol = new DecompressStringII();
		String input = "a1c0c2";
		String answer = "acc";
		String result = sol.decompress_inplace(input);
		assertEquals(answer, result);
	}
	
	@Test
	void testDecompress_case2() {
		DecompressStringII sol = new DecompressStringII();
		String input = "a1c0b2c4";
		String answer = "abbcccc";
		String result = sol.decompress_inplace(input);
		assertEquals(answer, result);
	}
	
}
