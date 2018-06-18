package hw.practice.concurrency;


public class VolatileMyRunnable {
	public static volatile boolean flag = false;

	public static class MyRunnable implements Runnable{
		@Override
		public void run() {
			while (!flag) {
				System.out.println("The thread is running...");
			}
			System.out.println("The thread is finished...");
		}
	}	
	
	public static void main(String[] args) throws InterruptedException {
		Thread newThread = new Thread(new MyRunnable());
		newThread.start();
		Thread.sleep(100000);
		flag = true;
		System.out.println("Main thread is finished...");
	}
}
