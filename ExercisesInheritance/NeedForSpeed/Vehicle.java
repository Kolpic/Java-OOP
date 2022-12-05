package NeedForSpeed;

public class Vehicle {
    public final static double DEFAULT_FUEL_CONSUMPTION = 1.25;
    private double fuelConsumption;
    private double fuel;
    private int horsepower;

    public Vehicle(double fuel, int horsepower) {
        setFuel(fuel);
        setHorsepower(horsepower);
        this.fuelConsumption = DEFAULT_FUEL_CONSUMPTION;
    }

    public void drive(double kilometers) {
        double litersNeededForTheDrive = kilometers * fuelConsumption;

        if (fuel >= litersNeededForTheDrive) {
            fuel -= litersNeededForTheDrive;
        }
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getFuel() {
        return fuel;
    }

    private void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public int getHorsepower() {
        return horsepower;
    }

    private void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }
}
