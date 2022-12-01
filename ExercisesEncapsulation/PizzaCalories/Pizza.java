package PizzaCalories;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private Dough dough;
    private List<Topping> toppings;

    public Pizza(String name, int numberOfToppings) {
        setName(name);
        setNumberOfToppings(numberOfToppings);
    }

    public double getOverallCalories() {

        return dough.calculateCalories() + toppings.stream()
                .mapToDouble(Topping::calculateCalories).sum();
    }

    public void addTopping (Topping topping) {
        toppings.add(topping);
    }

    private void setName(String name) {

        if (name.trim().isEmpty() || name.length() > 15) {

            throw new IllegalStateException("Pizza name should be between 1 and 15 symbols.");

        }

        this.name = name;
    }

    private void setNumberOfToppings(int numberOfToppings) {

        if (numberOfToppings >= 0 && numberOfToppings <= 10) {

            toppings = new ArrayList<>();

        } else {

            throw new IllegalStateException("Number of toppings should be in range [0..10].");

        }
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public String getName() {
        return name;
    }
}
