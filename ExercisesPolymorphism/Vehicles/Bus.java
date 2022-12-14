package Vehicles;

import java.text.DecimalFormat;

public class Bus extends Vehicle{

    public final static double AC_ADDITIONAL_CONSUMPTION_BUS_WITH_PEOPLE = 1.4;


    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption,tankCapacity);
    }

    @Override
    public String driving(Double distanceToTravel) {

        setFuelConsumption(getFuelConsumption() + AC_ADDITIONAL_CONSUMPTION_BUS_WITH_PEOPLE);
        String result = super.driving(distanceToTravel);
        setFuelConsumption(getFuelConsumption() - AC_ADDITIONAL_CONSUMPTION_BUS_WITH_PEOPLE);
        return result;
    }


    public String drivingEmpty(Double distanceToTravel) {
        return super.driving(distanceToTravel);
    }
}
