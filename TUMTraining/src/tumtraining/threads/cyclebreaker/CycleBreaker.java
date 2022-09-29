package tumtraining.threads.cyclebreaker;

/**
 * Task 8 circle breaker (threads) [25 points]
 * 
 * <pre>
 * This task is about parallel
 * circles in directed graphs consisting of several break out threads. Such a
 * graph consists of nodes connected by directed Edges are connected to each
 * other (start and end nodes of edges are always different, no error handling
 * is necessary for this). The following figure shows a
 * 
 * 
 * A thread t with ID idt starts with an initial node from which it starts the
 * graph traversed recursively. For each edge he distinguishes the following
 * cases: 
 * 1. If the thread ID of the target node is greater than idt, the edge is ignored. 
 * 
 * 2. If the target node is already blocked by the current thread,
 * then the edge removed to the target node. In this way a circle is broken in the graph.
 *  
 * 3. Otherwise, the edge is used for recursive descent. 
 * 
 * In general,
 * if the edge is not ignored, the target node is taken from the current one
 * Thread needs to be blocked. To do this, the thread may have to wait until
 * another thread left the destination node. It must always be ensured that no
 * deadlocks occur or uncoordinated parallel access
 * 
 * </pre>
 *
 */
public class CycleBreaker extends Thread {
	private Graph graph;
	private int startNode;

	public CycleBreaker(Graph graph, int startNode) {
		this.graph = graph;
		this.startNode = startNode;
		
		graph.prepareGraph();
	}

	private void traverse(int from, int to) throws InterruptedException {
		long myId = Thread.currentThread().getId(); // ID ist stets >= 0

		Node node = graph.getNode(to);
		boolean workWithNode = false;
		boolean cycle = false;

		// Synchronisierung von Knoten (nur zum Setzen des Locks): 3
		synchronized (node) {
			// Warten: 2 Punkte, je einen pro Bedingung
			while (node.threadId < myId && node.locked)
				// 1 Punkt, auch wenn anderswo gewartet wird
				node.wait();
			if (node.threadId <= myId) {
				// Zyklus richtig erkennen: 3 Punkte
				cycle = node.locked && myId == node.threadId;
				workWithNode = true;
				// Blockieren des Knotens: 2 Punkt
				node.locked = true;
				node.threadId = myId;
			}
		}

		// Ignorierung von Knoten: 2 Punkte
		if (!workWithNode)
			return;

		if (cycle && from != to)
			// Zyklus entfernen: 1 Punkt
			graph.removeEdge(from, to);
		else {
			// Traversierung an der richtigen Stelle: 1 Punkt
			// Kanten ab 'to' vom Graphen erfragen; 'graph' ist
			java.util.Set<Integer> edgesFromTo = graph.getEdges(to);
			// Rekursiv alle Kanten ab
			for (int dest : edgesFromTo)
				traverse(to, dest);
		}

		// Synchronisierung für Benachrichtigung: 2 Punkt
		synchronized (node) {
			// Rücksetzen des Locks: 1 Punkt
			node.locked = false;
			// Benachrichtigung: 1 Punkt
			node.notifyAll();
		}
	}

	@Override
	public void run() {
		// Error handling: 1 Punkt
		try {
			// 2 Punkt, zusammen mit Test in Zeile~38 bzgl. from != to
			traverse(startNode, startNode);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}