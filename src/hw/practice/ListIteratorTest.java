package hw.practice;

import java.util.LinkedList;
import java.util.ListIterator;

public class ListIteratorTest {
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		ListIterator<Integer> it = list.listIterator();

		// System.out.println("initial : " + it.previous()); // NoSuchElementException

		for (; it.hasNext();) {
			System.out.println(">>> : " + it.next());
			if (it.hasNext()) {
				System.out.println(">>> : " + it.next());
				System.out.println("<<< : " + it.previous()); 
			}
		}
	}
}
