package cardsWithPower;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Cards cardRank = Cards.valueOf(scanner.nextLine());
        Cards cardSuit = Cards.valueOf(scanner.nextLine());

        int cardRankPower = cardRank.getPower() + cardSuit.getPower();

        System.out.printf("Card name: %s of %s; Card power: %d%n",cardRank,cardSuit,cardRankPower);
    }
}
