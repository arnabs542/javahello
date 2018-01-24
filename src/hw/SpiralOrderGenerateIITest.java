package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SpiralOrderGenerateIITest {

	@Test
	void testSpiralGenerate_case1() {
        SpiralOrderGenerateII sol = new SpiralOrderGenerateII();
        int m = 3, n = 2;
        int[][] answer = new int[][] {{1, 2},{6, 3}, {5, 4}};
        int[][] result = sol.spiralGenerate(m, n);
        assertEquals(answer.length, result.length);
        for (int i = 0; i < answer.length; i++) {
        		assertArrayEquals(answer[i], result[i]);
        }
      }
	
	@Test
	void testSpiralGenerate_case2() {
        SpiralOrderGenerateII sol = new SpiralOrderGenerateII();
        int m = 1, n = 1;
        int[][] answer = new int[][] {{1}};
        int[][] result = sol.spiralGenerate(m, n);
        assertEquals(answer.length, result.length);
        for (int i = 0; i < answer.length; i++) {
        		assertArrayEquals(answer[i], result[i]);
        }
      }

}
