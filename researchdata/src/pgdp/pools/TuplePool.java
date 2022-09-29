package pgdp.pools;

import pgdp.hashset.TupleHashSet;

public class TuplePool<T, S> {
	TupleHashSet<T, S> tupleSet;

	public TuplePool() {
		this.tupleSet = new TupleHashSet<T, S>();

	}

	public Tuple<T, S> getByValue(T t, S s) {

		return tupleSet.find(t, s);
	}

	public Tuple<T, S> insert(Tuple<T, S> a) {

		if (tupleSet.insert(a))
			return a;

		else
			return null;

	}

	public int getNumberOfTuples() {
		return tupleSet.insertedTuples();

	}

}
