package NeedForSpeed;

public class Car extends Vehicle{

    public final static double DEFAULT_FUEL_CONSUMPTION = 3;

    public Car(double fuel, int horsepower) {
        super(fuel, horsepower);
        setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
    }
}
