package hw;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class APowerBTest {
	APowerB apb = new APowerB();

	@Test
	@DisplayName("case1_dpname")
	void test_case1() {
		int a = 2, b = 0;
		long ans = 1;
		long res = apb.power(a, b);
		assertEquals(ans, res);
	}
	
	@Test
	void test_case2() {
		int a = 2, b = 3;
		long ans = 8;
		long res = apb.power(a, b);
		assertEquals(ans, res);
	}
	
	@Test
	void test_case3() {
		int a = 0, b = 10;
		long ans = 0;
		long res = apb.power(a, b);
		assertEquals(ans, res);
	}
	
	@Test
	void test_case4() {
		int a = -2, b = 5;
		long ans = -32;
		long res = apb.power(a, b);
		System.out.println(res);
		assertEquals(ans, res);
	}

}
