package zoo.entities.animals;

public class TerrestrialAnimal extends BaseAnimal{

    private static final double KG = 5.50;
    private static final double KG_ADDED_AFTER_EAT = 5.70;

    public TerrestrialAnimal(String name, String kind, double price) {
        super(name, kind, KG, price);
    }

    @Override
    public void eat() {
        double addedKilograms = getKg() + KG_ADDED_AFTER_EAT;
        setKg(addedKilograms);
    }
}
