package game.actors.animals;

import edu.monash.fit2099.engine.positions.Location;
import game.items.fruits.YewBerry;

import java.util.Random;

/**
 * A concrete Bear type
 *
 * <p>
 *      Initialized with fixed hitpoints and warmth level.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 2.0
 */
public class Bear extends Animal {

    private static final int DETECT_RADIUS = 1;
    private static final double DROP_CHANCE = 0.5;

    public Bear() {
        super(AnimalInfo.BEAR.getNAME(),
                AnimalInfo.BEAR.getDISPLAY_CHARACTER(),
                AnimalInfo.BEAR.getHIT_POINT(),
                AnimalInfo.BEAR.getWARMTH_LEVEL());
    }

    /**
     * Defines the bear's spawn capability.
     * <p>
     *      When the bear spawns, it has a 50% chance to drop a {@link YewBerry}
     *      in each adjacent tile within one-tile radius.
     * </p>
     *
     * @param spawnGround the {@link Location} where the bear spawns
     */
    @Override
    public void spawnCapability(Location spawnGround) {
        Random rand = new Random();
        for (Location nearby : spawnGround.getNearbyLocations(DETECT_RADIUS)) {
            if (rand.nextDouble() < DROP_CHANCE) {
                nearby.addItem(new YewBerry());
            }
        }
    }


}
