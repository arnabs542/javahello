package hw.ood.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		ParkingLot lot = new ParkingLot(2, 4);
		List<Vehicle> list = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			System.out.print("Car " + i + " : ");
			final Vehicle v = i % 2 == 0 ? new Car() : new Truck();
			list.add(v);
			boolean hasSpot = lot.hasSpot(v);
			System.out.print(hasSpot);
			if (i < 8) {
				// make sure you enable assert if using it for test
				assert hasSpot;
				assert lot.park(v);
			} else {
				assert !hasSpot;
				assert !lot.park(v);
			}
			System.out.println();
		}
		
		assert list.size() == 12;
		int i = 0;
		for (Vehicle v : list) {
			assert i >= 8 || lot.leave(v);
			i++;
		}
	}
}
