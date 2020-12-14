package ua.nure.kulychenko.practice1;

import java.util.Arrays;

public class Part6 {
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[] simpleArray = new int[n];
		int nextNumber = 2;
		int counter = 0;
		boolean isSimple;
		while (counter < n) {
			int i = 0;
			isSimple = true;
			while (isSimple && i < counter) {
				if (nextNumber % simpleArray[i] == 0) {
					isSimple = false;
				}
				i++;
			}
			if (isSimple) {
				simpleArray[counter] = nextNumber;
				counter++;
				nextNumber++;

			} else {
				nextNumber++;

			}

		}
		System.out.println(Arrays.toString(simpleArray).replace(",", "").replace("[", "").replace("]", ""));

	}

}