package tumtraining.recursion.fs;

import java.util.Iterator;
import java.util.LinkedList;

public abstract class FSElement implements Iterable<FSElement> {

	private String name;

	@Override
	public Iterator<FSElement> iterator() {
		return new FSIterator(this);
	}

	class FSIterator implements Iterator<FSElement> {

		public FSIterator(FSElement root) {
			lst.add(root);
		}

		LinkedList<FSElement> lst = new LinkedList<FSElement>();

		@Override
		public boolean hasNext() {
			return !lst.isEmpty();
		}

		@Override
		public FSElement next() {
			FSElement nxt = lst.remove(0);

			if (nxt instanceof Folder fld) {
				int i = 0;
				for (FSElement fse : fld.getChildren()) {
					// if (!(fse instanceof Link)) {
						lst.add(i++, fse);
					// }
				}
			}

			return nxt;
		}

	}

	@Override
	public String toString() {
		return name;
	}

	public FSElement(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
