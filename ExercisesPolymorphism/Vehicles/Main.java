package Vehicles;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<String, Vehicle> vehicleMap = new LinkedHashMap<>();
        vehicleMap.put("Car", getVehicle(scanner));
        vehicleMap.put("Truck", getVehicle(scanner));
        vehicleMap.put("Bus",getVehicle(scanner));

        int numberOfOperations = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numberOfOperations; i++) {

            String[] command = scanner.nextLine().split("\\s+");

            String action = command[0];
            String vehicle = command[1];
            double argument = Double.parseDouble(command[2]);

            switch (action) {
                case "DriveEmpty":

                    Bus bus = (Bus) vehicleMap.get(vehicle);
                    System.out.println(bus.drivingEmpty(argument));

                    break;

                case "Drive":


                    System.out.println(vehicleMap.get(vehicle).driving(argument));

                    break;

                case "Refuel":

                    try {

                        vehicleMap.get(vehicle).refueling(argument);

                    } catch (IllegalStateException e) {

                        System.out.println(e.getMessage());

                    }


                    break;
            }
        }

        vehicleMap.values().forEach(System.out::println);
    }

    private static Vehicle getVehicle(Scanner scanner) {

        String[] vehicleData = scanner.nextLine().split("\\s+");
        String vehicleType = vehicleData[0];
        double fuelAmount = Double.parseDouble(vehicleData[1]);
        double fuelConsumption = Double.parseDouble(vehicleData[2]);
        double tankCapacity = Double.parseDouble(vehicleData[3]);

        switch (vehicleType) {
            case "Car":

                return new Car(fuelAmount, fuelConsumption, tankCapacity);

            case "Truck":

                return new Truck(fuelAmount, fuelConsumption, tankCapacity);

            case "Bus":

                return new Bus(fuelAmount, fuelConsumption, tankCapacity);

            default:

                throw new IllegalStateException("Missing car");
        }
    }
}
