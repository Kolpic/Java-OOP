package viceCity.models.players;

public class MainPlayer extends BasePlayer{

    private final static String MAIN_HERO_NAME = "Tommy Vercetti";
    private final static int STARTING_LIFE_POINTS = 100;

    public MainPlayer() {
        super(MAIN_HERO_NAME, STARTING_LIFE_POINTS);
    }
}
