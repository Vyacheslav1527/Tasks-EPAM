package ua.nure.kulichenko.practice5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Part4 {

	public static final String LSEP = System.lineSeparator();
	private Thread[] threads;
	protected int[] maxInts;
	
	public Part4(int[][] fileInts) {
		threads = new Thread[fileInts.length - 1];
		maxInts = new int[fileInts.length - 1];
	}

	public static String entryfileInts(int countRows, int countColumns) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < countRows; i++) {
			for (int j = 0; j < countColumns; j++) {
				sb.append(generateRandom());
				if (j == countColumns - 1) {
					sb.append(LSEP);
				} else {
					sb.append(" ");
				}
			}
		}
		return sb.toString();
	}

	private static int generateRandom() {
		SecureRandom random = new SecureRandom();		
		return random.nextInt(999);
	}

	public int findMaxThread(int[][] fileInts) throws InterruptedException {
		for (int i = 0; i < fileInts.length - 1; i++) {
			threads[i] = new MyThread(fileInts[i], i);
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}  
		}
		return ownSort(maxInts);
	}

	public int findMax(int[][] fileInts) throws InterruptedException {
		int max = 0;
		int max2 = 0;
		for (int i = 0; i < fileInts.length - 1; i++) {
			max2 = ownSort(fileInts[i]);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(max2 > max) {
				max = max2;
			}
		}
		return max;
	}

	public void stop() {
		for (int i = 0; i < threads.length; i++) {
			threads[i].interrupt();
		}
	}

	public class MyThread extends Thread {

		private int i;
		private int[] ar;

		public MyThread(int[] ar, int i) {
			this.i = i;
			this.ar = ar.clone();
		}

		public void run() {
			try {
				maxInts[i] = (ownSort(ar));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private static int ownSort(int[] array) throws InterruptedException {
		int maxInt = array[0];
		for (int i = 1; i < array.length; i++) {
			Thread.sleep(1);
			if (maxInt < array[i]) {	
				maxInt = array[i];
			}
		}
		return maxInt;
	}


	public static void main(String[] args) throws InterruptedException {
		long after;
		long before;
		
		int[][] fileInts = getArrayFromFile("part4.txt");
		Part4 p4 = new Part4(fileInts);
		before = System.currentTimeMillis();
		int max = p4.findMaxThread(fileInts);
		after = System.currentTimeMillis();
		p4.stop();
		System.out.println(max);
		System.out.println(after - before);
		
		before = System.currentTimeMillis();
		System.out.println(p4.findMax(fileInts));
		after = System.currentTimeMillis();
		System.out.println(after - before);
	}

	static int[][] getArrayFromFile(String fileName) {

		List<String> lines = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			while (br.ready()) {
				lines.add(br.readLine());
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		int fileIntsWidth = lines.get(0).split(" ").length;
		int fileIntsHeight = lines.size();

		int[][] fileInts = new int[fileIntsHeight][fileIntsWidth];

		for (int i = 0; i < fileIntsHeight; i++) {
			for (int j = 0; j < fileIntsWidth; j++) {
				String[] line = lines.get(i).split(" ");
				fileInts[i][j] = Integer.parseInt(line[j]);
			}
		}

		return fileInts;
	}
}
