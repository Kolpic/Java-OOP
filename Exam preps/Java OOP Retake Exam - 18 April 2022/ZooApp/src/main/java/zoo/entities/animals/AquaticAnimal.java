package zoo.entities.animals;

public class AquaticAnimal extends BaseAnimal{

    private static final double KG = 2.50;
    private static final double KG_ADDED_AFTER_EAT = 7.50;

    public AquaticAnimal(String name, String kind, double price) {
        super(name, kind, KG, price);
    }

    @Override
    public void eat() {
        double addedKilograms = getKg() + KG_ADDED_AFTER_EAT;
        setKg(addedKilograms);
    }
}
