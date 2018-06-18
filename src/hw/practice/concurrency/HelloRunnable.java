package hw.practice.concurrency;

import java.util.Random;

public class HelloRunnable implements Runnable{
	private int id;
	
	public HelloRunnable(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		int sleepTime = new Random().nextInt(10);
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Hello from @" + id + " --- " + Integer.toString(sleepTime));
	}
	public static void main (String[] args) {
		for (int i = 0; i < 30; i++) {
			Thread newThread = new Thread(new HelloRunnable(i));
			newThread.start();
			newThread.interrupt(); // expect java.lang.InterruptedException: sleep interruptedc... 
		}
		

	}
}
