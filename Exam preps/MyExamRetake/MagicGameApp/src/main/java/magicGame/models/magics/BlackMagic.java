package magicGame.models.magics;

public class BlackMagic extends MagicImpl{

    public BlackMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        int amountBeforeFire = getBulletsCount();
        int amountAfterFire = amountBeforeFire - 10;

        if (amountAfterFire <= 0) {
            setBulletsCount(0);
            return 0;
        } else {
            setBulletsCount(amountAfterFire);
            return 1;
        }
    }
}
