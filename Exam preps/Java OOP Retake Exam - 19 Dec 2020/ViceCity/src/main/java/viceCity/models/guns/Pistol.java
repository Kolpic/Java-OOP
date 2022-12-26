package viceCity.models.guns;

public class Pistol extends BaseGun{

    private final static int BULLETS_PER_BARREL = 10;
    private final static int TOTAL_BULLETS = 100;

    public Pistol(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }

    @Override
    public int fire() {

    }
}
