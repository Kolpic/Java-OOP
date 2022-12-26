package viceCity.models.guns;

public class Rifle extends BaseGun{

    private final static int BULLETS_PER_BARREL = 50;
    private final static int TOTAL_BULLETS = 500;

    public Rifle(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }
}
