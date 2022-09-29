package tumtraining.recursion;

public class Carriage {
	// Attribute
	private int emptyWeight;
	private int numberOfPassengers;
	private Carriage next;

	// Platz für Methoden
	
	protected int getPassengerCount() {
		if (this.next == null) {
			return this.numberOfPassengers;
		}
		return next.getPassengerCount() + this.numberOfPassengers;
	}
	
	protected int getWeight() {
		int selfWeight = 75 * this.numberOfPassengers + emptyWeight;
		if (this.next == null) {
			return selfWeight;
		}
		return next.getWeight() + selfWeight;
	}
}
