package hw.practice.concurrency;

public class Counter {
	private int value;
	private static int staticValue = 0;

	public Counter(int value) {
		this.value = value;
	}

	public void increase() {
		synchronized (this) {
			value++;
		}
		// do something else necessary....
		System.out.println("increased hello");
	}

	public static void staticIncrement() {
		synchronized (Counter.class) { // !!! Counter.class, lock on class to visite static variable
			staticValue++;
		}
	}

	public void decrease() {
		synchronized (this) {
			value--;
		}
	}

	public static synchronized void staticDecrease() {
		staticValue--;
	}

	public synchronized int getValue() {
		return value;
	}

	public static int getStaticCount() {
		synchronized (Counter.class) {
			return staticValue;
		}
	}
}

/**
 * 
 * synchronized on non-static method == synchronized(this),
 * static synchronized == synchronized(Counter.class) != synchronized(all instances)
 * 
 * 
 * 
 */
