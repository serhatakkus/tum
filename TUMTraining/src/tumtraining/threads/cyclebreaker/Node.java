package tumtraining.threads.cyclebreaker;

public class Node {
	public boolean locked;

	public long threadId;

	public Node() {
		locked = false;
		threadId = -1;
	}
}
