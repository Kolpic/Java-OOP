package Vehicles;

import java.text.DecimalFormat;

public abstract class Vehicle {

    protected double fuelQuantity;
    protected double fuelConsumption;
    protected double tankCapacity;

    public Vehicle(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
    }

    public String driving(Double distanceToTravel) {

        double fuelNeeded = fuelConsumption * distanceToTravel;

        if (fuelNeeded > fuelQuantity) {

            return this.getClass().getSimpleName() + " needs refueling";
        }

        setFuelQuantity(getFuelQuantity() - fuelNeeded);

        DecimalFormat df = new DecimalFormat("##.##");

        return String.format("%s travelled %s km",
                this.getClass().getSimpleName(), df.format(distanceToTravel));
    }

    public void refueling(Double litersRefueled) {

        if (litersRefueled <= 0) {

            throw new IllegalStateException("Fuel must be a positive number");

        }

        if (fuelQuantity + litersRefueled > tankCapacity) {

            throw new IllegalStateException("Cannot fit fuel in tank");

        }

        this.fuelQuantity += litersRefueled;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f",this.getClass().getSimpleName(), fuelQuantity);
    }
}
