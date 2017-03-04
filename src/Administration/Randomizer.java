package Administration;

import java.util.Random;

/**
 * Created by Max on 02.01.2017.
 */
public class Randomizer {

    public Randomizer(){

    }

    /**
     * Returns a random integer
     * @param max Maximum integer
     * @return The random integer
     */
    public static int getRandomInt(int max) {
        Random random = new Random();
        return (random.nextInt(max-1))+1;
    }
}

