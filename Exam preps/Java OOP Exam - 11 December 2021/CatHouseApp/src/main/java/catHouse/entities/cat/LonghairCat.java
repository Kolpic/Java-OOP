package catHouse.entities.cat;

public class LonghairCat extends BaseCat{

    private final static int KILOGRAMS = 9;

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price);
    }

    @Override
    public void eating() {
        setKilograms(KILOGRAMS + 3);
    }
}
