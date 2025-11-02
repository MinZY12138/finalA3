package game.actors.animals;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.RandomLocation;
import game.grounds.trees.apples.SummonApple;
import game.items.fruits.Apple;


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

        RandomLocation.randomChooseSurrounding(spawnGround,DETECT_RADIUS).addItem(SummonApple.summonApple());

    }

}
