package ua.nure.kulychenko.practice6.part6;

import java.io.FileNotFoundException;

import ua.nure.kulychenko.practice6.part1.Word;
import ua.nure.kulychenko.practice6.part1.WordContainer;

public class Part62 {
    public static void main(String[] args) throws FileNotFoundException {
        WordContainer wordContainer = new WordContainer();
        String[] lines = Util.readFile(args[0]);
        for (String line : lines) {
            String[] words = line.split(" ");
            for (String word : words) {
                wordContainer.add(new Word(word, 1));
            }
        }

        wordContainer.sort((Word x, Word y) -> {
            if (x.getContent().length() == y.getContent().length()) {
                return wordContainer.indexOf(x) - wordContainer.indexOf(y);
            } else {
                return y.getContent().length() - x.getContent().length();
            }
        });

        Object[] maxWords = wordContainer.stream().limit(3).sorted(
                (Word x, Word y) -> {
                    if (x.getContent().length() == y.getContent().length()) {
                        return wordContainer.indexOf(x) - wordContainer.indexOf(y);
                    } else {
                        return y.getContent().length() - x.getContent().length();
                    }
                }
        ).toArray();
        for (Object word : maxWords) {
            System.out.println(((Word) word).toLetterCountString());
        }
    }
}
