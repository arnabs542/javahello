package hw.ood.parkinglot;

// access label: public? package private?
class ParkingSpot {
	private static final float FEE_RATE = 2.0f;  // assume this is the fee rate, and same for different VehichleSize.
	private final VehicleSize size;
	private Vehicle currentVehicle; // null if no vehicle is parked inside
	
	ParkingSpot(VehicleSize size) {
		this.size = size;
	}
	
	/**
	 * Q: how to check size?
	 * 1). use if-else: if the given vehicle is Truck and the spot size is Large/Compact, else if...
	 * 2). use enum oridinal: return size.ordinal() >= v.getSize().ordinal;
	 * 		what if we later add new vehicle type? what if we later add new VehicleSize enum type?
	 * 		Bad extension..
	 * 3). Add a size attribute in enum VehicleSize and use it for comparison.
	 * 		Check the Planet example in java tutorial
	 * --> Our design / implementation should be open and easy for EXTENSION.
	 * @param v
	 * @return
	 */
	boolean fit(Vehicle v) {
		if (currentVehicle == null) {
			if (currentVehicle.getSize().sizeValue() <= size.sizeValue()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * record a vehicle is parked in by updating the currentVehicle field.
	 * @param v
	 */
	void park(Vehicle v) {
		currentVehicle = v;
	}
	
	void leave() {
		currentVehicle = null;
	}
	
	Vehicle getVehicle() {
		return currentVehicle;
	}
	
	
}
