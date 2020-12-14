package ua.nure.kulychenko.practice6.part4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {

    private final int min;
    private final int max;
    private final boolean reverse;

    public Range(int n, int m) {
        min = n;
        max = m;
        reverse = false;
    }

    public Range(int n, int m, boolean reverse) {
        min = n;
        max = m;
        this.reverse = reverse;
    }

    private class IteratorImpl implements Iterator<Integer> {
        private int i;

        @Override
        public boolean hasNext() {
            if (!Range.this.reverse) {
                return Range.this.min + i <= Range.this.max;
            } else {
                return Range.this.max - i >= Range.this.min;
            }
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (!Range.this.reverse) {
                return Range.this.min + i++;
            } else {
                return Range.this.max - i++;
            }
        }

        @Override
        public void remove() {
            throw new IllegalStateException();
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new IteratorImpl();
    }
}
