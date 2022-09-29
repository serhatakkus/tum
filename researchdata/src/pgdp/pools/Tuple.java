package pgdp.pools;

import java.util.Objects;

public class Tuple<T, S> {
	private T t;
	private S s;

	public Tuple(T t, S s) {

		this.t = t;
		this.s = s;

	}

	public T getT() {
		return t;

	}

	public S getS() {
		return s;
	}

	public boolean equals(Object a) {
		if (a instanceof Tuple<?, ?>) {
			Tuple<T, S> s1 = (Tuple<T, S>) a;
			if (this.t.equals(s1.getT()) && this.s.equals(s1.getS()))
				return true;
			else
				return false;
		}
		return false;

	}

	public int hashCode() {
		return Objects.hash(this.t, this.s);
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * String s = new String("Damla"); String a = new String("Damla");
	 * System.out.println(s.equals(a)); System.out.println(s.hashCode());
	 * System.out.println(a.hashCode());
	 * 
	 * }
	 */

}
