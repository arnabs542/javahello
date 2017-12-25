package exp;

public class TestFinal {
	final String name = "123";
	
	public TestFinal(String name) {
		//this.name = name;   // wrong if doing this, before it was assigned already.
	}
}
