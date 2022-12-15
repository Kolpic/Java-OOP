package WildFarm;

import java.text.DecimalFormat;

public abstract class Mammal extends Animal{

    protected String livingRegion;

    protected DecimalFormat df = new DecimalFormat("##.##");


    public Mammal(String animalName, String animalType, Double animalWeight, String livingRegion ) {
        super(animalName, animalType, animalWeight);
        this.livingRegion = livingRegion;
    }

    @Override
    public String toString() {
        return String.format("%s[%s, %s, %s, %d]",
                getAnimalType(),getAnimalName(),df.format(getAnimalWeight()), getLivingRegion(),getFoodEaten());
    }
//    Cat[Gray, Persian, 1.1, Home, 4]

    public String getLivingRegion() {
        return livingRegion;
    }
}
