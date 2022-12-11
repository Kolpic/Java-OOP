package CollectionHierarchy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        AddCollection addCollection = new AddCollection();
        AddRemoveCollection addRemoveCollection = new AddRemoveCollection();
        MyListImpl myList = new MyListImpl();

        String[] words = scanner.nextLine().split("\\s+");
        int count = Integer.parseInt(scanner.nextLine());

        printAdd(words, addCollection);
        printAdd(words, addRemoveCollection);
        printAdd(words, myList);

        printRemove(count, addRemoveCollection);
        printRemove(count, myList);

    }

    public static void printRemove(int count, AddRemovable addRemovable) {
        for (int i = 0; i < count; i++) {
            System.out.print(addRemovable.remove() + " ");
        }
        System.out.println();
    }

    public static void printAdd(String[] words, Addable addable) {
        for (String word : words) {
            System.out.print(addable.add(word) + " ");
        }
        System.out.println();
    }
}
