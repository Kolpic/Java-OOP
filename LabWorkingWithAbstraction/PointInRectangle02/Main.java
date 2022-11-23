import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = getNumbersFromUser(scanner);

        int bottomLeftX = numbers[0];
        int bottomLeftY = numbers[1];

        int topRightX = numbers[2];
        int topRightY = numbers[3];

        Point bottomLeft = new Point(bottomLeftX,bottomLeftY);
        Point topRight = new Point(topRightX,topRightY);

        Rectangle rectangle = new Rectangle(bottomLeft,topRight);

        int n = Integer.parseInt(scanner.nextLine());

        while (n-- != 0) {
            int[] coordinates = getNumbersFromUser(scanner);

            int x = coordinates[0];
            int y = coordinates[1];

            Point currentPoint = new Point(x,y);

            System.out.println(rectangle.contains(currentPoint));;
        }
    }

    private static int[] getNumbersFromUser(Scanner scanner) {
        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        return numbers;
    }
}
