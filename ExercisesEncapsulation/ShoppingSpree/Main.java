package ShoppingSpree;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] nameAndMoney = scanner.nextLine().split(";");

        Map<String,Person> peopleMap = new LinkedHashMap<>();

        for (int i = 0; i < nameAndMoney.length; i++) {

           String[] currentPerson = nameAndMoney[i].split("=");
           String name = currentPerson[0];
           double money = Double.parseDouble(currentPerson[1]);

           try {

               Person person = new Person(name,money);
               peopleMap.put(name,person);

           } catch (IllegalStateException e) {

               System.out.println(e.getMessage());
               return;

           }

        }

        String[] products = scanner.nextLine().split(";");

        Map<String,Product> productMap = new LinkedHashMap<>();

        for (int i = 0; i < products.length; i++) {

            String[] currentProducts = products[i].split("=");
            String name = currentProducts[0];
            double money = Double.parseDouble(currentProducts[1]);

            try {

                Product product = new Product(name,money);
                productMap.put(name,product);

            } catch (IllegalStateException e) {

                System.out.println(e.getMessage());
                return;

            }
        }

        String command = scanner.nextLine();

        while (!command.equals("END")) {
            String[] commandArr = command.split("\\s+");

            String buyerName = commandArr[0];
            String productName = commandArr[1];

            Person buyer = peopleMap.get(buyerName);
            Product productToBy = productMap.get(productName);

            try {

                buyer.buyProduct(productToBy);

            } catch (IllegalStateException e) {

                System.out.println(e.getMessage());
            }

            command = scanner.nextLine();
        }
        peopleMap.entrySet().stream().forEach(e ->
                System.out.print(e.getValue() + System.lineSeparator()));



    }
}
