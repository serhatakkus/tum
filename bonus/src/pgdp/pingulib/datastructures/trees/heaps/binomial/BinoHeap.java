package pgdp.pingulib.datastructures.trees.heaps.binomial;

import java.util.ArrayList;

public class BinoHeap<T extends Comparable<T>> {

	private ArrayList<BinoNode<T>> roots;
	private BinoNode<T> min;

	public BinoHeap() {
		roots = new ArrayList<>();
		min = null;
	}

	protected ArrayList<BinoNode<T>> getRoots() {
		return roots;
	}

	protected void setRoots(ArrayList<BinoNode<T>> roots) {
		this.roots = roots;
	}

	protected BinoNode<T> getMin() {
		return min;
	}

	protected void setMin(BinoNode<T> min) {
		this.min = min;
	}

	public boolean isEmpty() {
		return roots.isEmpty();
	}

	/**
	 * insert new entry into the heap
	 * 
	 * @param value entry that will be inserted
	 */
	public void insert(T value) {
		// TODO
	}

	/**
	 * merge two heaps
	 * 
	 * @param heap BinoHeap that is merged onto this heap
	 */
	public void merge(BinoHeap<T> heap) {
		// TODO
	}

	/**
	 * retrieves the current min-value
	 * 
	 * @return current min-value
	 */
	public T findMin() {
		// TODO
		return null;
	}

	/**
	 * removes current min-value from heap
	 */
	public void delMin() {
		// TODO
	}

	/**
	 * removes and retrieves current min-value
	 * 
	 * @return current min-value
	 */
	public T min() {
		// TODO
		return null;
	}

	/**
	 * decreases the value of an entry
	 * 
	 * @param key   old value
	 * @param value new value
	 */
	public void decreaseKey(T key, T value) {
		// TODO
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		// TODO
		return out.toString();
	}

}
