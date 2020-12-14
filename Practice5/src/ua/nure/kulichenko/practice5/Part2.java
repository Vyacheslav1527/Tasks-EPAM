package ua.nure.kulichenko.practice5;

import java.io.IOException;
import java.io.InputStream;

public class Part2 {

	private static final InputStream STD_IN = System.in;

	public static void main(String[] args) throws InterruptedException {

		System.setIn(new OwnStream());

		Thread t = new Thread() {
			public void run() {
				try {
					Spam.main(null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();

		t.join();

		System.setIn(STD_IN);

	}
}
