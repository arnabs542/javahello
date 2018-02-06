package hw.ood.parkinglot;
/**
 * Assumption:
 * 1. mulitple levels
 * 2. check vehicle size
 * 
 * API:
 * boolean hasSpot(Vehicle v);
 * boolean park(Vehicle v);
 * boolean leave(Vehicle v);
 * 
 * classes:
 * ParkingLot, Level, Vehicle, ParkingSpot, Car, Truck
 * 
 * @author 
 *
 */
public class ParkingLot {

	private final Level[] levels;
	
	// assumption:
	// every level has the same number of parking spots.
	public ParkingLot(int numLevels, int numSpotsPerLevel) {
		levels = new Level[numLevels];
		for (int i = 0; i < numLevels; i++) {
			levels[i] = new Level(numSpotsPerLevel);
		}
	}
	
	public boolean hasSpot(Vehicle v) {
		//TODO: check each level, for each level, can Level hasSpot(Vehicle)
		for (Level l : levels) {
			if (l.hasSpot(v)) {
				return true;
			}
		}
		return true;
	}
	
	//TODO: park
	public boolean park(Vehicle v) {
		// check each level for place to park
		// update spot, level, lot, ticket.
		for (Level l : levels) {
			if (l.hasSpot(v)) {
				l.park(v);
				return true;
			}
		}
		return false;
	}
	
	//TODO: leave
	public void leave(Vehicle v) {
		// find vehicle - spot mapping and leave.
		// update spot, level, lot, ticket.
		for (level l : levels) {
			
		}
	}	
	
	/**
	 * Extensions:
	 * 1. how to track vehicle-spot mapping (more efficient leave method implementation) ?
	 * 		i.e., what if we want to know the spot for a given vehicle?
	 * 
	 * OOD: performance / efficiency is still important !!! This is where you use data structures
	 *  and algorithms!
	 *  
	 *  (HashMap<VehicleSize, List<>>
	 *  // EnumMap
	 *  
	 *  2. Parking ticket?
	 *  1) How to define ParkingTicket class?
	 *  2) ParkingSpot id/number
	 *  3) Parking fee calculation (time, rate...)
	 */
}
