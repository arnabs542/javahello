package hw.ood.parkinglot;

public class Car extends Vehicle{
	public Car(String plate) {
		super(plate, VehicleSize.Compact);
		// TODO Auto-generated constructor stub
	}

	@Override
	public VehicleSize getSize() {
		return VehicleSize.Compact;
	}
}
