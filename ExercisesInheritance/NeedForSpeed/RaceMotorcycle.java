package NeedForSpeed;

public class RaceMotorcycle extends Motorcycle{

    public final static double DEFAULT_FUEL_CONSUMPTION = 8;

    public RaceMotorcycle(double fuel, int horsepower) {
        super(fuel, horsepower);
        setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
    }
}
