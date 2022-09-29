package pgdp.saleuine3;

import java.util.ArrayList;
import java.util.Random;

import pgdp.pingulib.datastructures.trees.BST;

public class Bestelluin implements Runnable {

	private final ArrayList<Integer> responsibleIDs;
	private final BST<MarketOrder> orderDB;

	private int ordersToMake;
	private Random random;

	private final String name;

	public Bestelluin(ArrayList<Integer> ids, int orders, BST<MarketOrder> orderDB, String name) {
		this.responsibleIDs = ids;
		this.ordersToMake = orders;
		this.random = new Random(responsibleIDs.size() * 42L);
		this.orderDB = orderDB;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	private MarketOrder generateMarketOrder() {
		int id = responsibleIDs.get(0 + random.nextInt(responsibleIDs.size()));
		return new MarketOrder(id, 1 + random.nextInt(10));
	}

	@Override
	public void run() {
		// TODO
	}
}
