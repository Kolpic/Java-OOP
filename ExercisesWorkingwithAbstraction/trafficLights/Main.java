package trafficLights;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] signals = scanner.nextLine().split("\\s+");
        int integrations = Integer.parseInt(scanner.nextLine());

        List<TrafficLights> trafficLightsList = new ArrayList<>();

        for (String signal : signals) {
            Color color = Color.valueOf(signal);
            TrafficLights trafficLights1 = new TrafficLights(color);
            trafficLightsList.add(trafficLights1);
        }

        for (int i = 0; i < integrations; i++) {
            for (TrafficLights currentLight : trafficLightsList) {
                currentLight.changeColor();
                System.out.print(currentLight.getColor() + " ");
            }
            System.out.println();
        }
    }
}
