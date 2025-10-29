package game.actors.animals;

import edu.monash.fit2099.engine.positions.Location;
import game.items.fruits.YewBerry;

import java.util.Random;

/**
 * A concrete Bear type
 * <p>
 * Initialized with fixed hitpoints and warmth level.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 2.0
 */
public class Bear extends Animal {

    public Bear() {
        super(AnimalInfo.BEAR.getNAME(),
                AnimalInfo.BEAR.getDISPLAY_CHARACTER(),
                AnimalInfo.BEAR.getHIT_POINT(),
                AnimalInfo.BEAR.getWARMTH_LEVEL());
    }
    @Override
    public void spawnCapability(Location spawnGround) {
        Random rand = new Random();
        for (Location nearby : spawnGround.getNearbyLocations(1)) {
            if (rand.nextDouble() < 0.5) {
                nearby.addItem(new YewBerry());
            }
        }
    }


}
