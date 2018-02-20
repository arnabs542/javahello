package hw.ood.dp.abstractfactory;

public class WinFactory implements GUIFactory {

	@Override
	public Button createButton() {
		return new WindowsBUtton(); // suppose WindowsBUtton is a derived class of Button
	}
	
}
