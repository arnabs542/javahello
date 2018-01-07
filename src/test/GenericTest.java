package test;

import java.util.ArrayList;

public class GenericTest {

	
	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<>();
		a.add(1);
		a.add(2);
		for (int val : a) {
			System.out.println(val);
		}
	}
}
