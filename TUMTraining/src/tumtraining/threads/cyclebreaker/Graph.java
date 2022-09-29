package tumtraining.threads.cyclebreaker;

public interface Graph {
	public Node getNode(int nodeIndex);

	public java.util.Set<Integer> getEdges(int fromNode);

	public void removeEdge(int from, int to);
	
	public void prepareGraph();
}
