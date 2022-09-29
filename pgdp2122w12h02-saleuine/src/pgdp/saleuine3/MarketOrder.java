package pgdp.saleuine3;

public class MarketOrder implements Comparable<MarketOrder> {
	private final int id;

	private long ordered;
	private long supply;

	public MarketOrder(int id, int order) {
		this.id = id;
		ordered = order;
	}

	public void addMarketOrder(MarketOrder marketOrder) {
		if (marketOrder.id != this.id) {
			return;
		}
		ordered += marketOrder.ordered;
	}

	public long supplyMarket(int maxSupply) {
		long diff = ordered - supply;
		if (diff > maxSupply) {
			supply += maxSupply;
			return maxSupply;
		} else {
			supply = ordered;
			return diff;
		}
	}

	public boolean isMarketOrderFullySupplied() {
		return supply == ordered;
	}

	@Override
	public int compareTo(MarketOrder o) {
		return Integer.compare(this.id, o.id);
	}

	@Override
	public String toString() {
		return "Market " + id + " ordered " + ordered + " and got supplied with " + supply;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof MarketOrder)) {
			return false;
		}
		return this.id == ((MarketOrder) o).id;
	}

	@Override
	public int hashCode() {
		// override hashCode for good practice since equals is overwritten 
		return this.id;
	}
}
