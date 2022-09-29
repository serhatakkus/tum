package tumtraining.threads;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Task 8: Faculty pool /20 Write a server that sends back a list of ten random
 * factorials over TCP gives. Assume that the requests only arrive sporadically
 * and hold on therefore ready a buffer of BUFFER_SIZE factorials. THREAD_COUNT
 * Threads should den Fill buffer and sleep if at least BUFFER_SIZE factorials
 * are in the buffer. Of the Server should calculate factorials between 1 and
 * MAX_FAC and on port PORT on requests waiting. When a request is made, the
 * server should deliver output such as this:
 * 
 * <pre>
 * Hints:
 * 
 * • Write your solutions to the TODOs in the source text. There is
 * enough space for them Solution. Please keep in mind that neither the free
 * place nor the points are matched one-to-one for a line of code stand!
 * 
 * • Vector is basically a dynamic array. Important methods are add() to get a Add
 * element at the end remove(0) to add the first element from the Vector to
 * delete and return, and size() to know how many elements are currently are in
 * the vector.
 * 
 * • Vector is threads-safe, ie add(), remove() and size() don't
 * have to be used for the secure parallel access. Other functions are not
 * necessarily thread-safe and may therefore require a lock on the monitor.
 * 
 * • Use only necessary locks so that the threads really work in parallel be able.
 * 
 * • When there is no work to do, threads are supposed to be idle.
 * 
 * • Remember to catch exceptions.
 * </pre>
 * 
 * @author supo
 *
 */
public class FacServer {
	private static ServerSocket serverSocket;
	private static Vector<Fac> facBuffer = new Vector<Fac>();
	public static final int BUFFER_SIZE = 100;
	public static final int MAX_FAC = 10000;
	public static final int PORT = 35000;
	public static final int THREAD_COUNT = 20;

	/* Calculate the factorial of n */
	public static BigInteger factorial(int n) {
		BigInteger result = BigInteger.ONE;
		for (int i = 2; i <= n; i++)
			result = result.multiply(BigInteger.valueOf(i));
		return result;
	}

	/* Data structure to store n and its factorial */
	private static class Fac {
		public int n;
		public BigInteger f;

		public Fac(int n, BigInteger f) {
			this.n = n;
			this.f = f;
		}
	}

	/**
	 * Add the FacGenerator class, which fills the facBuffer in a thread and becomes
	 * inactive when the facBuffer is full.
	 * 
	 * @author supo
	 *
	 */
	private static class FacGenerator extends Thread {
		private Vector<Fac> facBuffer;

		public FacGenerator(Vector<Fac> facBuffer) {
			this.facBuffer = facBuffer;
		}

		public void run() {
			// TODO endless event loop (1p)
			while (true) {

				// generate a new random number
				int n = ThreadLocalRandom.current().nextInt(1, MAX_FAC + 1);
				// calculate factorial
				Fac f = new Fac(n, factorial(n));
				// TODO check if we need to put more numbers in the buffer (1p)
				// and if so, add f to the buffer (1p) and
				// wake up all other waiting threads (2p)

				if (this.facBuffer.size() < BUFFER_SIZE) {
					synchronized (this.facBuffer) {
						this.facBuffer.add(f);
						facBuffer.notifyAll();
					}
				}

				// TODO if we don't need new numbers, make thread idle (3p)
				synchronized (this.facBuffer) {
					try {
						this.facBuffer.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	/**
	 * Write the FacServerHandler class, which acts as a thread when a new TCP
	 * request is received is started (see also the main method below). She gives
	 * the ten nearest faculties return. If there are too few factorials, the class
	 * will wait until at least ten are in the buffer are. If there are less than
	 * BUFFR_SIZE factorials, it wakes up the FacGenerator threads.
	 * 
	 * @author supo
	 *
	 */
	private static class FacServerHandler extends Thread {
		private Socket client;
		private PrintWriter out;
		private Vector<Fac> facBuffer;

		public FacServerHandler(Socket s, Vector<Fac> facBuffer) {
			this.client = s;
			this.facBuffer = facBuffer;
		}

		public void run() {
			try {
				this.out = new PrintWriter(this.client.getOutputStream(), true);
				this.out.println("Hello, your factorials are: ");
				// TODO Wait until there are at least 10 numbers in the pool (4p)
				while (this.facBuffer.size() < 10) {
					synchronized (this.facBuffer) {
						try {
							this.facBuffer.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}

				// Takes 10 numbers from the pool and writes them to the output stream
				for (int i = 0; i < 10; i++) {
					// Vector is thread-safe, we don't need extra access
					// to secure.
					Fac n = facBuffer.remove(0);
					// Output TODO number according to example output (1p)
					// 2!: 2
					System.out.println(n.n + "!: " + n.f);
				}
				// TODO If there are less than 100 numbers in the buffer, wake them up
				// generator threads on (3p)
				if (this.facBuffer.size() < BUFFER_SIZE) {
					synchronized (this.facBuffer) {
						this.facBuffer.notifyAll();
					}
				}

				// End stream and socket
				this.out.close();
				this.client.close();
			} catch (IOException e) {
				System.out.println("Cannot write to socket. ");
			}
		}
	}

	/**
	 * The main method should start the FacGenerator threads and then open a socket
	 * and start a FacServerHandler thread with every TCP request on a ServerSocket.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO: Run generator threads (2p)
		for (int i = 0; i < THREAD_COUNT; i++) {
			new FacGenerator(facBuffer).start();
		}

		// Open server socket
		serverSocket = new ServerSocket(PORT);
		// TODO: New requests in an infinite loop
		// accept and process in a new FacServerHandler thread. (2p)
		// Note: use serverSocket.accept() to set a
		// accept request

		while (true) {
			new FacServerHandler(serverSocket.accept(), facBuffer).start();
		}

	}
}
