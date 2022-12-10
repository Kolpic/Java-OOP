package FoodShortage;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfPeople = Integer.parseInt(scanner.nextLine());

        Map<String, Buyer> buyers = new HashMap<>();

        for (int i = 0; i < numberOfPeople; i++) {
            String[] inputInfo = scanner.nextLine().split("\\s+");

            if (inputInfo.length == 4) {

                String name = inputInfo[0];
                int age = Integer.parseInt(inputInfo[1]);
                String id = inputInfo[2];
                String birthdate = inputInfo[3];

                Citizen citizen = new Citizen(name, age, id, birthdate);
                buyers.put(name, citizen);

            } else {

                String name = inputInfo[0];
                int age = Integer.parseInt(inputInfo[1]);
                String group = inputInfo[2];

                Rebel rebel = new Rebel(name, age, group);
                buyers.put(name,rebel);

            }

            String name = scanner.nextLine();

            while (!name.equals("End")) {

                Buyer buyer = buyers.get(name);

                if (buyer != null) {
                    buyer.buyFood();
                }

                name = scanner.nextLine();
            }
        }

        int totalFood = buyers.values().stream()
                .mapToInt(Buyer::getFood)
                .sum();

        System.out.println(totalFood);
    }
}
