package hw.ood.dp.factory;
/**
 * 1. Separate instance/object creation logic from its usage
 * 2. More clean especially when we have complicated instance creation logic
 * 3. Easier to extend the instance creation logic (extend the factory only)
 * 4. Provide chances to have different object allocation strategies (e.g., what if we
 *    want to reuse shape objects)
 * @author 
 *
 */
public class FactoryPatternDemo {
	private final ShapeFactory shapeFactory = new ShapeFactory();
	
	public void demo() {
		// get an object of circle and call its draw method.
		Shape shape1 = shapeFactory.getShape("CIRCLE");
		// call draw method
		shape1.draw();
		
		// get an object of rectangle and call its draw method.
		Shape shape2 = shapeFactory.getShape("Rectangle");
		// call draw method
		shape2.draw();
		
		// get an object of square and call its draw method.
		Shape shape3 = shapeFactory.getShape("SQUARE");
		// call draw method
		shape3.draw();
	}
	
	public static void main(String[] args) {
		FactoryPatternDemo demo = new FactoryPatternDemo();
		demo.demo();
	}
}
