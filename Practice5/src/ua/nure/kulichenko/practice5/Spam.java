package ua.nure.kulichenko.practice5;

import java.io.IOException;

public class Spam {

	protected String[] messages;
	protected int[] times;
	private Thread[] threads;

	public Spam(String[] messages, int[] times) {
		this.messages = messages;
		this.times = times;
		threads = new Thread[messages.length];
	}

	public void startAll() {
		for (Thread t : threads) {
			t.start();
		}
	}

	public void stopAll() {
		for (Thread t : threads) {
			t.interrupt();
		}
	}

	public static void main(String[] args) throws IOException {

		String[] messages = new String[] { "@@@", "bbbbbbb" };
		int[] times = new int[] { 650, 800 };
		Spam spam = new Spam(messages, times);
		for (int i = 0; i < times.length; i++) {
			spam.threads[i] = new Worker(times[i], messages[i]);
		}
		spam.startAll();
		
		if (System.in.read() == System.lineSeparator().codePointAt(0)) {
			spam.stopAll();
		}
	}

	private static class Worker extends Thread {
		private String s;
		private int time;

		Worker(int time, String s) {
			this.time = time;
			this.s = s;
		}

		@Override
		public void run() {
			while (!isInterrupted()) {
				try {
					Thread.sleep(time);
					System.out.println(s);
				} catch (InterruptedException e) {
					interrupt();
				}
			}
		}
	}
}

