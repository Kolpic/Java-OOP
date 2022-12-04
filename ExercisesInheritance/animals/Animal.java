package animals;

public class Animal {
    private String name;
    private int age;
    private String gender;

    public Animal(String name, int age, String gender) {
        setName(name);
        setAge(age);
        setGender(gender);
    }

    private void setName(String name) {
        if (name.trim().isEmpty()) {

            throw new IllegalStateException("Invalid input!");

        } else {
            this.name = name;
        }
    }

    private void setAge(int age) {
        if (age < 0) {

            throw new IllegalStateException("Invalid input!");

        } else {
            this.age = age;
        }
    }

    private void setGender(String gender) {
        if (gender.trim().isEmpty()) {

            throw new IllegalStateException("Invalid input!");

        } else {
            this.gender = gender;
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append(System.lineSeparator());
        sb.append(String.format("%s %d %s%n",name, age, gender));
        sb.append(produceSound());

        return sb.toString();
    }

    public String produceSound() {
        return "";
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
}
