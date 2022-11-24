package cardRank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Card Ranks:");

        for (Cards cards : Cards.values()) {
            System.out.printf("Ordinal value: %d; Name value: %s%n",cards.ordinal(),cards.name());
        }
    }
}
