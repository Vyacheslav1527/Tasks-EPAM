package ua.nure.kulichenko.practice5;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Part5 {

	private RandomAccessFile raf;
	private final String path;
	private final int threadsNumber;
	private final int quantity;

	Part5(String path, int threadsNumber, int quantity) {
		this.path = path;
		this.threadsNumber = threadsNumber;
		this.quantity = quantity;
	}

	public void cleanUp(String path) throws IOException {
		Path pathFile = Paths.get(path);
		Files.deleteIfExists(pathFile);
	}
		
	public void writeThreads() throws IOException {
		cleanUp(path);
		raf = new RandomAccessFile(path, "rw");
		creatureThread(threadsNumber);
		raf.close();
	}

	private void creatureThread(int nameThread) {
		if (nameThread == 0) {
			return;
		}
		String name = "" + nameThread;
		Thread t = new Thread(name) {
			@Override
			public void run() {
				try {
					int a = Integer.parseInt(name) - 1;
					creatureThread(a);
					randomWriteText(a);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private synchronized void randomWriteText(int pos) throws IOException {
		int amendment = System.lineSeparator().getBytes().length;
		raf.seek(pos * (quantity + amendment));

		for (int i = 0; i < quantity; i++) {
			char ch = (char) ('0' + pos);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			raf.write(ch);
		}

		raf.write(System.lineSeparator().getBytes());
	}

	public void readText() throws IOException {
		raf = new RandomAccessFile(path, "r");
		StringBuilder sb = new StringBuilder();
		int b = raf.read();
		while (b != -1) {
			sb.append((char) b);
			b = raf.read();
		}
		System.out.println(sb);
	}

	public static void main(String[] args) throws IOException {

		Part5 part5 = new Part5("part5.txt", 10, 20);
		part5.writeThreads();
		part5.readText();

	}
}

