package AnimalFarm;

public class Chicken {
    private String name;
    private int age;

    public Chicken(String name, int age) {
        setName(name);
        setAge(age);
    }

    public double productPerDay() {
        return calculateProductPerDay();
    }

    private double calculateProductPerDay() {
        if (age >= 0 && age <=5) {
            return  2;
        } else if (age >= 6 && age <= 11) {
            return 1;
        } else {
            return 0.75;
        }
    }

    private void setAge(int age) {
       if (age > 0 && age < 15) {
           this.age = age;
       } else {
           throw new IllegalStateException("Age should be between 0 and 15.");
       }
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalStateException("Name cannot be empty.");
        }

        this.name = name;

    }

    @Override
    public String toString() {
        return String.format("Chicken %s (age %d) can produce %.2f eggs per day.",name,age,productPerDay());
    }
}
