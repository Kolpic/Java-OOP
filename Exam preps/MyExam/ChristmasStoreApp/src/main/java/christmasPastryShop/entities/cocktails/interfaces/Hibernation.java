package christmasPastryShop.entities.cocktails.interfaces;

import christmasPastryShop.entities.cocktails.interfaces.BaseCocktail;

public class Hibernation extends BaseCocktail {

    private static final double PRICE_HIBERNATION = 4.50;

    public Hibernation(String name, int size, String brand) {
        super(name, size, PRICE_HIBERNATION, brand);
    }
}
