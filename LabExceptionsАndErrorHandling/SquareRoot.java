package LabExceptionsАndErrorHandling;

import java.util.Scanner;

public class SquareRoot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String number = scanner.nextLine();

        try {

            double numberInt = Double.parseDouble(number);

            if (numberInt >= 0) {

                System.out.printf("%.2f%n",Math.sqrt(numberInt));

            } else {

                System.out.println("Invalid");

            }

        } catch (IllegalArgumentException e) {

            System.out.println("Invalid");

        } finally {
            System.out.println("Goodbye");
        }
    }
}
