package ua.nure.kulychenko.practice6.part3;

import java.util.ArrayList;

public class Parking {
    private  ArrayList<Byte> placesList;
    private final int capacity;
    private static final Byte EMPTY = 0;
    private static final Byte OCCUPIED = 1;

    public Parking(int size) {
        placesList = new ArrayList<>(size);
        capacity = size;
        fillPlaces();
    }

    public boolean arrive(int k) {
        if (k < 0 || k >= capacity) {
            throw new IllegalArgumentException();
        }
        if (placesList.get(k).equals(EMPTY)) {
            placesList.set(k, OCCUPIED);
            return true;
        }
        if (placesList.contains(EMPTY)) {
            int i = k;
            while (placesList.get(i).equals(OCCUPIED)) {
                ++i;
                if (i >= capacity) {
                    i = 0;
                }
            }
            placesList.set(i, OCCUPIED);
            return true;
        }
        return false;
    }

    public boolean depart(int k) {
        if (k < 0 || k >= capacity) {
            throw new IllegalArgumentException();
        }
        boolean placeIsOccupied = placesList.get(k).equals(OCCUPIED);
        if (placeIsOccupied) {
            placesList.set(k, EMPTY);
        }
        return placeIsOccupied;
    }

    public void print() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Byte b : placesList) {
            sb.append(b);
        }
        return sb.toString();
    }

    private void fillPlaces() {
        for (int i = 0; i < capacity; i++) {
            placesList.add(EMPTY);
        }
    }
}
