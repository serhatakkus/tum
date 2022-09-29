package tumtraining.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SemaTest {

	public static void main(String[] args) throws InterruptedException {
		runViaThreads();
	}
	
	public static void runViaExecutor() {
		
		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < 100; i++) {
			executorService.submit(() -> {
				try {
					Connection.getInstance().connect();
				} catch (InterruptedException e) {
				}
			});
		}
		executorService.shutdown();
	}
	
	public static void runViaThreads() {
		for (int i = 0; i < 1000; i++) {

			Thread t = new Thread(() -> {
				try {
					Connection.getInstance().connect();
				} catch (InterruptedException e) {
				}
			});
			
			t.start();
		}
	}

}
