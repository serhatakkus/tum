package pgdp.saleuine2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A FIFO-queue for TradeOrder objects
 */
public class TradeOrderQueue {
	private Queue<TradeOrder> queue;

	public TradeOrderQueue() {
		queue = new LinkedList<TradeOrder>();
	}

	/**
	 * Checks whether the queue is empty or not
	 * @return true if the queue contains no elements
	 */
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	/**
	 * Returns the number of elements in the queue
	 * @return the number of elements in the queue
	 */
	public int size() {
		return queue.size();
	}

	/**
	 * Adds element to the end of the queue
	 * @param element: the element to be added to the queue
	 */
	public void add(TradeOrder element) {
		queue.add(element);
	}

	/**
	 * Removes and returns the first element in the queue
	 * @return the first element of the queue or null, if it is empty
	 */
	public TradeOrder poll() {
		return queue.poll();
	}

	/**
	 * Returns the internal representation of the queue. Only for testing. Do NOT change!!!
	 * @return the queue's internal representation
	 */
	public LinkedList<TradeOrder> toLinkedList() {
		return (LinkedList<TradeOrder>) queue;
	}
}
