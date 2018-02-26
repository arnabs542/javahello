package hw;

public class AAA<T, K> {
	private T value;
	public T getValue() {
		return value;
	}
	
	public void setValue(K v) {
		System.out.println("passing in type: " + String.valueOf(K) + v);
	}
	
}
