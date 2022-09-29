package tumtraining.recursion.fs;

public class Link extends FSElement {
	public Link(String name, FSElement link) {
		super(name);
		this.link = link;
	}

	private FSElement link;

	@Override
	public String toString() {
		return this.getName() + " -> ";
	}
	
	public FSElement getLink() {
		return link;
	}

	public void setLink(FSElement link) {
		this.link = link;
	}
}
