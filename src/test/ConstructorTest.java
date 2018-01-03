package test;

public class ConstructorTest {
//	public int x = -1;
//	public int y = -1;

	public int x;
	public int y;
	
	public ConstructorTest () {}
	public ConstructorTest (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static void main(String[] args) {
		ConstructorTest sol = new ConstructorTest(); 
		// above wrong, will say undefined constructor, if first constructor not defined.
		System.out.println(sol.x + ", " + sol.y);
	}
}
