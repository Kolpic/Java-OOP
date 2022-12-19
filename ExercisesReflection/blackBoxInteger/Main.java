package blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Class<BlackBoxInt> clazz = BlackBoxInt.class;
        Constructor<BlackBoxInt> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        BlackBoxInt blackBoxInt = constructor.newInstance();

        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        Field innerValue = clazz.getDeclaredField("innerValue");
        innerValue.setAccessible(true);

        while (!"END".equals(command)) {

            String methodName = command.split("_")[0];
            int methodNumber = Integer.parseInt(command.split("_")[1]);

            Method method = clazz.getDeclaredMethod(methodName,int.class);
            method.setAccessible(true);
            method.invoke(blackBoxInt,methodNumber);

            System.out.println(innerValue.get(blackBoxInt));

            command = scanner.nextLine();
        }

    }
}
