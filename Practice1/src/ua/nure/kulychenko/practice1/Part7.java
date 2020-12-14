package ua.nure.kulychenko.practice1;

public class Part7 {
	public static final int NUM64 = 64;
	public static final int NUM26 = 26;
	private static final String A = " ==> ";

	public static int str2int(String number) {

		int res = 0;
		for (int i = 0, j = number.length() - 1; i < number.length(); i++, j--) {
			res += (number.charAt(j) - NUM64) * Math.pow(NUM26, i);
		}
		return res;

	}

	public static StringBuilder int2str(int number) {
		StringBuilder chars = new StringBuilder();
		chars.append("");

		StringBuilder charsMirror = new StringBuilder();
		charsMirror.append("");
		int modul;
		int divident = number;
		while (divident != 0) {
			modul = divident % NUM26;
			if (modul == 0) {
				chars.append("Z");

				divident = (divident - 1) / NUM26;
			} else {
				chars.append((char) (modul + NUM64));
				divident = (divident - modul) / NUM26;
			}
		}
		for (int i = 0; i < chars.length(); i++) {
			charsMirror.append(chars.charAt(chars.length() - i - 1));
		}
		return charsMirror;

	}
	
	public static StringBuilder rightColumn(String number) {
		  StringBuilder chars = new StringBuilder();
		  chars.append("");
		  int num;
		  num = str2int(number);
		  num++;
		  chars = int2str (num);
		  return chars;
		 }

	

	public static void main(String[] args) {

		String char0 = "A";
		String char1 = "B";
		String char2 = "Z";
		String char3 = "AA";
		String char4 = "AZ";
		String char5 = "BA";
		String char6 = "ZZ";
		String char7 = "AAA";

		System.out.println(char0 + A + Part7.str2int(char0) + A + char0);
		System.out.println(char1 + A + Part7.str2int(char1) + A + char1);
		System.out.println(char2 + A + Part7.str2int(char2) + A + char2);
		System.out.println(char3 + A + Part7.str2int(char3) + A + char3);
		System.out.println(char4 + A + Part7.str2int(char4) + A + char4);
		System.out.println(char5 + A + Part7.str2int(char5) + A + char5);
		System.out.println(char6 + A + Part7.str2int(char6) + A + char6);
		System.out.println(char7 + A + Part7.str2int(char7) + A + char7);

	}

}
