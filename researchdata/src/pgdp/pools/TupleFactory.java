package pgdp.pools;

public class TupleFactory<T, S> {

	TuplePool<T, S> pool;

	public TupleFactory() {

		pool = new TuplePool<T, S>();

	}

	public Tuple<T, S> create(T t, S s) {

		Tuple<T, S> tuple = pool.getByValue(t, s);
		if (tuple != null) {
			return tuple;
		}

		tuple = pool.insert(new Tuple<T, S>(t, s));
		return tuple;
	}

	public Tuple<T, S> intern(Tuple<T, S> b) {

		return pool.insert(b);

	}

}
