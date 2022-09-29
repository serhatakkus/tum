package pgdp.saleuine2;

public class WeightOrder extends TradeOrder {

	private final int targetWeight;

	public WeightOrder(int targetWeight) {
		this.targetWeight = targetWeight;

	}

	public int getTargetWeight() {
		return targetWeight;
	}

	@Override
	public String orderType() {
		return "Zielgewicht: " + targetWeight + "g";
	}

	public String toString() {
		return "Die Bestellung(Zielgewicht: " + targetWeight + "g) hat ein Gesamtgewicht von " + getCurrentWeight()
				+ "g und kostet " + getTotalCost() + "PD.";
	}

	public boolean isOrderFulfilled() {
		if (getCurrentWeight() >= targetWeight) {
			return true;
		}
		return false;
	}
}
