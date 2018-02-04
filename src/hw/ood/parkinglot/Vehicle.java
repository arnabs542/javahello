package hw.ood.parkinglot;

public abstract class Vehicle {
	// data fields...
	private final String plate;
	private final VehicleSize size;
	public abstract VehicleSize getSize();
	
	public Vehicle (String plate, VehicleSize size) {
		this.plate = plate;
		this.size = size;
	}
}
