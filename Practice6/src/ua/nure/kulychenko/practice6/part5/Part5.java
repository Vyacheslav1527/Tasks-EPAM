package ua.nure.kulychenko.practice6.part5;

public class Part5 {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.add(new Integer[]{3, 1, 2, 0, 5, 4});
        System.out.println(tree.add(6));
        System.out.println(tree.add(6));
        System.out.println("~~~~~~~");
        tree.print();
        System.out.println("~~~~~~~");
        System.out.println(tree.remove(5));
        System.out.println(tree.remove(5));
        System.out.println("~~~~~~~");
        tree.print();
    }
}
