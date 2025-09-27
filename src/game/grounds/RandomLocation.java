package game.grounds;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;

import java.util.Random;

/**
 * <h1>RandomLocation class</h1>
 * <p>
 * The {@code RandomLocation} will randomly select a {@link Location} of the {@link GameMap}.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0.0
 * @since 2025-09-24
 */
public class RandomLocation {

    /**
     * A random object.
     */
    private static final Random RAND = new Random();

    /**
     * Random choose a location on the given game map.
     *
     * @param map the game map
     * @return the location (x, y) on the given game map
     */
    public static Location randomChooseLocation(GameMap map) {
        NumberRange x = map.getXRange();
        NumberRange y = map.getYRange();
        int randomX = RAND.nextInt(x.min(), x.max() + 1);
        int randomY = RAND.nextInt(y.min(), y.max() + 1);
        return map.at(randomX, randomY);
    }
}
