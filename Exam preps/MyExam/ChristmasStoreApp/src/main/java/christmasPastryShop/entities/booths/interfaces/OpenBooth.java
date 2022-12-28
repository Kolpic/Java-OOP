package christmasPastryShop.entities.booths.interfaces;

import christmasPastryShop.entities.booths.interfaces.BaseBooth;

public class OpenBooth extends BaseBooth {

    private static final double PRICE = 2.50;

    public OpenBooth(int boothNumber, int capacity) {
        super(boothNumber, capacity, PRICE);
    }
}
