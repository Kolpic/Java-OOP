package christmasPastryShop.entities.booths.interfaces;

import christmasPastryShop.entities.booths.interfaces.BaseBooth;

public class PrivateBooth extends BaseBooth {

    private static final double PRICE = 3.50;

    public PrivateBooth(int boothNumber, int capacity) {
        super(boothNumber, capacity, PRICE);
    }
}
