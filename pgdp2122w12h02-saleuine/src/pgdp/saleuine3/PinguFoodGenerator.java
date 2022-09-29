package pgdp.saleuine3;

import java.util.ArrayList;

import pgdp.pingulib.datastructures.trees.BST;

public class PinguFoodGenerator implements Runnable {

	private int supplyRuns;
	private int maxSupply;
	private long totalSupply;

	private final BST<MarketOrder> orderDB;
	private final ArrayList<Integer> responsibleIDs;

	private final String name;

	public PinguFoodGenerator(int maxSupply, int supplyRuns, BST<MarketOrder> orderDB, ArrayList<Integer> ids,
			String name) {
		this.maxSupply = maxSupply;
		this.supplyRuns = supplyRuns;
		this.totalSupply = 0;
		this.orderDB = orderDB;
		this.responsibleIDs = ids;
		this.name = name;
	}

	@Override
	public void run() {
		// TODO
	}

	public String getName() {
		return name;
	}

}
