package WildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        List<Animal> animals = new ArrayList<>();
        List<Food> foods = new ArrayList<>();

        while (!command.equals("End")) {
            String[] commandInfo = command.split("\\s+");
            String[] foodInfo = scanner.nextLine().split("\\s+");

            String animalType = commandInfo[0];
            String animalName = commandInfo[1];
            double animalWeight = Double.parseDouble(commandInfo[2]);
            String animalLivingRegion = commandInfo[3];

            String typeOfFood = foodInfo[0];
            int quantity = Integer.parseInt(foodInfo[1]);

            switch (animalType) {

                case "Mouse":

                    Animal mouse = new Mouse(animalName,animalWeight,animalLivingRegion);
                    animals.add(mouse);

                    break;

                case "Cat":
                    String catBreed = commandInfo[4];

                    Animal cat = new Cat(animalName,animalWeight,animalLivingRegion,catBreed);
                    animals.add(cat);

                    break;

                case "Tiger":

                    Animal tiger = new Tiger(animalName,animalWeight,animalLivingRegion);
                    animals.add(tiger);

                    break;

                case "Zebra":

                    Animal zebra = new Zebra(animalName,animalWeight,animalLivingRegion);
                    animals.add(zebra);

                    break;
            }

            switch (typeOfFood) {
                case "Vegetable":

                    Food vegetable = new Vegetable(quantity);
                    foods.add(vegetable);

                    break;

                case "Meat":

                    Food meat = new Meat(quantity);
                    foods.add(meat);

                    break;
            }



            command = scanner.nextLine();
        }


        for (int i = 0; i < animals.size(); i++) {

            Animal animal = animals.get(i);
            Food food = foods.get(i);

            animal.makeSound();
            animal.eat(food);
        }


        for (int i = 0; i < animals.size(); i++) {

            System.out.println(animals.get(i));

        }
    }
}
