package viceCity.models.players;

public class CivilPlayer extends BasePlayer{

    private final static int STARTING_LIFE_POINTS = 50;

    public CivilPlayer(String name) {
        super(name, STARTING_LIFE_POINTS);
    }
}
