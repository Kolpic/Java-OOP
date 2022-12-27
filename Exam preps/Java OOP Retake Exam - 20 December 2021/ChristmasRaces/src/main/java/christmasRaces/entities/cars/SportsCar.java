package christmasRaces.entities.cars;

import static christmasRaces.common.ExceptionMessages.INVALID_HORSE_POWER;

public class SportsCar extends BaseCar{

    private static final double CUBIC_CENTIMETRES = 3000;

    public SportsCar(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETRES);
        checkHorsePower(horsePower);
    }

    private void checkHorsePower(int horsePower) {
        if (horsePower < 250 || horsePower > 450) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER,horsePower));
        }
    }
}
