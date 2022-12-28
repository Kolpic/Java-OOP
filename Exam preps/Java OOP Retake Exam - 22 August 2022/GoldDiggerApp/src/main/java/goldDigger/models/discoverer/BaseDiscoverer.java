package goldDigger.models.discoverer;

import goldDigger.models.museum.BaseMuseum;
import goldDigger.models.museum.Museum;

import static goldDigger.common.ExceptionMessages.*;

public abstract class BaseDiscoverer implements Discoverer{

    private String name;
    private double energy;
    private Museum museum;

    protected BaseDiscoverer(String name, double energy) {
        setName(name);
        setEnergy(energy);
        museum = new BaseMuseum();
    }

    private void setEnergy(double energy) {
        if (energy < 0) {
            throw new IllegalArgumentException(DISCOVERER_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(DISCOVERER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getEnergy() {
        return energy;
    }

    @Override
    public boolean canDig() {
        return getEnergy() > 0;
    }

    @Override
    public Museum getMuseum() {
        return museum;
    }

    @Override
    public void dig() {

        double energyToBeSet = getEnergy() - 15;

        if (energyToBeSet < 0) {
            energyToBeSet = 0;
        }

        setEnergy(energyToBeSet);
    }
}
