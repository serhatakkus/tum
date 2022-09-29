package pgdp.game.helper;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public final class Random {
    private static RandomGenerator generator = RandomGeneratorFactory.of("Xoshiro256PlusPlus").create(69420);

    private Random() {
    }

    public static void setGenerator(RandomGenerator generator) {
        Random.generator = generator;
    }

    /**
     * returns a random integer between min (inclusive) and max (exclusive).
     *
     * @param min from (inclusive)
     * @param max to (exclusive)
     * @return random int
     */
    public static int get(int min, int max) {
        return generator.nextInt(min, max);
    }
}
