package ua.nure.kulichenko.practice5;

public class Part1 {
	
	Part1() {
		printName();
	}

	public static void main(String[] args) {

		MyThread t1 = new MyThread();
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			t1.interrupt();
		}

		Runnable mr = new MyRunnable();
		Thread t2 = new Thread(mr);
		t2.start();
		try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			t2.interrupt();
		}

		Thread t3 = new Thread(Part1::printName);
		t3.start();
		try {
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			t2.interrupt();
		}

	}

	static class MyThread extends Thread {
		@Override
		public void run() {
			printName();
		}
	}

	static class MyRunnable implements Runnable {
		@Override
		public void run() {
			printName();
		}
	}

	private static void printName() {

		for (int i = 0; i < 3; i++) {
			try {
				Thread.sleep(333);
				System.out.println(Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
