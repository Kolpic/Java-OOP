package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class MuscleCar extends BaseCar {

    private static final double CUBIC_CENTIMETRES = 5000;

    public MuscleCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETRES);
        checkHorsePower(horsePower);
    }

    private void checkHorsePower(int horsePower) {
        if (horsePower < 400 || horsePower > 600) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER,horsePower));
        }
    }

}
