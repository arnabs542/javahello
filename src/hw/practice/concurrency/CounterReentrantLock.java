package hw.practice.concurrency;

import java.util.concurrent.locks.ReentrantLock;

public class CounterReentrantLock {
	private static int value = 0;
	private final ReentrantLock lock = new ReentrantLock();
	private int id = 0;

	public CounterReentrantLock(int value) {
		this.value = value;
	}

	public void increase() {
		lock.lock();
		try {
			value++;
			// do something else necessary....
			System.out.println("increased hello: " + Integer.toString(value));
		} finally {
			lock.unlock();
		}
	}

	public void decrease() {
		lock.lock();
		try {
			value--;
			System.out.println("decreased hello: " + Integer.toString(value));	
		} finally {
			lock.unlock();
		}
	}

	public synchronized int getValue() {
		return value;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 30; i++) {
			
		}
	}

}
