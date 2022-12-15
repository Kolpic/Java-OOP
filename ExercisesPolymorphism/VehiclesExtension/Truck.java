package Vehicles;

public class Truck extends Vehicle{

    public final static double AC_ADDITIONAL_CONSUMPTION = 1.6;
    public final static double TRUCK_GAS_CAPACITY = 0.95;

    public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption + AC_ADDITIONAL_CONSUMPTION, tankCapacity);
    }


    @Override
    public void refueling(Double litersRefueled) {

       super.refueling(litersRefueled * TRUCK_GAS_CAPACITY);

    }
}
