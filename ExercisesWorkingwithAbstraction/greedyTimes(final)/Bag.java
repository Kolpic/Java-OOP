package greedyTimes;

import java.util.LinkedHashMap;
import java.util.List;

public class Bag {
    private List<Item> items;
    private int capacity;

    @Override
    public String toString() {
//        for (var item : bag.entrySet()) {
//            Long sumValues = item.getValue().values().stream().mapToLong(l -> l).sum();
//
//            System.out.println(String.format("<%s> $%s", item.getKey(), sumValues));
//
//            item.getValue().entrySet().stream()
//                    .sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey()))
//                    .forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));
//
//        }
        //print

        return "";
    }

    public void put(Item item) {
        // Proverki

        if (capacity < getTotalAmount() + item.getAmount()) {
            return;
        }

        switch (item.getItemType()) {
            case GEM:

                if (getGemAmount() + item.getAmount() > getGoldAmount()) {
                    return;
                }
                break;
            case CASH:

                if (getCashAmount() + item.getAmount() > getGemAmount()) {
                    return;
                }
                break;
        }

//        if (!bag.containsKey(itemType)) {
//            bag.put((itemType), new LinkedHashMap<String, Long>());
//        }
//
//        if (!bag.get(itemType).containsKey(itemName)) {
//            bag.get(itemType).put(itemName, 0L);
//        }
//
//
//        bag.get(itemType).put(itemName, bag.get(itemType).get(itemName) + amount);
        // check

        items.add(item);
    }

    public long getTotalAmount() {
        return getGoldAmount() + getGemAmount() + getCashAmount();
    }

    public long getGoldAmount() {
       return  this.items.stream().filter(e -> e.getItemType().equals(ItemType.GOLD))
                .mapToLong(e -> e.getAmount())
                .sum();
    }

    public long getGemAmount() {
       return  this.items.stream().filter(e -> e.getItemType().equals(ItemType.GEM))
                .mapToLong(e -> e.getAmount())
                .sum();
    }

    public long getCashAmount() {
       return  this.items.stream().filter(e -> e.getItemType().equals(ItemType.CASH))
                .mapToLong(e -> e.getAmount())
                .sum();
    }
}
