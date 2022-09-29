package pgdp.pingulib.datastructures.trees;

import java.util.List;

public abstract class Node<T> {

	private T value;
	private Node<T>[] children;

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

	public Node<T> getChild(int i) {
		if (i < 0 || children.length <= i) {
			return null;
		}
		return children[i];
	}

	public void setChild(int i, Node<T> c) {
		if (i < 0 || children.length <= i) {
			return;
		}
		children[i] = c;
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
