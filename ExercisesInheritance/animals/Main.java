package animals;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (!input.equals("Beast!")) {
            String animalType = input;
            String[] nameAgeGender = scanner.nextLine().split("\\s+");
            String name = nameAgeGender[0];
            int age = Integer.parseInt(nameAgeGender[1]);
            String gender = nameAgeGender[2];

            try {

                switch (animalType) {
                    case "Dog":

                        Dog dog = new Dog(name, age, gender);
                        System.out.println(dog);

                        break;

                    case "Frog":

                        Frog frog = new Frog(name, age, gender);
                        System.out.println(frog);

                        break;

                    case "Cat":

                        Cat cat = new Cat(name, age, gender);
                        System.out.println(cat);

                        break;

                    case "Kitten":

                        Kitten kitten = new Kitten(name, age);
                        System.out.println(kitten);

                        break;

                    case "Tomcat":

                        Tomcat tomCat = new Tomcat(name, age);
                        System.out.println(tomCat);

                        break;
                }

            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }

            input = scanner.nextLine();
        }

    }
}
