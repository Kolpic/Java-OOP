package NeedForSpeed;

public class SportCar extends Car{

    public final static double DEFAULT_FUEL_CONSUMPTION = 10;

    public SportCar(double fuel, int horsepower) {
        super(fuel, horsepower);
        setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
    }
}
