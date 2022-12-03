package RandomArrayList;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class RandomArrayList<Integer> extends ArrayList<Object> {

    public Object getRandomElement() {
        int index = ThreadLocalRandom.current().nextInt(0, size());
        return get(index);
    }
}
