package pgdp.saleuine2;

import java.math.BigDecimal;

public class TradeOrder {

	private BigDecimal totalCost = BigDecimal.valueOf(0);
	private int currentWeight = 0;

	public TradeOrder() {

	}

	public boolean supplyOrder(PinguFood supply, BigDecimal cost) {

		if (!supply.isEdible()) {
			return false;
		}

		if (isOrderFulfilled()) {
			return false;
		}

		// update order
		totalCost = totalCost.add(cost);
		currentWeight += supply.getWeight();

		if (this instanceof AmountOrder) {

			AmountOrder currentAmountOrder = (AmountOrder) this;

			if (supply instanceof Anchovie) {
				if (currentAmountOrder.getCurrentAmountAnchovies() < currentAmountOrder.getTargetAmountAnchovies()) {
					currentAmountOrder.setCurrentAmountAnchovies(currentAmountOrder.getCurrentAmountAnchovies() + 1);
				}
			} else if (supply instanceof Crustacean) {
				if (currentAmountOrder.getCurrentAmountCrustaceans() < currentAmountOrder
						.getTargetAmountCrustaceans()) {
					currentAmountOrder
							.setCurrentAmountCrustaceans(currentAmountOrder.getCurrentAmountCrustaceans() + 1);
				}
			} else if (supply instanceof Sardine) {
				if (currentAmountOrder.getCurrentAmountSardines() < currentAmountOrder.getTargetAmountSardines()) {
					currentAmountOrder.setCurrentAmountSardines(currentAmountOrder.getCurrentAmountSardines() + 1);
				}
			}
		}

		if (isOrderFulfilled()) {
			return false;
		}
		return true;
	}

	public boolean isOrderFulfilled() {
		if (currentWeight > 0) {
			return true;
		}
		return false;
	}

	public String orderType() {
		return "Einzeln";

	}

	public String toString() {
		return "Die Bestellung(Einzeln) hat ein Gesamtgewicht von " + currentWeight + "g und kostet " + totalCost
				+ "PD.";

	}

	public int getCurrentWeight() {
		return currentWeight;
	}

	public void setCurrentWeight(int currentWeight) {
		this.currentWeight = currentWeight;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

}
