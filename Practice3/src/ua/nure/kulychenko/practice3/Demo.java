package ua.nure.kulichenko.practice3;

import java.security.NoSuchAlgorithmException;


public class Demo {
    private static final String INPUT_PART_1 = Util.readFile("Part1.txt");
    private static final String INPUT_PART_2 = Util.readFile("Part2.txt");
    private static final String INPUT_PART_3= Util.readFile("Part3.txt");
    private static final String INPUT_PART_6= Util.readFile("Part6.txt");

    public static void main(String[] args) {
        System.out.println(Part1.convert1(INPUT_PART_1));
        System.out.println();

        System.out.println(Part1.convert2(INPUT_PART_1));
        System.out.println();

        System.out.println(Part1.convert3(INPUT_PART_1));
        System.out.println();

        System.out.println(Part1.convert4(INPUT_PART_1));
        System.out.println();

        System.out.println(Part2.convert(INPUT_PART_2));
        System.out.println();
        System.out.println(Part3.convert(INPUT_PART_3));
        System.out.println();

        Part4.main(args);
        System.out.println();

        for (int i = 1; i <= 100; i++) {
            String s = Part5.decimal2Roman(i);
            System.out.println(i + " ====> " + s + " ====> " + Part5.roman2Decimal(s));
        }
        System.out.println();
        System.out.println(Part6.convert(INPUT_PART_6));

    }

}