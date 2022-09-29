package tumtraining.threads.cyclebreaker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(String[] args) {

		Graph graph = new Graph() {

			List<Node> nodes = new ArrayList<Node>();
			List<Edge> edges = new ArrayList<Edge>();

			@Override
			public void removeEdge(int from, int to) {
				for (int i = 0; i < edges.size(); i++) {
					if (edges.get(i).getFrom() == from && edges.get(i).getTo() == to) {
						edges.remove(i);
						System.out.println("removing edge " + from + " --> " + to);
						return;
					}
				}

			}

			@Override
			public Node getNode(int nodeIndex) {
				return nodes.get(nodeIndex);
			}

			@Override
			public Set<Integer> getEdges(int fromNode) {
				Set<Integer> tos = new HashSet<Integer>();
				for (Edge edge : edges) {
					if (edge.getFrom() == fromNode) {
						tos.add(edge.getTo());
					}
				}
				return tos;
			}

			@Override
			public void prepareGraph() {
				for (int i = 0; i < 9; i++) {
					nodes.add(new Node());
				}
				
				edges.add(new Edge(0, 1));
				edges.add(new Edge(0, 3));
				edges.add(new Edge(0, 6));
				edges.add(new Edge(3, 2));
				edges.add(new Edge(4, 3));
				edges.add(new Edge(4, 6));
				edges.add(new Edge(5, 4));
				edges.add(new Edge(6, 5));
				edges.add(new Edge(6, 7));
				edges.add(new Edge(8, 5));
			}
		};
		
		CycleBreaker cycle = new CycleBreaker(graph, 8);
		cycle.run();
	}

}
