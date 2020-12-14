package ua.nure.kulichenko.practice5;

public class Part3 {
	private int counter;
	private int counter2;
	
	private Thread[] threads;
	private int n;
	private int k;
	private int t;
	
	public Part3(int n, int k, int t) { 
		this.n = n;
		this.k = k;
		this.t = t;
	}
	
	public void reset() { 
		this.counter = 0;
		this.counter2 = 0;
 }	
	public void test() throws InterruptedException {
		threads = new Thread[this.n];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new MyThread(this);
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
		
 }
	public void testSync() throws InterruptedException {
		threads = new Thread[this.n];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new MyThreadSync(this);
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
			

		
 }	
	
	static class MyThread extends Thread{
		
		private Part3 p3;
		
		public MyThread(Part3 p3) {
			this.p3 = p3;
		}
		
		@Override
		public void run() {
			
			for (int i = 0; i < p3.k; i++) {
			System.out.printf("%s %s%n", p3.counter, p3.counter2);
			p3.counter++;
			try {
				Thread.sleep(p3.t);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			p3.counter2++;
		}
		}
	}
	
static class MyThreadSync extends Thread{
		
		private Part3 p3;
		
		public MyThreadSync(Part3 p3) {
			this.p3 = p3;
		}
		
		@Override
		public void run() {
			
			for (int i = 0; i < p3.k; i++) {
				synchronized (p3) {
					System.out.printf("%s %s%n", p3.counter, p3.counter2);
					p3.counter++;
					try {
						Thread.sleep(p3.t);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					p3.counter2++;
				}
			
		}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Part3 p = new Part3(3, 5, 100);
		p.test();
		p.reset();
		p.testSync();
	}	
}