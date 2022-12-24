package football.entities.player;

public class Man extends BasePlayer{

    private static final double KG_MAN = 85.50;

    public Man(String name, String nationality, int strength) {
        super(name, nationality, KG_MAN, strength);
    }

    @Override
    public void stimulation() {
        int newStrength = 145 + getStrength();
        setStrength(newStrength);
    }
}
