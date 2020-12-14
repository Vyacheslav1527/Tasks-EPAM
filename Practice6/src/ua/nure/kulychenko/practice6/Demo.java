package ua.nure.kulychenko.practice6;

import java.io.IOException;

import ua.nure.kulychenko.practice6.part1.Part1;
import ua.nure.kulychenko.practice6.part2.Part2;
import ua.nure.kulychenko.practice6.part3.Part3;
import ua.nure.kulychenko.practice6.part4.Part4;
import ua.nure.kulychenko.practice6.part5.Part5;
import ua.nure.kulychenko.practice6.part6.Part6;

public class Demo {
    public static void main(String[] args) throws IOException {
        System.out.println("~~~~~~~~~~~~Part1");
        Part1.main(args);
        System.out.println("~~~~~~~~~~~~Part2");
        Part2.main(args);
        System.out.println("~~~~~~~~~~~~Part3");
        Part3.main(args);
        System.out.println("~~~~~~~~~~~~Part4");
        Part4.main(args);
        System.out.println("~~~~~~~~~~~~Part5");
        Part5.main(args);
        System.out.println("~~~~~~~~~~~~Part6");
        System.out.println(" P1 ");
        Part6.main(new String[] {"--input", "part6.txt", "--task", "frequency"});
        System.out.println(" P2 ");
        Part6.main(new String[] {"--input", "part6.txt", "--task", "length"});
        System.out.println(" P3 ");
        Part6.main(new String[] {"--input", "part6.txt", "--task", "duplicates"});
    }
}
