package DefineAnInterfacePerson;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Birthable> birthables = new ArrayList<>();

        String input = scanner.nextLine();

        while (!input.equals("End")) {
            String[] inputInfo = input.split("\\s+");

            switch (inputInfo[0]) {
                case "Citizen":

                    String name = inputInfo[1];
                    int age = Integer.parseInt(inputInfo[2]);
                    String id = inputInfo[3];
                    String birthDate = inputInfo[4];

                    Citizen citizen = new Citizen(name,age,id,birthDate);
                    birthables.add(citizen);

                    break;

                case "Pet":

                    String namePet = inputInfo[1];
                    String birthDatePet = inputInfo[2];

                    Pet pet = new Pet(namePet,birthDatePet);
                    birthables.add(pet);

                    break;

            }

            input = scanner.nextLine();
        }

        String year = scanner.nextLine();
        List<String> dates = new ArrayList<>();

        for (Birthable current : birthables) {

            if (current.getBirthDate().endsWith(year)) {
                dates.add(current.getBirthDate());
            }
        }

        if (dates.isEmpty()) {
            System.out.println("<no output>");
        } else {
            for (String current : dates) {
                System.out.println(current);
            }
        }
    }
}

