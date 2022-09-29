package pgdp.pingulib.datastructures.trees;

import java.util.LinkedList;
import java.util.List;

public class BST<T extends Comparable<T>> {

	private BSTNode<T> root;

	public BST() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		if (root == null) {
			return 0;
		}
		return root.size();
	}

	public boolean contains(T value) {
		if (isEmpty()) {
			return false;
		}
		return root.contains(value);
	}

	public T get(T value) {
		if (isEmpty()) {
			return null;
		}
		return root.get(value);
	}

	public boolean insert(T value) {
		if (value == null) {
			return false;
		}
		if (isEmpty()) {
			root = new BSTNode<T>(value);
			return true;
		} else {
			return root.insert(value);
		}
	}

	public List<T> toList() {
		if (isEmpty()) {
			return new LinkedList<>();
		}
		return root.toList(Order.IN);
	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return "[]";
		} else {
			return root.toString();
		}
	}
}
