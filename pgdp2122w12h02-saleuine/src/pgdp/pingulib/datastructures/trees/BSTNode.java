package pgdp.pingulib.datastructures.trees;

public class BSTNode<T extends Comparable<T>> extends BinaryNode<T> {

	public BSTNode(T value, BSTNode<T> left, BSTNode<T> right) {
		super(value, left, right);
	}

	public BSTNode(T value) {
		super(value, null, null);
	}

	public boolean contains(T value) {
		if (getValue().equals(value)) {
			return true;
		} else if (value.compareTo(getValue()) < 0) {
			if (getLeft() == null) {
				return false;
			} else {
				return ((BSTNode<T>) getLeft()).contains(value);
			}
		} else {
			if (getRight() == null) {
				return false;
			} else {
				return ((BSTNode<T>) getRight()).contains(value);
			}
		}
	}

	public T get(T value) {
		if (getValue().equals(value)) {
			return getValue();
		} else if (value.compareTo(getValue()) < 0) {
			if (getLeft() == null) {
				return null;
			} else {
				return ((BSTNode<T>) getLeft()).get(value);
			}
		} else {
			if (getRight() == null) {
				return null;
			} else {
				return ((BSTNode<T>) getRight()).get(value);
			}
		}
	}

	public boolean insert(T value) {
		if (getValue().equals(value)) {
			return false;
		} else if (value.compareTo(getValue()) < 0) {
			if (getLeft() == null) {
				setLeft(new BSTNode<T>(value));
				return true;
			} else {
				return ((BSTNode<T>) getLeft()).insert(value);
			}
		} else {
			if (getRight() == null) {
				setRight(new BSTNode<T>(value));
				return true;
			} else {
				return ((BSTNode<T>) getRight()).insert(value);
			}
		}
	}

}
