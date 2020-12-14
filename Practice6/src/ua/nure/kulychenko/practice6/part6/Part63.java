package ua.nure.kulychenko.practice6.part6;

import java.io.FileNotFoundException;
import java.util.Locale;

import ua.nure.kulychenko.practice6.part1.Word;
import ua.nure.kulychenko.practice6.part1.WordContainer;

public class Part63 {
    public static void main(String[] args) throws FileNotFoundException {
        WordContainer wordContainer = new WordContainer();
        String[] lines = Util.readFile(args[0]);
        for (String line : lines) {
            String[] words = line.split(" ");
            for (String word : words) {
                wordContainer.add(new Word(word, 1));
            }
        }
        WordContainer container = new WordContainer();
        wordContainer.stream().filter((Word x) -> x.getFrequency() > 1).limit(3).forEach((Word x) -> {
            String modifiedContent = new StringBuilder(x.getContent()).reverse().toString();
            container.add(new Word(modifiedContent.toUpperCase(Locale.getDefault()), x.getFrequency()));
        });


        for (Word w : container) {
            System.out.println(w.getContent());
        }
    }
}
