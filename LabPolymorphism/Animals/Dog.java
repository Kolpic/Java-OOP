package Animals;

public class Dog extends Animal{

    public Dog(String name, String favouriteFood) {
        super(name, favouriteFood);
    }

    @Override
    public String explainSelf() {
        StringBuilder sb = new StringBuilder();

        sb.append(firstLine()).append(System.lineSeparator()).append("DJAAF");

        return sb.toString();
    }
}
