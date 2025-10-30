package game.actors.animals;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.trees.yewBerrys.YewBerryChild;
import game.grounds.trees.yewBerrys.YewBerryTree;


import java.util.List;
import java.util.Random;

/**
 * A concrete Wolf type
 * <p>
 * Initialized with fixed hitpoints and warmth level.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 2.0
 */
public class Wolf extends Animal{

    private static final int DETECT_RADIUS = 1;

    public Wolf(){
        super(AnimalInfo.WOLF.getNAME(),
                AnimalInfo.WOLF.getDISPLAY_CHARACTER(),
                AnimalInfo.WOLF.getHIT_POINT(),
                AnimalInfo.WOLF.getWARMTH_LEVEL());
    }

    /**
     * Defines the wolf’s environmental influence when spawned.
     * <p>
     *      The wolf randomly selects a nearby empty tile (within one-tile radius)
     *      to grow a mature {@link YewBerryTree}, which has its detect mode enabled.
     * </p>
     *
     * @param spawnGround the {@link Location} where the wolf spawns
     */
    @Override
    public void spawnCapability(Location spawnGround) {
        List<Location> nearby = spawnGround.getNearbyLocations(DETECT_RADIUS);
        if (!nearby.isEmpty()) {
            Location randomSpot = nearby.get(new Random().nextInt(nearby.size()));
            if (!randomSpot.containsAnActor()) {
                YewBerryTree tree = YewBerryChild.createMatureYewBerryTree();
                tree.setDetectMode(true); // mark it as special
                randomSpot.setGround(tree);

            }
        }
    }
}
