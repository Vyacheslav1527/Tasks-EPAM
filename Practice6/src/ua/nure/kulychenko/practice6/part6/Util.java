package ua.nure.kulychenko.practice6.part6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Util {

    private static final String ENCODING = "Cp1251";

    public static void main(String[] args) {
        System.out.println("Util");
    }

    public static String[] readFile(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file, ENCODING);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
            if (scanner.hasNextLine()) {
                stringBuilder.append(System.lineSeparator());
            }
        }
        scanner.close();
        return stringBuilder.toString().split(System.lineSeparator());
    }

    public static void writeFileWithSeparator(String path, String[] lines) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            for (String s : lines) {
                writer.append(s);
                writer.append(System.lineSeparator());
            }
            writer.flush();
        }
    }

    public static void writeFile(String path, String[] lines) throws IOException {
        try (FileWriter writer = new FileWriter(path)) {
            for (String s : lines) {
                writer.append(s);
            }
            writer.flush();
        }
    }


    //InputStreamReader Scanner (ENCODING)
}
