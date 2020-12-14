package ua.nure.kulichenko.practice5;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class OwnStream extends InputStream {

	private String s = System.lineSeparator();
	private InputStream in = new ByteArrayInputStream(s.getBytes());

	@Override
	public int read() throws IOException {
		int i;
		try {

			Thread.sleep(2000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if ((i = (in.read())) == -1) {

			in.close();
		}

		return i;
	}

}

