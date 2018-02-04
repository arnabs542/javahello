package hw.ood.parkinglot;

public class Truck extends Vehicle{
	public Truck(String plate) {
		super(plate, VehicleSize.Large);
		// TODO Auto-generated constructor stub
	}

	@Override
	public VehicleSize getSize() {
		return VehicleSize.Large;
	}
}
