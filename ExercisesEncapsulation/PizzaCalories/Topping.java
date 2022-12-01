package PizzaCalories;

public class Topping {
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        setToppingType(toppingType);
        setWeight(weight);
    }

    public double calculateCalories() {
        return (2 * weight) * findToppingType();
    }

    private void setToppingType(String toppingType) {

        if (!toppingType.equals("Meat") && !toppingType.equals("Veggies") &&
                !toppingType.equals("Cheese") && !toppingType.equals("Sauce")) {

            String message = "Cannot place " + toppingType + " on top of your pizza.";
            throw new IllegalStateException(message);

        }
        this.toppingType = toppingType;
    }

    private void setWeight(double weight) {

        if (weight >= 1 && weight <= 50) {

            this.weight = weight;

        } else {

            String message = toppingType + " weight should be in the range [1..50].";
            throw new IllegalStateException(message);

        }
    }

    private double findToppingType() {

        switch (toppingType) {
            case "Meat":

                return 1.2;

            case "Veggies":

                return 0.8;

            case "Cheese":

                return 1.1;

            case "Sauce":

                return 0.9;

            default:
                return 0;
        }
    }

}
