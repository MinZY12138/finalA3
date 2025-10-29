package game.actors.animals;

import edu.monash.fit2099.engine.positions.Location;
import game.items.fruits.Apple;

import java.util.List;
import java.util.Random;

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

    public Deer(){
        super(AnimalInfo.DEER.getNAME(),
                AnimalInfo.DEER.getDISPLAY_CHARACTER(),
                AnimalInfo.DEER.getHIT_POINT(),
                AnimalInfo.DEER.getWARMTH_LEVEL());
    }

    public void spawnCapability(Location spawnGround){
        List<Location> nearby = spawnGround.getNearbyLocations(1);
        if (!nearby.isEmpty()) {
            Location randomSpot = nearby.get(new Random().nextInt(nearby.size()));
            if (!randomSpot.containsAnActor()) {
                randomSpot.addItem(new Apple());
            }
        }

    }

}
