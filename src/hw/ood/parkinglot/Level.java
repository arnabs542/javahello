package hw.ood.parkinglot;

import java.util.ArrayList;
import java.util.List;

class Level {
	// tracking Parking Spots
	private final List<ParkingSpot> spots;
	
	Level (int numOfSpots) {
		this.spots = new ArrayList<>(numOfSpots);
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
	}
	

}
