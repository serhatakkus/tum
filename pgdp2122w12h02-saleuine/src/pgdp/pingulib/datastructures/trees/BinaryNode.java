package pgdp.pingulib.datastructures.trees;

import java.util.LinkedList;
import java.util.List;

public class BinaryNode<T> extends Node<T> {

	public BinaryNode(T value, BinaryNode<T> left, BinaryNode<T> right) {
		super(value, left, right);
	}

	public BinaryNode(T value) {
		super(value, null, null);
	}

	public BinaryNode<T> getLeft() {
		return (BinaryNode<T>) getChild(0);
	}

	public BinaryNode<T> getRight() {
		return (BinaryNode<T>) getChild(1);
	}

	public void setLeft(BinaryNode<T> left) {
		setChild(0, left);
	}

	public void setRight(BinaryNode<T> right) {
		setChild(1, right);
	}

	@Override
	public List<T> toList(Order order) {
		List<T> list = new LinkedList<>();
		List<T> leftList = new LinkedList<>();
		List<T> rightList = new LinkedList<>();
		if (getLeft() != null) {
			leftList = getLeft().toList(order);
		}
		if (getRight() != null) {
			rightList = getRight().toList(order);
		}
		switch (order) {
		case IN:
			list.addAll(leftList);
			list.add(getValue());
			list.addAll(rightList);
			break;
		case POST:
			list.addAll(leftList);
			list.addAll(rightList);
			list.add(getValue());
			break;
		case PRE:
			list.add(getValue());
			list.addAll(leftList);
			list.addAll(rightList);
			break;
		default:
			break;
		}
		return list;
	}

	public String toString(Order order) {
		if (isLeaf()) {
			return "[" + getValue().toString() + "]";
		}
		String leftStr = "-";
		String rightStr = "-";
		if (getLeft() != null) {
			leftStr = getLeft().toString(order);
		}
		if (getRight() != null) {
			rightStr = getRight().toString(order);
		}
		switch (order) {
		case IN:
			return "[" + leftStr + ", " + getValue().toString() + ", " + rightStr + "]";
		case POST:
			return "[" + leftStr + ", " + rightStr + ", " + getValue().toString() + "]";
		case PRE:
			return "[" + getValue().toString() + ", " + leftStr + ", " + rightStr + "]";
		default:
			return "SOMETHING WENT WRONG!";
		}
	}

	@Override
	public String toString() {
		return toString(Order.IN);
	}
}
