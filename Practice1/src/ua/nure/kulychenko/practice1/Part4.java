package ua.nure.kulychenko.practice1;

public class Part4 {
	public static void main(String[] args) {
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);

		while (y != 0) {
			int a = x % y;
			x = y;
			y = a;
		}
		System.out.println(x);
	}
}
