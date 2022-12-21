package LabExceptionsАndErrorHandling;

import java.util.Arrays;
import java.util.Scanner;

public class NumberInRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] range = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int firstNumber = range[0];
        int secondNumber = range[1];

        System.out.println("Range: " + "[" + firstNumber + "..." + secondNumber + "]");

        boolean isValidNumber = false;

        while (!isValidNumber) {

            String command = scanner.nextLine();

            try {

                int commandInt = Integer.parseInt(command);

                    if (isInRange(firstNumber,secondNumber,commandInt)) {
                        System.out.println("Valid number: " + commandInt);
                        isValidNumber = true;
                        break;
                    }


            } catch (NumberFormatException ignored) {
            }

            System.out.println("Invalid number: " + command);
        }
    }

    private static boolean isInRange(int firstNumber, int secondNumber, int number) {
        return number >= firstNumber && number <= secondNumber;
    }
}
