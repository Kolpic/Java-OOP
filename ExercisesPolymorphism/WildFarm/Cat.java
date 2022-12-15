package WildFarm;

public class Cat extends Feline {

    protected String breed;

    public Cat(String animalName, Double animalWeight, String livingRegion, String breed) {
        super(animalName, "Cat", animalWeight, livingRegion);
        this.breed = breed;
    }


    @Override
    void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    void eat(Food food) {
        setFoodEaten(getFoodEaten() + food.getQuantity());
    }

    @Override
    public String toString() {
        return String.format("%s[%s, %s, %s, %s, %d]",
                getAnimalType(),getAnimalName(), getBreed(),
                df.format(getAnimalWeight()), getLivingRegion(),getFoodEaten());
    }
//    Cat[Gray, Persian, 1.1, Home, 4]

    public String getBreed() {
        return breed;
    }
}
