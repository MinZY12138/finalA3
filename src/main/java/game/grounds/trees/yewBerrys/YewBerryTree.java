package game.grounds.trees.yewBerrys;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.GroundInfo;
import game.grounds.trees.ProduceableFruitTree;


/**
 * <h1>Class represent YewBerryTree</h1>
 *
 * <p>
 * Represent YewBerryTree tree in this system.
 * </p>
 * <p>
 * Extends from {@link ProduceableFruitTree}
 *
 * @author Shee Seng Cheng
 * @version 2.0
 * Modified by: Ng Jun Jie
 */
public class YewBerryTree extends ProduceableFruitTree
{
    /**
     * Indicate the turn to produce a fruit.
     */
    private static final int TURN_TO_PRODUCE_FRUIT = 5;

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
     *
     * @param location the place to drop the fruit to.
     */
    @Override
    protected void summonFruit(Location location)
    {
        //Drop a YewBerry object on to the location.
        location.addItem(SummonYewBerry.summonYewBerry());
    }
}
