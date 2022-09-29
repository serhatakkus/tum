package tumtraining.recursion;

public class Train {
	// Attribute
	private int weightOfLocomotive;
	private Carriage first;

	// Method that determines the number of passengers in the entire train
	// Helper method in Carriage must be recursive
	public int getNumPassengers() {
		return first.getPassengerCount();
	}

	// Method that determines the total weight of the train
	// Helper method in Carriage must be recursive
	public int getTotalWeight() {
		return first.getWeight() + this.weightOfLocomotive;
	}
}