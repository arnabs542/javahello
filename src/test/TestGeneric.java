package test;

import java.util.ArrayList;
import java.util.List;

public class TestGeneric {
	public static void main(String[] args) {
		List<Integer> a = new ArrayList();
		a.add(1);
		// a.add('c'); will raise compile error.
	}
}
