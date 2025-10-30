package game.grounds.trees.yewBerrys;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.GroundInfo;
import game.grounds.trees.ProduceableFruitTree;


import java.util.List;
import java.util.Random;

/**
 * <h1>Class represent YewBerryTree</h1>
 *
 * <p>
 *     Represent YewBerryTree tree in this system.
 * </p>
 *
 * Extends from {@link ProduceableFruitTree}
 *
 * @author Shee Seng Cheng
 * @version 2.0
 * Modified by: Ng Jun Jie
 */
public class YewBerryTree extends ProduceableFruitTree implements SummonYewBerry
{
    private static final int TURN_TO_PRODUCE_FRUIT = 5;
    private static final int DETECT_RADIUS = 1;
    private boolean detectMode = false;
    /**
     * Constructor for YewBerryTree
     */
    public YewBerryTree()
    {
        //Pass its parameter to its parent's constructor.
        super(GroundInfo.YEW_BERRY_TREE.getDISPLAY_CHAR(),
                GroundInfo.YEW_BERRY_TREE.getNAME(),
                TURN_TO_PRODUCE_FRUIT);
    }

    /**
     * Method to summon a fruits on a specific location.
     * @param location the place to drop the fruit to.
     */
    @Override
    protected void summonFruit(Location location)
    {
        //Drop a YewBerry object on to the location.
        location.addItem(this.summonYewBerry());
    }

    /**
     * Setter to enables or disables detection-based spawning.
     *
     * @param detectMode true to enable actor detection mode, false for normal mode
     */
    public void setDetectMode(boolean detectMode) {
       this.detectMode = detectMode;
    }


    /**
     * Periodic update method called every game tick.
     * <p>
     * If detection mode is active, the tree checks for nearby actors before spawning fruits.
     * Otherwise, it behaves like a normal {@link ProduceableFruitTree}.
     * </p>
     *
     * @param location the location of this tree
     */
    @Override
    public void tick(Location location) {
        if (detectMode){
            boolean actorNearby = false;
            List<Location> nearby = location.getNearbyLocations(DETECT_RADIUS);

            for (Location place : nearby){
                if (place.containsAnActor()){
                    actorNearby = true;
                    break;
                }
            }
            if (actorNearby) {
                List<Location> near = location.getNearbyLocations(DETECT_RADIUS);
                if (!near.isEmpty()) {
                    Location randomSpot = near.get(new Random().nextInt(near.size()));
                    if (!randomSpot.containsAnActor()) {
                        summonFruit(randomSpot);
                    }
                }


            }

        } else {
            super.tick(location);
        }
    }
}
