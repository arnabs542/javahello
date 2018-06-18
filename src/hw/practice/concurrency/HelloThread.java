package hw.practice.concurrency;

public class HelloThread extends Thread{
	private int id;
	
	public HelloThread(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		System.out.println("Hello from @" + id);
	}
	public static void main (String[] args) {
		for (int i = 0; i < 30; i++) {
			Thread newThread = new HelloThread(i);
			newThread.start();			
		}
		

	}
}
