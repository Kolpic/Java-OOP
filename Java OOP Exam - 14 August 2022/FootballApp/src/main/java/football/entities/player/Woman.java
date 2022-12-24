package football.entities.player;

public class Woman extends BasePlayer{

    private static final double KG_WOMAN = 60.00;

    public Woman(String name, String nationality, int strength) {
        super(name, nationality, KG_WOMAN, strength);
    }

    @Override
    public void stimulation() {
        int newStrength = 115 + getStrength();
         setStrength(newStrength);
    }

}
