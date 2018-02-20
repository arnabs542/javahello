package hw.ood.dp.singleton;

public class SingletonLazy {
	private static volatile SingletonLazy instance = null; // !! can not use final here
	/**
	 * --- volatile ---
	 * The Java volatile keyword is used to mark a Java variable as "being stored in main memory". 
	 * More precisely that means, that every read of a volatile variable will be read from the computer's main memory, 
	 * and not from the CPU cache, and that every write to a volatile variable will be written to main memory, 
	 * and not just to the CPU cache. 
	 * Actually, since Java 5 the volatile keyword guarantees more than just that volatile variables 
	 * are written to and read from main memory. I will explain that in the following sections.
	 */
	private SingletonLazy() {};
	
	public static SingletonLazy getInstance() {
		if (instance == null) {
			synchronized(SingletonLazy.class) {
				if (instance == null) {
					instance = new SingletonLazy();
				}
			}
		}
		return instance;
	}
}
