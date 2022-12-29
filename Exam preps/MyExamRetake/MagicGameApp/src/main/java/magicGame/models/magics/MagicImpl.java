package magicGame.models.magics;

import static magicGame.common.ExceptionMessages.*;

public abstract class MagicImpl implements Magic{

    private String name;
    private int bulletsCount;

    public MagicImpl(String name, int bulletsCount) {
        this.setName(name);
        this.setBulletsCount(bulletsCount);
    }

    protected void setBulletsCount(int bulletsCount) {
        if (bulletsCount < 0) {
            throw new IllegalArgumentException(INVALID_MAGIC_BULLETS_COUNT);
        }
        this.bulletsCount = bulletsCount;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(INVALID_MAGIC_NAME);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getBulletsCount() {
        return this.bulletsCount;
    }
}
