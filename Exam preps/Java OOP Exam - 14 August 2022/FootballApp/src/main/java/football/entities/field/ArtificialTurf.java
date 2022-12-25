package football.entities.field;

import football.entities.player.Player;

public class ArtificialTurf extends BaseField{

    private static final int CAPACITY_ARTIFICIAL_TURF = 150;

    public ArtificialTurf(String name) {
        super(name, CAPACITY_ARTIFICIAL_TURF);
    }


}
