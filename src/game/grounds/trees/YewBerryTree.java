package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.items.fruits.YewBerry;

/**
 * <h1>Class represent YewBerryTree</h1>
 *
 * <p>
 *     Represent YewBerryTree tree in this system.
 * </p>
 *
 * Extends from {@link Tree}
 *
 * @author  Shee Seng Cheng
 * {@code @modifiedBy}  Ng Jun Jie
 * @version 2.0
 */
public class YewBerryTree extends Tree
{
    /**
     * Constructor for YewBerryTree
     */
    public YewBerryTree()
    {
        //Pass its parameter to its parent's constructor.
        super('Y', "Yew Berry Tree", 5);
    }

    /**
     * Method to summon a fruits on a specific location.
     * @param location the place to drop the fruit to.
     */
    @Override
    protected void summonFruit(Location location)
    {
        //Drop a YewBerry object on to the location.
        location.addItem(new YewBerry());
    }
}
