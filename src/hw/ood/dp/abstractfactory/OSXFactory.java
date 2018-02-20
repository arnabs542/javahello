package hw.ood.dp.abstractfactory;

public class OSXFactory implements GUIFactory{

	@Override
	public Button createButton() {
		return new MacButton(); // Suppose MacButton is a derived class of Button
	}
	
}
