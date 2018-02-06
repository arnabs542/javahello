package hw.ood.parkinglot;

public enum VehicleSize {
	Compact (100), 
	Regular (200), 
	Large (300);
	
	private final int size;
	VehicleSize(int size) {
		this.size = size;
	}
	
	public int sizeValue() {
		return size;
	}
}
