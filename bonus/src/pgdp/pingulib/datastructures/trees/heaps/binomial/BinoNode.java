package pgdp.pingulib.datastructures.trees.heaps.binomial;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BinoNode<T extends Comparable<T>> extends Node<T> {

	@SafeVarargs
	public BinoNode(T value, BinoNode<T>... nodes) {
		super(value, nodes);
	}

	/**
	 * get order of the Binomial Tree with this node as root
	 */
	public int order() {
		return super.getChildren().length;
	}

	public void addChild(BinoNode<T> node) {
		this.setChildren(Arrays.copyOf(this.getChildren(), this.getChildren().length + 1));
		this.setChild(this.getChildren().length - 1, node);
	}

	/**
	 * decreases the value of an entry
	 * 
	 * @param key    old value
	 * @param value  new value
	 * @param parent parent of current node
	 */
	public void decreaseKey(T key, T value, BinoNode<T> parent) {
		if (value.compareTo(key) >= 0) {
			return;
		}
		// swap if new value less than parent's value
		if (value.compareTo(parent.getValue()) < 0) {
			parent.setValue(value);
			this.setValue(parent.getValue());
		} else {
			this.setValue(value);
		}
	}

	/**
	 * returns list in given order. <Order.IN> is not defined for Binomial Heaps,
	 * thus <null> will be returned.
	 */
	@Override
	public List<T> toList(Order order) {
		if (order == Order.IN) {
			return null;
		}
		List<T> list = new LinkedList<>();
		if (order == Order.PRE) {
			list.add(getValue());
		}
		for (Node<T> node : super.getChildren()) {
			list.addAll(node.toList(order));
		}
		if (order == Order.POST) {
			list.add(getValue());
		}
		return list;
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append("[").append(this.getValue().toString()).append(", ");
		for (BinoNode<T> node : (BinoNode<T>[]) this.getChildren()) {
			out.append(node.toString()).append(", ");
		}
		out.setLength(out.length() - 2);
		out.append("]");
		return out.toString();
	}

}
