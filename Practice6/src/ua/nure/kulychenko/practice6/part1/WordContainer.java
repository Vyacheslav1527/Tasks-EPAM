package ua.nure.kulychenko.practice6.part1;

import java.util.ArrayList;
import java.util.Scanner;

public class WordContainer extends ArrayList<Word> {

    private static final String SEPARATOR = System.lineSeparator();
    private static final long serialVersionUID = 1;

    public void sort() {
        sort((Word x, Word y) -> {
            if (x.getFrequency() == y.getFrequency()) {
                return x.getContent().compareTo(y.getContent());
            } else {
                return y.getFrequency() - x.getFrequency();
            }
        });
    }

    public static void main(String[] args) {
        WordContainer container = new WordContainer();
        Scanner scanner = new Scanner(System.in);
        String line;
        if (scanner.hasNext()) {
            while (!"stop".equals(line = scanner.next())) {
                container.add(new Word(line, 1));
            }
            container.sort();
            for (Word w : container) {
                System.out.printf("%s : %s%n", w.getContent(), w.getFrequency());
            }
        }
    }

    @Override
    public boolean add(Word word) {
        int index = indexOf(word);
        if (index == -1) {
            return super.add(word);
        } else {
            Word w = get(index);
            w.setFrequency(w.getFrequency() + 1);
            return true;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            sb.append(get(i)).append(SEPARATOR);
        }
        return sb.toString();
    }
}
