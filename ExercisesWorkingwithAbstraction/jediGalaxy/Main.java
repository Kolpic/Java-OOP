package jediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rowSize = dimensions[0];
        int colSize = dimensions[1];

        int[][] galaxy = new int[rowSize][colSize];

        int value = 0;

        readMatrix(galaxy, value);

        String command = scanner.nextLine();

        long sum = 0;

        while (!command.equals("Let the Force be with you")) {

            evilMoves(scanner, galaxy);

            sum = ivoMovesAndSumTheDiagonal(galaxy, command, sum);

            command = scanner.nextLine();
        }

        System.out.println(sum);
    }

    private static long ivoMovesAndSumTheDiagonal(int[][] matrix, String command, long sum) {
        int[] ivoCoordinates = Arrays.stream(command.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int ivoRwo = ivoCoordinates[0];
        int ivoCol = ivoCoordinates[1];

        while (ivoRwo >= 0 && ivoCol < matrix[1].length) {
            if (ivoRwo < matrix.length && ivoCol >= 0 && ivoCol < matrix[0].length) {
                sum += matrix[ivoRwo][ivoCol];
            }

            ivoCol++;
            ivoRwo--;
        }
        return sum;
    }

    private static void evilMoves(Scanner scanner, int[][] matrix) {
        int[] evilCoordinates = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int evilRow = evilCoordinates[0];
        int evilCol = evilCoordinates[1];


        while (evilRow >= 0 && evilCol >= 0) {
            if (evilRow < matrix.length && evilCol < matrix[0].length) {
                matrix[evilRow] [evilCol] = 0;
            }
            evilRow--;
            evilCol--;
        }
    }

    private static void readMatrix(int[][] matrix, int value) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                matrix[r][c] = value++;
            }
        }
    }
}
