package hw.ood.parkinglot;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

public class ParkingTicket {
	private final Temporal startTS;
	private Temporal endTS;
	private Duration duration;
	
	public ParkingTicket(Vehicle v) {
		startTS = LocalDateTime.now();
	}
	
	public void setEndTS() {
		this.endTS = LocalDateTime.now();
	}
}
