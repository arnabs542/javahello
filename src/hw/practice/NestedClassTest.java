package hw.practice;

public class NestedClassTest {
	public static void main(String[] args) {
		Outer o = new Outer();
		Outer.NonStaticNested nsn = o.new NonStaticNested();
		nsn.show();
		
		Outer.StaticNested sn = new Outer.StaticNested();
		sn.show();
		sn.sShow();
		Outer.StaticNested.sShow();

	}
}

class Outer {
	private String nonStaticVar = "non-static string";
	private static String staticVar = "static string";
	
	public class NonStaticNested {
		public void show() {
			System.out.println("Non-StaticNested::show " + nonStaticVar);
			System.out.println("Non-StaticNested::show-static " + staticVar);

		}
	}
	
	public static class StaticNested {
		public void show() {
			System.out.println("StaticNested::show " + staticVar);
		}
		
		public static void sShow() {
			System.out.println("StaticNested::staticShow " + staticVar);
		}
	}
}
