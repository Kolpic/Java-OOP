package animals;

public class Tomcat extends Cat{

    public Tomcat(String name, int age) {
        super(name, age, "male");
    }

    public String produceSound() {
       return "MEOW";

    }
}
