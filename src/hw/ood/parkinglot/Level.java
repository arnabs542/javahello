package hw.ood.parkinglot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Level {
	// tracking Parking Spots
	private final List<ParkingSpot> spots;
	
	Level (int numOfSpots) {
		List<ParkingSpot> list = new ArrayList<>(numOfSpots);
		int i = 0;
		// half of the spots will be compact
		for (; i < numOfSpots / 2; i++) {
			list.add(new ParkingSpot(VehicleSize.Compact));
		}
		for (; i < numOfSpots; i++) {
			list.add(new ParkingSpot(VehicleSize.Large));
		}
		this.spots = Collections.unmodifiableList(list);
		// TODO: what's missing here?
		// Q: what if we want to enforce the semantic that the Level layout is fixed and will never change?
		// https://docs.oracle.com/javase/7/docs/api/java/util/Collections.html#unmodifiableList(java.util.List)
		/**
		 * List list  = new ArrayList<ParkingSpot>();
		 * spots = Collections.unmodifiableList(list);
		 */
	}
	
	boolean hasSpot(Vehicle v) {
		for (ParkingSpot s : spots) {
			if (s.fit(v)) {
				return true;
			}
		}
		return false;
	}
	
	boolean park(Vehicle v) {
		for (ParkingSpot s : spots) {
			if (s.fit(v)) {
				s.park(v);
				return true;
			}
		}
		return false;
	}
	
	boolean leave(Vehicle v) {
		//TODO: scan all the parking spots in this level and call ParkingSpot#leave
		for (ParkingSpot s : spots) {
			if (s.getVehicle() == v) {
				s.leave();
				return true;
			}
		}
		return false;
		
	}
	

}
