package hw.ood.parkinglot;

public class Car extends Vehicle{
//	public Car(String plate) {
//		super(plate, VehicleSize.Compact);
//	}

	@Override
	public VehicleSize getSize() {
		return VehicleSize.Compact;
	}
}
