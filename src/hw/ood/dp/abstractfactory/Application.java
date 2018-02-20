package hw.ood.dp.abstractfactory;
/**
 * 
 * @author 
 *
 */
public class Application {
	private final GUIFactory factory;
	
	// input: the GUIFactory factory used to create buttons
	// passed-in factory can be different according to OS or some config
	Application(GUIFactory f) {
		this.factory = f;
	}
	
	void run() {
		// Application.run does not need to care about what kind of factory it is
		// Only need to call factory's createButton
		// And no matter what kind of button is created, button can do paint.
		Button button = factory.createButton();
		button.paint();
	}
	
	public static void main(String[] args) {
		// Read the configuration file
		// If the OS specified in the config file is Windows, then
			// construct a WinFactory
			// run an Application with Winfactory
		// else
			// Construct an OSXFactory
			// run an Application with OSXFactory
		
	}
}
