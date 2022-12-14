package Animals;

public class Cat extends Animal{

    public Cat(String name, String favouriteFood) {
        super(name, favouriteFood);
    }

    @Override
    public String explainSelf() {
        StringBuilder sb = new StringBuilder();

        sb.append(firstLine()).append(System.lineSeparator()).append("MEEOW");

        return sb.toString();
    }

}
