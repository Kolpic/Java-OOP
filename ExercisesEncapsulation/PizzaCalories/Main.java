package PizzaCalories;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] pizzaInput = scanner.nextLine().split("\\s+");

        String pizzaName = pizzaInput[1];
        int numberOfToppings = Integer.parseInt(pizzaInput[2]);

        String[] doughInput = scanner.nextLine().split("\\s+");

        String flourType = doughInput[1];
        String bakingTechnique = doughInput[2];
        double weightInGrams = Double.parseDouble(doughInput[3]);

        try {

            Pizza pizza = new Pizza(pizzaName,numberOfToppings);
            Dough dough = new Dough(flourType, bakingTechnique, weightInGrams);
            pizza.setDough(dough);

            String input = scanner.nextLine();

            while (!input.equals("END")) {

                String[] inputArr = input.split("\\s+");

                String toppingType = inputArr[1];
                double weight = Double.parseDouble(inputArr[2]);

                Topping topping = new Topping(toppingType,weight);
                pizza.addTopping(topping);

                input = scanner.nextLine();
            }

            System.out.printf("%s - %.2f",pizza.getName(),pizza.getOverallCalories());

        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

    }
}
