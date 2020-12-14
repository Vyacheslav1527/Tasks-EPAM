package ua.nure.kulychenko.practice6.part6;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ua.nure.kulychenko.practice6.part1.Word;
import ua.nure.kulychenko.practice6.part1.WordContainer;

public class Part61 {
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
            if (x.getFrequency() == y.getFrequency()) {
                return wordContainer.indexOf(x) - wordContainer.indexOf(y);
            } else {
                return y.getFrequency() - x.getFrequency();
            }
        });

        Object[] maxWords = wordContainer.stream().limit(3).sorted(
                Comparator.comparing(Word::getContent)
        ).toArray();
        List<Object> maxWordsList = Arrays.asList(maxWords);
        Collections.reverse(maxWordsList);
        for (Object word : maxWordsList) {
            System.out.println(word);
        }
    }
}
