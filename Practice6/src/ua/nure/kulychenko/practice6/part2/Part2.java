package ua.nure.kulychenko.practice6.part2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Part2 {

    public static void main(String[] args) {
        List<Integer> arrayList = fillList(new ArrayList<>(), 17);
        List<Integer> linkedList = fillList(new LinkedList<>(), 17);

        System.out.printf("ArrayList#Index: %s ms%n", removeByIndex(arrayList, 1));
        System.out.printf("LinkedList#Index: %s ms%n", removeByIndex(linkedList, 1));

        arrayList = fillList(new ArrayList<>(), 17);
        linkedList = fillList(new LinkedList<>(), 17);

        System.out.printf("ArrayList#Iterator: %s ms%n", removeByIterator(arrayList, 1));
        System.out.printf("LinkedList#Iterator: %s ms%n", removeByIterator(linkedList, 1));
    }

    public static void remove(int index, List list) {
        list.remove(index);
    }

    public static long removeByIndex(List<Integer> list, int k) {
        long startTime = System.currentTimeMillis();
        int position = 0;
        while (list.size() != 1) {
            for (int i = 0; i < k; i++) {
                if (position >= list.size()) {
                    position = 0;
                }
                if (i == k - 1) {
                    remove(position, list);
                    position--;
                }
                position++;
            }
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static long removeByIterator(List<Integer> list, int k) {
        long startTime = System.currentTimeMillis();
        int counter = 0;
        while (list.size() > 1) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                it.next();
                if (counter == k - 1 && list.size() > 1) {
                    it.remove();
                    counter = 0;
                    continue;
                }
                ++counter;
            }
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    private static List<Integer> fillList(List<Integer> list, int amount) {
        for (int i = 0; i < amount; i++) {
            list.add(i);
        }
        return list;
    }
}
