package ua.nure.kulychenko.practice6.part1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import ua.nure.kulychenko.practice6.part1.WordContainer;

public class Part1 {
    private static final InputStream STD_IN = System.in;

    private static final String ENCODING = "Cp1251";

    public static void main(String[] args) throws IOException {
        String input = "asd 43 asdf asd 43\n" +
                "434 asdf\n" +
                "kasdf asdf stop asdf\n" +
                "stop";
        // set the mock input
        System.setIn(new ByteArrayInputStream(
                input.replace("^", System.lineSeparator()).getBytes(ENCODING)));
        WordContainer.main(args);
        // restore the standard input
        System.setIn(STD_IN);
    }
}

