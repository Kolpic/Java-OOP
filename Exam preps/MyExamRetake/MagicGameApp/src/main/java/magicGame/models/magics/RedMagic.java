package magicGame.models.magics;

public class RedMagic extends MagicImpl{

    public RedMagic(String name, int bulletsCount) {
        super(name, bulletsCount);
    }

    @Override
    public int fire() {
        int amountBeforeFire = getBulletsCount();
        int amountAfterFire = amountBeforeFire - 1;

        if (amountAfterFire == 0) {
            setBulletsCount(amountAfterFire);
            return 0;
        } else {
            setBulletsCount(amountAfterFire);
            return 1;
        }
    }
}
