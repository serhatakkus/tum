package pgdp.saleuine3;

import java.util.ArrayList;
import java.util.List;

import pgdp.pingulib.datastructures.trees.BST;

public class SaleuinBusiness {
	private BST<MarketOrder> orderDB;
	private List<ArrayList<Integer>> bestelluinIDLists;
	private List<ArrayList<Integer>> generatorIDLists;

	private int ordersPerBestelluin;
	private int supplyRunsPerGenerator;
	private int maxGeneratorOutput;

	public SaleuinBusiness(List<ArrayList<Integer>> bestelluinIDLists, List<ArrayList<Integer>> generatorIDLists,
			int ordersPerBestelluin, int supplyRunsPerGenerator, int maxGeneratorOutput) {
		this.orderDB = new BST<>();
		this.bestelluinIDLists = bestelluinIDLists;
		this.generatorIDLists = generatorIDLists;
		this.ordersPerBestelluin = ordersPerBestelluin;
		this.supplyRunsPerGenerator = supplyRunsPerGenerator;
		this.maxGeneratorOutput = maxGeneratorOutput;
	}

	public BST<MarketOrder> getOrderDB() {
		return orderDB;
	}

	public void runBusiness() {
		for (int i = 0; i < bestelluinIDLists.size(); i++) {
			ArrayList<Integer> ids = bestelluinIDLists.get(i);
			String nameId = String.valueOf(i + 1);
			Bestelluin order = new Bestelluin(ids, ordersPerBestelluin, orderDB, "Bestelluin " + nameId);
			Thread th = new Thread(order, order.getName());
			th.start();
		}

		for (int i = 0; i < generatorIDLists.size(); i++) {
			ArrayList<Integer> ids = generatorIDLists.get(i);
			String nameId = String.valueOf(i + 1);
			PinguFoodGenerator food = new PinguFoodGenerator(maxGeneratorOutput, supplyRunsPerGenerator, orderDB, ids,
					"Generator " + nameId);
			Thread th = new Thread(food, food.getName());
			th.start();
		}
	}
}
