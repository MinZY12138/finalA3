package game.actors.animals;

import edu.monash.fit2099.engine.positions.Location;
import game.items.fruits.Apple;

import java.util.List;


/**
 * A concrete Dear type
 * <p>
 * Initialized with fixed hitpoints and warmth level.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 2.0
 */
public class Deer extends Animal{


    private static final int DETECT_RADIUS = 1;

    public Deer(){
        super(AnimalInfo.DEER.getNAME(),
                AnimalInfo.DEER.getDISPLAY_CHARACTER(),
                AnimalInfo.DEER.getHIT_POINT(),
                AnimalInfo.DEER.getWARMTH_LEVEL());
    }

    /**
     * Defines the deer's special spawn behavior.
     * <p>
     *      When spawned, the deer will drop a single {@link Apple}
     *      at a random nearby empty location within a one-tile radius.
     * </p>
     *
     * @param spawnGround the {@link Location} where the deer spawns
     */
    public void spawnCapability(Location spawnGround){
        List<Location> nearby = spawnGround.getNearbyLocations(DETECT_RADIUS);
        if (!nearby.isEmpty()) {
            Location randomSpot = nearby.get(RAND.nextInt(nearby.size()));
            if (!randomSpot.containsAnActor()) {
                randomSpot.addItem(new Apple());
            }
        }

    }

}
