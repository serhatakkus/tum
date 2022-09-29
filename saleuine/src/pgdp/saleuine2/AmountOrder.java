package pgdp.saleuine2;

public class AmountOrder extends TradeOrder {

	private final int targetAmountAnchovies;
	private final int targetAmountCrustaceans;
	private final int targetAmountSardines;
	private int currentAmountAnchovies;
	private int currentAmountCrustaceans;
	private int currentAmountSardines;

	public AmountOrder(int targetAmountAnchovies, int targetAmountCrustaceans, int targetAmountSardines) {
		this.targetAmountAnchovies = targetAmountAnchovies;
		this.targetAmountCrustaceans = targetAmountCrustaceans;
		this.targetAmountSardines = targetAmountSardines;
		// TODO Auto-generated constructor stub
	}

	public int getCurrentAmountAnchovies() {
		return currentAmountAnchovies;
	}

	public void setCurrentAmountAnchovies(int currentAmountAnchovies) {
		this.currentAmountAnchovies = currentAmountAnchovies;
	}

	public int getCurrentAmountCrustaceans() {
		return currentAmountCrustaceans;
	}

	public void setCurrentAmountCrustaceans(int currentAmountCrustaceans) {
		this.currentAmountCrustaceans = currentAmountCrustaceans;
	}

	public int getCurrentAmountSardines() {
		return currentAmountSardines;
	}

	public void setCurrentAmountSardines(int currentAmountSardines) {
		this.currentAmountSardines = currentAmountSardines;
	}

	public int getTargetAmountAnchovies() {
		return targetAmountAnchovies;
	}

	public int getTargetAmountCrustaceans() {
		return targetAmountCrustaceans;
	}

	public int getTargetAmountSardines() {
		return targetAmountSardines;
	}

	@Override
	public String orderType() {
		return "Anzahl: [" + targetAmountAnchovies + "," + targetAmountCrustaceans + "," + "targetAmountSardines" + "]";
	}

	public String toString() {
		return "Die Bestellung(Anzahl: [" + targetAmountAnchovies + "," + targetAmountCrustaceans + ","
				+ targetAmountSardines + "]) hat ein Gesamtgewicht von " + getCurrentWeight() + "g und kostet "
				+ getTotalCost() + "PD.";
	}

	public boolean isOrderFulfilled() {
		if (currentAmountAnchovies >= targetAmountAnchovies && currentAmountSardines >= targetAmountSardines
				&& currentAmountCrustaceans >= targetAmountCrustaceans) {
			return true;
		}
		return false;
	}
}
