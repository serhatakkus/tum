package tumtraining.recursion.fs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Folder extends FSElement {

	private List<FSElement> children;

	public Folder(String name, FSElement[] arr) {
		super(name);

		this.children = new ArrayList<FSElement>();
		if (arr != null) {
			this.children.addAll(Arrays.asList(arr));
		}

	}

	/**
	 * recursive
	 * 
	 * root//etc//home
	 * 
	 * @param e
	 * @param path
	 */
	public void add(FSElement e, String path) throws RuntimeException {
		// initialize the children
		if (children == null) {
			children = new ArrayList<FSElement>();
		}

		// check if the path is null or empty
		if (path == null) {
			throw new RuntimeException();
		} else if (path.isEmpty()) {
			this.children.add(e);
		}

		// get index of "/"
		int indexOf = path.indexOf("/");

		// get root folder from the path
		String root = path;
		String newPath = "";
		if (indexOf >= 0) {
			root = path.substring(0, indexOf); // root				// etc		// home
			newPath = path.substring(indexOf + 1); //   etc/home	// home		.add
		}

		// check if the current folder is the root
		if (this.getName().equals(root)) {
			this.add(e, newPath);
			return;
		}

		// else, search the root folder name in the children
		for (FSElement child : children) {
			if (child.getName().equals(root)) {
				if (child instanceof Folder fld) {
					fld.add(e, newPath);
					return;
				} else if (child instanceof Link lnk) {
					Folder fld = (Folder) lnk.getLink();
					fld.add(e, newPath);
					return;
				} else {
					throw new RuntimeException();
				}
			}
		}

	}

	@Override
	public String toString() {
		List<String> subStr = new ArrayList<String>();
		for (FSElement ch : children) {
			subStr.add(ch.toString());
		}
		return "\n[" + this.getName() + "\n" + subStr.stream().reduce("", (e1, e2) -> e1 + " " + e2) + "]\n";
	}

	public List<FSElement> getChildren() {
		return children;
	}

	public void setChildren(List<FSElement> children) {
		this.children = children;
	}
}
