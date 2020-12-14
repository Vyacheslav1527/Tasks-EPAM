package ua.nure.kulychenko.practice6.part3;

public class Part3 {
    public static void main(String[] args) {
        Parking parking = new Parking(4);
        demoPrint(parking, parking.arrive(2));
        demoPrint(parking, parking.arrive(3));
        demoPrint(parking, parking.arrive(2));
        demoPrint(parking, parking.arrive(2));
        demoPrint(parking, parking.arrive(2));
        demoPrint(parking, parking.depart(1));
        demoPrint(parking, parking.depart(1));
        System.out.println(parking);
    }

    private static void demoPrint(Parking parking, boolean result) {
        System.out.printf("%s, %s%n", parking, result);
    }
}
