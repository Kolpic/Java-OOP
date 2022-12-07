package BorderControl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Citizen> citizens = new ArrayList<>();
        List<Robot> robots = new ArrayList<>();

        String command = scanner.nextLine();

        while (!command.equals("End")) {
            String[] commandInfo = command.split("\\s+");

            if (commandInfo.length == 3) {
                String name = commandInfo[0];
                int age = Integer.parseInt(commandInfo[1]);
                String id = commandInfo[2];

                Citizen citizen = new Citizen(name,age,id);
                citizens.add(citizen);

            } else {
                String model = commandInfo[0];
                String id = commandInfo[1];

                Robot robot = new Robot(model,id);
                robots.add(robot);
            }

            command = scanner.nextLine();
        }

        String determinateNumbers = scanner.nextLine();

        for (Citizen current : citizens) {
            if (current.getId().endsWith(determinateNumbers)) {
                System.out.println(current.getId());
            }
        }

        for (Robot currentRobot : robots) {
            if (currentRobot.getId().endsWith(determinateNumbers)) {
                System.out.println(currentRobot.getId());
            }
        }

    }
}
