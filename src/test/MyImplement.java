package test;

public class MyImplement implements MyInterfaceI, MyInterfaceII{
	private int value = 0;
	
	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public void addValue() {
		value += 1;
	}

	@Override
	public int getValue(boolean canBeNull) {
		if (canBeNull == true) {
			return -1;
		} else {
			return value;
		}
	}
	
	public static  void main(String[] args) {
		MyInterfaceI obj = new MyImplement();
		obj.addValue();
		// System.out.println(obj.getValue()); --> compile error
		System.out.println(obj.getValue(true));
		
		MyInterfaceII obj2 = new MyImplement();
		obj2.addValue();
		obj2.addValue();
		System.out.println(obj2.getValue()); 
		// System.out.println(obj2.getValue(true)); --> compile error
		
		MyImplement obj3 = new MyImplement();
		obj3.addValue();
		obj3.addValue();
		obj3.addValue();
		System.out.println(obj3.getValue()); 
		System.out.println(obj3.getValue(true)); 
		System.out.println(obj3.getValue(false)); 

	}
	
}
