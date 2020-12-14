package ua.nure.kulichenko.practice5;

public class Part6 {

	private static final Object M = new Object();

	public static void main(String[] args) throws InterruptedException {

		Thread t = new Thread() {
			public void run() {
				while (!isInterrupted()) {
					try {
						synchronized (M) {
							M.wait();
						}
					} catch (InterruptedException e) {
						interrupt();
					}
				}
			}
		};

		t.start();

		synchronized (M) {
			M.wait(100);
			M.notifyAll();
			System.out.println(t.getState());
			M.wait(100);
			System.out.println(t.getState());
		}

		t.interrupt();
		t.join();
		System.out.println(t.getState());
	}
}