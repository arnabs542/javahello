package hw.ood.dp.singleton;

public class Singleton {
	private static final Singleton INSTANCE = new Singleton(); // STATIC, FINAL !!!
	
	private Singleton() {}; //!!! private the constructor, and make the getInstance public
	
	public static Singleton getInstance() { // !! static, does not need an instance
		return INSTANCE;
	}
}
