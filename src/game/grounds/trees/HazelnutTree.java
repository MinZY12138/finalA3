package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Location;
import game.items.fruits.Hazelnut;

/**
 * <h1>Class represent HazelnutTree</h1>
 *
 * <p>
 *     Represent HazelnutTree tree in this system.
 * </p>
 *
 * Extends from {@link Tree}
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public class HazelnutTree extends Tree
{
    /**
     * Constructor for HazelnutTree
     */
    public HazelnutTree()
    {
        //Pass its parameter to its parent's constructor.
        super('A', "Hazelnut Tree", 10);
    }

    /**
     * Method to summon a fruits on a specific location.
     * @param location the place to drop the fruit to.
     */
    @Override
    protected void summonFruit(Location location)
    {
        //Drop a Hazelnut object on to the location.
        location.addItem(new Hazelnut());
    }
}
