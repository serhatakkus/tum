package tumtraining.threads;

public class Exam {

	public static void main(String[] args) {
		Thread a = new Thread(() -> {
			while(true) {
				System.out.println("thread a");
			}
		});
		Thread b = new Thread(() -> {
			while (true) {
				System.out.println("thread b");
			}
		});
		
		Runnable c = new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					System.out.println("thread a");
				}
			}
		};
		
		a.start();
		b.start();
		
		a.run();
		b.run();
	}
}
