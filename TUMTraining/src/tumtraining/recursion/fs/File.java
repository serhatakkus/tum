package tumtraining.recursion.fs;

public class File extends FSElement {

	public File(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "(" + this.getName() + ")";
	}
}
