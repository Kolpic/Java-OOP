package football.entities.field;

import football.entities.player.Player;

public class NaturalGrass extends BaseField{

    private static final int CAPACITY_NATURAL_GRASS = 250;

    public NaturalGrass(String name) {
        super(name, CAPACITY_NATURAL_GRASS);
    }

}
