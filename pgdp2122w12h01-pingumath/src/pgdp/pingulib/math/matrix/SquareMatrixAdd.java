package pgdp.pingulib.math.matrix;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Maximilian Anzinger
 */

public class SquareMatrixAdd {

	private static final int MIN_DIM = 1 << 1;

	/**
	 * Sequential matrix addition of A and B
	 * 
	 * @param A matrix
	 * @param B matrix
	 * @return result of the matrix addition in a new matrix
	 */
	public static SquareMatrix addSequential(SquareMatrix A, SquareMatrix B) {
		if (A == null) {
			throw new IllegalArgumentException("A can not be null");
		}
		if (B == null) {
			throw new IllegalArgumentException("B can not be null");
		}
		if (A.getDimension() != B.getDimension()) {
			throw new IllegalArgumentException("A and B have different dimensions");
		}
		SquareMatrix C = new SquareMatrix(A.getDimension());
		for (int i = 1; i <= A.getDimension(); i++) {
			for (int j = 1; j <= A.getDimension(); j++) {
				C.set(i, j, A.get(i, j).add(B.get(i, j)));
			}
		}
		return C;
	}

	/**
	 * Parallel matrix addition of A and B. If the dimensions are smaller or equal
	 * than the default value <MIN_DIM>, the sequential matrix addition will be
	 * applied.
	 * 
	 * @param A matrix
	 * @param B matrix
	 * @return result of the matrix addition in a new matrix
	 */
	public static SquareMatrix addParallel(SquareMatrix A, SquareMatrix B) {
		return addParallel(A, B, MIN_DIM);
	}

	/**
	 * Parallel matrix addition of A and B. If <minDim> is smaller than the default
	 * value <MIN_DIM>, the default value will be used. If the dimensions are
	 * smaller or equal than <minDim>, the sequential matrix addition will be
	 * applied.
	 * 
	 * @param A      matrix
	 * @param B      matrix
	 * @param minDim dimension
	 * @return result of the matrix addition in a new matrix
	 */
	public static SquareMatrix addParallel(SquareMatrix A, SquareMatrix B, int minDim) {
		// TODO
		return null;
	}

	private static class AddComputeThread extends ComputeThread {

		private static AtomicInteger computeThreadCounter = new AtomicInteger(0);

		/**
		 * initialize compute thread for matrix addition.
		 * 
		 * @param threadID index where result will be stored
		 * @param A        first matrix
		 * @param B        second matrix
		 * @param minDim   threshold for sequential computation
		 * @param results  array reference where the result will be stored
		 */
		AddComputeThread(int threadID, SquareMatrix A, SquareMatrix B, int minDim, SquareMatrix[] results) {
			super(threadID, A, B, minDim, results);
			this.setName("AddComputeThread-" + computeThreadCounter.incrementAndGet());
		}

		/**
		 * resets the thread counter
		 */
		public static synchronized void resetThreadCount() {
			computeThreadCounter = new AtomicInteger(0);
		}

		/**
		 * retrieves the current thread count
		 * 
		 * @return thread count
		 */
		public static AtomicInteger getThreadCount() {
			return computeThreadCounter;
		}

		@Override
		public void run() {
			// TODO
		}
	}
}
