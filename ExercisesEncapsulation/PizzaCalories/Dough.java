package PizzaCalories;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        setFlourType(flourType);
        setBakingTechnique(bakingTechnique);
        setWeight(weight);
    }

    public double calculateCalories() {

        return (2 * weight) * findFlourType() * findBakingTechnique();
    }

    private void setFlourType(String flourType) {

        if (!flourType.equals("White") && !flourType.equals("Wholegrain")) {

            throw new IllegalStateException("Invalid type of dough.");
        }

        this.flourType = flourType;

    }

    private void setBakingTechnique(String bakingTechnique) {

        if (!bakingTechnique.equals("Crispy") && !bakingTechnique.equals("Chewy")
                && !bakingTechnique.equals("Homemade")) {

            throw new IllegalStateException("Invalid type of dough.");
        }

        this.bakingTechnique = bakingTechnique;
    }

    private void setWeight(double weight) {

        if (weight >= 1 && weight <= 200) {

            this.weight = weight;

        } else {

            throw new IllegalStateException("Dough weight should be in the range [1..200].");
        }
    }

    private double findFlourType() {

        if (flourType.equals("White")) {
            return 1.5;
        } else if (flourType.equals("Wholegrain")) {
            return 1;
        } else {
            return 0;
        }

    }

    private double findBakingTechnique() {

        if (bakingTechnique.equals("Crispy")) {
            return 0.9;
        } else if (bakingTechnique.equals("Chewy")) {
            return 1.1;
        } else if (bakingTechnique.equals("Homemade")) {
            return 1;
        } else {
            return 0;
        }
    }

}
