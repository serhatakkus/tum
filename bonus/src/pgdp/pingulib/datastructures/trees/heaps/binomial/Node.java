package pgdp.pingulib.datastructures.trees.heaps.binomial;

import java.util.List;

public abstract class Node<T> {

	private T value;
	private Node<T>[] children;

	/**
	 * Constructor
	 * 
	 * @param value value of the Node
	 * @param nodes children of the node (possibly empty if leaf)
	 */
	@SafeVarargs
	public Node(T value, Node<T>... nodes) {
		this.value = value;
		children = nodes;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	/**
	 * get i-th child of node
	 * 
	 * @param i index of the child
	 * @return i-th child of node
	 */
	public Node<T> getChild(int i) {
		if (i < 0 || children.length <= i) {
			return null;
		}
		return children[i];
	}

	/**
	 * set i-th child of node
	 * 
	 * @param i index of the child
	 * @param c new child node
	 */
	public void setChild(int i, Node<T> c) {
		if (i < 0 || children.length <= i) {
			return;
		}
		children[i] = c;
	}

	public Node<T>[] getChildren() {
		return children;
	}

	protected void setChildren(Node<T>[] children) {
		this.children = children;
	}

	public boolean isLeaf() {
		for (Node<T> node : children) {
			if (node != null) {
				return false;
			}
		}
		return true;
	}

	public int height() {
		int height = 0;
		for (Node<T> node : children) {
			if (node != null) {
				int nodeHeight = node.height();
				height = height < nodeHeight ? nodeHeight : height;
			}
		}
		return height + 1;
	}

	public int size() {
		int size = 0;
		for (Node<T> node : children) {
			if (node != null) {
				size += node.size();
			}
		}
		return size + 1;
	}

	public abstract List<T> toList(Order order);
}
