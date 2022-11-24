package cardSuit;

import cardSuit.Cards;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        if (input.equals("Card Suits")) {

            System.out.println("Card Suits:");

            for (Cards cards : Cards.values()) {
                System.out.printf("Ordinal value: %d; Name value: %s%n",cards.ordinal(),cards.getName());
            }
        }
    }
}
