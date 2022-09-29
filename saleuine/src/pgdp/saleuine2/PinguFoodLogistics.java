package pgdp.saleuine2;

import static pgdp.PinguLib.randomInt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PinguFoodLogistics {

	private TradeOrderQueue orderBook = new TradeOrderQueue();
	private BigDecimal ppgAnchovies;
	private BigDecimal ppgCrustaceans;
	private BigDecimal ppgSardines;

	private static List<PinguFood> wastedFood = new ArrayList<PinguFood>();

	public PinguFoodLogistics(BigDecimal ppgAnchovies, BigDecimal ppgCrustaceans, BigDecimal ppgSardines) {
		this.ppgAnchovies = ppgAnchovies;
		this.ppgCrustaceans = ppgCrustaceans;
		this.ppgSardines = ppgSardines;
	}

	public void acceptNewOrder(TradeOrder order) {
		orderBook.add(order);
	}

	public void clearOrderBook() {

		System.out.println("Es können " + orderBook.size() + " Bestellungen abgearbeitet werden.");
		while (!orderBook.isEmpty()) {
			TradeOrder currentOrder = orderBook.poll();

			// generate new attributes based on the order type
			int orderType = 0;
			AmountOrder currentAmountOrder = null;

			if (currentOrder instanceof WeightOrder) {
				orderType = 1;
			} else if (currentOrder instanceof AmountOrder) {
				currentAmountOrder = (AmountOrder) currentOrder;
				orderType = 2;
			}

			// generate food until the order is fulfilled
			while (!currentOrder.isOrderFulfilled()) {
				
				PinguFood newFood = null;

				// generate desired type of food until all of them are fulfilled ONLY for the
				// AMOUNT ORDER
				if (orderType == 2) {
					if (currentAmountOrder.getCurrentAmountAnchovies() < currentAmountOrder
							.getTargetAmountAnchovies()) {
						newFood = generateAnchovie();
					} else if (currentAmountOrder.getCurrentAmountCrustaceans() < currentAmountOrder
							.getTargetAmountCrustaceans()) {
						newFood = generateCrustacean();
					} else if (currentAmountOrder.getCurrentAmountSardines() < currentAmountOrder
							.getTargetAmountSardines()) {
						newFood = generateSardine();
					}
				} else {
					newFood = generatePinguFood();
				}
				
				// check if the generated food is edible
				if (!newFood.isEdible()) {
					registerUnusedFood(newFood);
				} else {
					// calculate the cost of the food
					BigDecimal cost = BigDecimal.ZERO;
					
					if (newFood instanceof Anchovie) {
						cost = ppgAnchovies.multiply(BigDecimal.valueOf(newFood.getWeight()));
					} else if (newFood instanceof Crustacean) {
						cost = ppgCrustaceans.multiply(BigDecimal.valueOf(newFood.getWeight()));
					} else if (newFood instanceof Sardine) {
						cost = ppgSardines.multiply(BigDecimal.valueOf(newFood.getWeight()));
					}
					
					currentOrder.supplyOrder(newFood, cost);
				}
			}
			
			currentOrder.toString();
		}
	}

	private void registerUnusedFood(PinguFood food) {
		wastedFood.add(food);
	}

	public void printWasteStatistics() {

		int unusedPinguFood = wastedFood.size();
		int weightUnusedPinguFood = 0;
		BigDecimal valuePinguFood = BigDecimal.ZERO;
		
		for (PinguFood wasted : wastedFood) {
			weightUnusedPinguFood += wasted.getWeight();
			BigDecimal wastedValue = BigDecimal.ZERO;
			
			if (wasted instanceof Anchovie) {
				wastedValue = ppgAnchovies.multiply(BigDecimal.valueOf(wasted.getWeight()));
			} else if (wasted instanceof Crustacean) {
				wastedValue = ppgCrustaceans.multiply(BigDecimal.valueOf(wasted.getWeight()));
			} else if (wasted instanceof Sardine) {
				wastedValue = ppgSardines.multiply(BigDecimal.valueOf(wasted.getWeight()));
			}
			
			valuePinguFood.add(wastedValue);
		}

		String statistics = "Bisher konnten " + unusedPinguFood + " Tiere mit einem Gesamtgewicht von "
				+ weightUnusedPinguFood
				+ "g nicht verwertet werden.\\nClaudia und Karl-Heinz ist dadurch ein Profit von " + valuePinguFood
				+ "PD entgangen.";
		System.out.println(statistics);
	}

	public TradeOrderQueue getOrderBook() {
		return orderBook;
	}

	public void setOrderBook(TradeOrderQueue orderBook) {
		this.orderBook = orderBook;
	}

	public BigDecimal getPpgAnchovies() {
		return ppgAnchovies;
	}

	public void setPpgAnchovies(BigDecimal ppgAnchovies) {
		this.ppgAnchovies = ppgAnchovies;
	}

	public BigDecimal getPpgCrustaceans() {
		return ppgCrustaceans;
	}

	public void setPpgCrustaceans(BigDecimal ppgCrustaceans) {
		this.ppgCrustaceans = ppgCrustaceans;
	}

	public BigDecimal getPpgSardines() {
		return ppgSardines;
	}

	public void setPpgSardines(BigDecimal ppgSardines) {
		this.ppgSardines = ppgSardines;
	}

	public static void main(String[] args) {
		PinguFoodLogistics market = new PinguFoodLogistics(BigDecimal.ONE, BigDecimal.valueOf(0.5),
				BigDecimal.valueOf(2));
		market.acceptNewOrder(new TradeOrder());
		market.acceptNewOrder(new WeightOrder(1000));
		market.acceptNewOrder(new AmountOrder(2, 2, 2));
		market.clearOrderBook();
		market.printWasteStatistics();
	}

	/**
	 * The following methods generate Anchovie, Crustacean or Sardine object
	 * WARNING: do NOT change these methods unless you want to fail the tests
	 */

	/*  */ // remove after implementing PinguFood + subclasses
	public static PinguFood generatePinguFood() {
		switch (randomInt(0, 2)) {
		case 0:
			return generateAnchovie();
		case 1:
			return generateCrustacean();
		case 2:
			return generateSardine();
		default:
			throw new SecurityException("You changed the code!");
		}
	}

	public static Anchovie generateAnchovie() {
		return new Anchovie(randomInt(0, 5), randomInt(1, 55));
	}

	public static Crustacean generateCrustacean() {
		return new Crustacean(randomInt(1, 10));
	}

	public static Sardine generateSardine() {
		return new Sardine(randomInt(0, 10), randomInt(20, 300), randomInt(1, 22));
	}
	/* */ // remove after implementing PinguFood + subclasses
}
