package greedyTimes;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long bagCapacity = Long.parseLong(scanner.nextLine());
        String[] wealth = scanner.nextLine().split("\\s+");

        Bag myBag = new Bag();

        for (int i = 0; i < wealth.length; i += 2) {
            String itemName = wealth[i];
            long amount = Long.parseLong(wealth[i + 1]);

            Item item = createItem(itemName, amount);

            myBag.put(item);

        }
        System.out.println(myBag);
    }

    private static Item createItem(String itemName, long amount) {

        ItemType itemType;

        if (itemName.length() == 3) {
            itemType = ItemType.CASH;
        } else if (itemName.toLowerCase().endsWith("gem")) {
            itemType = ItemType.GEM;
        } else if (itemName.toLowerCase().equals("gold")) {
            itemType = ItemType.GOLD;
        } else {
            return null;
        }

        return new Item(itemType,itemName,amount);

    }
}
